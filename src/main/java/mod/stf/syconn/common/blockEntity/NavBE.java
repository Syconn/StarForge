package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ApplicationBE;
import mod.stf.syconn.api.util.AnchorPos;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.containers.NavContainer;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.util.FlightController;
import mod.stf.syconn.util.ShipBody;
import mod.stf.syconn.util.TripPath;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavBE extends ApplicationBE<NavContainer> {

    private boolean showShip = false;
    private boolean showPath = false;
    private boolean flying = false;
    private AnchorPos pos;
    private ShipBody ship;
    private Map<BlockPos, double[]> position;
    private Direction dir;
    private TripPath path;
    private FlightController controller;

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, null);
    }

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState, BasicApplication<NavContainer> application) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, application);
    }

    public void setShip(Schematic ship, Direction dir) {
        this.ship = new ShipBody(ship);
        this.dir = dir;
        update();
        createRendering();
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void showShip(boolean showShip) {
        createRendering();
        this.showShip = showShip;
        update();
    }

    public void showPath(boolean showPath) {
        this.showPath = showPath;
        update();
    }

    public boolean showShip() {
        return showShip;
    }

    public boolean showPath() {
        return showPath;
    }

    public boolean isFlying() {
        return flying;
    }

    public TripPath getPath() {
        return path;
    }

    public Direction getDir() {
        return dir;
    }

    public double[] getPosition(BlockPos state) {
        return position.get(state);
    }

    public AnchorPos getPos() {
        return pos;
    }

    public ShipBody getShip() {
        return ship;
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag tag = super.saveData();
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        if (dir != null)
            tag.putInt("direction", dir.get3DDataValue());
        if (path != null)
            tag.put("path", path.save());
        if (controller != null)
            tag.put("cntrl", controller.save());
        if (ship != null)
            tag.put("body", ship.save());
        tag.putBoolean("show_ship", showShip);
        tag.putBoolean("show_path", showPath);
        tag.putBoolean("flying", flying);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        if (dir != null)
            tag.putInt("direction", dir.get3DDataValue());
        if (path != null)
            tag.put("path", path.save());
        if (controller != null)
            tag.put("cntrl", controller.save());
        if (ship != null)
            tag.put("body", ship.save());
        tag.putBoolean("show_ship", showShip);
        tag.putBoolean("show_path", showPath);
        tag.putBoolean("flying", flying);
        super.saveAdditional(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("pos"))
            pos = AnchorPos.read(tag.getCompound("pos"));
        if (tag.contains("positions"))
            position = NbtUtil.readPositions(tag.getCompound("positions"));
        if (tag.contains("direction"))
            dir = Direction.from3DDataValue(tag.getInt("direction"));
        if (tag.contains("path"))
            path = TripPath.read(tag.getCompound("path"));
        if (tag.contains("cntrl"))
            controller = new FlightController(tag.getCompound("cntrl"));
        if (tag.contains("body"))
            ship = new ShipBody(tag.getCompound("body"));
        showShip = tag.getBoolean("show_ship");
        showPath = tag.getBoolean("show_path");
        flying = tag.getBoolean("flying");
        super.load(tag);
    }

    public void fly(BlockPos d, int s) {
        if (ship != null && !flying) {
            flying = true;
            createPath(d);
            controller = new FlightController(getPath(), s);
            update();
            controller.start(level, this);
            removeShip();
        }
    }

    public void reachDest(){
        if (controller != null && !controller.getFlightPath().isEmpty() && flying) {
            flying = false;
            //removeShip();
            update();
            //controller.start(level, this);
        }
    }

    private void removeShip(){
        for (BlockID id : ship.getBlockIDs()) {
            level.setBlock(id.pos(), Blocks.AIR.defaultBlockState(), 2);
        }
    }

    private void createPath(BlockPos d){
        path = new TripPath(ship, d, dir, level);
        update();
    }

    public boolean shouldShipRender(){
        return ship != null && !position.isEmpty() && showShip;
    }

    public void createRendering(){
        position = new HashMap<>();
        if (ship != null) {
            pos = anchorPos(ship.getBlockIDs());
            for (BlockID id : ship.getBlockIDs()){
                if (level.getBlockState(id.pos()).equals(id.state()))
                    position.put(id.pos(), genPosition(id.pos(), pos));
            }
            update();
        }
    }

    private double[] genPosition(BlockPos pos, AnchorPos anchorPos){
        double[] position = new double[6];
        double xDif = anchorPos.x() - pos.getX();
        double yDif = anchorPos.y() - pos.getY();
        double zDif = anchorPos.z() - pos.getZ();
        position[0] = -xDif;
        position[1] = yDif;
        position[2] = zDif;
        position[3] = 0;
        position[4] = 0;
        position[5] = 0;
        if (yDif < 0){
            position[4] = 4 * -yDif;
        }
        if (xDif < 0){
            position[3] = -4 * -xDif;
        } else if (xDif > 0){
            position[3] = 4 * xDif;
        }
        if (zDif < 0){
            position[5] = 4 * -zDif;
        } else if (zDif > 0){
            position[5] = -4 * zDif;
        }
        return position;
    }

    private AnchorPos anchorPos(List<BlockID> posses) {
        BlockPos pos = posses.get(0).pos();
        double bX = pos.getX(), bY = pos.getY(), bZ = pos.getZ(), sX = pos.getX(), sY = pos.getY(), sZ = pos.getZ();
        for (BlockID id : posses) {
            BlockPos pos2 = id.pos();
            if (pos2.getX() > bX) bX = pos2.getX();
            if (pos2.getY() > bY) bY = pos2.getY();
            if (pos2.getZ() > bZ) bZ = pos2.getZ();
            if (pos2.getX() < sX) sX = pos2.getX();
            if (pos2.getY() < sY) sY = pos2.getY();
            if (pos2.getZ() < sZ) sZ = pos2.getZ();
        }
        return new AnchorPos((int) ((bX + sX) / 2), (int) sY, (int) ((bZ + sZ) / 2));
    }

    @Override
    public AABB getRenderBoundingBox() {
        return IForgeBlockEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public void tickServer() {

    }

    @Override
    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(0) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Nav Computer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new NavContainer(pContainerId, worldPosition, pPlayerInventory, pPlayer);
    }

    public void moveSchem(ShipBody sc, Direction dir, double distance) {
        List<BlockID> ids = new ArrayList<>();
        for (BlockID id : sc.getBlockIDs()) {
            double x = id.pos().getX() + dir.getStepX() * distance;
            double y = id.pos().getY() + dir.getStepY() * distance;
            double z = id.pos().getZ() + dir.getStepZ() * distance;
            ids.add(new BlockID(id.state(), new BlockPos(x, y, z)));
        }
        this.ship = new ShipBody(ids);
    }

    public void reset(NavBE be, Direction dir, List<BlockID> ids) {
        this.showShip = be.showShip;
        this.showPath = be.showPath;
        this.dir = dir;
        this.ship = new ShipBody(ids);
        createRendering();
        update();
    }

    public void move(NavBE be, Direction dir, double distance) {
        if (be != null) {
            this.pos = be.getPos();
            this.showShip = be.showShip;
            this.showPath = be.showPath;
            this.flying = be.flying;
            this.dir = dir;
            this.controller = be.controller;
            moveSchem(be.getShip(), dir, distance);
            createRendering();
            update();
        }
    }
}