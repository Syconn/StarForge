package mod.stf.syconn.client.audio;

import mod.stf.syconn.common.entity.ThrownLightsaber;
import mod.stf.syconn.init.ModSounds;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;

public class ThrownLightsaberSound extends AbstractTickableSoundInstance {

    private final ThrownLightsaber lightsaber;

    public ThrownLightsaberSound(ThrownLightsaber lightsaber) {
        super(ModSounds.LIGHTSABER_BUZZ.get(), SoundSource.MASTER, SoundInstance.createUnseededRandom());
        this.looping = true;
        this.lightsaber = lightsaber;
//        this.attenuation = Attenuation.NONE;
        this.tick();
    }

    public void tick() {
        if (this.lightsaber.isRemoved()) {
            this.stop();
        } else if (lightsaber.isAlive()) {
            this.x = (float) lightsaber.getX();
            this.y = (float) lightsaber.getY();
            this.z = (float) lightsaber.getZ();
            this.volume = 1.0F;
            this.delay = 0;
            return;
        }

        this.stop();
    }
}
