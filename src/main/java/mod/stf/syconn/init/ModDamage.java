package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ModDamage {

    public static final ResourceKey<DamageType> LIGHTSABER = register("lightsaber");
    public static final ResourceKey<DamageType> BLASTER = register("blaster");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Reference.MOD_ID, name));
    }
}
