package mod.stf.syconn.item;

import mod.stf.syconn.StarForge;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.common.blockEntity.SchematicBe;
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
            } else {
                stack.getOrCreateTag().put("schematic", Schematic.createSchematic(pos1, pos));
                pos1 = null;
            }
        }

        //pContext.
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pIsAdvanced.isAdvanced()){
            for (BlockPos pos : Schematic.readSchematic(pLevel, pStack.getOrCreateTag().getCompound("schematic"))){
                pTooltipComponents.add(new TextComponent(pos.toShortString() + ": " + pLevel.getBlockState(pos).getBlock().getName().getString()));
            }
        }
    }
}
