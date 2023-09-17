package mod.stf.syconn.world.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class ChunkManager implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<ChunkData> CHUNKS = CapabilityManager.get(new CapabilityToken<>(){});

    private ChunkData chunkData = null;
    private final LazyOptional<ChunkData> opt = LazyOptional.of(this::createChunkData);

    private ChunkData createChunkData() {
        if (chunkData == null) {
            chunkData = new ChunkData();
        }
        return chunkData;
    }

    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CHUNKS) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createChunkData().saveNBTData(nbt);
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        createChunkData().loadNBTData(nbt);
    }
}