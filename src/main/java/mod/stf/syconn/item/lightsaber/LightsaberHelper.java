package mod.stf.syconn.item.lightsaber;

import mod.stf.syconn.init.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Random;

public class LightsaberHelper {

    public static void setData(ItemStack stack, LightsaberData data){
        CompoundTag tag = new CompoundTag();
        tag.putFloat("id", data.getHandle().getId());
        tag.putBoolean("state", data.isActive());
        LColor.save(tag, data.getColor());
        tag.putBoolean("hide_bar", data.hideBar());
        stack.getOrCreateTag().put("ldata", tag);
    }

    private static boolean hasData(ItemStack stack){
        return stack.getTag() != null && stack.getTag().contains("ldata");
    }

    @Nullable
    public static LightsaberData getData(ItemStack stack){
        if (hasData(stack)) {
            CompoundTag tag = stack.getOrCreateTag().getCompound("ldata");
            return new LightsaberData(LightsaberData.HandleType.getHandle(tag.getFloat("id")), tag.getBoolean("state"), LColor.read(tag), tag.getBoolean("hide_bar"));
        }
        return null;
    }

    public static NonNullList<ItemStack> createDefaults(){
        NonNullList<ItemStack> pItems = NonNullList.create();

        for (LightsaberData.HandleType type : LightsaberData.HandleType.values()){
            ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
            LightsaberData data = new LightsaberData(type, false, new LColor(type.getColor()), false);
            LightsaberHelper.setData(stack, data);
            pItems.add(stack);
        }

        return pItems;
    }

    public static ItemStack randomPreset(boolean jedi){
        LightsaberData.HandleType[] jeditypes = {LightsaberData.HandleType.AHSOKA, LightsaberData.HandleType.ANAKIN, LightsaberData.HandleType.KAL, LightsaberData.HandleType.LUKE, LightsaberData.HandleType.YODA, LightsaberData.HandleType.OBI};
        LightsaberData.HandleType[] sithtypes = {LightsaberData.HandleType.DARK_SABER, LightsaberData.HandleType.KYLO, LightsaberData.HandleType.MAUL};

        if (jedi){
            int i = new Random().nextInt(jeditypes.length);
            return createPreset(jeditypes[i]);
        } else {
            int i = new Random().nextInt(sithtypes.length);
            return createPreset(sithtypes[i]);
        }
    }

    public static ItemStack createPreset(LightsaberData.HandleType type){
        ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
        LightsaberData data = new LightsaberData(type, true, new LColor(type.getColor()));
        LightsaberHelper.setData(stack, data);
        return stack;
    }

    public static ItemStack activate(ItemStack stack){
        LightsaberData data = getData(stack);
        if (data != null) {
            data.setState(true);
            setData(stack, data);
        }
        return stack;
    }

    public static ItemStack customOnLightsaber(LightsaberData.HandleType type, LColor color){
        ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
        //stack.setHoverName(new TextComponent(type.getName()).append(new TextComponent(" Lightsaber").withStyle(ColorConverter.convert(type.getDefaultColor()))));
        LightsaberData data = new LightsaberData(type, true, color);
        LightsaberHelper.setData(stack, data);
        return stack;
    }

    public static ItemStack customOffLightsaber(LightsaberData.HandleType type, LColor color, boolean hideBar){
        ItemStack stack = new ItemStack(ModItems.LIGHTSABER.get());
        //stack.setHoverName(new TextComponent(type.getName()).append(new TextComponent(" Lightsaber").withStyle(ColorConverter.convert(type.getDefaultColor()))));
        LightsaberData data = new LightsaberData(type, false, color, hideBar);
        LightsaberHelper.setData(stack, data);
        return stack;
    }
}
