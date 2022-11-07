package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ClientMenuBlockEntity;
import mod.stf.syconn.api.util.AnchorPos;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.containers.SchematicContainer;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchematicBe extends ClientMenuBlockEntity {
    private Schematic schematic = null;
    private AnchorPos pos;
    private Map<BlockPos, double[]> position;

    public SchematicBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SCHEMATIC_BE.get(), pWorldPosition, pBlockState);
    }

    public void createBlockImage(){
        position = new HashMap<>();
        setChanged();
        if (schematic != null) {
            pos = anchorPos(schematic.getBlockIDs());
            for (BlockID id : schematic.getBlockIDs()){
                position.put(id.pos(), genPosition(id.pos(), pos));
            }
            update();
        }
    }

    private double[] genPosition(BlockPos pos, AnchorPos anchorPos){
        double[] position = new double[3];
        double xDif = anchorPos.x() - pos.getX();
        double yDif = pos.getY() - anchorPos.y();
        double zDif = anchorPos.z() - pos.getZ();
        position[0] = xDif;
        position[1] = yDif;
        position[2] = zDif;
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

    public void setSchematic(Schematic schematic) {
        this.schematic = schematic;
    }

    public AnchorPos getAnchor(){
        return pos;
    }

    public double[] getPosition(BlockPos state) {
        return position.get(state);
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag tag = super.saveData();
        if (schematic != null)
            tag.put("schematic", schematic.saveSchematic());
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        return tag;
    }

    public Schematic getSchematic() {
        return schematic;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        if (schematic != null)
            tag.put("schematic", schematic.saveSchematic());
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        super.saveAdditional(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("schematic"))
            schematic = Schematic.readSchematic(tag.getCompound("schematic"));
        if (tag.contains("pos"))
            pos = AnchorPos.read(tag.getCompound("pos"));
        if (tag.contains("positions"))
            position = NbtUtil.readPositions(tag.getCompound("positions"));
        super.load(tag);
    }

    @Override
    public void tickServer() {

    }

    @Override
    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Schematic");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SchematicContainer(pContainerId, worldPosition, pPlayerInventory, pPlayer);
    }
}
