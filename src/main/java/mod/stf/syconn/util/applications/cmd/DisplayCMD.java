package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.ColorCodes;
import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.util.applications.NavigationApplication;
import mod.stf.syconn.util.applications.subcmd.BasicSCM;
import mod.stf.syconn.util.applications.subcmd.DisplayPathSCM;
import mod.stf.syconn.util.applications.subcmd.DisplayShipSCM;

import java.util.ArrayList;
import java.util.List;

public class DisplayCMD extends BasicCommand<NavigationApplication> {

    public DisplayCMD(NavigationApplication application) {
        super("/", "display", application);

    }

    @Override
    protected List<BasicSCM> getSubCMDS() {
        List<BasicSCM> sub = new ArrayList<>();
        sub.add(new DisplayShipSCM(this));
        sub.add(new DisplayPathSCM(this));
        return sub;
    }

    @Override
    protected boolean hasSubCMDS() {
        return true;
    }

    @Override
    public CommandStatus hasParameters(String[] cmd) {
        return CommandStatus.ERROR();
    }

    @Override
    public void execute() {

    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("/display ship " + ColorCodes.WHITE + "<True, False, 0, 1>", CommandStatus.Status.HELP);
    }
}
