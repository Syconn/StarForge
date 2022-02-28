package mod.stf.syconn.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangDatagen extends LanguageProvider {

    public LangDatagen(DataGenerator gen, String locale) {
        super(gen, Reference.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModItems.F_11D.get(), "F-11 Blaster");
        add(ModItems.Lightsaber.get(), "Lightsaber");
        add(ModBlocks.LIGHTSABER_CRAFTER.get(), "Lightsaber Workstation");
        add("key.lightsaber.activate", "Key Activate Lightsaber");
        add("key.categories.stf", "StarForge");
        add("itemGroup.StarForge", "StarForge");
    }
}
