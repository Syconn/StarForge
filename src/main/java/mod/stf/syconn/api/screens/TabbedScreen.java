package mod.stf.syconn.api.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.containers.ContainerMenu;
import mod.stf.syconn.api.screens.componet.TabButton;
import mod.stf.syconn.api.util.Tab;
import mod.stf.syconn.api.util.TabEnum;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.MessageClickTab;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;

public abstract class TabbedScreen<T extends ContainerMenu> extends BasicContainerScreen<T> {

    private final List<Tab> tabs;
    private TabButton[] tabButtons;
    protected T inv;

    public TabbedScreen(T pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.tabs = createTabs();
        this.inv = pMenu;
    }

    @Override
    protected void init() {
        super.init();
        tabButtons = new TabButton[tabs.size()];
        int size = tabs.size() - 1;

        for (int i = 0; i < tabs.size(); i++){
            if (i == 0) //LEFT
                addRenderableWidget(tabButtons[i] = new TabButton(leftPos, topPos, TabButton.State.LEFT, tabs.get(i), this::tabbedClicked));
            else if (i == size) //RIGHT
                addRenderableWidget(tabButtons[i] = new TabButton(leftPos + imageWidth - 28, topPos, TabButton.State.RIGHT, tabs.get(i), this::tabbedClicked));
            //MIDDLE
            else addRenderableWidget(tabButtons[i] = new TabButton(calcUnit(i) + leftPos, topPos, TabButton.State.MIDDLE, tabs.get(i), this::tabbedClicked));
        }

        tabButtons[startingTabId() - 1].setSelected(true);
    }

    private int calcUnit(int i){
        int size = tabs.size();
        return (imageWidth / size + 12) * i + (4 * i);
    }

    public void tabbedClicked(Button button){
        if (!((TabButton)button).isSelected()){
            for (int i = 0; i < tabs.size(); i++){
                if (i != ((TabButton)button).getId() - 1) {
                    tabButtons[i].setSelected(false);
                }
            } ((TabButton)button).setSelected(true);
        }
        Network.getPlayChannel().sendToServer(new MessageClickTab(((TabButton)button).getId(), inv.getBlockEntity().getBlockPos()));
    }

    protected abstract List<Tab> createTabs();
    protected abstract int startingTabId();
}