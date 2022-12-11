package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageDisassemble;
import mod.stf.syconn.util.applications.NavigationApplication;

public class DisassembleCMD extends BasicCommand<NavigationApplication> {

    public DisassembleCMD(NavigationApplication application) {
        super("/", "disassemble", application);
    }

    @Override
    public CommandStatus hasParameters(String cmd) {
        return new CommandStatus("DONE", CommandStatus.Status.SUCCESS);
    }

    @Override
    public void execute() {
        Network.getPlayChannel().sendToServer(new MessageDisassemble(application.getPos()));
    }

    @Override
    public CommandStatus info() {
        return new CommandStatus("Disassemble Ship and give the blocks", CommandStatus.Status.HELP);
    }
}
