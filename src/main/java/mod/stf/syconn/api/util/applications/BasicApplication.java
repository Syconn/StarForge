package mod.stf.syconn.api.util.applications;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.api.containers.ApplicationContainer;
import mod.stf.syconn.api.screens.ApplicationScreen;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;

import java.util.List;

public interface BasicApplication<T extends ApplicationContainer> {

    void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick, int width, int height);
    List<BasicCommand> getCMDS();
    void runCMD(String cmd);
    void init(ApplicationScreen screen, int width, int height);
    void tick();
    void renderLabels(PoseStack pPoseStack, String title, int width, int height);
    void renderBG(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY, int width, int height);
    void read(CompoundTag tag);
    void resize(ApplicationScreen screen, Minecraft pMinecraft, int pWidth, int pHeight);
    void keyPressed(int pKeyCode, int pScanCode, int pModifiers);
    CompoundTag save();
}
