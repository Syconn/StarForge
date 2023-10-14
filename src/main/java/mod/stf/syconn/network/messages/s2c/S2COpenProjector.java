package mod.stf.syconn.network.messages.s2c;

import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.client.screen.MapScreen;
import mod.stf.syconn.common.blockEntity.ProbeBe;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class S2COpenProjector implements IMessage<S2COpenProjector> {

    private BlockPos pos;
    private List<BlockPos> positions;

    public S2COpenProjector() {}

    public S2COpenProjector(BlockPos pos, List<BlockPos> positions) {
        this.pos = pos;
        this.positions = positions;
    }

    public void encode(S2COpenProjector message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
        CompoundTag tag = new CompoundTag();
        tag.put("positions", NbtUtil.writeBlockPosses(message.positions));
        buffer.writeNbt(tag);
    }

    public S2COpenProjector decode(FriendlyByteBuf buffer) {
        return new S2COpenProjector(buffer.readBlockPos(), NbtUtil.readBlockPosses(buffer.readNbt().getCompound("positions")));
    }

    public void handle(S2COpenProjector message, Supplier<NetworkEvent.Context> supplier) { // TODO NAME SATALLITES?
        supplier.get().enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            List<ChunkPos> testList = new ArrayList<>();
            Map<ChunkPos, BlockPos> position = new HashMap<>();
            Map<ChunkPos, String> names = new HashMap<>();
            for (BlockPos pos : message.positions) {
                ChunkPos chunk = new ChunkPos(pos);
                String string = ((ProbeBe) level.getBlockEntity(pos)).getName().getString();
                if (!testList.contains(chunk)) {
                    testList.add(chunk);
                    position.put(chunk, pos);
                    names.put(chunk, string);
                } else if (names.containsKey(chunk) && names.get(chunk).equals(new ChunkPos(pos).toString())) names.put(chunk, string);
            }
            Minecraft.getInstance().setScreen(new MapScreen(message.pos, position, names, level.getBlockEntity(message.pos, ModBlockEntities.MAP_BE.get()).get().isFastRender()));
        });
        supplier.get().setPacketHandled(true);
    }
}
