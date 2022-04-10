package mod.stf.syconn.item.guns;

import mod.stf.syconn.common.entity.BlasterBolt;
import mod.stf.syconn.item.GunItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;

public class EnergyGun extends GunItem {

    public EnergyGun(Properties pProperties) {
        super(pProperties, 20);
    }

    @Override
    public ThrowableProjectile createBullet(Player player) {
        return new BlasterBolt(player, player.getLevel());
    }

    @Override
    public ItemStack createGun() {
        return new ItemStack(this);
    }
}
