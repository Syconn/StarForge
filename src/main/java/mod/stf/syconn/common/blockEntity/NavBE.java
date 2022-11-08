package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ApplicationBE;
import mod.stf.syconn.api.util.AnchorPos;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.containers.NavContainer;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavBE extends ApplicationBE<NavContainer> {

    private boolean enabled = false;
    private Schematic ship;
    private AnchorPos pos;
    private Map<BlockPos, double[]> position;

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, null);
    }

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState, BasicApplication<NavContainer> application) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, application);
    }

    public void setShip(Schematic ship) {
        this.ship = ship;
        update();
        createBlockImage();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        update();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Schematic getShip() {
        return ship;
    }

    public double[] getPosition(BlockPos state) {
        return position.get(state);
    }

    public AnchorPos getPos() {
        return pos;
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag tag = super.saveData();
        if (ship != null)
            tag.put("ship", ship.saveSchematic());
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        tag.putBoolean("enabled", enabled);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        if (ship != null)
            tag.put("ship", ship.saveSchematic());
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        tag.putBoolean("enabled", enabled);
        super.saveAdditional(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("ship"))
            ship = Schematic.readSchematic(tag.getCompound("ship"));
        if (tag.contains("pos"))
            pos = AnchorPos.read(tag.getCompound("pos"));
        if (tag.contains("positions"))
            position = NbtUtil.readPositions(tag.getCompound("positions"));
        enabled = tag.getBoolean("enabled");
        super.load(tag);
    }

    public boolean shouldRender(){
        return ship != null && !position.isEmpty() && enabled;
    }

    public void createBlockImage(){
        position = new HashMap<>();
        if (ship != null) {
            pos = anchorPos(ship.getBlockIDs());
            for (BlockID id : ship.getBlockIDs()){
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
        position[0] = xDif;
        position[1] = yDif;
        position[2] = zDif;
        position[3] = 0;
        position[4] = 0;
        position[5] = 0;
        if (yDif < 0){
            position[4] = 4 * -yDif;
        }
        if (xDif < 0){
            position[3] = 4 * -xDif;
        } else if (xDif > 0){
            position[3] = -4 * xDif;
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
}
