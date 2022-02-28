package mod.stf.syconn.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.stf.syconn.StarForge;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.util.ColorConverter;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Lightsaber extends Item {

    public Lightsaber() {
        super(new Item.Properties().stacksTo(1).tab(StarForge.Tab).rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        LightsaberData data = LightsaberHelper.getData(pStack);

        pTooltipComponents.add(new TextComponent(data.getColor().getClosetColor().getName()).withStyle(ColorConverter.convert(data.getColor().getClosetColor())));
        pStack.setHoverName(new TextComponent(data.getHandle().getName()).append(new TextComponent(" Lightsaber").withStyle(ColorConverter.convert(data.getColor().getClosetColor()))));

        if (pIsAdvanced.isAdvanced()){
            pTooltipComponents.add(new TextComponent("").withStyle(ChatFormatting.GREEN));
            pTooltipComponents.add(new TextComponent("Properties").withStyle(ChatFormatting.GREEN));
            pTooltipComponents.add(new TextComponent("Model: " + data.getHandle().getType()).withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return 13;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 8.0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)-1f, AttributeModifier.Operation.ADDITION));
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? builder.build() : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        LightsaberData data = LightsaberHelper.getData(itemstack);
        if (data != null && data.isActive()) {
            pPlayer.startUsingItem(pHand);
        }
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return LightsaberHelper.getData(pStack).getColor().getDecimal();
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (allowdedIn(pCategory)){
            pItems.addAll(LightsaberHelper.createDefaults());
        }
    }
}
