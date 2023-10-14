package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.TabbedScreen;
import mod.stf.syconn.api.screens.componet.TabButton;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.api.util.data.IngredientDisplay;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.common.recipes.ModIngredient;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.c2s.C2SClickTab;
import mod.stf.syconn.network.messages.c2s.C2SCraftHilt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;

import java.util.ArrayList;
import java.util.List;

public class HiltScreen extends TabbedScreen<HiltContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/workstation_hilt.png");
    private final HiltContainer inv;
    private ExtendedButton craftButton;

    private final Inventory playerInv;
    private List<IngredientDisplay> ingredientDisplays;

    public HiltScreen(HiltContainer container, Inventory inv, Component name) {
        super(container, inv, Component.empty());
        this.playerInv = inv;
        this.inv = container;
        imageWidth = 198;
        imageHeight = 184;
    }

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
        Network.getPlayChannel().sendToServer(new C2SClickTab(((TabButton)button).getId(), inv.getBlockEntity().getBlockPos()));
    }

    protected void containerTick() {
        this.ingredientDisplays.forEach(IngredientDisplay::tick);
    }

    protected void init() {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        createIngredientDisplays();
        super.init();
        addRenderableWidget(new ExtendedButton(relX + 8, relY + 53, 20, 20, Component.literal("<"), pButton -> {
            if (inv.recipeNum == 0) {
                inv.selectedRecipe = inv.recipes.get(inv.recipes.size() - 1);
                inv.recipeNum = inv.recipes.size() - 1;
            } else {
                inv.recipeNum--;
                inv.selectedRecipe = inv.recipes.get(inv.recipeNum);
            }
            createIngredientDisplays();
        }));

        addRenderableWidget(new ExtendedButton(relX + 148, relY + 53, 20, 20, Component.literal(">"), pButton -> {
            if (inv.recipeNum == inv.recipes.size() - 1) {
                inv.selectedRecipe = inv.recipes.get(0);
                inv.recipeNum = 0;
            } else {
                inv.recipeNum++;
                inv.selectedRecipe = inv.recipes.get(inv.recipeNum);
            }
            createIngredientDisplays();
        }));

        addRenderableWidget(craftButton = new ExtendedButton(relX + 155, relY + 78, 36, 18, Component.literal("Craft"), pButton -> {
            Network.getPlayChannel().sendToServer(new C2SCraftHilt(inv.selectedRecipe.id()));
        }));
    }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderGuiItem(inv.selectedRecipe.item(), relX + 84, relY + 30, 40.0F, 40.0F, 32.0F, true, itemRenderer.getModel(inv.selectedRecipe.item(), null, Minecraft.getInstance().player, 0));
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
        if (checkSlots(pPoseStack, relX, relY)){
            craftButton.visible = true;
        } else craftButton.visible = false;
    }

    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        drawCenteredString(pPoseStack, font, LightsaberHelper.getData(inv.selectedRecipe.item()).getHandle().getName(), 88, 59, 0xFFFFFF);
    }

    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, GUI);
        blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    private boolean checkSlots(PoseStack pPoseStack, int relX, int relY){
        int checks = 0;

        for (int i = 0; i < ingredientDisplays.size(); i++){
            ModIngredient ingredient = (ModIngredient) ingredientDisplays.get(i).getIngredient();
            int amountNeeded = ingredient.getCount();
            int playerAmount = 0;
            for (int pI = 0; pI < playerInv.items.size(); pI++){
                if (ingredient.test(playerInv.items.get(pI))){
                    playerAmount += playerInv.items.get(pI).getCount();
                }
            }
            amountNeeded -= playerAmount;

            RenderSystem.setShaderTexture(0, GUI);
            int minX = relX + 11 + (27 * i);
            int minY = relY + 76;
            if (amountNeeded <= 0) {
                blit(pPoseStack, minX, minY, 198, 20, 18, 18);
                ingredientDisplays.get(i).display(pPoseStack, 0, relX + 12 + (27 * i), relY + 76);
                checks++;
            } else {
                blit(pPoseStack, minX, minY, 198, 38, 18, 18);
                ingredientDisplays.get(i).display(pPoseStack, amountNeeded, relX + 12 + (27 * i), relY + 76);
            }
        }

        return checks >= inv.selectedRecipe.materials().size();
    }

    public void createIngredientDisplays() {
        ingredientDisplays = new ArrayList<>();
        inv.selectedRecipe.materials().forEach(ingredient -> ingredientDisplays.add(new IngredientDisplay(ingredient)));
    }
}
