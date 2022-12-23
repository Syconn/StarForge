package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.containers.slots.ToggleSlotHandler;
import mod.stf.syconn.api.screens.TabbedScreen;
import mod.stf.syconn.api.screens.componet.TabButton;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.block.LightsaberCrafter;
import mod.stf.syconn.common.blockEntity.CrafterBE;
import mod.stf.syconn.common.containers.ColorContainer;
import mod.stf.syconn.common.containers.HiltContainer;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.init.ModRecipes;
import mod.stf.syconn.item.lightsaber.LColor;
import mod.stf.syconn.item.lightsaber.LightsaberHelper;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageClickTab;
import mod.stf.syconn.network.messages.MessageCraftHilt;
import mod.stf.syconn.util.recipe.ModIngredient;
import mod.stf.syconn.util.recipe.Recipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.StonecutterScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.widget.ExtendedButton;

import java.util.ArrayList;
import java.util.List;

public class HiltScreen extends TabbedScreen<HiltContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/containers/workstation_hilt.png");
    private ExtendedButton craftButton;
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
    }

    @Override
    protected void containerTick() {

    }

    @Override
    protected void init() {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        super.init();
        addRenderableWidget(new ExtendedButton(relX + 8, relY + 53, 20, 20, new TextComponent("<"), pButton -> {
            if (inv.recipeNum == 0) {
                inv.selectedRecipe = inv.recipes.get(inv.recipes.size() - 1);
                inv.recipeNum = inv.recipes.size() - 1;
            } else {
                inv.recipeNum--;
                inv.selectedRecipe = inv.recipes.get(inv.recipeNum);
            }
        }));

        addRenderableWidget(new ExtendedButton(relX + 148, relY + 53, 20, 20, new TextComponent(">"), pButton -> {
            if (inv.recipeNum == inv.recipes.size() - 1) {
                inv.selectedRecipe = inv.recipes.get(0);
                inv.recipeNum = 0;
            } else {
                inv.recipeNum++;
                inv.selectedRecipe = inv.recipes.get(inv.recipeNum);
            }
        }));

        addRenderableWidget(craftButton = new ExtendedButton(relX + 155, relY + 78, 36, 18, new TextComponent("Craft"), pButton -> {
            Network.getPlayChannel().sendToServer(new MessageCraftHilt(inv.selectedRecipe));
        }));
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        checkSlots(pPoseStack, relX, relY);
        renderGuiItem(inv.selectedRecipe.getOutput(), relX + 84, relY + 30, 40.0F, 40.0F, 32.0F, true, itemRenderer.getModel(inv.selectedRecipe.getOutput(), null, Minecraft.getInstance().player, 0));
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);

        if (checkSlots(pPoseStack, relX, relY)){
            craftButton.visible = true;
        } else craftButton.visible = false;
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        drawCenteredString(pPoseStack, font, LightsaberHelper.getData(inv.selectedRecipe.getOutput()).getHandle().getName(), 88, 59, 0xFFFFFF);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, GUI);
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    private boolean checkSlots(PoseStack pPoseStack, int relX, int relY){
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
            if (amountNeeded <= 0) {
                this.blit(pPoseStack, relX + 11 + (27 * i), relY + 76, 198, 20, 18, 18);
                itemRenderer.renderAndDecorateItem(new ItemStack(ingredient.item()), relX + 12 + (27 * i), relY + 76);
                checks++;
            } else {
                this.blit(pPoseStack, relX + 11 + (27 * i), relY + 76, 198, 38, 18, 18);
                itemRenderer.renderAndDecorateItem(new ItemStack(ingredient.item()), relX + 12 + (27 * i), relY + 76);
                itemRenderer.renderGuiItemDecorations(font, new ItemStack(ingredient.item(), amountNeeded), relX + 12 + (27 * i), relY + 76);
            }
        }

        return checks >= inv.selectedRecipe.getInputs().length;
    }
}
