package mod.stf.syconn.client.rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mod.stf.syconn.api.util.GameTestUtil;
import mod.stf.syconn.client.rendering.entity.HoloPlayerRender;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static mod.stf.syconn.common.containers.HoloContainer.SLOT_IDS;

@OnlyIn(Dist.CLIENT)
public class HoloRender implements BlockEntityRenderer<HoloBE> {

    private final Minecraft mc = Minecraft.getInstance();
    public HoloRender(BlockEntityRendererProvider.Context pContext) { }

    public void render(HoloBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if (mc.level.getBlockState(pBlockEntity.getBlockPos()).getBlock() == ModBlocks.HOLO_PROJECTOR.get() && pBlockEntity.getSkin() != null) {
            HoloPlayerRender playerRenderer = new HoloPlayerRender(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(), mc.getItemRenderer(), mc.getBlockRenderer(), mc.gameRenderer.itemInHandRenderer, mc.getResourceManager(), mc.getEntityModels(), mc.font), pBlockEntity);
            Player player = null;

            if (pBlockEntity.getMode().equals("Username")){
                for (PlayerInfo playerInfo : mc.getConnection().getOnlinePlayers()){
                    if (playerInfo.getProfile().getName().equals(pBlockEntity.getUrlOrName())){
                        player = mc.level.getPlayerByUUID(playerInfo.getProfile().getId());
                    }
                }
            }

            pPoseStack.pushPose();
            Direction direction = mc.level.getBlockState(pBlockEntity.getBlockPos()).getValue(BlockStateProperties.HORIZONTAL_FACING);
            AttachFace face = mc.level.getBlockState(pBlockEntity.getBlockPos()).getValue(BlockStateProperties.ATTACH_FACE);

            if (direction == Direction.EAST){
                pPoseStack.mulPose(Axis.YP.rotationDegrees(-90));
                pPoseStack.translate(0, 0, -1);
            } else if (direction == Direction.WEST){
                pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
                pPoseStack.translate(-1, 0, 0);
            } else if (direction == Direction.SOUTH){
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
                pPoseStack.translate(-1, 0, -1);
            }

            if (face == AttachFace.FLOOR){
                pPoseStack.translate(0.5, 0.3, 0.5);
            } else if (face == AttachFace.CEILING){
                pPoseStack.translate(0.5, -0.3, 0.5);
            } else if (face == AttachFace.WALL){
                pPoseStack.translate(0.5, 0.3, 0.5);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
            }

            if (player == null) {
                player = GameTestUtil.makeMockPlayer(mc.level);
                player.setYHeadRot(0);
            }

            for (int i = 0; i < pBlockEntity.getInventory().getSlots(); i++) {
                player.setItemSlot(SLOT_IDS[i], pBlockEntity.getInventory().getStackInSlot(i));
            }

            pPoseStack.scale(0.5f, 0.5f, 0.5f);
            playerRenderer.render(player, 0, pPartialTick, pPoseStack, pBufferSource, LightTexture.FULL_BLOCK);
            pPoseStack.popPose();
        }
    }
}