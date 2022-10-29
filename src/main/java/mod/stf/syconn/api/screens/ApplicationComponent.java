package mod.stf.syconn.api.screens;

import mod.stf.syconn.api.containers.ApplicationContainer;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public abstract class ApplicationComponent<T extends ApplicationContainer> extends GuiComponent implements BasicApplication<T> {

    protected final Font font = Minecraft.getInstance().font;
    protected final BlockPos pos;
    protected final List<AbstractWidget> widgets = new ArrayList<>();

    public ApplicationComponent(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void runCMD(String cmd) {
        if (cmd.matches("/help")) {
            for (BasicCommand command : getCMDS()) {
                handleCMDStatus(command.info());
            }
            return;
        } else {
            for (BasicCommand command : getCMDS()) {
                if (command.run(cmd)) {
                    handleCMDStatus(command.error());
                    return;
                }
            }
        }
        handleCMDStatus(new CommandStatus("COMMAND UNRECOGNIZED USE /Help", CommandStatus.Status.UNKNOWN));
    }

    public abstract void handleCMDStatus(CommandStatus error);

    @Override
    public void keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        for (AbstractWidget widget : widgets){
            widget.keyPressed(pKeyCode, pScanCode, pModifiers);
        }
    }

    public <T extends AbstractWidget & Widget & NarratableEntry> void createWidget(T widget, ApplicationScreen screen){
        screen.createWidget(widget);
        widgets.add(widget);
    }

    public BlockPos getPos() {
        return pos;
    }
}
