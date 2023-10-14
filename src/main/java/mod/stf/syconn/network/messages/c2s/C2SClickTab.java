package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public class C2SClickTab implements IMessage<C2SClickTab> {

    private int id;
    private BlockPos pos;

    public C2SClickTab() {}

    public C2SClickTab(int id, BlockPos pos) {
        this.id = id;
        this.pos = pos;
    }

    @Override
    public void encode(C2SClickTab message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.id);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public C2SClickTab decode(FriendlyByteBuf buffer) {
        return new C2SClickTab(buffer.readInt(), buffer.readBlockPos());
    }

    @Override
    public void handle(C2SClickTab message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();
            BlockState oldState = player.level.getBlockState(message.pos);
            player.level.setBlock(message.pos, oldState.setValue(LightsaberCrafter.MODE, LightsaberCrafter.States.getById(message.id)), 2);
            NetworkHooks.openScreen(player, (MenuProvider) player.level.getBlockEntity(message.pos), message.pos);
        });
        supplier.get().setPacketHandled(true);
    }
}
