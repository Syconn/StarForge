package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.item.lightsaber.LightsaberData;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(ModItems.F_11D.get().toString(), modLoc("item/f_11"));
        withExistingParent(ModItems.STORMTROOPER_EGG.get().toString(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.JEDI_EGG.get().toString(), mcLoc("item/template_spawn_egg"));
        singleTexture(ModItems.SCHEMATIC_ITEM.get().toString(), mcLoc("item/generated"), "layer0", modLoc("item/schematic_item"));

        ItemModelBuilder builder = withExistingParent(ModItems.LIGHTSABER.get().toString(), modLoc("item/lightsaber_off/yoda"));

        for (LightsaberData.HandleType type : LightsaberData.HandleType.values()){
            builder.override().predicate(new ResourceLocation("model"), type.getId()).predicate(new ResourceLocation("active"), 0.0f).model(generated("item/lightsaber_off/" + type.getType())).end();
            builder.override().predicate(new ResourceLocation("model"), type.getId()).predicate(new ResourceLocation("active"), 1.0f).model(generated("item/lightsaber_on/" + type.getType())).end();
        }
    }

    private ModelFile generated(String loc) {
        return new ModelFile.UncheckedModelFile(modLoc(loc));
    }
}
