package mod.stf.syconn.api.util.applications;

import mod.stf.syconn.util.applications.cmd.CMDHelper;
import mod.stf.syconn.util.applications.subcmd.BasicSCM;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class BasicCommand<T extends BasicApplication> extends CMDHelper {

    private final String symbol;
    private final String name;
    private final String start;
    private CommandStatus status;
    protected Level level = Minecraft.getInstance().level;
    protected T application;

    public BasicCommand(String symbol, String name, T application) {
        this.symbol = symbol;
        this.name = name;
        this.start = symbol + name;
        this.application = application;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public T getApplication() {
        return application;
    }

    protected abstract List<BasicSCM> getSubCMDS();
    protected boolean hasSubCMDS(){
        return getSubCMDS() != null;
    }

    public boolean run(String cmd){
        if (!hasSubCMDS()) {
            if (validCMD(cmd)) {
                execute();
            }
        } else {
            if (handleSubParameters(cmd)) {
                return cmd.split(" ")[0].matches(symbol + name);
            }
        }
        return cmd.split(" ")[0].matches(symbol + name);
    }

    private boolean validCMD(String cmd){
        if (cmd.length() >= start.length()) {
            String begin = cmd.substring(0, start.length());
            if (begin.toLowerCase().matches(start.toLowerCase())){
                status = hasParameters(cmd.substring(start.length()));
                return hasParameters(cmd.substring(start.length())).isSuccessful();
            }
        }
        return false;
    }

    protected boolean handleSubParameters(String t){
        status = new CommandStatus("No Valid Sub CMDS", CommandStatus.Status.ERROR);
        if (t.length() > start.length()) {
            String text = t.substring(start.length());
            for (BasicSCM cmd : getSubCMDS()) {
                if (cmd.validCMD(text).isSuccessful()) {
                    status = cmd.validCMD(text);
                    cmd.execute();
                    return true;
                }
            }
        }
        return false;
    }

    public CommandStatus error(){
        return status;
    }

    public abstract CommandStatus hasParameters(String cmd);
    public abstract void execute();
    public abstract CommandStatus info();
}
