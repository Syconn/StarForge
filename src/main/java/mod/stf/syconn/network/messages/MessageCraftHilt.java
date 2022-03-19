package mod.stf.syconn.network.messages;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.init.ModRecipes;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.util.recipe.ModIngredient;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
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
                if (handler.getStackInSlot(0) == ItemStack.EMPTY) {
                    for (int i = 0; i < message.recipe.getInputs().length; i++) {
                        int count = message.recipe.getInputs()[i].amount();

                        for (int j = 0; j < player.getInventory().getContainerSize(); j++) {
                            if (player.getInventory().getItem(j).getItem() == message.recipe.getInputs()[i].item()){
                                int num = player.getInventory().getItem(j).getCount();
                                if (num >= count){
                                    player.getInventory().removeItem(j, count);
                                    count -= num;
                                } else {
                                    count -= num;
                                    player.getInventory().removeItem(j, num);
                                }
                            }
                        }
                    }

                    handler.insertItem(0, message.recipe.getOutput().copy(), false);
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
