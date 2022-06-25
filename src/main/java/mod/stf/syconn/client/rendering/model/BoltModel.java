package mod.stf.syconn.client.rendering.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.BlasterBolt;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class BoltModel extends EntityModel<BlasterBolt> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "bolt"), "main");
	private final ModelPart body;

	public BoltModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 30.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition leftBottom_r1 = body.addOrReplaceChild("leftBottom_r1", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4128F, -7.5F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition capTop_r1 = body.addOrReplaceChild("capTop_r1", CubeListBuilder.create().texOffs(0, 0).addBox(18.5F, -0.5F, -1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -0.5F, -1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0592F, -7.1464F, -10.5F, 0.0F, -1.5708F, 2.3562F));

		PartDefinition rightBottom_r1 = body.addOrReplaceChild("rightBottom_r1", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(BlasterBolt entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}