package mod.stf.syconn.client.datagen;

import mod.stf.syconn.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new ItemModels(generator.getPackOutput(), event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(),new LangDatagen(generator.getPackOutput(), "en_us"));
    }
}
