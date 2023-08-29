package mod.stf.syconn.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class GunItem extends Item {

    protected int maxHeat;
    protected static final String HEAT = "HEAT";

    public GunItem(Properties pProperties, int maxHeat) {
        super(pProperties);
        this.maxHeat = maxHeat;
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private static final HumanoidModel.ArmPose EXAMPLE_POSE = HumanoidModel.ArmPose.create("gun_pose", false, (model, entity, arm) -> {
                if (arm == HumanoidArm.RIGHT) {
                    model.rightArm.xRot = (float) Math.toRadians(-90 + entity.getXRot());
                    model.rightArm.yRot = (float) Math.toRadians(entity.getYHeadRot() -  entity.yBodyRot);
                } else {
                    model.leftArm.xRot = (float) Math.toRadians(-90 + entity.getXRot());
                    model.leftArm.yRot = (float) Math.toRadians(entity.getYHeadRot() -  entity.yBodyRot);
                }
            });
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (!itemStack.isEmpty()) {
                    if (entityLiving.getUsedItemHand() == hand) {
                        return EXAMPLE_POSE;
                    }
                }
                return HumanoidModel.ArmPose.EMPTY;
            }
        });
    }

    public int getUseDuration(ItemStack stack) {
        return 700;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (stack.getOrCreateTag().getInt(HEAT) > 0) {
            ThrowableProjectile bolt = createBullet(pPlayer);
            bolt.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 3.0F, 0.0F);
            pLevel.addFreshEntity(bolt);
            pLevel.playSound(null, pPlayer, shootSound(), SoundSource.PLAYERS, 0.8f, 1.0f);
            stack.getOrCreateTag().putInt(HEAT, stack.getOrCreateTag().getInt(HEAT) - 1);
        } else {
            pPlayer.getCooldowns().addCooldown(this, 20);
            stack.getOrCreateTag().putInt(HEAT, maxHeat);
        }
        return InteractionResultHolder.pass(stack);
    }

    public int getBarColor(ItemStack pStack) {
        return Objects.requireNonNull(ChatFormatting.DARK_PURPLE.getColor());
    }

    public int getBarWidth(ItemStack pStack) {
        CompoundTag tag = pStack.getOrCreateTag();
        return 13 * tag.getInt(HEAT) / maxHeat;
    }

    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    public abstract ThrowableProjectile createBullet(LivingEntity shooter);
    public abstract SoundEvent shootSound();
}
