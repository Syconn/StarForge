package mod.stf.syconn.common.containers;

import com.mojang.datafixers.util.Pair;
import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.inventory.InventoryMenu.*;

public class HoloContainer extends ContainerMenu {

    private final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_HELMET, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_BOOTS, new ResourceLocation("item/empty_slot_sword"), EMPTY_ARMOR_SLOT_SHIELD};
    public static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

    public HoloContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.HOLO_CONTAINER.get(), windowId, pos, playerInventory, player, ModBlocks.HOLO_PROJECTOR.get());
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
            for (int i = 0; i < 6; i++) {
                int e = i;
                addSlot(new SlotItemHandler(h, i, 5, (18 * i) - 12) {
                    public int getMaxStackSize() {
                        return 0;
                    }

                    public boolean mayPlace(@NotNull ItemStack stack) {
                        if (SLOT_IDS[e] == EquipmentSlot.OFFHAND) return true;
                        return stack.canEquip(SLOT_IDS[e], player);
                    }

                    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                        return Pair.of(InventoryMenu.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS[e]);
                    }
                });
            }
        });
        layoutPlayerInventorySlots(8, 109);
    }
}
