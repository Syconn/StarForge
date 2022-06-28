package mod.stf.syconn.client.rendering.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.yggdrasil.response.MinecraftTexturesPayload;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.ImageUtil;
import mod.stf.syconn.api.util.SkinGrabber;
import mod.stf.syconn.common.blockEntity.HoloBE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.MapRenderer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.resource.PathResourcePack;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class HoloPlayerRender extends PlayerRenderer {

    private HoloBE be;

    public HoloPlayerRender(EntityRendererProvider.Context p_174557_, HoloBE be) {
        super(p_174557_, be.isSlim());
        this.be = be;
    }

    @Override
    protected void renderNameTag(AbstractClientPlayer pEntity, Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer pEntity) {
        if (be.getSkin() == null){
            return DefaultPlayerSkin.getDefaultSkin();
        }

        //return Minecraft.getInstance().getTextureManager().register("holo", new DynamicTexture(ImageUtil.tint(this.be.getSkin(), DyeColor.BLUE)));
        return Minecraft.getInstance().getTextureManager().register("holo", new DynamicTexture(this.be.getSkin()));
    }
}
