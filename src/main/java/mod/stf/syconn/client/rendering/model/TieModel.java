package mod.stf.syconn.client.rendering.model;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.TieFighter;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class TieModel extends EntityModel<TieFighter> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart Wing;
	private final ModelPart Wing2;
	private final ModelPart MiddleCenter;

	public TieModel(ModelPart root) {
		this.Wing = root.getChild("Wing");
		this.Wing2 = root.getChild("Wing2");
		this.MiddleCenter = root.getChild("MiddleCenter");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Wing = partdefinition.addOrReplaceChild("Wing", CubeListBuilder.create(), PartPose.offset(64.0F, 20.0F, 12.5F));

		PartDefinition outerRing = Wing.addOrReplaceChild("outerRing", CubeListBuilder.create().texOffs(56, 0).addBox(-28.0F, -48.0F, -32.5F, 4.0F, 2.0F, 26.0F, new CubeDeformation(0.0F))
				.texOffs(30, 44).addBox(-28.0F, -2.0F, -32.7F, 4.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outerRing_r1 = outerRing.addOrReplaceChild("outerRing_r1", CubeListBuilder.create().texOffs(80, 105).addBox(-2.0F, -22.7F, 44.0F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(92, 105).addBox(-2.0F, -10.7F, 6.2F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -12.2919F, -43.1103F, 0.3054F, 0.0F, 0.0F));

		PartDefinition outerRing_r2 = outerRing.addOrReplaceChild("outerRing_r2", CubeListBuilder.create().texOffs(104, 105).addBox(-2.0F, -20.7F, -41.0F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 106).addBox(-2.0F, -8.0F, -3.0F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition Body = Wing.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-28.0F, -38.0F, -13.0F, 2.0F, 44.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -8.0F, -19.5F));

		PartDefinition bod_r1 = Body.addOrReplaceChild("bod_r1", CubeListBuilder.create().texOffs(18, 89).addBox(-1.0F, -8.0F, -9.0F, 2.0F, 25.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(80, 72).addBox(-1.0F, -20.7F, -39.0F, 2.0F, 25.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.0F, -8.0F, 19.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition bod_r2 = Body.addOrReplaceChild("bod_r2", CubeListBuilder.create().texOffs(34, 89).addBox(-1.0F, -10.7F, 8.2F, 2.0F, 25.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 89).addBox(-1.0F, -22.7F, 37.0F, 2.0F, 25.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.0F, -4.2919F, -23.6103F, 0.3054F, 0.0F, 0.0F));

		PartDefinition MiddleCircle = Wing.addOrReplaceChild("MiddleCircle", CubeListBuilder.create().texOffs(98, 79).addBox(-31.0F, -17.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -8.0F, -19.5F));

		PartDefinition cube_r1 = MiddleCircle.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 19).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 21).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = MiddleCircle.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 0).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.4835F, 0.0F, 0.0F));

		PartDefinition cube_r3 = MiddleCircle.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 4).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.3963F, 0.0F, 0.0F));

		PartDefinition cube_r4 = MiddleCircle.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 8).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r5 = MiddleCircle.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(56, 12).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r6 = MiddleCircle.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(56, 28).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition cube_r7 = MiddleCircle.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(56, 32).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r8 = MiddleCircle.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(56, 36).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r9 = MiddleCircle.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 40).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r10 = MiddleCircle.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(64, 66).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r11 = MiddleCircle.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(26, 72).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r12 = MiddleCircle.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(26, 76).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r13 = MiddleCircle.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(26, 80).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r14 = MiddleCircle.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(90, 14).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r15 = MiddleCircle.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(90, 18).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r16 = MiddleCircle.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(90, 22).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r17 = MiddleCircle.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(90, 42).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r18 = MiddleCircle.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(90, 61).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition suports = Wing.addOrReplaceChild("suports", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -19.5F));

		PartDefinition struct = suports.addOrReplaceChild("struct", CubeListBuilder.create().texOffs(86, 92).addBox(-29.0F, -24.4F, 7.3F, 4.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(10, 19).addBox(-29.0F, -24.9F, 7.3F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition struct2 = suports.addOrReplaceChild("struct2", CubeListBuilder.create().texOffs(91, 66).addBox(-28.5F, 15.5F, -4.0F, 4.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 70).addBox(-28.5F, 15.0F, -4.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.9F, -11.4F, 3.1416F, 0.0F, 0.0F));

		PartDefinition struct3 = suports.addOrReplaceChild("struct3", CubeListBuilder.create().texOffs(0, 70).addBox(-28.5F, 7.0115F, 4.1272F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(50, 92).addBox(-28.5F, 6.5115F, 2.6272F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -22.9F, -8.4F, 2.0595F, 0.0F, 0.0F));

		PartDefinition struct4 = suports.addOrReplaceChild("struct4", CubeListBuilder.create().texOffs(64, 47).addBox(-28.5F, -8.2569F, 3.9939F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(90, 47).addBox(-28.5F, -8.7569F, 2.4939F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -22.7F, 8.5F, 1.0647F, 0.0F, 0.0F));

		PartDefinition struct5 = suports.addOrReplaceChild("struct5", CubeListBuilder.create().texOffs(64, 28).addBox(-28.5F, 7.257F, -23.9939F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(90, 28).addBox(-28.5F, 6.757F, -25.4939F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 7.3F, -8.5F, -2.0769F, 0.0F, 0.0F));

		PartDefinition struct6 = suports.addOrReplaceChild("struct6", CubeListBuilder.create().texOffs(30, 0).addBox(-28.5F, -8.0115F, -24.1272F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(90, 0).addBox(-28.5F, -8.5115F, -25.6272F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 7.1F, 8.6F, -1.0821F, 0.0F, 0.0F));

		PartDefinition middle = Wing.addOrReplaceChild("middle", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -19.5F));

		PartDefinition left = middle.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 12).addBox(-30.0F, -29.0F, 4.0F, 6.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(64, 56).addBox(-30.0F, -28.0F, 5.0F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(68, 72).addBox(-30.0F, -27.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 82).addBox(-30.0F, -26.0F, 7.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right = middle.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition right_r1 = right.addOrReplaceChild("right_r1", CubeListBuilder.create().texOffs(0, 76).addBox(-2.5F, -2.0F, 1.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 10).addBox(-2.5F, -3.0F, 0.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 0).addBox(-2.5F, -4.0F, -1.0F, 6.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -5.0F, -2.0F, 6.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.5F, -24.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition mid = middle.addOrReplaceChild("mid", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition mid_r1 = mid.addOrReplaceChild("mid_r1", CubeListBuilder.create().texOffs(44, 72).addBox(-2.5F, -10.0F, -6.0F, 6.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(64, 47).addBox(-2.5F, -10.0F, -7.0F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(68, 19).addBox(-2.5F, -9.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.5F, -24.0F, -4.0F, -1.5708F, 0.0F, 3.1416F));

		PartDefinition Wing2 = partdefinition.addOrReplaceChild("Wing2", CubeListBuilder.create(), PartPose.offset(-10.0F, 20.4F, 11.5F));

		PartDefinition outerRing2 = Wing2.addOrReplaceChild("outerRing2", CubeListBuilder.create().texOffs(56, 0).addBox(-28.0F, -48.0F, -32.5F, 4.0F, 2.0F, 26.0F, new CubeDeformation(0.0F))
				.texOffs(30, 44).addBox(-28.0F, -2.0F, -32.7F, 4.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outerRing_r3 = outerRing2.addOrReplaceChild("outerRing_r3", CubeListBuilder.create().texOffs(80, 105).addBox(-2.0F, -22.7F, 44.0F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(92, 105).addBox(-2.0F, -10.7F, 6.2F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -12.2919F, -43.1103F, 0.3054F, 0.0F, 0.0F));

		PartDefinition outerRing_r4 = outerRing2.addOrReplaceChild("outerRing_r4", CubeListBuilder.create().texOffs(104, 105).addBox(-2.0F, -20.7F, -41.0F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(50, 106).addBox(-2.0F, -8.0F, -3.0F, 4.0F, 25.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition Body2 = Wing2.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(0, 0).addBox(-28.0F, -38.0F, -13.0F, 2.0F, 44.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -8.0F, -19.5F));

		PartDefinition bod_r3 = Body2.addOrReplaceChild("bod_r3", CubeListBuilder.create().texOffs(18, 89).addBox(-1.0F, -8.0F, -9.0F, 2.0F, 25.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(80, 72).addBox(-1.0F, -20.7F, -39.0F, 2.0F, 25.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.0F, -8.0F, 19.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition bod_r4 = Body2.addOrReplaceChild("bod_r4", CubeListBuilder.create().texOffs(34, 89).addBox(-1.0F, -10.7F, 8.2F, 2.0F, 25.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 89).addBox(-1.0F, -22.7F, 37.0F, 2.0F, 25.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.0F, -4.2919F, -23.6103F, 0.3054F, 0.0F, 0.0F));

		PartDefinition MiddleCircle2 = Wing2.addOrReplaceChild("MiddleCircle2", CubeListBuilder.create().texOffs(98, 79).addBox(-31.0F, -17.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -8.0F, -19.5F));

		PartDefinition cube_r19 = MiddleCircle2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(30, 19).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 21).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r20 = MiddleCircle2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(56, 0).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.4835F, 0.0F, 0.0F));

		PartDefinition cube_r21 = MiddleCircle2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(56, 4).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.3963F, 0.0F, 0.0F));

		PartDefinition cube_r22 = MiddleCircle2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(56, 8).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r23 = MiddleCircle2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(56, 12).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r24 = MiddleCircle2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(56, 28).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition cube_r25 = MiddleCircle2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(56, 32).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r26 = MiddleCircle2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(56, 36).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r27 = MiddleCircle2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(56, 40).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r28 = MiddleCircle2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(64, 66).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r29 = MiddleCircle2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(26, 72).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r30 = MiddleCircle2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(26, 76).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r31 = MiddleCircle2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(26, 80).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r32 = MiddleCircle2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(90, 14).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r33 = MiddleCircle2.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(90, 18).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r34 = MiddleCircle2.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(90, 22).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r35 = MiddleCircle2.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(90, 42).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r36 = MiddleCircle2.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(90, 61).addBox(-5.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -16.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition suports2 = Wing2.addOrReplaceChild("suports2", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -19.5F));

		PartDefinition struct7 = suports2.addOrReplaceChild("struct7", CubeListBuilder.create().texOffs(86, 92).addBox(-29.0F, -24.4F, 7.3F, 4.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(10, 19).addBox(-29.0F, -24.9F, 7.3F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition struct8 = suports2.addOrReplaceChild("struct8", CubeListBuilder.create().texOffs(91, 66).addBox(-28.5F, 15.5F, -4.0F, 4.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 70).addBox(-28.5F, 15.0F, -4.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.9F, -11.4F, 3.1416F, 0.0F, 0.0F));

		PartDefinition struct9 = suports2.addOrReplaceChild("struct9", CubeListBuilder.create().texOffs(0, 70).addBox(-28.5F, 7.0115F, 4.1272F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(50, 92).addBox(-28.5F, 6.5115F, 2.6272F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -22.9F, -8.4F, 2.0595F, 0.0F, 0.0F));

		PartDefinition struct10 = suports2.addOrReplaceChild("struct10", CubeListBuilder.create().texOffs(64, 47).addBox(-28.5F, -8.2569F, 3.9939F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(90, 47).addBox(-28.5F, -8.7569F, 2.4939F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -22.7F, 8.5F, 1.0647F, 0.0F, 0.0F));

		PartDefinition struct11 = suports2.addOrReplaceChild("struct11", CubeListBuilder.create().texOffs(64, 28).addBox(-28.5F, 7.2569F, -23.9939F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(90, 28).addBox(-28.5F, 6.7569F, -25.4939F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 7.3F, -8.5F, -2.0769F, 0.0F, 0.0F));

		PartDefinition struct12 = suports2.addOrReplaceChild("struct12", CubeListBuilder.create().texOffs(30, 0).addBox(-28.5F, -8.0115F, -24.1272F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(90, 0).addBox(-28.5F, -8.5115F, -25.6272F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 7.1F, 8.6F, -1.0821F, 0.0F, 0.0F));

		PartDefinition middle2 = Wing2.addOrReplaceChild("middle2", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -19.5F));

		PartDefinition left2 = middle2.addOrReplaceChild("left2", CubeListBuilder.create().texOffs(0, 12).addBox(-30.0F, -29.0F, 4.0F, 6.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(64, 56).addBox(-30.0F, -28.0F, 5.0F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(68, 72).addBox(-30.0F, -27.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 82).addBox(-30.0F, -26.0F, 7.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right2 = middle2.addOrReplaceChild("right2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition right_r2 = right2.addOrReplaceChild("right_r2", CubeListBuilder.create().texOffs(0, 76).addBox(-2.5F, -2.0F, 1.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 10).addBox(-2.5F, -3.0F, 0.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 0).addBox(-2.5F, -4.0F, -1.0F, 6.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -5.0F, -2.0F, 6.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.5F, -24.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition mid2 = middle2.addOrReplaceChild("mid2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition mid_r2 = mid2.addOrReplaceChild("mid_r2", CubeListBuilder.create().texOffs(44, 72).addBox(-2.5F, -10.0F, -6.0F, 6.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(64, 47).addBox(-2.5F, -10.0F, -7.0F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(68, 19).addBox(-2.5F, -9.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.5F, -24.0F, -4.0F, -1.5708F, 0.0F, 3.1416F));

		PartDefinition MiddleCenter = partdefinition.addOrReplaceChild("MiddleCenter", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Front = MiddleCenter.addOrReplaceChild("Front", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition window = Front.addOrReplaceChild("window", CubeListBuilder.create(), PartPose.offset(0.0F, -38.0F, 7.0F));

		PartDefinition top = window.addOrReplaceChild("top", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.6F, 10.3F, 0.5F));

		PartDefinition top_r1 = top.addOrReplaceChild("top_r1", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8937F));

		PartDefinition top_r2 = top.addOrReplaceChild("top_r2", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 3.1241F));

		PartDefinition top_r3 = top.addOrReplaceChild("top_r3", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1154F));

		PartDefinition top_r4 = top.addOrReplaceChild("top_r4", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.0281F));

		PartDefinition top_r5 = top.addOrReplaceChild("top_r5", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.9409F));

		PartDefinition top_r6 = top.addOrReplaceChild("top_r6", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8536F));

		PartDefinition top_r7 = top.addOrReplaceChild("top_r7", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.7663F));

		PartDefinition top_r8 = top.addOrReplaceChild("top_r8", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.6791F));

		PartDefinition top_r9 = top.addOrReplaceChild("top_r9", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.5918F));

		PartDefinition top_r10 = top.addOrReplaceChild("top_r10", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.5045F));

		PartDefinition top_r11 = top.addOrReplaceChild("top_r11", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.4173F));

		PartDefinition top_r12 = top.addOrReplaceChild("top_r12", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.33F));

		PartDefinition top_r13 = top.addOrReplaceChild("top_r13", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.2427F));

		PartDefinition top_r14 = top.addOrReplaceChild("top_r14", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.1555F));

		PartDefinition top_r15 = top.addOrReplaceChild("top_r15", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0682F));

		PartDefinition top_r16 = top.addOrReplaceChild("top_r16", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.9809F));

		PartDefinition top_r17 = top.addOrReplaceChild("top_r17", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8064F));

		PartDefinition top_r18 = top.addOrReplaceChild("top_r18", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.7191F));

		PartDefinition top_r19 = top.addOrReplaceChild("top_r19", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition top_r20 = top.addOrReplaceChild("top_r20", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.4835F));

		PartDefinition top_r21 = top.addOrReplaceChild("top_r21", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.3963F));

		PartDefinition top_r22 = top.addOrReplaceChild("top_r22", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

		PartDefinition top_r23 = top.addOrReplaceChild("top_r23", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition top_r24 = top.addOrReplaceChild("top_r24", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

		PartDefinition top_r25 = top.addOrReplaceChild("top_r25", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition top_r26 = top.addOrReplaceChild("top_r26", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition top_r27 = top.addOrReplaceChild("top_r27", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition top_r28 = top.addOrReplaceChild("top_r28", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition top_r29 = top.addOrReplaceChild("top_r29", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition top_r30 = top.addOrReplaceChild("top_r30", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition top_r31 = top.addOrReplaceChild("top_r31", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition top_r32 = top.addOrReplaceChild("top_r32", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition top_r33 = top.addOrReplaceChild("top_r33", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition top_r34 = top.addOrReplaceChild("top_r34", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition top_r35 = top.addOrReplaceChild("top_r35", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition top_r36 = top.addOrReplaceChild("top_r36", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition top_r37 = top.addOrReplaceChild("top_r37", CubeListBuilder.create().texOffs(17, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.6319F));

		PartDefinition bottom = window.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, 10.3F, 0.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bottom_r1 = bottom.addOrReplaceChild("bottom_r1", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8937F));

		PartDefinition bottom_r2 = bottom.addOrReplaceChild("bottom_r2", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 3.1241F));

		PartDefinition bottom_r3 = bottom.addOrReplaceChild("bottom_r3", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1154F));

		PartDefinition bottom_r4 = bottom.addOrReplaceChild("bottom_r4", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.0281F));

		PartDefinition bottom_r5 = bottom.addOrReplaceChild("bottom_r5", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.9409F));

		PartDefinition bottom_r6 = bottom.addOrReplaceChild("bottom_r6", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8536F));

		PartDefinition bottom_r7 = bottom.addOrReplaceChild("bottom_r7", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.7663F));

		PartDefinition bottom_r8 = bottom.addOrReplaceChild("bottom_r8", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.6791F));

		PartDefinition bottom_r9 = bottom.addOrReplaceChild("bottom_r9", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.5918F));

		PartDefinition bottom_r10 = bottom.addOrReplaceChild("bottom_r10", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.5045F));

		PartDefinition bottom_r11 = bottom.addOrReplaceChild("bottom_r11", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.4173F));

		PartDefinition bottom_r12 = bottom.addOrReplaceChild("bottom_r12", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.33F));

		PartDefinition bottom_r13 = bottom.addOrReplaceChild("bottom_r13", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.2427F));

		PartDefinition bottom_r14 = bottom.addOrReplaceChild("bottom_r14", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.1555F));

		PartDefinition bottom_r15 = bottom.addOrReplaceChild("bottom_r15", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0682F));

		PartDefinition bottom_r16 = bottom.addOrReplaceChild("bottom_r16", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.9809F));

		PartDefinition bottom_r17 = bottom.addOrReplaceChild("bottom_r17", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8064F));

		PartDefinition bottom_r18 = bottom.addOrReplaceChild("bottom_r18", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.7191F));

		PartDefinition bottom_r19 = bottom.addOrReplaceChild("bottom_r19", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition bottom_r20 = bottom.addOrReplaceChild("bottom_r20", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.4835F));

		PartDefinition bottom_r21 = bottom.addOrReplaceChild("bottom_r21", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.3963F));

		PartDefinition bottom_r22 = bottom.addOrReplaceChild("bottom_r22", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

		PartDefinition bottom_r23 = bottom.addOrReplaceChild("bottom_r23", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition bottom_r24 = bottom.addOrReplaceChild("bottom_r24", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

		PartDefinition bottom_r25 = bottom.addOrReplaceChild("bottom_r25", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition bottom_r26 = bottom.addOrReplaceChild("bottom_r26", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition bottom_r27 = bottom.addOrReplaceChild("bottom_r27", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition bottom_r28 = bottom.addOrReplaceChild("bottom_r28", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition bottom_r29 = bottom.addOrReplaceChild("bottom_r29", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition bottom_r30 = bottom.addOrReplaceChild("bottom_r30", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition bottom_r31 = bottom.addOrReplaceChild("bottom_r31", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition bottom_r32 = bottom.addOrReplaceChild("bottom_r32", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition bottom_r33 = bottom.addOrReplaceChild("bottom_r33", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition bottom_r34 = bottom.addOrReplaceChild("bottom_r34", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition bottom_r35 = bottom.addOrReplaceChild("bottom_r35", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition bottom_r36 = bottom.addOrReplaceChild("bottom_r36", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bottom_r37 = bottom.addOrReplaceChild("bottom_r37", CubeListBuilder.create().texOffs(11, 104).addBox(-0.6F, -10.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.6319F));

		PartDefinition centerLeft = window.addOrReplaceChild("centerLeft", CubeListBuilder.create().texOffs(15, 108).addBox(-3.0729F, -4.0141F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0729F, 10.0141F, 4.5F));

		PartDefinition centerLeft_r1 = centerLeft.addOrReplaceChild("centerLeft_r1", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.9671F));

		PartDefinition centerLeft_r2 = centerLeft.addOrReplaceChild("centerLeft_r2", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.8798F));

		PartDefinition centerLeft_r3 = centerLeft.addOrReplaceChild("centerLeft_r3", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.7053F));

		PartDefinition centerLeft_r4 = centerLeft.addOrReplaceChild("centerLeft_r4", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.618F));

		PartDefinition centerLeft_r5 = centerLeft.addOrReplaceChild("centerLeft_r5", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.5307F));

		PartDefinition centerLeft_r6 = centerLeft.addOrReplaceChild("centerLeft_r6", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.4435F));

		PartDefinition centerLeft_r7 = centerLeft.addOrReplaceChild("centerLeft_r7", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition centerLeft_r8 = centerLeft.addOrReplaceChild("centerLeft_r8", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.2689F));

		PartDefinition centerLeft_r9 = centerLeft.addOrReplaceChild("centerLeft_r9", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.2253F));

		PartDefinition centerLeft_r10 = centerLeft.addOrReplaceChild("centerLeft_r10", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.1817F));

		PartDefinition centerLeft_r11 = centerLeft.addOrReplaceChild("centerLeft_r11", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.0944F));

		PartDefinition centerLeft_r12 = centerLeft.addOrReplaceChild("centerLeft_r12", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 2.0071F));

		PartDefinition centerLeft_r13 = centerLeft.addOrReplaceChild("centerLeft_r13", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.9199F));

		PartDefinition centerLeft_r14 = centerLeft.addOrReplaceChild("centerLeft_r14", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.8326F));

		PartDefinition centerLeft_r15 = centerLeft.addOrReplaceChild("centerLeft_r15", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.7453F));

		PartDefinition centerLeft_r16 = centerLeft.addOrReplaceChild("centerLeft_r16", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.6581F));

		PartDefinition centerLeft_r17 = centerLeft.addOrReplaceChild("centerLeft_r17", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition centerLeft_r18 = centerLeft.addOrReplaceChild("centerLeft_r18", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.4835F));

		PartDefinition centerLeft_r19 = centerLeft.addOrReplaceChild("centerLeft_r19", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.3963F));

		PartDefinition centerLeft_r20 = centerLeft.addOrReplaceChild("centerLeft_r20", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.309F));

		PartDefinition centerLeft_r21 = centerLeft.addOrReplaceChild("centerLeft_r21", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.2217F));

		PartDefinition centerLeft_r22 = centerLeft.addOrReplaceChild("centerLeft_r22", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition centerLeft_r23 = centerLeft.addOrReplaceChild("centerLeft_r23", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 1.0472F));

		PartDefinition centerLeft_r24 = centerLeft.addOrReplaceChild("centerLeft_r24", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition centerLeft_r25 = centerLeft.addOrReplaceChild("centerLeft_r25", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition centerLeft_r26 = centerLeft.addOrReplaceChild("centerLeft_r26", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition centerLeft_r27 = centerLeft.addOrReplaceChild("centerLeft_r27", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition centerLeft_r28 = centerLeft.addOrReplaceChild("centerLeft_r28", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition centerLeft_r29 = centerLeft.addOrReplaceChild("centerLeft_r29", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition centerLeft_r30 = centerLeft.addOrReplaceChild("centerLeft_r30", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition centerLeft_r31 = centerLeft.addOrReplaceChild("centerLeft_r31", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition centerLeft_r32 = centerLeft.addOrReplaceChild("centerLeft_r32", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition centerLeft_r33 = centerLeft.addOrReplaceChild("centerLeft_r33", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition centerLeft_r34 = centerLeft.addOrReplaceChild("centerLeft_r34", CubeListBuilder.create().texOffs(15, 108).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5229F, 0.2859F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition centerRight = window.addOrReplaceChild("centerRight", CubeListBuilder.create().texOffs(20, 51).addBox(0.0F, 6.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 5.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition CenterRight_r1 = centerRight.addOrReplaceChild("CenterRight_r1", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition CenterRight_r2 = centerRight.addOrReplaceChild("CenterRight_r2", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.9671F));

		PartDefinition CenterRight_r3 = centerRight.addOrReplaceChild("CenterRight_r3", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.7925F));

		PartDefinition CenterRight_r4 = centerRight.addOrReplaceChild("CenterRight_r4", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.7053F));

		PartDefinition CenterRight_r5 = centerRight.addOrReplaceChild("CenterRight_r5", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.618F));

		PartDefinition CenterRight_r6 = centerRight.addOrReplaceChild("CenterRight_r6", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.5307F));

		PartDefinition CenterRight_r7 = centerRight.addOrReplaceChild("CenterRight_r7", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.4435F));

		PartDefinition CenterRight_r8 = centerRight.addOrReplaceChild("CenterRight_r8", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.3562F));

		PartDefinition CenterRight_r9 = centerRight.addOrReplaceChild("CenterRight_r9", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.2689F));

		PartDefinition CenterRight_r10 = centerRight.addOrReplaceChild("CenterRight_r10", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.2253F));

		PartDefinition CenterRight_r11 = centerRight.addOrReplaceChild("CenterRight_r11", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.1817F));

		PartDefinition CenterRight_r12 = centerRight.addOrReplaceChild("CenterRight_r12", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.0944F));

		PartDefinition CenterRight_r13 = centerRight.addOrReplaceChild("CenterRight_r13", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 2.0071F));

		PartDefinition CenterRight_r14 = centerRight.addOrReplaceChild("CenterRight_r14", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.9199F));

		PartDefinition CenterRight_r15 = centerRight.addOrReplaceChild("CenterRight_r15", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.8326F));

		PartDefinition CenterRight_r16 = centerRight.addOrReplaceChild("CenterRight_r16", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.7453F));

		PartDefinition CenterRight_r17 = centerRight.addOrReplaceChild("CenterRight_r17", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.6581F));

		PartDefinition CenterRight_r18 = centerRight.addOrReplaceChild("CenterRight_r18", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition CenterRight_r19 = centerRight.addOrReplaceChild("CenterRight_r19", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.4835F));

		PartDefinition CenterRight_r20 = centerRight.addOrReplaceChild("CenterRight_r20", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.3963F));

		PartDefinition CenterRight_r21 = centerRight.addOrReplaceChild("CenterRight_r21", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.309F));

		PartDefinition CenterRight_r22 = centerRight.addOrReplaceChild("CenterRight_r22", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.2217F));

		PartDefinition CenterRight_r23 = centerRight.addOrReplaceChild("CenterRight_r23", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.1345F));

		PartDefinition CenterRight_r24 = centerRight.addOrReplaceChild("CenterRight_r24", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 1.0472F));

		PartDefinition CenterRight_r25 = centerRight.addOrReplaceChild("CenterRight_r25", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.9599F));

		PartDefinition CenterRight_r26 = centerRight.addOrReplaceChild("CenterRight_r26", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.8727F));

		PartDefinition CenterRight_r27 = centerRight.addOrReplaceChild("CenterRight_r27", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition CenterRight_r28 = centerRight.addOrReplaceChild("CenterRight_r28", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.6981F));

		PartDefinition CenterRight_r29 = centerRight.addOrReplaceChild("CenterRight_r29", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.6109F));

		PartDefinition CenterRight_r30 = centerRight.addOrReplaceChild("CenterRight_r30", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.5236F));

		PartDefinition CenterRight_r31 = centerRight.addOrReplaceChild("CenterRight_r31", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.4363F));

		PartDefinition CenterRight_r32 = centerRight.addOrReplaceChild("CenterRight_r32", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.3491F));

		PartDefinition CenterRight_r33 = centerRight.addOrReplaceChild("CenterRight_r33", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.2618F));

		PartDefinition CenterRight_r34 = centerRight.addOrReplaceChild("CenterRight_r34", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.1745F));

		PartDefinition CenterRight_r35 = centerRight.addOrReplaceChild("CenterRight_r35", CubeListBuilder.create().texOffs(20, 51).addBox(-0.55F, -4.3F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 10.3F, 0.5F, 0.0F, 0.0F, 0.0873F));

		PartDefinition beam = window.addOrReplaceChild("beam", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition beam_r1 = beam.addOrReplaceChild("beam_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-16.0F, -0.5F, 10.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 10.2F, 0.5F, 0.0F, -0.5934F, 0.0F));

		PartDefinition beam_r2 = beam.addOrReplaceChild("beam_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-7.0F, -0.5F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 10.2F, 0.5F, 0.0F, 0.5934F, 0.0F));

		PartDefinition beam_r3 = beam.addOrReplaceChild("beam_r3", CubeListBuilder.create().texOffs(31, 43).addBox(-0.8492F, -8.0582F, 4.9F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5154F, 10.5F, 0.5F, 0.5934F, 0.0F, 0.7418F));

		PartDefinition beam_r4 = beam.addOrReplaceChild("beam_r4", CubeListBuilder.create().texOffs(31, 43).addBox(-0.8492F, 0.9418F, 4.9F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5154F, 10.5F, 0.5F, -0.5934F, 0.0F, 0.7418F));

		PartDefinition beam_r5 = beam.addOrReplaceChild("beam_r5", CubeListBuilder.create().texOffs(31, 43).addBox(-0.479F, 8.3354F, 9.9F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4846F, 3.5F, 0.5F, -0.5934F, 0.0F, -0.7418F));

		PartDefinition beam_r6 = beam.addOrReplaceChild("beam_r6", CubeListBuilder.create().texOffs(31, 43).addBox(-0.479F, -0.6646F, -0.1F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4846F, 3.5F, 0.5F, 0.5934F, 0.0F, -0.7418F));

		PartDefinition beam_r7 = beam.addOrReplaceChild("beam_r7", CubeListBuilder.create().texOffs(31, 43).addBox(-0.5154F, -7.5F, -0.7F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5154F, 20.5F, 0.5F, -0.5934F, 0.0F, 0.0F));

		PartDefinition beam_r8 = beam.addOrReplaceChild("beam_r8", CubeListBuilder.create().texOffs(31, 43).addBox(-0.5154F, 0.2F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5154F, 0.5F, 0.5F, 0.5934F, 0.0F, 0.0F));

		PartDefinition gun = Front.addOrReplaceChild("gun", CubeListBuilder.create().texOffs(0, 0).addBox(3.4F, -15.4F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.9F, -14.9F, 4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.1F, -15.4F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.6F, -14.9F, 4.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition left4 = Front.addOrReplaceChild("left4", CubeListBuilder.create().texOffs(0, 0).addBox(19.5F, -32.7F, -18.0F, 15.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -32.7F, -1.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.5F, -32.7F, -2.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.5F, -32.7F, -3.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.5F, -32.7F, -4.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.5F, -32.7F, -5.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(16.5F, -32.7F, -6.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(17.5F, -32.7F, -7.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(18.5F, -32.7F, -8.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -32.7F, 0.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -33.7F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -33.7F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -33.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.5F, -33.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.5F, -33.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.5F, -33.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.5F, -33.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(16.5F, -33.7F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -34.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -34.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.5F, -34.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.5F, -34.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.5F, -34.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -34.7F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -35.7F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -35.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -35.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.5F, -35.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -35.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -34.7F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -35.7F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -36.7F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -37.7F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -38.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -38.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -38.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -38.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -38.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -38.7F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.5F, -38.7F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.5F, -38.7F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.5F, -39.7F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -39.7F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -39.7F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -39.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -39.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -39.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -39.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, -39.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -40.7F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -40.7F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -40.7F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -40.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -40.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -40.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, -40.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -41.7F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -41.7F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -41.7F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -41.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -41.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, -41.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -42.7F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -42.7F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -42.7F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -42.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, -42.7F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -43.7F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -43.7F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -43.7F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, -43.7F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, -38.7F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -37.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.5F, -37.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.5F, -36.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -36.7F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.5F, -36.7F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.5F, -36.7F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, -37.7F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.5F, -38.7F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -38.7F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -38.7F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(17.5F, -33.7F, -19.0F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.5F, -34.7F, -20.0F, 13.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.5F, -35.7F, -21.0F, 12.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.5F, -36.7F, -22.0F, 11.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.5F, -37.7F, -23.0F, 11.0F, 1.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.4941F, -36.7F, -28.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.4941F, -35.7F, -28.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.4941F, -34.7F, -28.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.4941F, -33.7F, -28.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.4941F, -34.7F, -29.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.4941F, -35.7F, -29.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.4941F, -33.7F, -29.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.4941F, -34.7F, -30.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.4941F, -33.7F, -30.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.4941F, -33.7F, -31.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.4941F, -32.7F, -31.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.4941F, -32.7F, -30.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.4941F, -32.7F, -28.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.4941F, -32.7F, -29.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.4941F, -30.7F, -28.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.4941F, -30.7F, -29.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.4941F, -30.7F, -30.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.4941F, -30.7F, -31.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition left_r1 = left4.addOrReplaceChild("left_r1", CubeListBuilder.create().texOffs(0, 0).addBox(1.8529F, -8.5039F, -7.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8529F, -8.5039F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -7.5039F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -6.5039F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -5.5039F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -4.5039F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -3.5039F, -7.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -5.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -5.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -5.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -5.5039F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -6.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -6.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -6.5039F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -8.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -11.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -12.5039F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -12.5039F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -11.5039F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -10.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -9.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -8.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -8.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -8.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -8.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -8.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -8.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -8.5039F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -13.5039F, -2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -12.5039F, -3.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -11.5039F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -10.5039F, -5.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -9.5039F, -6.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -10.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -12.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -9.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -13.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -13.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -13.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -13.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -12.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -12.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -12.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -12.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -11.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -11.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -11.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -11.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -9.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -9.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -9.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -9.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -10.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -10.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -10.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -10.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -10.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -9.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -11.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -9.5039F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -8.5039F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -7.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -7.5039F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -7.5039F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -6.5039F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -5.5039F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -4.5039F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -4.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -4.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -4.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -4.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -4.5039F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -3.5039F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.8529F, -3.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.8529F, -3.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -3.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -3.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -3.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -3.5039F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6471F, -25.2039F, -19.5F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r2 = left4.addOrReplaceChild("left_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.1471F, -13.4961F, 6.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -13.4961F, 6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8529F, -13.4961F, 6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -12.4961F, 6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.8471F, -11.4961F, 8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -11.4961F, 8.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.8471F, -12.4961F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -12.4961F, 7.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -10.4961F, 8.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -11.4961F, 7.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -5.4961F, 8.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.1471F, -5.4961F, 10.5F, 13.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -5.4961F, 9.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -5.4961F, 8.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -5.4961F, 7.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -7.4961F, 8.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -7.4961F, 7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -8.4961F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -8.4961F, 8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -9.4961F, 8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -9.4961F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -10.4961F, 7.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -11.4961F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -11.4961F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -11.4961F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -12.4961F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -12.4961F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -11.4961F, 6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -13.4961F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -13.4961F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -13.4961F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -13.4961F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -13.4961F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -13.4961F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -13.4961F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -13.4961F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -13.4961F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -12.4961F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -11.4961F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -10.4961F, 6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -9.4961F, 6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -10.4961F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -10.4961F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -10.4961F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -10.4961F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -10.4961F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -9.4961F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -9.4961F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -9.4961F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -9.4961F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -9.4961F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -9.4961F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.8529F, -8.4961F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.8529F, -8.4961F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -8.4961F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -8.4961F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -8.4961F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -8.4961F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -8.4961F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -8.4961F, 6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -7.4961F, 6.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(17.8529F, -7.4961F, -1.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(16.8529F, -7.4961F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.8529F, -7.4961F, 0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.8529F, -7.4961F, 1.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -7.4961F, 2.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -7.4961F, 3.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -7.4961F, 4.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -7.4961F, 5.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6471F, -25.2039F, -19.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_r3 = left4.addOrReplaceChild("left_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.35F, -10.75F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.65F, -10.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.65F, -8.75F, -1.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.65F, -9.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.35F, -8.75F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.35F, -7.75F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.35F, -7.75F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.35F, -6.75F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.35F, -6.75F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.35F, -5.75F, -1.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.35F, -5.75F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8559F, -28.45F, -27.5F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r4 = left4.addOrReplaceChild("left_r4", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -5.5F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, -5.5F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -6.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -7.5F, 0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -9.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4941F, -28.2F, -28.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r5 = left4.addOrReplaceChild("left_r5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -6.5F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -7.5F, 0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -9.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4941F, -29.2F, -29.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r6 = left4.addOrReplaceChild("left_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-4.65F, -10.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8559F, -31.45F, -30.5F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r7 = left4.addOrReplaceChild("left_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -9.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4941F, -30.2F, -30.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r8 = left4.addOrReplaceChild("left_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.65F, -8.75F, -1.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.35F, -7.75F, -1.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.65F, -10.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8559F, -30.45F, -29.5F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r9 = left4.addOrReplaceChild("left_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.65F, -8.75F, -1.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.35F, -7.75F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.35F, -6.75F, -1.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.65F, -10.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8559F, -29.45F, -28.5F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r10 = left4.addOrReplaceChild("left_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -9.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -7.5F, 0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -8.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -7.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -6.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, -5.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, -5.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, -6.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.0F, -5.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.0F, -4.5F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.0F, -4.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4941F, -27.2F, -27.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r11 = left4.addOrReplaceChild("left_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.8471F, -11.4961F, 8.5F, 1.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -9.4961F, 8.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -10.4961F, 8.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -11.4961F, 8.5F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6471F, -23.2039F, -21.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_r12 = left4.addOrReplaceChild("left_r12", CubeListBuilder.create().texOffs(0, 0).addBox(5.8529F, -8.4961F, 8.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -9.4961F, 8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -10.4961F, 8.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.8471F, -11.4961F, 8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -11.4961F, 8.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6471F, -24.2039F, -20.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_r13 = left4.addOrReplaceChild("left_r13", CubeListBuilder.create().texOffs(0, 0).addBox(12.8F, 0.0F, -3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8F, -1.0F, -3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8F, 0.0F, -4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8F, 0.0F, -5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8F, 0.0F, -6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8F, -1.0F, -5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8F, -1.0F, -4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8F, -2.0F, -3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8F, -3.0F, -3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8F, -2.0F, -5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8F, -2.0F, -4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8F, -3.0F, -6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8F, -3.0F, -4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8F, -3.0F, -5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8F, -2.0F, -6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8F, -2.0F, -7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8F, -1.0F, -8.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8F, -1.0F, -7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8F, -1.0F, -6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8F, 0.0F, -7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8F, 0.0F, -8.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8F, 0.0F, -9.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8F, 1.0F, -10.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8F, 1.0F, -9.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8F, 1.0F, -8.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8F, 1.0F, -7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8F, 1.0F, -6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8F, 1.0F, -5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8F, 1.0F, -4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8F, 2.0F, -4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8F, 2.0F, -5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8F, 1.0F, -3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.8F, 2.0F, -3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8F, 2.0F, -6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8F, 2.0F, -8.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8F, 2.0F, -7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8F, 2.0F, -10.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8F, 2.0F, -9.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8F, 2.0F, -11.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6471F, -14.7F, -10.1529F, 0.0F, 1.5708F, 3.1416F));

		PartDefinition left_r14 = left4.addOrReplaceChild("left_r14", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -17.5F, -0.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.0F, -17.5F, -0.5F, 11.0F, 1.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-11.0F, -16.5F, 0.5F, 11.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.0F, -15.5F, 1.5F, 12.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -14.5F, 2.5F, 13.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.0F, -13.5F, 3.5F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.5F, -35.2F, -3.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r15 = left4.addOrReplaceChild("left_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -18.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -18.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -17.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, -16.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -15.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, -14.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.5F, -13.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -35.2F, 0.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r16 = left4.addOrReplaceChild("left_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 1.5F, 4.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, 0.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -0.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -1.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, 1.5F, 6.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -0.5F, 4.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, 0.5F, 5.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, 1.5F, 5.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, 0.5F, 4.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -1.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -1.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -0.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -0.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, 0.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, 1.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -2.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -2.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -2.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -1.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -2.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -1.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -0.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 1.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 0.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, 1.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, 0.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.0F, 1.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.0F, 1.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.0F, 0.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, -0.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, 1.5F, 6.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, 1.5F, 5.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, 1.5F, 4.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, 1.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 1.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, 1.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.0F, 1.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.0F, 1.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.0F, 0.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, 0.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 0.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, 0.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, 0.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, 0.5F, 4.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, 0.5F, 5.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -0.5F, 4.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -0.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -0.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -0.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -0.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, -1.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, -0.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -1.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -1.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -1.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -1.5F, 3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -2.5F, 2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -2.5F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -2.5F, 0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -2.5F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -41.2F, -5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition left_r17 = left4.addOrReplaceChild("left_r17", CubeListBuilder.create().texOffs(0, 0).addBox(7.85F, -2.4961F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.85F, -2.4961F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.85F, -2.4961F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.85F, -2.4961F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.85F, -1.4961F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.85F, -1.4961F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.85F, -1.4961F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.85F, -1.4961F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.85F, -1.4961F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.85F, -0.4961F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.85F, -0.4961F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.85F, -0.4961F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.85F, -0.4961F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.85F, -0.4961F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.85F, -0.4961F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.85F, 0.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.85F, 0.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.85F, 0.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.85F, 0.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.85F, 0.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.85F, 0.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.85F, 0.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.85F, 1.5039F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.85F, 1.5039F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.85F, 1.5039F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.85F, 1.5039F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.85F, 1.5039F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.85F, 1.5039F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.85F, 1.5039F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.85F, 1.5039F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.9F, 1.5039F, 2.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, -41.2039F, -21.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_r18 = left4.addOrReplaceChild("left_r18", CubeListBuilder.create().texOffs(0, 0).addBox(2.85F, -2.5039F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.85F, -1.5039F, -3.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.85F, -0.5039F, -2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.85F, 0.4961F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.85F, 1.4961F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, -41.2039F, -21.5F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r19 = left4.addOrReplaceChild("left_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-7.4F, 1.5F, 4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.4F, 1.5F, 5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.4F, 1.5F, 6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-10.4F, 1.5F, 7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-11.4F, 1.5F, 8.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-12.4F, 1.5F, 9.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.4F, 1.5F, 10.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-12.4F, -2.5F, 5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.4F, -2.5F, 6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-11.4F, -2.5F, 4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-10.4F, -2.5F, 3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.4F, -1.5F, 3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-10.4F, -1.5F, 4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-11.4F, -1.5F, 5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-12.4F, -1.5F, 6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.4F, -1.5F, 7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.4F, 0.5F, 9.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.4F, -0.5F, 8.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-12.4F, 0.5F, 8.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-12.4F, -0.5F, 7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-11.4F, 0.5F, 7.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-11.4F, -0.5F, 6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-10.4F, 0.5F, 6.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-10.4F, -0.5F, 5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.4F, 0.5F, 5.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.4F, 0.5F, 4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.4F, -0.5F, 4.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.4F, -0.5F, 3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.4F, 0.5F, 3.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.65F, -41.2F, -30.35F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_r20 = left4.addOrReplaceChild("left_r20", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, 0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -0.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -2.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -5.5F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, -6.5F, 11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -6.5F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -5.5F, 7.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, -5.5F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -4.5F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, -4.5F, 7.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, -3.5F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -3.5F, 7.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, -2.5F, 7.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -2.5F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, -2.5F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -2.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -2.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -2.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -2.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -2.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.5F, -6.5F, 1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.5F, -5.5F, 0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.5F, -4.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.5F, -3.5F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -4.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -6.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -3.5F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -7.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -7.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -7.5F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -7.5F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -6.5F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -7.5F, 7.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -6.5F, 7.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -6.5F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -6.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -6.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -5.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -5.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -5.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -5.5F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -3.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -3.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -3.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -3.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -4.5F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -4.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -4.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -4.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -3.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -5.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -3.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -2.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -1.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -1.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -0.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, 0.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, 1.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, 1.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, 1.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, 1.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 1.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 2.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, 2.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.5F, 2.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, 2.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, 2.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, 2.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 2.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -19.2F, -1.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r21 = left4.addOrReplaceChild("left_r21", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, 0.0F, -4.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -1.0F, -4.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 0.0F, -5.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 0.0F, -6.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, 0.0F, -7.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -1.0F, -6.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -1.0F, -5.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -2.0F, -4.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -3.0F, -4.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -2.0F, -6.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -2.0F, -5.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -3.0F, -7.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -3.0F, -5.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -3.0F, -6.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -2.0F, -7.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -2.0F, -8.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -1.0F, -9.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -1.0F, -8.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -1.0F, -7.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, 0.0F, -8.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, 0.0F, -9.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, 0.0F, -10.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, 1.0F, -11.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, 1.0F, -10.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, 1.0F, -9.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, 1.0F, -8.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 1.0F, -7.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 1.0F, -6.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, 1.0F, -5.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, 2.0F, -5.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, 2.0F, -6.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, 1.0F, -4.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, 2.0F, -4.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 2.0F, -7.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, 2.0F, -9.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 2.0F, -8.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, 2.0F, -11.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, 2.0F, -10.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, 2.0F, -12.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -14.7F, -4.5F, 0.0F, -1.5708F, 3.1416F));

		PartDefinition left_r22 = left4.addOrReplaceChild("left_r22", CubeListBuilder.create().texOffs(0, 0).addBox(2.7F, 2.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.7F, 1.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.7F, 0.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.3F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.3F, -2.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.3F, -3.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -14.7F, -13.0F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r23 = left4.addOrReplaceChild("left_r23", CubeListBuilder.create().texOffs(0, 0).addBox(4.8F, 1.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8F, 0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8F, -1.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.2F, -3.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.8F, -2.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -41.2F, -13.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r24 = left4.addOrReplaceChild("left_r24", CubeListBuilder.create().texOffs(0, 0).addBox(-8.7941F, -3389.7039F, -4.0F, 18.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7059F, 3289.8961F, -13.0F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r25 = left4.addOrReplaceChild("left_r25", CubeListBuilder.create().texOffs(0, 0).addBox(3.0F, -30.0F, -4.0F, 18.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.0F, 2.0F, -4.0F, 18.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.0F, 2.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, 1.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 0.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -2.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -3.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5F, -40.7F, -13.0F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r26 = left4.addOrReplaceChild("left_r26", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -46.2F, 2.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r27 = left4.addOrReplaceChild("left_r27", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -48.2F, 0.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r28 = left4.addOrReplaceChild("left_r28", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -49.2F, -0.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r29 = left4.addOrReplaceChild("left_r29", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -50.2F, -1.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r30 = left4.addOrReplaceChild("left_r30", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -7.5F, 2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -47.2F, 1.5F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right4 = Front.addOrReplaceChild("right4", CubeListBuilder.create().texOffs(0, 0).addBox(7.8529F, -5.1961F, -14.4657F, 15.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.1471F, -5.1961F, 2.5343F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.8529F, -5.1961F, 1.5343F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -5.1961F, 0.5343F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -5.1961F, -0.4657F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8529F, -5.1961F, -1.4657F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -5.1961F, -2.4657F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -5.1961F, -3.4657F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -5.1961F, -4.4657F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -5.1961F, 3.5343F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1471F, -6.1961F, 3.5343F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -6.1961F, 2.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.1471F, -6.1961F, 1.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.8529F, -6.1961F, 0.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -6.1961F, -0.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -6.1961F, -1.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8529F, -6.1961F, -2.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -6.1961F, -3.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -7.1961F, 1.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.1471F, -7.1961F, 0.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.8529F, -7.1961F, -0.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -7.1961F, -1.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.8529F, -7.1961F, -2.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1471F, -7.1961F, 2.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.1471F, -8.1961F, 2.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -8.1961F, 0.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.1471F, -8.1961F, -0.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.8529F, -8.1961F, -1.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1471F, -8.1961F, 1.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.1471F, -7.1961F, 3.5343F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.1471F, -8.1961F, 3.5343F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.1471F, -9.1961F, 2.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.1471F, -10.1961F, 2.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.1471F, -10.1961F, 0.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.1471F, -10.1961F, 1.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.1471F, -9.1961F, 1.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1471F, -9.1961F, 0.5343F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.1471F, -9.1961F, -0.4657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.1471F, -9.1961F, 3.5343F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.1471F, -10.1961F, 3.5343F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.1471F, -11.1961F, 3.5343F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.1471F, -11.1961F, 3.5343F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.1471F, -11.1961F, 3.5343F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, -12.1961F, 2.5343F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, -13.1961F, 1.5343F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, -14.1961F, 0.5343F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, -15.1961F, -0.4657F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, -16.1961F, -1.4657F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, 10.8039F, 2.5343F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, 11.8039F, 1.5343F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, 14.8039F, -1.4657F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.1471F, 15.8039F, -11.4657F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, 14.8039F, -1.4657F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, 13.8039F, -0.4657F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, 12.8039F, 0.5343F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.1471F, -18.0961F, -11.4657F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-14.1471F, 10.8039F, 2.5343F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -6.1961F, -15.4657F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8529F, -7.1961F, -16.4657F, 13.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -8.1961F, -17.4657F, 12.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.1471F, -9.1961F, -18.4657F, 11.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1471F, -10.1961F, -19.4657F, 11.0F, 1.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.3529F, -27.9039F, 2.4657F, 0.0F, 0.0F, -3.1416F));

		PartDefinition left_r31 = right4.addOrReplaceChild("left_r31", CubeListBuilder.create().texOffs(0, 0).addBox(8.8529F, -12.5039F, 13.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.1471F, -13.5039F, 9.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -13.5039F, 9.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8529F, -13.5039F, 9.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -12.5039F, 9.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -11.5039F, 9.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -10.5039F, 9.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -9.5039F, 9.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -8.5039F, 9.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -10.5039F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -10.5039F, 13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -10.5039F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -10.5039F, 11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -11.5039F, 13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -11.5039F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -11.5039F, 11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -13.5039F, 18.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -13.5039F, 17.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -13.5039F, 16.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -13.5039F, 16.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -13.5039F, 15.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -13.5039F, 13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -13.5039F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -13.5039F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -13.5039F, 11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -13.5039F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -12.5039F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -12.5039F, 11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -12.5039F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -11.5039F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -10.5039F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -9.5039F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -9.5039F, 15.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -9.5039F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -9.5039F, 13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -9.5039F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -9.5039F, 11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -8.5039F, 10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.8529F, -8.5039F, 16.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.8529F, -8.5039F, 15.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -8.5039F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -8.5039F, 13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -8.5039F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -8.5039F, 11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -2.7F, -32.9657F, 3.1416F, 3.1416F, 0.0F));

		PartDefinition left_r32 = right4.addOrReplaceChild("left_r32", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0471F, -13.4961F, -15.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0471F, -12.4961F, -14.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0471F, -11.4961F, -13.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0471F, -10.4961F, -12.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0471F, -9.4961F, -11.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1471F, -8.4961F, -10.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.8529F, -8.4961F, -10.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.8529F, -8.4961F, -10.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8529F, -8.4961F, -10.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -6.4961F, -10.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -6.4961F, -14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -6.4961F, -13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -6.4961F, -12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -7.4961F, -12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -7.4961F, -13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8529F, -7.4961F, -11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -6.4961F, -11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8529F, -5.4961F, -10.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -4.4961F, -10.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -5.4961F, -12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -5.4961F, -15.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -5.4961F, -14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -5.4961F, -13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.8529F, -5.4961F, -11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -4.4961F, -11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -4.4961F, -16.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -4.4961F, -15.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -4.4961F, -14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -4.4961F, -13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -4.4961F, -12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.8529F, -3.4961F, -17.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.8529F, -3.4961F, -16.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -3.4961F, -15.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -3.4961F, -14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -3.4961F, -13.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -3.4961F, -12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -3.4961F, -11.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.8529F, -3.4961F, -10.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(9.8529F, -2.4961F, -10.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(17.8529F, -2.4961F, -18.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(16.8529F, -2.4961F, -17.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.8529F, -2.4961F, -16.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(14.8529F, -2.4961F, -15.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(13.8529F, -2.4961F, -14.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(12.8529F, -2.4961F, -13.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(11.8529F, -2.4961F, -12.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(10.8529F, -2.4961F, -11.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -2.7F, -32.9657F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_r33 = right4.addOrReplaceChild("left_r33", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -17.5F, -0.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.0F, -17.5F, -0.5F, 11.0F, 1.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-11.0F, -16.5F, 0.5F, 11.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.0F, -15.5F, 1.5F, 12.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -14.5F, 2.5F, 13.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.0F, -13.5F, 3.5F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.8529F, -7.6961F, 0.0343F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r34 = right4.addOrReplaceChild("left_r34", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -18.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -18.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -18.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -17.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, -16.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -15.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, -14.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.5F, -13.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.6471F, -7.6961F, 4.0343F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_r35 = right4.addOrReplaceChild("left_r35", CubeListBuilder.create().texOffs(0, 0).addBox(-3.9441F, -2.4961F, -2.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.9441F, -1.4961F, -3.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0441F, -0.4961F, -4.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.8441F, 1.5039F, -10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.8441F, 1.5039F, -19.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.8441F, 1.5039F, -10.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.8441F, 1.5039F, -20.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.9559F, 1.5039F, -10.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8559F, 1.5039F, -19.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.9559F, 1.5039F, -10.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8559F, 1.5039F, -20.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.9559F, 1.5039F, -10.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8559F, 1.5039F, -21.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.8441F, 1.5039F, -10.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.8441F, 1.5039F, -21.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.8441F, 1.5039F, -10.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.8441F, 1.5039F, -22.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.1441F, 1.5039F, -22.5F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0441F, 1.5039F, -10.5F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0441F, 1.5039F, -6.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0441F, 0.5039F, -5.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0029F, 13.3F, -23.9657F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_r36 = right4.addOrReplaceChild("left_r36", CubeListBuilder.create().texOffs(0, 0).addBox(4.9F, 3300.7039F, 4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.9F, 3300.7039F, 4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.9F, 3300.7039F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.8F, 3300.7039F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.2F, 3300.7039F, -8.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.8F, 3300.7039F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.8F, 3300.7039F, -6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.9F, 3300.7039F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.9F, 3300.7039F, 4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.9F, 3300.7039F, 4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.9F, 3300.7039F, 4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.1F, 3300.7039F, 4.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.9F, 3300.7039F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.9F, 3300.7039F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.9F, 3300.7039F, -6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.9F, 3300.7039F, -8.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0588F, -3317.8F, -9.4657F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_r37 = right4.addOrReplaceChild("left_r37", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, 0.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -0.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -2.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, -2.5F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, -2.5F, 5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, -2.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -2.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -2.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -2.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -2.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -2.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -2.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, -1.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -1.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -0.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, 0.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, 1.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, 1.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, 1.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, 1.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 1.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 2.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.5F, 2.5F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.5F, 2.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.5F, 2.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.5F, 2.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, 2.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.5F, 2.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6471F, 8.3039F, 2.0343F, 3.1416F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(TieFighter entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Wing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Wing2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		MiddleCenter.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}