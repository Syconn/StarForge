package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.ColorCodes;
import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageNavEnabled;
import mod.stf.syconn.util.applications.NavigationApplication;

public class DisplayCMD extends BasicCommand<NavigationApplication> {

    private boolean enabled;
    private int gof;
    public DisplayCMD(NavigationApplication application) {
        super("/", "display", application);
        if (level.getBlockEntity(application.getPos()) instanceof NavBE)
            enabled = ((NavBE) level.getBlockEntity(application.getPos())).isEnabled();
        gof = 0;
    }

    @Override
    public CommandStatus hasParameters(String cmd) {
        String[] parameters = cmd.trim().toLowerCase().split("\\s+");
        if (parameters[0].matches("ship")){
            if (parameters.length > 1){
                if (isTrue(parameters[1])) {
                    enabled = true;
                } else {
                    enabled = false;
                }
            } else {
                if (gof == 0){
                    enabled = !enabled;
                    gof++;
                } else {
                    gof = 0;
                }
            }
            return new CommandStatus("Rendering Ship: " + enabled, CommandStatus.Status.SUCCESS);
        }
        return new CommandStatus("Need more parameters", CommandStatus.Status.ERROR);
    }

    @Override
    public void execute() {
        Network.getPlayChannel().sendToServer(new MessageNavEnabled(enabled, application.getPos()));
    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("/display ship " + ColorCodes.WHITE + "<True, False, 0, 1>", CommandStatus.Status.HELP);
    }
}
