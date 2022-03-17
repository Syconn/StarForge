package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.TabbedScreen;
import mod.stf.syconn.api.screens.componet.TabButton;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageClickTab;
import mod.stf.syconn.util.GuiHelper;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class HiltScreen extends TabbedScreen<HiltContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/containers/workstation_hilt.png");
    private HiltContainer inv;
    private BlockPos pos;
    private Inventory playerInv;



    public HiltScreen(HiltContainer container, Inventory inv, Component name) {
        super(container, inv, new TextComponent(""));
        this.playerInv = inv;
        this.inv = container;
        this.pos = container.getBlockEntity().getBlockPos();
        imageWidth = 198;
        imageHeight = 184;
    }

    @Override
    protected List<Tab> createTabs() {
        List<Tab> tabs = new ArrayList<>();
        for (LightsaberCrafter.States state : LightsaberCrafter.States.values()){
            tabs.add(new Tab(state.getId(), state.icon(), "tab." + ModBlocks.LIGHTSABER_CRAFTER.getId().getPath() + "." + state.getSerializedName()));
        }
        return tabs;
    }

    @Override
    protected int startingTabId() {
        return LightsaberCrafter.States.HILT.getId();
    }

    @Override
    public void tabbedClicked(Button button) {
        super.tabbedClicked(button);
        Network.getPlayChannel().sendToServer(new MessageClickTab(((TabButton)button).getId(), inv.getBlockEntity().getBlockPos()));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;

        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, GUI);
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        this.blit(pPoseStack, relX + 11, relY + 76, 198, 20, 18, 18);
        this.blit(pPoseStack, relX + 38, relY + 76, 198, 20, 18, 18);
        this.blit(pPoseStack, relX + 65, relY + 76, 198, 20, 18, 18);
        this.blit(pPoseStack, relX + 92, relY + 76, 198, 20, 18, 18);
        this.blit(pPoseStack, relX + 119, relY + 76, 198, 20, 18, 18);
        this.blit(pPoseStack, relX + 146, relY + 76, 198, 20, 18, 18);
    }
}
