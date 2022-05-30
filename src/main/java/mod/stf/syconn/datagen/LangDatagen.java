package mod.stf.syconn.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModDamage;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.Lightsaber;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangDatagen extends LanguageProvider {

    public LangDatagen(DataGenerator gen, String locale) {
        super(gen, Reference.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModItems.F_11D.get(), "F-11 Blaster");
        add(ModItems.LIGHTSABER.get(), "Lightsaber");
        add(ModBlocks.LIGHTSABER_CRAFTER.get(), "Lightsaber Workstation");

        add("key.lightsaber.activate", "Key Activate Lightsaber");
        add("key.categories.stf", "StarForge");

        add("itemGroup.StarForge", "StarForge");

        add("death.attack.lightsaber", "%1$s was cut down by %2$s");
        add("death.attack.blaster", "%1$s was gunned down by %2$s");

        addEntityType(ModEntities.STORMTROOPER, "StormTrooper");

        add("tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + LightsaberCrafter.States.COLOR.getSerializedName(), "Colorizer");
        add("tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + LightsaberCrafter.States.CONSTRUCTION.getSerializedName(), "Part Crafter");
        add("tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + LightsaberCrafter.States.HILT.getSerializedName(), "Hilt Crafter");
    }
}
