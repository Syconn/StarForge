package mod.stf.syconn.network.messages;

import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.init.ModRecipes;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageCraftHilt implements IMessage<MessageCraftHilt> {
    
    private Recipe recipe;
    
    public MessageCraftHilt() {}

    public MessageCraftHilt(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void encode(MessageCraftHilt message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.recipe.getId());
    }

    @Override
    public MessageCraftHilt decode(FriendlyByteBuf buffer) {
        return new MessageCraftHilt(ModRecipes.HILT_RECIPES.get(buffer.readInt()));
    }

    @Override
    public void handle(MessageCraftHilt message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if(!(player.containerMenu instanceof HiltContainer))
                return;

            HiltContainer crafter = (HiltContainer) player.containerMenu;

            LazyOptional<IItemHandler> cap = crafter.getBlockEntity().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

            if (cap.isPresent()) {
                IItemHandler handler = cap.resolve().get();

                for (int i = 0; i < message.recipe.getInputs().length; i++) {
                    if (handler.getStackInSlot(i).getItem() != message.recipe.getInputs()[i].item() || handler.getStackInSlot(i).getCount() < message.recipe.getInputs()[i].amount())
                        return;
                }

                for (int i = 0; i < message.recipe.getInputs().length; i++)
                    handler.extractItem(i, message.recipe.getInputs()[i].amount(), false);
                
                handler.insertItem(7, message.recipe.getOutput(), false);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
