package mod.stf.syconn.util.applications;

import mod.stf.syconn.api.util.ColorCodes;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageSetShip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.commands.CommandFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.commands.GiveCommand;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LoadShipCMD extends BasicCommand<NavigationApplication> {

    private Player player;
    private BlockPos pos1;
    private BlockPos pos2;

    public LoadShipCMD(NavigationApplication application) {
        super("/", "load", application);
    }

    @Override
    public CommandStatus hasParameters(String cmd) {
        String[] parameters = cmd.trim().split("\\s+");

        if (parameters.length > 3) {
            pos1 = CMDTools.getBlockPos(Mths.splitArray(parameters, 0, 3));
            if (pos1 != null) {
                pos2 = CMDTools.getBlockPos(Mths.splitArray(parameters, 3, 3));
                if (pos2 != null) {
                    return new CommandStatus("Successful BlockPos's", CommandStatus.Status.SUCCESS);
                }
                return new CommandStatus("Not Proper Pos 2", CommandStatus.Status.ERROR);
            }
        }

        if (parameters.length > 0){
            for (PlayerInfo playerInfo : Minecraft.getInstance().getConnection().getOnlinePlayers()){
                if (playerInfo.getProfile().getName().equalsIgnoreCase(parameters[0])){
                    player = Minecraft.getInstance().level.getPlayerByUUID(playerInfo.getProfile().getId());
                }
            }

            if (player != null){
                return new CommandStatus("Grabbed Player Schematic", CommandStatus.Status.SUCCESS);
            } else {
                return new CommandStatus("No Player Found", CommandStatus.Status.ERROR);
            }
        }
        return new CommandStatus("need more parameters", CommandStatus.Status.ERROR);
    }

    @Override
    public void execute() {
        if (player != null){
            Network.getPlayChannel().sendToServer(new MessageSetShip(Schematic.fromSchematic(player.getLevel(), player.getMainHandItem().getOrCreateTag().getCompound("schematic")).cleanSchematic(level), application.getPos()));
        } else if (pos1 != null && pos2 != null){
            Network.getPlayChannel().sendToServer(new MessageSetShip(Schematic.genSchematic(pos1, pos2), application.getPos()));
        }
    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("/load " + ColorCodes.WHITE + "<pos1> " + ColorCodes.WHITE + "<pos2> or /load " + ColorCodes.WHITE + "<playerName> EX - /load " + ColorCodes.AQUA + "10 " + ColorCodes.AQUA + "20 " + ColorCodes.AQUA + "30 " + ColorCodes.AQUA + "30 " + ColorCodes.AQUA + "20 "+ ColorCodes.AQUA + "5 or /load " + ColorCodes.GREEN + " Syconn", CommandStatus.Status.HELP);
    }
}
