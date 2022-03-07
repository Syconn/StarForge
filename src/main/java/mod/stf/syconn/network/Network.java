package mod.stf.syconn.network;

import mod.stf.syconn.Reference;
import mod.stf.syconn.network.messages.IMessage;
import mod.stf.syconn.network.messages.MessageActivateLightsaber;
import mod.stf.syconn.network.messages.MessageChangeColor;
import mod.stf.syconn.network.messages.MessageClickTab;
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
        register(MessageChangeColor.class, new MessageChangeColor(), NetworkDirection.PLAY_TO_SERVER);
        register(MessageClickTab.class, new MessageClickTab(), NetworkDirection.PLAY_TO_SERVER);
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
