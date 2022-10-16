package mod.stf.syconn.api.util.applications;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class BasicCommand<T extends BasicApplication> {

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

    public boolean run(String cmd){
        if (validCMD(cmd)){
            execute();
        }
        return cmd.split(" ")[0].matches(symbol + name);
    }

    private boolean validCMD(String cmd){
        if (cmd.length() >= start.length()) {
            String begin = cmd.substring(0, start.length());
            if (begin.toLowerCase().matches(start.toLowerCase())){
                status = hasParameters(cmd.substring(start.length()));
                return hasParameters(cmd.substring(start.length())).status().isSuccessful();
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
