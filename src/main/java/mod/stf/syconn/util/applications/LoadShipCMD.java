package mod.stf.syconn.util.applications;

import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.api.util.data.Schematic;
import net.minecraft.commands.CommandFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
            //TODO NEVER WILL WORK REWORK ORDER
            else {
                player = level.getServer().getPlayerList().getPlayerByName(parameters[0]);

                if (player != null){
                    return new CommandStatus("Grabbed Player Schematic", CommandStatus.Status.SUCCESS);
                } else {
                    return new CommandStatus("No Player Found or Not Proper Pos 1", CommandStatus.Status.ERROR);
                }
            }
        }
        return new CommandStatus("need more parameters", CommandStatus.Status.ERROR);
    }

    @Override
    public void execute() {
        if (player != null){
            application.setShip(Schematic.fromSchematic(player.getLevel(), player.getUseItem().getOrCreateTag().getCompound("schematic")).cleanSchematic(level));
        } else if (pos1 != null && pos2 != null){
            application.setShip(Schematic.genSchematic(pos1, pos2));
        }
    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("/load <pos1> <pos2> or /load <playerName> EX - /load 10 20 30 30 20 5 or /load Syconn", CommandStatus.Status.HELP);
    }
}
