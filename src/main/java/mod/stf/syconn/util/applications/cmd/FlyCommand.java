package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageShipFly;
import mod.stf.syconn.util.applications.NavigationApplication;
import mod.stf.syconn.util.applications.subcmd.BasicSCM;
import net.minecraft.core.BlockPos;

import java.util.List;

public class FlyCommand extends BasicCommand<NavigationApplication> {

    private BlockPos p1;
    private int p2;

    public FlyCommand(NavigationApplication application) {
        super("/", "fly", application);
    }

    @Override
    protected List<BasicSCM> getSubCMDS() {
        return null;
    }

    @Override
    public CommandStatus hasParameters(String cmd) {
        String[] parameters = cmd.trim().toLowerCase().split("\\s+");

//        if (parameters.length >= 4) {
//            p1 = getBlockPos(parameters, 0);
//            if (p1 != BlockPos.ZERO){
//                if (Mths.isNumeric(parameters[3])) {
//                    p2 = Integer.parseInt(parameters[3]);
//                    return new CommandStatus("", CommandStatus.Status.SUCCESS);
//                }
//                return new CommandStatus("Not Number", CommandStatus.Status.ERROR);
//            }
//        }
//        return new CommandStatus("Not valid block pos", CommandStatus.Status.ERROR);
        return new CommandStatus("", CommandStatus.Status.SUCCESS);
    }

    @Override
    public void execute() {
        Network.getPlayChannel().sendToServer(new MessageShipFly(new BlockPos(0, 122, 183), 2, application.getPos()));
    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("Needs 2 block Pos and a speed", CommandStatus.Status.SUCCESS);
    }
}
