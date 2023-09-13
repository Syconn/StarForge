package mod.stf.syconn.util.data;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.api.util.RenderUtil;
import mod.stf.syconn.api.util.data.VectorData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.ArrayList;
import java.util.List;

public record BlockInChunkData(BlockState state, BlockPos pos, VectorData data, int x, int z) {

    public void render(PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, LevelChunk level, int lowestY) {
        pPoseStack.pushPose();
        pPoseStack.translate(x, pos.getY() - lowestY, z);
        if (state.getFluidState().isEmpty()) RenderUtil.renderSingleBlock(state, pPoseStack, pBufferSource, pPackedLight, level.getLevel(), pos, data);
        else RenderUtil.renderLiquid(pPoseStack, pBufferSource, state, data);
        pPoseStack.popPose();
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.put("state", NbtUtils.writeBlockState(state));
        tag.put("pos", NbtUtils.writeBlockPos(pos));
        tag.putInt("x", x);
        tag.putInt("z", z);
        tag.put("data", data.save());
        return tag;
    }

    public static CompoundTag saveAll(List<BlockInChunkData> data) {
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        data.forEach(blockInChunkData -> listTag.add(blockInChunkData.save()));
        tag.put("blocks", listTag);
        return tag;
    }

    public static BlockInChunkData read(CompoundTag tag) {
        return new BlockInChunkData(NbtUtils.readBlockState(Minecraft.getInstance().level.holderLookup(Registries.BLOCK), tag.getCompound("state")),
                NbtUtils.readBlockPos(tag.getCompound("pos")), new VectorData(tag.getCompound("data")), tag.getInt("x"), tag.getInt("z"));
    }

    public static List<BlockInChunkData> readAll(CompoundTag tag) {
        List<BlockInChunkData> blocks = new ArrayList<>();
        if(tag.contains("blocks", Tag.TAG_LIST)) tag.getList("blocks", Tag.TAG_COMPOUND).forEach(data -> blocks.add(read((CompoundTag) data)));
        return blocks;
    }
}
