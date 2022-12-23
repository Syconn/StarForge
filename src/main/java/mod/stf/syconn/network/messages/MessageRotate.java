package mod.stf.syconn.network.messages;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.RotationHelper;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MessageRotate implements IMessage<MessageRotate> {

    private Direction dRot;
    private BlockPos pos;

    public MessageRotate() {}

    public MessageRotate(Direction dRot, BlockPos pos) {
        this.dRot = dRot;
        this.pos = pos;
    }

    @Override
    public void encode(MessageRotate message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.dRot.get3DDataValue());
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public MessageRotate decode(FriendlyByteBuf buffer) {
        return new MessageRotate(Direction.from3DDataValue(buffer.readInt()), buffer.readBlockPos());
    }

    @Override
    public void handle(MessageRotate message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if (player != null){
                if (player.level.getBlockEntity(message.pos) instanceof NavBE be){
                    if (be.getDir() != message.dRot) {
                        ItemStack stack = new ItemStack(ModItems.SCHEMATIC_ITEM.get());
                        stack.setHoverName(new TextComponent("ROTATED " + message.dRot.name().toUpperCase()));
                        List<BlockID> placing = new ArrayList<>();
                        for (BlockID id : be.getShip().getBlockIDs()){
                            BlockPos pos = RotationHelper.rotate(be, id, be.getDir(), message.dRot);
                            if (id.state().hasProperty(HorizontalDirectionalBlock.FACING)){

                                placing.add(new BlockID(id.state(), pos));
                            } else {
                                placing.add(new BlockID(id.state(), pos));
                            }
                            player.level.setBlock(id.pos(), Blocks.AIR.defaultBlockState(), 2);
                        }
                        for (BlockID id : placing){
                            id.place(player.level);
                            if (id.state().getBlock() == ModBlocks.NAV_COMPUTER.get())
                                ((NavBE)player.level.getBlockEntity(id.pos())).reset(be, message.dRot, placing);
                        }
                        stack.getOrCreateTag().put("schematic", new Schematic(placing).saveSchematic());
                        player.addItem(stack);
                    }
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }

    public int getDir(Direction b, Direction d, Direction t){
        return 0;
    }
}
