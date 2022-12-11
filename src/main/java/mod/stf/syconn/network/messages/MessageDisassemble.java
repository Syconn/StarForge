package mod.stf.syconn.network.messages;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageDisassemble implements IMessage<MessageDisassemble> {

    private BlockPos pos;
    public MessageDisassemble() {}

    public MessageDisassemble(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void encode(MessageDisassemble message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageDisassemble decode(FriendlyByteBuf buffer) {
        return new MessageDisassemble(buffer.readBlockPos());
    }

    @Override
    public void handle(MessageDisassemble message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            if (player != null){
                if (player.level.getBlockEntity(message.pos) instanceof NavBE be){
                    for (BlockID id : be.getShip().getBlockIDs()){
                        player.addItem(new ItemStack(id.state().getBlock(), 1));
                        player.level.setBlock(id.pos(), Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
