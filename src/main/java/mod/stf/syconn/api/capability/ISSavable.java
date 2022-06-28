package mod.stf.syconn.api.capability;

import net.minecraft.nbt.CompoundTag;

public interface ISSavable {

    void saveNBTData(CompoundTag compound);

    void loadNBTData(CompoundTag compound);
}
