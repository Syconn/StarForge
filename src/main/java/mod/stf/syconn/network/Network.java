package mod.stf.syconn.network;

import mod.stf.syconn.Reference;
import mod.stf.syconn.network.messages.*;
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
        register(MessageActivateLightsaber.class, new MessageActivateLightsaber(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageThrowLightsaber.class, new MessageThrowLightsaber(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageChangeColor.class, new MessageChangeColor(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageClickTab.class, new MessageClickTab(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageCraftHilt.class, new MessageCraftHilt(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageHoloMode.class, new MessageHoloMode(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageSetupSkin.class, new MessageSetupSkin(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageSlimSkin.class, new MessageSlimSkin(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageResetHolo.class, new MessageResetHolo(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageHoloGear.class, new MessageHoloGear(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageShootGuns.class, new MessageShootGuns(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageLoadBlock.class, new MessageLoadBlock(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageHyperdrive.class, new MessageHyperdrive(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageSetShip.class, new MessageSetShip(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageNavEnabled.class, new MessageNavEnabled(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageRotate.class, new MessageRotate(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageDisassemble.class, new MessageDisassemble(), NetworkDirection.PLAY_TO_SERVER);
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
