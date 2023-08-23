package mod.stf.syconn.api.util.applications;

import mod.stf.syconn.util.applications.cmd.CMDHelper;
import mod.stf.syconn.util.applications.subcmd.BasicSCM;
import net.minecraft.client.Minecraft;
import net.minecraft.server.commands.TeleportCommand;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicCommand<T extends BasicApplication> extends CMDHelper {

    private final String symbol;
    private final String name;
    private CommandStatus status;
    protected Level level = Minecraft.getInstance().level;
    protected T application;

    public BasicCommand(String symbol, String name, T application) {
        this.symbol = symbol;
        this.name = name;
        this.application = application;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public List<String> getSudo(){
        List<String> sudo = new ArrayList<>();
        sudo.add(name);
        return sudo;
    }

    public T getApplication() {
        return application;
    }

    protected List<BasicSCM> getSubCMDS() {
        return null;
    }
    protected boolean hasSubCMDS(){
        return getSubCMDS() != null;
    }

    public boolean run(String cmd){
        for (String name : getSudo()) {
            String comb = symbol+name;
            if (cmd.length() >= comb.length() && cmd.substring(0, comb.length()).matches(comb)){ //VALID COMMAND
                if (hasSubCMDS()){
                    handleSubParameters(comb, cmd);
                } else {
                    String[] substring = cmd.substring(symbol.length() + name.length()).trim().split("\\s+");
                    if (substring[0].matches("help")){
                        status = info();
                        return true;
                    }
                    status = hasParameters(substring);
                    if (status.isSuccessful()){
                        execute();
                    }
                }
                return true;
            }
        }
        return false;
    }

    protected void handleSubParameters(String start, String t){
        status = new CommandStatus("No Valid Sub CMDS", CommandStatus.Status.ERROR);
        String text = t.substring(start.length());
        for (BasicSCM cmd : getSubCMDS()) {
            if (cmd.validCMD(text).isSuccessful()) {
                status = cmd.validCMD(text);
                cmd.execute();
            }
        }
    }

    public CommandStatus error(){
        return status;
    }

    public abstract CommandStatus hasParameters(String[] cmd);
    public abstract void execute();
    public abstract CommandStatus info();
}
