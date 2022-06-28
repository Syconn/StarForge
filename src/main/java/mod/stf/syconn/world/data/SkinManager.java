package mod.stf.syconn.world.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class SkinManager implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<SkinData> SKINS = CapabilityManager.get(new CapabilityToken<>(){});

    private SkinData skinData = null;
    private final LazyOptional<SkinData> opt = LazyOptional.of(this::createSkinData);

    @Nonnull
    private SkinData createSkinData() {
        if (skinData == null) {
            skinData = new SkinData();
        }
        return skinData;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == SKINS) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createSkinData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createSkinData().loadNBTData(nbt);
    }
}
