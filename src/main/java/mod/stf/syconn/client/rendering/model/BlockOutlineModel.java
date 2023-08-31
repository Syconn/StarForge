package mod.stf.syconn.client.rendering.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BlockOutlineModel extends EntityModel {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "block_overlay"), "main");
    private final ModelPart bb_main;

    public BlockOutlineModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(49, 0).addBox(-8.1F, -16.1F, -8.1F, 1.0F, 16.2F, 16.2F, new CubeDeformation(0.0F))
                .texOffs(0, 43).addBox(-7.1F, -16.1F, -8.1F, 14.2F, 1.1F, 16.2F, new CubeDeformation(0.0F))
                .texOffs(52, 73).addBox(7.1F, -16.1F, -8.1F, 1.0F, 16.2F, 16.2F, new CubeDeformation(0.0F))
                .texOffs(65, 45).addBox(-7.1F, -1.0F, -8.1F, 14.2F, 1.1F, 16.2F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-7.1F, -15.0F, -8.1F, 14.2F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 80).addBox(-7.1F, -15.0F, 7.1F, 14.2F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        bb_main.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
