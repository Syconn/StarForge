package mod.stf.syconn.common.blockEntity;

import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.util.data.ChunkHandler;
import mod.stf.syconn.world.data.ChunkData;
import mod.stf.syconn.world.data.ChunkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class MapBe extends ClientBlockEntity {

    private ChunkHandler chunkHandler;
    private BlockPos selectedPos = BlockPos.ZERO;
    private List<BlockPos> selections = new ArrayList<>();
    private static int map_update = 0;
    private static int receiving_signal = 0;

    public MapBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MAP_BE.get(), pWorldPosition, pBlockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MapBe be) {
        if (level.getCapability(ChunkManager.CHUNKS).isPresent()) {
            ChunkData cap = level.getCapability(ChunkManager.CHUNKS).resolve().get();
            if (map_update < 1000) map_update++;
            else if (!be.selectedPos.equals(BlockPos.ZERO)) be.chunkHandler = cap.getChunkHandler(be.selectedPos, level);
            if (receiving_signal < 1000) receiving_signal++;
            else if (!be.selectedPos.equals(BlockPos.ZERO) && !cap.getChunkOptions(pos).contains(be.selectedPos)) be.selectedPos = BlockPos.ZERO;
            update(level, pos, state);
        }
    }

    public void setSelections() {
        selections = level.getCapability(ChunkManager.CHUNKS).map(data -> data.getChunkOptions(worldPosition)).orElse(List.of());
        update();
    }

    public List<BlockPos> getSelections() {
        return selections;
    }

    protected CompoundTag saveData() {
        CompoundTag tag = new CompoundTag();
        tag.put("selectedpos", NbtUtils.writeBlockPos(selectedPos));
        if (chunkHandler != null) tag.put("handler", chunkHandler.save());
        if (!selections.isEmpty()) tag.put("selections", NbtUtil.writeBlockPosses(selections));
        return tag;
    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("selectedpos", NbtUtils.writeBlockPos(selectedPos));
        if (!selections.isEmpty()) tag.put("selections", NbtUtil.writeBlockPosses(selections));
        if (chunkHandler != null) tag.put("handler", chunkHandler.save());
    }

    public void load(CompoundTag tag) {
        super.load(tag);
        selectedPos = NbtUtils.readBlockPos(tag.getCompound("selectedpos"));
        if (tag.contains("selections")) selections = NbtUtil.readBlockPosses(tag.getCompound("selections"));
        if (tag.contains("handler")) chunkHandler = new ChunkHandler(tag.getCompound("handler"));
    }

    public AABB getRenderBoundingBox() {
        return IForgeBlockEntity.INFINITE_EXTENT_AABB;
    }
}
