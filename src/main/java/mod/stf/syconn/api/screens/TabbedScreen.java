package mod.stf.syconn.api.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.componet.TabButton;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.api.util.TabEnum;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;

public abstract class TabbedScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    private final List<Tab> tabs;

    public TabbedScreen(T pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.tabs = createTabs();
    }

    @Override
    protected void init() {
        super.init();
        int size = tabs.size() - 1;

        for (int i = 0; i < tabs.size(); i++){
            if (i == 0) //LEFT
                addRenderableWidget(new TabButton(leftPos, topPos, TabButton.State.LEFT, tabs.get(i), this::tabbedClicked));
            else if (i == size) //RIGHT
                addRenderableWidget(new TabButton(calcUnit(i) + leftPos, topPos, TabButton.State.RIGHT, tabs.get(i), this::tabbedClicked));
            //MIDDLE
            else addRenderableWidget(new TabButton(calcUnit(i) + leftPos, topPos, TabButton.State.MIDDLE, tabs.get(i), this::tabbedClicked));
        }
    }

    private int calcUnit(int i){
        int size = tabs.size();
        return (imageWidth / size + 12) * i + (4 * i);
    }

    public abstract void tabbedClicked(Button button);
    protected abstract List<Tab> createTabs();

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {

    }
}
