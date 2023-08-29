package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModelGen extends BlockStateProvider {

    public BlockModelGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MOD_ID, exFileHelper);
    }

    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.MAP_PROJECTOR.get(), generated("map_projector"));
    }

    private ModelFile generated(String loc) {
        return new ModelFile.UncheckedModelFile(modLoc("block/" + loc));
    }
}
