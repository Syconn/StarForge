package mod.stf.syconn.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.screens.componet.ToggleButton;
import mod.stf.syconn.common.blockEntity.MapBe;
import mod.stf.syconn.network.Network;
import mod.stf.syconn.network.messages.c2s.C2SSetFastRender;
import mod.stf.syconn.network.messages.c2s.C2SSetName;
import mod.stf.syconn.network.messages.c2s.C2SSetRender;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.client.gui.widget.ExtendedButton;

import java.util.List;
import java.util.Map;

public class MapScreen extends Screen {

    private Boolean fastRender;

    private final int imageWidth = 224;
    private final int imageHeight = 139;

    private final BlockPos pos;

    private final Map<ChunkPos, BlockPos> positions;
    private final Map<ChunkPos, String> names;

    public MapScreen(BlockPos pos, Map<ChunkPos, BlockPos> positions, Map<ChunkPos, String> names, boolean fastRender) {
        super(Component.literal("Map Screen"));
        this.pos = pos;
        this.positions = positions;
        this.names = names;
        this.fastRender = fastRender;
    }

    protected void init() {
        super.init();
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        int y = -1; int x = 0;
        for (ChunkPos pos : positions.keySet()) {
            if (x % 4 == 0) { y++; x = 0; }
            addRenderableWidget(new ExtendedButton(relX + 4 + x * 55, relY + 5 + y * 25, 50, 20, Component.literal(names.get(pos)), b -> toggle(b, positions.get(pos))));
            x++;
        }
        addRenderableWidget(new ExtendedButton(relX + 73, relY + 115, 80, 20, Component.literal("FAST RENDER").withStyle(fastRender ? ChatFormatting.GREEN : ChatFormatting.RED), this::toggle));
    }

    private void toggle(Button button, BlockPos pos) {
        Network.getPlayChannel().sendToServer(new C2SSetRender(this.pos, pos));
        onClose();
    }

    private void toggle(Button button) {
        fastRender = !fastRender;
        Network.getPlayChannel().sendToServer(new C2SSetFastRender(this.pos, fastRender));
        button.setMessage(Component.literal("FAST RENDER").withStyle(fastRender ? ChatFormatting.GREEN : ChatFormatting.RED));
    }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void renderBackground(PoseStack pPoseStack) {
        super.renderBackground(pPoseStack);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, new ResourceLocation(Reference.MOD_ID, "textures/gui/map.png"));
        blit(pPoseStack, relX, relY, 0, 0, imageWidth, imageHeight);
    }
}
