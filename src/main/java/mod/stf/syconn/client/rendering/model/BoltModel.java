package mod.stf.syconn.client.rendering.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.BlasterBoltEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BoltModel extends EntityModel<BlasterBoltEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "custom_model"), "main");
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart bb_main;

	public BoltModel(ModelPart root) {
		this.leftWing = root.getChild("leftWing");
		this.rightWing = root.getChild("rightWing");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -8.0F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.3F, 24.0F, 0.0F));

		PartDefinition leftWingBottum_r1 = leftWing.addOrReplaceChild("leftWingBottum_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.3538F, -6.6457F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leftWingTop_r1 = leftWing.addOrReplaceChild("leftWingTop_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3536F, -8.3536F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.7F, 16.5F, 0.0F));

		PartDefinition RightWingBottom_r1 = rightWing.addOrReplaceChild("RightWingBottom_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.3536F, 0.8536F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition RightWingTop_r1 = rightWing.addOrReplaceChild("RightWingTop_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -8.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3526F, -0.8536F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -8.0F, -8.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(BlasterBoltEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leftWing.render(poseStack, buffer, packedLight, packedOverlay);
		rightWing.render(poseStack, buffer, packedLight, packedOverlay);
		bb_main.render(poseStack, buffer, packedLight, packedOverlay);
	}
}