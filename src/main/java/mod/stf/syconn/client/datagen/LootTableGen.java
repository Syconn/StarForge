package mod.stf.syconn.client.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class LootTableGen extends LootTableProvider {

    public LootTableGen(PackOutput p_254123_) {
        super(p_254123_, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)));
    }
}