package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.TabbedScreen;
import mod.stf.syconn.api.screens.componet.TabButton;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageClickTab;
import mod.stf.syconn.network.messages.MessageCraftHilt;
import mod.stf.syconn.util.recipe.ModIngredient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.widget.ExtendedButton;

import java.util.ArrayList;
import java.util.List;

public class HiltScreen extends TabbedScreen<HiltContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/containers/workstation_hilt.png");
    private HiltContainer inv;
    private ExtendedButton craftButton;
    private BlockPos pos;
    private Inventory playerInv;

    public HiltScreen(HiltContainer container, Inventory inv, Component name) {
        super(container, inv, Component.empty());
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

    protected int startingTabId() {
        return LightsaberCrafter.States.HILT.getId();
    }

    public void tabbedClicked(Button button) {
        super.tabbedClicked(button);
        Network.getPlayChannel().sendToServer(new MessageClickTab(((TabButton)button).getId(), inv.getBlockEntity().getBlockPos()));
    }

    protected void containerTick() {

    }

    protected void init() {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        super.init();
        addRenderableWidget(new ExtendedButton(relX + 8, relY + 53, 20, 20, Component.literal("<"), pButton -> {
            if (inv.recipeNum == 0) {
                inv.selectedRecipe = inv.recipes.get(inv.recipes.size() - 1);
                inv.recipeNum = inv.recipes.size() - 1;
            } else {
                inv.recipeNum--;
                inv.selectedRecipe = inv.recipes.get(inv.recipeNum);
            }
        }));

        addRenderableWidget(new ExtendedButton(relX + 148, relY + 53, 20, 20, Component.literal(">"), pButton -> {
            if (inv.recipeNum == inv.recipes.size() - 1) {
                inv.selectedRecipe = inv.recipes.get(0);
                inv.recipeNum = 0;
            } else {
                inv.recipeNum++;
                inv.selectedRecipe = inv.recipes.get(inv.recipeNum);
            }
        }));

        addRenderableWidget(craftButton = new ExtendedButton(relX + 155, relY + 78, 36, 18, Component.literal("Craft"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageCraftHilt(inv.selectedRecipe));
        }));
    }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderGuiItem(inv.selectedRecipe.getOutput(), relX + 84, relY + 30, 40.0F, 40.0F, 32.0F, true, itemRenderer.getModel(inv.selectedRecipe.getOutput(), null, Minecraft.getInstance().player, 0));
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
        if (checkSlots(pPoseStack, pMouseX, pMouseY, relX, relY)){
            craftButton.visible = true;
        } else craftButton.visible = false;
    }

    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        drawCenteredString(pPoseStack, font, LightsaberHelper.getData(inv.selectedRecipe.getOutput()).getHandle().getName(), 88, 59, 0xFFFFFF);
    }

    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, GUI);
        blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    private boolean checkSlots(PoseStack pPoseStack, int pMouseX, int pMouseY, int relX, int relY){
        int checks = 0;

        for (int i = 0; i < inv.selectedRecipe.getInputs().length; i++){
            ModIngredient ingredient = inv.selectedRecipe.getInputs()[i];
            int amountNeeded = inv.selectedRecipe.getInputs()[i].amount();
            int playerAmount = 0;
            for (int pI = 0; pI < minecraft.player.getInventory().items.size(); pI++){
                if (minecraft.player.getInventory().items.get(pI).getItem() == ingredient.item()){
                    playerAmount += minecraft.player.getInventory().items.get(pI).getCount();
                }
            }
            amountNeeded -= playerAmount;

            RenderSystem.setShaderTexture(0, GUI);
            int minX = relX + 11 + (27 * i);
            int minY = relY + 76;
            if (amountNeeded <= 0) {
                blit(pPoseStack, minX, minY, 198, 20, 18, 18);
                itemRenderer.renderAndDecorateItem(pPoseStack, new ItemStack(ingredient.item()), relX + 12 + (27 * i), relY + 76);
                checks++;
            } else {
                blit(pPoseStack, minX, minY, 198, 38, 18, 18);
                itemRenderer.renderAndDecorateItem(pPoseStack, new ItemStack(ingredient.item()), relX + 12 + (27 * i), relY + 76);
                itemRenderer.renderGuiItemDecorations(pPoseStack, font, new ItemStack(ingredient.item(), amountNeeded), relX + 12 + (27 * i), relY + 76);
            }
//            if (pMouseX >= minX && pMouseX <= minX + 18 && pMouseY >= minY && pMouseY <= minY + 18) {
//                this.renderTooltip(pPoseStack, new ItemStack(ingredient.item()), pMouseX, pMouseY);
//            }
        }

        return checks >= inv.selectedRecipe.getInputs().length;
    }
}
