// Made with Blockbench 4.8.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Block<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "block"), "main");
	private final ModelPart bb_main;

	public Block(ModelPart root) {
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

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}