package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageRotate;
import mod.stf.syconn.util.applications.NavigationApplication;
import mod.stf.syconn.util.applications.subcmd.BasicSCM;
import net.minecraft.core.Direction;

import java.util.List;

public class RotateCMD extends BasicCommand<NavigationApplication> {

    private Direction dir;

    public RotateCMD(NavigationApplication application) {
        super("/", "r", application);
    }

    @Override
    protected List<BasicSCM> getSubCMDS() {
        return null;
    }

    @Override
    public CommandStatus hasParameters(String cmd) {
        String[] parameters = cmd.trim().split("\\s+");

        if (parameters.length > 0){
            if (getDir(parameters[0]) != null){
                dir = getDir(parameters[0]);
                if (level.getBlockEntity(application.getPos()) instanceof NavBE be) {
                    if (be.getDir() != dir)
                        return new CommandStatus("Rotating", CommandStatus.Status.SUCCESS);
                    return new CommandStatus("Already Rotated That Direction", CommandStatus.Status.WARN);
                }
            }
        }
        return new CommandStatus("Not Valid Direction", CommandStatus.Status.ERROR);
    }

    @Override
    public void execute() {
        Network.getPlayChannel().sendToServer(new MessageRotate(dir, application.getPos()));
    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("TESTING", CommandStatus.Status.HELP);
    }
}
