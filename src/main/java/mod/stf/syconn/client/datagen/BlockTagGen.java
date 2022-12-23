package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class BlockTagGen extends BlockTagsProvider {

    public BlockTagGen(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ModTags.Blocks.OPEN_BLOCK)
                .replace(false)
                .add(Blocks.AIR)
                .add(Blocks.CAVE_AIR)
                .add(Blocks.VOID_AIR)
                .add(Blocks.VINE)
                .add(Blocks.CAVE_VINES)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.CAVE_VINES_PLANT)
                .add(Blocks.TWISTING_VINES);
    }
}
