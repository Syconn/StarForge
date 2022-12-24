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
        register(MessageActivateLightsaber.class, new MessageActivateLightsaber());
        register(MessageThrowLightsaber.class, new MessageThrowLightsaber());
        register(MessageChangeColor.class, new MessageChangeColor());
        register(MessageClickTab.class, new MessageClickTab());
        register(MessageCraftHilt.class, new MessageCraftHilt());
        register(MessageHoloMode.class, new MessageHoloMode());
        register(MessageSetupSkin.class, new MessageSetupSkin());
        register(MessageSlimSkin.class, new MessageSlimSkin());
        register(MessageResetHolo.class, new MessageResetHolo());
        register(MessageHoloGear.class, new MessageHoloGear());
        register(MessageShootGuns.class, new MessageShootGuns());
        register(MessageLoadBlock.class, new MessageLoadBlock());
        register(MessageHyperdrive.class, new MessageHyperdrive());
        register(MessageSetShip.class, new MessageSetShip());
        register(MessageShowShip.class, new MessageShowShip());
        register(MessageRotate.class, new MessageRotate());
        register(MessageDisassemble.class, new MessageDisassemble());
        register(MessageShipFly.class, new MessageShipFly());
        register(MessageShowPath.class, new MessageShowPath());
    }

    private static <T> void register(Class<T> clazz, IMessage<T> message, NetworkDirection direction)
    {
        playChannel.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle, Optional.of(direction));
    }

    private static <T> void register(Class<T> clazz, IMessage<T> message)
    {
        playChannel.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    public static SimpleChannel getPlayChannel()
    {
        return playChannel;
    }
}
