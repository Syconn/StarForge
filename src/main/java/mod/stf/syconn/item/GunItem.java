package mod.stf.syconn.item;

import mod.stf.syconn.common.entity.BlasterBolt;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.Objects;

public abstract class GunItem extends Item {

    protected int maxHeat;
    protected final String HEAT = "HEAT";

    public GunItem(Properties pProperties, int maxHeat) {
        super(pProperties);
        this.maxHeat = maxHeat;
    }

    public int getUseDuration(ItemStack stack) {
        return 700;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (stack.getOrCreateTag().getInt(HEAT) > 0) {
            ThrowableProjectile bolt = createBullet(pPlayer);
            bolt.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 3.0F, 0.0F);
            pLevel.addFreshEntity(bolt);
            stack.getOrCreateTag().putInt(HEAT, stack.getOrCreateTag().getInt(HEAT) - 1);
        } else {
            pPlayer.getCooldowns().addCooldown(this, 20);
            stack.getOrCreateTag().putInt(HEAT, maxHeat);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Objects.requireNonNull(ChatFormatting.DARK_PURPLE.getColor());
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        CompoundTag tag = pStack.getOrCreateTag();
        return 13 * tag.getInt(HEAT) / maxHeat;
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        pItems.add(createGun());
    }

    public abstract ThrowableProjectile createBullet(LivingEntity shooter);
    public abstract ItemStack createGun();
}
