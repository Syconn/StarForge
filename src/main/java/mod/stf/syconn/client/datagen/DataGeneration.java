package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            BlockTagGen blockTagGen = new BlockTagGen(generator, event.getExistingFileHelper());
            generator.addProvider(blockTagGen);
            generator.addProvider(new ItemTagGen(generator, blockTagGen, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new LangDatagen(generator, "en_us"));
        }
    }
}
