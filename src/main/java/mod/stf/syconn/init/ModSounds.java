package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

    public static final RegistryObject<SoundEvent> LIGHTSABER_IGNITION = register("item.lightsaber.ignition");
    public static final RegistryObject<SoundEvent> LIGHTSABER_DEACTIVATION = register("item.lightsaber.deactivation");
    public static final RegistryObject<SoundEvent> LIGHTSABER_BLOCK = register("item.lightsaber.block");
    public static final RegistryObject<SoundEvent> LIGHTSABER_SWING = register("item.lightsaber.swing");
    public static final RegistryObject<SoundEvent> LIGHTSABER_BUZZ = register("item.lightsaber.buzz");
    public static final RegistryObject<SoundEvent> BLASTER_SHOOT = register("item.blaster.shoot");

    private static RegistryObject<SoundEvent> register(String key) {
        return REGISTER.register(key, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, key)));
    }
}
