package mod.stf.syconn.util.applications;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.ApplicationComponent;
import mod.stf.syconn.api.screens.ApplicationScreen;
import mod.stf.syconn.api.screens.componet.TextScreen;
import mod.stf.syconn.api.util.ColorFormattedLine;
import mod.stf.syconn.api.util.ColorFormattedString;
import mod.stf.syconn.api.util.ColoredString;
import mod.stf.syconn.api.util.applications.BasicCommand;
import mod.stf.syconn.api.util.applications.CommandStatus;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.client.screen.componets.SubmittableTextBox;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.common.containers.NavContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class NavigationApplication extends ApplicationComponent<NavContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/navigation.png");
    private final Font font = Minecraft.getInstance().font;

    private SubmittableTextBox typer;
    private TextScreen textScreen;

    public final int imageWidth = 228;
    public final int imageHeight = 197;

    public NavigationApplication(BlockPos pos) {
        super(pos);
    }

    @Override
    public void init(ApplicationScreen screen, int width, int height) {
        int relX = (width - this.imageWidth) / 2;
        int relY = (height - this.imageHeight) / 2;
        createWidget(typer = new SubmittableTextBox(font, relX + 8, relY + 177, 212, 12, ""), screen);
        createWidget(textScreen = new TextScreen(relX + 9, relY + 18, 212, 173, 160,  relX + 208, relY + 17, "", typer), screen);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick, int width, int height) {
        int relX = (width - this.imageWidth) / 2;
        int relY = (height - this.imageHeight) / 2;
    }

    @Override
    public void renderLabels(PoseStack pPoseStack, String title, int width, int height) {
        font.draw(pPoseStack, title, 40, -9, 4210752);
    }

    @Override
    public void tick() {
        if (typer.hasSubmitted()){
            String submission = typer.getSubmission();
            textScreen.multiLineTyper.addLine(submission);
            runCMD(submission);
        }
    }

    @Override
    public void renderBG(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY, int width, int height) {
        int relX = (width - this.imageWidth) / 2;
        int relY = (height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void resize(ApplicationScreen screen, Minecraft pMinecraft, int pWidth, int pHeight) {
        String s = typer.getValue();
        List<ColorFormattedString> l = textScreen.multiLineTyper.getLines();
        screen.init(pMinecraft, pWidth, pHeight);
        typer.setValue(s);
        textScreen.multiLineTyper.setLines(l);
    }

    @Override
    public List<BasicCommand> getCMDS() {
        List<BasicCommand> cmd = new ArrayList<>();
        cmd.add(new MoveCMD(this));
        cmd.add(new LoadShipCMD(this));
        return cmd;
    }

    @Override
    public void handleCMDStatus(CommandStatus error) {
        textScreen.multiLineTyper.addLine(error.getStatus());
    }

    @Override
    public void read(CompoundTag tag) {}

    @Override
    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        return tag;
    }

    public Schematic getShip() {
        return ((NavBE) Minecraft.getInstance().level.getBlockEntity(pos)).getShip();
    }
}
