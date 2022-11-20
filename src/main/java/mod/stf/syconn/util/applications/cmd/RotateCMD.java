package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageRotate;
import mod.stf.syconn.util.applications.NavigationApplication;
import net.minecraft.core.Direction;

public class RotateCMD extends BasicCommand<NavigationApplication> {

    private Direction dir;

    public RotateCMD(NavigationApplication application) {
        super("/", "r", application);
    }

    @Override
    public CommandStatus hasParameters(String cmd) {
        String[] parameters = cmd.trim().split("\\s+");

        if (parameters.length > 0){
            for (Direction direction : Direction.values()) {
                if (direction.getName().equals(parameters[0].toLowerCase())) {
                    dir = direction;
                    return new CommandStatus("Rotating", CommandStatus.Status.SUCCESS);
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
