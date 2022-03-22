// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class custom_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart Blade;

	public custom_model(ModelPart root) {
		this.Blade = root.getChild("Blade");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Blade = partdefinition.addOrReplaceChild("Blade", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, 24.0F, -16.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Core = Blade.addOrReplaceChild("Core", CubeListBuilder.create().texOffs(0, 0).addBox(-0.0873F, -14.5268F, -0.439F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.439F, -14.5268F, -0.0873F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -8.0F, -8.0F));

		PartDefinition hexadecagon_r1 = Core.addOrReplaceChild("hexadecagon_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.439F, -23.6056F, -0.0873F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.0873F, -23.6056F, -0.439F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0787F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r2 = Core.addOrReplaceChild("hexadecagon_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.439F, -23.6056F, -0.0873F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.0873F, -23.6056F, -0.439F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0787F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r3 = Core.addOrReplaceChild("hexadecagon_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.0873F, -23.6056F, -0.439F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0787F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r4 = Core.addOrReplaceChild("hexadecagon_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-0.0873F, -23.6056F, -0.439F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0787F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition Outer = Blade.addOrReplaceChild("Outer", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1375F, -14.6717F, -0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1375F, -14.6717F, 0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.6912F, -14.6717F, -0.1375F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.6912F, -14.6717F, -0.1375F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -8.0F, -8.0F));

		PartDefinition hexadecagon_r5 = Outer.addOrReplaceChild("hexadecagon_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6912F, -25.4603F, -0.1375F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.6912F, -25.4603F, -0.1375F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1375F, -25.4603F, 0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1375F, -25.4603F, -0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.7886F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r6 = Outer.addOrReplaceChild("hexadecagon_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6912F, -25.4603F, -0.1375F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.6912F, -25.4603F, -0.1375F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1375F, -25.4603F, 0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1375F, -25.4603F, -0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.7886F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r7 = Outer.addOrReplaceChild("hexadecagon_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1375F, -25.4603F, 0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1375F, -25.4603F, -0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.7886F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r8 = Outer.addOrReplaceChild("hexadecagon_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1375F, -25.4603F, 0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1375F, -25.4603F, -0.6912F, 0.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.7886F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition Top = Outer.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 0).addBox(-0.138F, -14.6597F, -0.6937F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.6937F, -14.6597F, -0.138F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r9 = Top.addOrReplaceChild("hexadecagon_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6937F, -37.2968F, -0.138F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.138F, -37.2968F, -0.6937F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.6371F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r10 = Top.addOrReplaceChild("hexadecagon_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6937F, -37.2968F, -0.138F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.138F, -37.2968F, -0.6937F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.6371F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r11 = Top.addOrReplaceChild("hexadecagon_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-0.138F, -37.2968F, -0.6937F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.6371F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r12 = Top.addOrReplaceChild("hexadecagon_r12", CubeListBuilder.create().texOffs(0, 0).addBox(-0.138F, -37.2968F, -0.6937F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.6371F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition Top2 = Outer.addOrReplaceChild("Top2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1049F, -14.687F, -0.5272F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5272F, -14.687F, -0.1049F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r13 = Top2.addOrReplaceChild("hexadecagon_r13", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5272F, -28.3456F, -0.1049F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1049F, -28.3456F, -0.5272F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.6585F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r14 = Top2.addOrReplaceChild("hexadecagon_r14", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5272F, -28.3456F, -0.1049F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1049F, -28.3456F, -0.5272F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.6585F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r15 = Top2.addOrReplaceChild("hexadecagon_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1049F, -28.3456F, -0.5272F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.6585F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r16 = Top2.addOrReplaceChild("hexadecagon_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1049F, -28.3456F, -0.5272F, 0.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.6585F, 0.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Blade.render(poseStack, buffer, packedLight, packedOverlay);
	}
}