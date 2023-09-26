package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModDamage;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.Lightsaber;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class LangDatagen extends LanguageProvider {

    public LangDatagen(PackOutput packOutput, String locale) {
        super(packOutput, Reference.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModItems.F_11D.get(), "F-11 Blaster");
        add(ModItems.LIGHTSABER.get(), "Lightsaber");
        add(ModItems.STORMTROOPER_EGG.get(), "StormTrooper Spawn Egg");
        add(ModItems.JEDI_EGG.get(), "Jedi Spawn Egg");
        add(ModItems.TIE_ITEM.get(), "Tie Fighter Spawn Egg");
        add(ModItems.SCHEMATIC_ITEM.get(), "Schematic Item");
        add(ModItems.PROBE_LINKER.get(), "Probe Linker");
        add(ModBlocks.LIGHTSABER_CRAFTER.get(), "Lightsaber Workstation");
        add(ModBlocks.SCHEMATIC_PROJECTOR.get(), "Schematic Projector");
        add(ModBlocks.HOLO_PROJECTOR.get(), "Holographic Projector");
        add(ModBlocks.MAP_PROJECTOR.get(), "Battlefield Projector");
        add(ModBlocks.PROBE.get(), "Probe");
        add(Reference.MOD_ID + ".category.lightsaber.title", "Lightsaber Crafter");
        add(Reference.MOD_ID + ".category.lightsaber.materials", "Materials");
        add("key.lightsaber.activate", "Key Activate Lightsaber");
        add("key.categories.stf", "StarForge");
        add("itemGroup.StarForge", "StarForge");
        add("death.attack.lightsaber.item", "%1$s was cut down by %2$s with %3$s");
        add("tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + LightsaberCrafter.States.COLOR.getSerializedName(), "Colorizer");
        add("tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + LightsaberCrafter.States.CONSTRUCTION.getSerializedName(), "Part Crafter");
        add("tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + LightsaberCrafter.States.HILT.getSerializedName(), "Hilt Crafter");
    }
}
