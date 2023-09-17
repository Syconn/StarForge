package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import mod.stf.syconn.block.MapProjector;
import mod.stf.syconn.block.Probe;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModItems;
import mod.stf.syconn.util.MultiBlockAlignment;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockModelGen extends BlockStateProvider {

    public BlockModelGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MOD_ID, exFileHelper);
    }

    protected void registerStatesAndModels() {
        getVariantBuilder(ModBlocks.MAP_PROJECTOR.get()).forAllStates(state -> {
            MultiBlockAlignment alignment = state.getValue(MapProjector.ALIGNMENT);
            boolean top = state.getValue(MapProjector.TOP);
            return ConfiguredModel.builder()
                    .modelFile(alignment == MultiBlockAlignment.MID ? generated(BuiltInRegistries.BLOCK.getKey(ModBlocks.MAP_PROJECTOR.get()).getPath() + (top ? "_top" : "")) : new ModelFile.UncheckedModelFile(mcLoc("block/air")))
                    .rotationY(alignment.getYRot())
                    .uvLock(false)
                    .build();
        });
        getVariantBuilder(ModBlocks.PROBE.get()).forAllStates(state -> {
            return ConfiguredModel.builder()
                    .modelFile(state.getValue(Probe.ORBIT) ? generated("probe_air") : generated("probe"))
                    .build();
        });

        itemModels().withExistingParent(BuiltInRegistries.BLOCK.getKey(ModBlocks.PROBE.get()).getPath(), modLoc("block/probe"));
        itemModels().withExistingParent(BuiltInRegistries.BLOCK.getKey(ModBlocks.MAP_PROJECTOR.get()).getPath(), modLoc("block/map_projector_top"));
    }

    private ModelFile generated(String loc) {
        return new ModelFile.UncheckedModelFile(modLoc("block/" + loc));
    }
}
