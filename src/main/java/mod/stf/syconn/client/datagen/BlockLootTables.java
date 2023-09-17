package mod.stf.syconn.client.datagen;

import mod.stf.syconn.init.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.List;

public class BlockLootTables extends BlockLootSubProvider {

    public BlockLootTables() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    protected void generate() {
        dropSelf(ModBlocks.HOLO_PROJECTOR.get());
        dropSelf(ModBlocks.SCHEMATIC_PROJECTOR.get());
        dropSelf(ModBlocks.LIGHTSABER_CRAFTER.get());
        dropSelf(ModBlocks.MAP_PROJECTOR.get());
        dropSelf(ModBlocks.PROBE.get());
    }

    protected Iterable<Block> getKnownBlocks() {
        return List.of(ModBlocks.HOLO_PROJECTOR.get(), ModBlocks.SCHEMATIC_PROJECTOR.get(), ModBlocks.LIGHTSABER_CRAFTER.get(), ModBlocks.PROBE.get(), ModBlocks.MAP_PROJECTOR.get());
    }
}
