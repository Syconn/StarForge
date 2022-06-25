package mod.stf.syconn.client.rendering.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HoloPlayerRender extends PlayerRenderer {

    String player;

    public HoloPlayerRender(EntityRendererProvider.Context p_174557_, boolean p_174558_, String player) {
        super(p_174557_, p_174558_);
        this.player = player;
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer pEntity) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/trooper_commander.png");
    }

    @Override
    protected void renderNameTag(AbstractClientPlayer pEntity, Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

    }
}
