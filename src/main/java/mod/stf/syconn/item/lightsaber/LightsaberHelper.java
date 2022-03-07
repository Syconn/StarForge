package mod.stf.syconn.item.lightsaber;

import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.util.ColorConverter;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class LightsaberHelper {

    public static void setData(ItemStack stack, LightsaberData data){
        CompoundTag tag = new CompoundTag();
        tag.putFloat("id", data.getHandle().getId());
        tag.putBoolean("state", data.isActive());
        LColor.save(tag, data.getColor());
        stack.getOrCreateTag().put("ldata", tag);
        stack.setHoverName(new TextComponent(data.getHandle().getName()).append(new TextComponent(" Lightsaber").withStyle(ColorConverter.convert(data.getColor().getClosetColor()))));
    }

    private static boolean hasData(ItemStack stack){
        return stack.getTag() != null && stack.getTag().contains("ldata");
    }

    @Nullable
    public static LightsaberData getData(ItemStack stack){
        if (hasData(stack)) {
            CompoundTag tag = stack.getOrCreateTag().getCompound("ldata");
            return new LightsaberData(LightsaberData.HandleType.getHandle(tag.getFloat("id")), tag.getBoolean("state"), LColor.read(tag));
        }
        return null;
    }

    public static NonNullList<ItemStack> createDefaults(){
        NonNullList<ItemStack> pItems = NonNullList.create();

        for (LightsaberData.HandleType type : LightsaberData.HandleType.values()){
            ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
            stack.setHoverName(new TextComponent(type.getName()).append(new TextComponent(" Lightsaber").withStyle(ColorConverter.convert(type.getDefaultColor()))));
                    //.withStyle(ColorConverter.convert(type.getDefaultColor())));
            LightsaberData data = new LightsaberData(type, false, new LColor(type.getColor()));
            LightsaberHelper.setData(stack, data);
            pItems.add(stack);
        }

        return pItems;
    }
}
