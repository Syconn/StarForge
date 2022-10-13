package mod.stf.syconn.api.util.applications;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class BasicCommand<T extends BasicApplication> {

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

    public boolean run(String cmd){
        if (validCMD(cmd)){
            execute();
        }
        return cmd.split(" ")[0].matches(symbol + name);
    }

    private boolean validCMD(String cmd){
        if (cmd.length() >= symbol.length()+name.length()) {
            String start = cmd.substring(0, symbol.length()+name.length());
            if (start.matches(symbol + name)){
                status = hasParameters(cmd.substring(symbol.length()+name.length()));
                return hasParameters(cmd.substring(symbol.length()+name.length())).status().isSuccessful();
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
