package mod.stf.syconn.util.applications.subcmd;

import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageShowShip;
import mod.stf.syconn.util.applications.cmd.DisplayCMD;

public class DisplayShipSCM extends BasicSCM<DisplayCMD> {

    private boolean enabled;
    private int gof;

    public DisplayShipSCM(DisplayCMD parentCMD) {
        super(parentCMD, "ship");
        if (level.getBlockEntity(parentCMD.getApplication().getPos()) instanceof NavBE)
            enabled = ((NavBE) level.getBlockEntity(parentCMD.getApplication().getPos())).showShip();
        gof = 0;
    }

    @Override
    protected CommandStatus hasParameters(String[] cmd) {
        if (cmd[0].matches(name)){
            if (cmd.length > 1){
                if (isTrue(cmd[1])) {
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
        Network.getPlayChannel().sendToServer(new MessageShowShip(enabled, parentCMD.getApplication().getPos()));
    }
}
