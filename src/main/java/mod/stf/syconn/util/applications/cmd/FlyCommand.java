package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.ColorCodes;
import mod.stf.syconn.api.util.ColoredString;
import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageShipFly;
import mod.stf.syconn.util.applications.NavigationApplication;
import mod.stf.syconn.util.applications.subcmd.BasicSCM;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
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
    public List<String> getSudo() {
        return createList(super.getSudo(), new String[] {"f"});
    }

    @Override
    public CommandStatus hasParameters(String cmd) {
        String[] parameters = cmd.trim().split("\\s+");

        if (parameters.length > 2) {
            p1 = getBlockPos(parameters, 0);
            if (p1 != BlockPos.ZERO){
                return new CommandStatus("", CommandStatus.Status.SUCCESS);
            }
        }
        return new CommandStatus("Not Valid BlockPos", CommandStatus.Status.ERROR);
    }

    @Override
    public void execute() {
        Network.getPlayChannel().sendToServer(new MessageShipFly(p1, 2, application.getPos()));
    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("Needs 2 block Pos and a speed", CommandStatus.Status.SUCCESS);
    }
}
