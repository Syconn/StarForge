package mod.stf.syconn.util.applications.subcmd;

import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.util.applications.cmd.CMDHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

public abstract class BasicSCM<T extends BasicCommand> extends CMDHelper {

    protected final T parentCMD;
    protected final String name;
    protected Level level = Minecraft.getInstance().level;

    public BasicSCM(T parentCMD, String name) {
        this.parentCMD = parentCMD;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public T getParentCMD() {
        return parentCMD;
    }

    public CommandStatus validCMD(String cmd){
        String[] par = cmd.trim().toLowerCase().split("\\s+");
        return hasParameters(par);
    }

    protected abstract CommandStatus hasParameters(String[] cmd);
    public abstract void execute();
}
