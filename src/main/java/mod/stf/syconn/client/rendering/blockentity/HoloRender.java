package mod.stf.syconn.client.rendering.blockentity;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import mod.stf.syconn.api.util.GameTestUtil;
import mod.stf.syconn.api.util.SkinGrabber;
import mod.stf.syconn.client.rendering.entity.HoloPlayerRender;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.MapRenderer;
import net.minecraft.client.gui.components.spectator.SpectatorGui;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.spectator.categories.SpectatorPage;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.RandomStringUtils;

import javax.json.JsonObject;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class HoloRender implements BlockEntityRenderer<HoloBE> {

    Minecraft mc = Minecraft.getInstance();

    public HoloRender(BlockEntityRendererProvider.Context pContext) {
    }

    @Override
    public void render(HoloBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if (mc.level.getBlockState(pBlockEntity.getBlockPos()).getBlock() == ModBlocks.HOLO_PROJECTOR.get() && pBlockEntity.getSkin() != null) {
            HoloPlayerRender playerRenderer = new HoloPlayerRender(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(), mc.getItemRenderer(), mc.getResourceManager(), mc.getEntityModels(), mc.font), pBlockEntity);
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
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(-90));
                pPoseStack.translate(0, 0, -1);
            } else if (direction == Direction.WEST){
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90));
                pPoseStack.translate(-1, 0, 0);
            } else if (direction == Direction.SOUTH){
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
                pPoseStack.translate(-1, 0, -1);
            }

            if (face == AttachFace.FLOOR){
                pPoseStack.translate(0.5, 0.3, 0.5);
            } else if (face == AttachFace.CEILING){
                pPoseStack.translate(0.5, -0.3, 0.5);
            } else if (face == AttachFace.WALL){
                pPoseStack.translate(0.5, 0.3, 0.5);
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
            }

            if (player == null) {
                player = GameTestUtil.makeMockPlayer(mc.level);
                player.setYHeadRot(0);
            }

            if (pBlockEntity.getMainHand() != null)
                player.setItemSlot(EquipmentSlot.MAINHAND, pBlockEntity.getMainHand());
            if (pBlockEntity.getOffHand() != null)
                player.setItemSlot(EquipmentSlot.OFFHAND, pBlockEntity.getOffHand());
            for (int i = 0; i < 4; i++) {
                if (pBlockEntity.getArmour()[i] != null)
                    player.setItemSlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i), pBlockEntity.getArmour()[i]);
            }

            pPoseStack.scale(0.5f, 0.5f, 0.5f);
            playerRenderer.render(player, 0, pPartialTick, pPoseStack, pBufferSource, pPackedLight);
            pPoseStack.popPose();
        }
    }
}