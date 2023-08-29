package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new ItemModels(generator.getPackOutput(), event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new LangDatagen(generator.getPackOutput(), "en_us"));
        generator.addProvider(event.includeClient(), new LootTableGen(generator.getPackOutput()));
        generator.addProvider(event.includeClient(), new BlockModelGen(generator.getPackOutput(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new BlockTagsGen(generator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new RecipeGen(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(generator.getPackOutput(), event.getLookupProvider(), DatapackProvider.BUILDER, Set.of(Reference.MOD_ID)));
    }
}
