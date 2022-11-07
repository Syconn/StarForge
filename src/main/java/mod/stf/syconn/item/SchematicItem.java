package mod.stf.syconn.item;

import mod.stf.syconn.StarForge;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.blockEntity.SchematicBe;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SchematicItem extends Item {

    private BlockPos pos1 = null;

    public SchematicItem() {
        super(new Item.Properties().tab(StarForge.Tab).rarity(Rarity.EPIC).stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        ItemStack stack = pContext.getItemInHand();
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        if (!level.isClientSide()) {
            if (pos1 == null){
                pos1 = pos;
                pContext.getPlayer().sendMessage(new TextComponent("Pos1 Selected").withStyle(ChatFormatting.YELLOW), Util.NIL_UUID);
            } else {
                stack.getOrCreateTag().put("schematic", Schematic.genSchematic(pos1, pos).cleanSchematic().saveSchematic());
                pos1 = null;
                pContext.getPlayer().sendMessage(new TextComponent("Schematic Created").withStyle(ChatFormatting.YELLOW), Util.NIL_UUID);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pIsAdvanced.isAdvanced()){
            int i = 0;
            List<BlockID> sc = Schematic.readSchematic(pStack.getOrCreateTag().getCompound("schematic")).getBlockIDs();
            for (BlockID id : sc){
                if (i < 15) {
                    pTooltipComponents.add(new TextComponent(id.pos().toShortString() + ": " + id.state().getBlock().getName().getString()));
                } else if (i == 15) {
                    pTooltipComponents.add(new TextComponent("+" + (sc.size() - i) + " More"));
                }
                i++;
            }
        }
    }
}
