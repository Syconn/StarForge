package mod.stf.syconn.world.data;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.api.capability.ISSavable;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.Map;

public class SkinData implements ISSavable {

    Map<String, ServerPixelImage> skins = new HashMap<>();
    Map<String, Boolean> slim_skins = new HashMap<>();

    public ServerPixelImage getSkin(String name) {
        return skins.get(name);
    }

    public void putSkins(String name, ServerPixelImage skin){
        if (!skins.containsKey(name)){
            skins.put(name, skin);
        }
    }

    public void setSkins(Map<String, ServerPixelImage> skins){
        this.skins = skins;
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

    public void setSlimSkins(Map<String, Boolean> skins){
        this.slim_skins = skins;
    }

    public boolean containsSlim(String name){
        return slim_skins.containsKey(name);
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
                tag.put("pixels", NbtUtil.writeServerImage(skin));
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
                ServerPixelImage image = NbtUtil.readServerImage(tag.getCompound("pixels"));
                boolean slim = tag.getBoolean("slim");
                skins.put(name, image);
                slim_skins.put(name, slim);
            });
        }
    }
}
