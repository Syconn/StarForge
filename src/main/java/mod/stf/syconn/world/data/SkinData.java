package mod.stf.syconn.world.data;

import mod.stf.syconn.api.capability.ISSavable;
import mod.stf.syconn.api.util.data.PixelImage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.Map;

public class SkinData implements ISSavable {

    Map<String, PixelImage> skins = new HashMap<>();
    Map<String, Boolean> slim_skins = new HashMap<>();

    public PixelImage getSkin(String name) {
        return skins.get(name);
    }

    public void putSkins(String name, PixelImage skin){
        if (!skins.containsKey(name)){
            skins.put(name, skin);
        }
    }

    public boolean contains(String name){
        return skins.containsKey(name);
    }

    public boolean getSlimSkin(String name) {
        return slim_skins.get(name);
    }

    public void putSlimSkins(String name, boolean skin){
        if (!slim_skins.containsKey(name)){
            slim_skins.put(name, skin);
        }
    }

    public void resetSkins(){
        skins.clear();
        slim_skins.clear();
    }

    @Override
    public void saveNBTData(CompoundTag compound) {
        ListTag map = new ListTag();
        skins.forEach((name, skin) -> {
            if (skin != null) {
                CompoundTag tag = new CompoundTag();
                tag.putString("name", name);
                tag.put("pixels", skin.write());
                tag.putBoolean("slim", slim_skins.get(name));
                map.add(tag);
            }
        });
        compound.put("skins", map);
    }

    @Override
    public void loadNBTData(CompoundTag compound) {
        this.skins.clear();
        this.slim_skins.clear();
        if (compound.contains("skins", Tag.TAG_LIST)){
            ListTag map = compound.getList("skins", Tag.TAG_COMPOUND);
            map.forEach(nbt -> {
                CompoundTag tag = (CompoundTag) nbt;
                String name = tag.getString("name");
                PixelImage image = PixelImage.read(tag.getCompound("pixels"));
                boolean slim = tag.getBoolean("slim");
                skins.put(name, image);
                slim_skins.put(name, slim);
            });
        }
    }
}
