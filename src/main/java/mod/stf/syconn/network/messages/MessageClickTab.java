package mod.stf.syconn.network.messages;

import mod.stf.syconn.block.LightsaberCrafter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.Objects;
import java.util.function.Supplier;

public class MessageClickTab implements IMessage<MessageClickTab> {

    private int id;
    private BlockPos pos;

    public MessageClickTab() {}

    public MessageClickTab(int id, BlockPos pos) {
        this.id = id;
        this.pos = pos;
    }

    @Override
    public void encode(MessageClickTab message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.id);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageClickTab decode(FriendlyByteBuf buffer) {
        return new MessageClickTab(buffer.readInt(), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageClickTab message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            BlockState oldState = player.level.getBlockState(message.pos);
            player.level.setBlock(message.pos, oldState.setValue(LightsaberCrafter.MODE, LightsaberCrafter.States.getById(message.id)), 2);
            NetworkHooks.openScreen(player, (MenuProvider) player.level.getBlockEntity(message.pos), message.pos);
        });
        supplier.get().setPacketHandled(true);
    }
}
