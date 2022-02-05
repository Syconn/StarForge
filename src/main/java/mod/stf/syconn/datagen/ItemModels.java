package mod.stf.syconn.datagen;

import mod.stf.syconn.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //withExistingParent(ModItems.Drone_Egg.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
        //withExistingParent(, modLoc("block/mysterious_ore_overworld"));
    }
}
