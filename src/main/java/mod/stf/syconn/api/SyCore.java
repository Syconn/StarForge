package mod.stf.syconn.api;

import mod.stf.syconn.api.network.Network;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class SyCore {


    //TODO WHEN EXTRA SOURCE MAKE IT AUTO CALL (ALSO PRIVATE)
    public static void onCommonSetup(FMLCommonSetupEvent event)
    {
        Network.init();
    }

    public static void onClientSetup(FMLClientSetupEvent event)
    {

    }
}
