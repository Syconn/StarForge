package mod.stf.syconn.client.rendering.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.yggdrasil.response.MinecraftTexturesPayload;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.ImageUtil;
import mod.stf.syconn.api.util.SkinGrabber;
import mod.stf.syconn.client.rendering.layer.CustomCapeLayer;
import mod.stf.syconn.common.blockEntity.HoloBE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.MapRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.resource.PathResourcePack;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class HoloPlayerRender extends LivingEntityRenderer<Player, PlayerModel<Player>> {

    private HoloBE be;

    public HoloPlayerRender(EntityRendererProvider.Context pContext, HoloBE be) {
        super(pContext, new PlayerModel<>(pContext.bakeLayer(be.isSlim() ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), be.isSlim()), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel(pContext.bakeLayer(be.isSlim() ? ModelLayers.PLAYER_SLIM_INNER_ARMOR : ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(pContext.bakeLayer(be.isSlim() ? ModelLayers.PLAYER_SLIM_OUTER_ARMOR : ModelLayers.PLAYER_OUTER_ARMOR))));
        this.addLayer(new PlayerItemInHandLayer<>(this));
        this.addLayer(new ArrowLayer<>(pContext, this));
        this.addLayer(new CustomCapeLayer(this));
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet()));
        this.addLayer(new ElytraLayer<>(this, pContext.getModelSet()));
        this.addLayer(new ParrotOnShoulderLayer<>(this, pContext.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer<>(this, pContext.getModelSet()));
        this.addLayer(new BeeStingerLayer<>(this));
        this.be = be;
    }

    @Override
    protected void renderNameTag(Player pEntity, Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

    }

    @Override
    public void render(Player pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        setModelProperties(pEntity);
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Player pEntity) {
        if (be.getSkin() == null){
            return DefaultPlayerSkin.getDefaultSkin();
        }

        //return Minecraft.getInstance().getTextureManager().register("holo", new DynamicTexture(ImageUtil.tintV2(this.be.getSkin(), DyeColor.LIGHT_BLUE)));
        return Minecraft.getInstance().getTextureManager().register("holo", new DynamicTexture(this.be.getSkin().getImageFromPixels()));
    }

    private void setModelProperties(Player pClientPlayer) {
        PlayerModel<Player> playermodel = this.getModel();
        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.rightPants.visible = true;
            playermodel.leftPants.visible = true;
            playermodel.leftSleeve.visible = true;
            playermodel.rightSleeve.visible = true;
            playermodel.jacket.visible = true;
            playermodel.hat.visible = true;
            playermodel.crouching = pClientPlayer.isCrouching();
            HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(pClientPlayer, InteractionHand.MAIN_HAND);
            HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(pClientPlayer, InteractionHand.OFF_HAND);
            if (humanoidmodel$armpose.isTwoHanded()) {
                humanoidmodel$armpose1 = pClientPlayer.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
            }

            if (pClientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArmPose = humanoidmodel$armpose;
                playermodel.leftArmPose = humanoidmodel$armpose1;
            } else {
                playermodel.rightArmPose = humanoidmodel$armpose1;
                playermodel.leftArmPose = humanoidmodel$armpose;
            }
        }
    }

    private static HumanoidModel.ArmPose getArmPose(Player p_117795_, InteractionHand p_117796_) {
        ItemStack itemstack = p_117795_.getItemInHand(p_117796_);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (p_117795_.getUsedItemHand() == p_117796_ && p_117795_.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (useanim == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }

                if (useanim == UseAnim.CROSSBOW && p_117796_ == p_117795_.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }

                if (useanim == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }
            } else if (!p_117795_.swinging && itemstack.is(Items.CROSSBOW) && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }

            return HumanoidModel.ArmPose.ITEM;
        }
    }
}
