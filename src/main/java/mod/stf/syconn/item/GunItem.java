package mod.stf.syconn.item;

import mod.stf.syconn.common.entity.BlasterBoltEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GunItem extends Item {

    public GunItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        BlasterBoltEntity bolt = new BlasterBoltEntity(pPlayer, pLevel);
        bolt.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 3.0F, 1.0F);
        pLevel.addFreshEntity(bolt);
        return InteractionResultHolder.pass(stack);
    }
}
