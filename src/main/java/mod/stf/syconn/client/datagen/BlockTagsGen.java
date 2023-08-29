package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagsGen extends BlockTagsProvider {

    public BlockTagsGen(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256596_, p_256513_, Reference.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.LIGHTSABER_CRAFTER.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.HOLO_PROJECTOR.get(), ModBlocks.SCHEMATIC_PROJECTOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.HOLO_PROJECTOR.get(), ModBlocks.SCHEMATIC_PROJECTOR.get(), ModBlocks.LIGHTSABER_CRAFTER.get());
    }
}
