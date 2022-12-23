package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTagGen extends ItemTagsProvider {

    public ItemTagGen(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }
}
