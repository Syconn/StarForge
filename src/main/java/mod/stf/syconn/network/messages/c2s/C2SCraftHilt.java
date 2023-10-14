package mod.stf.syconn.network.messages.c2s;

import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.common.recipes.LightsaberRecipe;
import mod.stf.syconn.common.recipes.ModIngredient;
import mod.stf.syconn.network.messages.IMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SCraftHilt implements IMessage<C2SCraftHilt> {
    
    private ResourceLocation id;
    
    public C2SCraftHilt() {}

    public C2SCraftHilt(ResourceLocation id) {
        this.id = id;
    }

    public void encode(C2SCraftHilt message, FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(message.id);
    }

    public C2SCraftHilt decode(FriendlyByteBuf buffer) {
        return new C2SCraftHilt(buffer.readResourceLocation());
    }

    public void handle(C2SCraftHilt message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer player = supplier.get().getSender();

            if(!(player.containerMenu instanceof HiltContainer crafter))
                return;

            LazyOptional<IItemHandler> cap = crafter.getBlockEntity().getCapability(ForgeCapabilities.ITEM_HANDLER);
            LightsaberRecipe recipe = LightsaberRecipe.getRecipeById(player.level, message.id);

            if (cap.isPresent()) {
                IItemHandler handler = cap.resolve().get();
                if (handler.getStackInSlot(0) == ItemStack.EMPTY) {
                    for (ModIngredient ingredient : recipe.materials()) {
                        int count = ingredient.getCount();
                        for (int j = 0; j < player.getInventory().getContainerSize(); j++) {
                            if (ingredient.test(player.getInventory().getItem(j))){
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

                    handler.insertItem(0, recipe.item().copy(), false);
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
