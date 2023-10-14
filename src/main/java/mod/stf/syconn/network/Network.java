package mod.stf.syconn.network;

import mod.stf.syconn.Reference;
import mod.stf.syconn.network.messages.*;
import mod.stf.syconn.network.messages.c2s.*;
import mod.stf.syconn.network.messages.s2c.S2COpenProjector;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class Network {

    private static final String PROTOCOL_VERSION = "1";
    private static int nextId = 0;
    private static SimpleChannel playChannel;

    public static void init()
    {
        playChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Reference.MOD_ID, "play"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();
        register(C2SActivateLightsaber.class, new C2SActivateLightsaber(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SThrowLightsaber.class, new C2SThrowLightsaber(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SChangeColor.class, new C2SChangeColor(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SClickTab.class, new C2SClickTab(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SCraftHilt.class, new C2SCraftHilt(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SHoloMode.class, new C2SHoloMode(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SSetupSkin.class, new C2SSetupSkin(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SSlimSkin.class, new C2SSlimSkin(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SResetHolo.class, new C2SResetHolo(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SShootGuns.class, new C2SShootGuns(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SLoadBlock.class, new C2SLoadBlock(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SSetName.class, new C2SSetName(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SSetRender.class, new C2SSetRender(), NetworkDirection.PLAY_TO_SERVER);
        register(C2SSetFastRender.class, new C2SSetFastRender(), NetworkDirection.PLAY_TO_SERVER);
        register(S2COpenProjector.class, new S2COpenProjector(), NetworkDirection.PLAY_TO_CLIENT);
    }

    private static <T> void register(Class<T> clazz, IMessage<T> message, NetworkDirection direction)
    {
        playChannel.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle, Optional.of(direction));
    }

    public static SimpleChannel getPlayChannel()
    {
        return playChannel;
    }
}
