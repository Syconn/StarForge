package mod.stf.syconn.client.rendering.model;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.stf.syconn.Reference;
import mod.stf.syconn.common.entity.StormTrooper;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PlayerLikeModel<M extends LivingEntity> extends HumanoidModel<M> {

    public static final String BODY = "body";

    public static ModelLayerLocation MODEL = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "stormtrooper"), BODY);

    public static LayerDefinition createBodyLayer() {
        return LayerDefinition.create(PlayerModel.createMesh(CubeDeformation.NONE, false), 64, 64);
    }

    public static MeshDefinition createMesh(CubeDeformation pCubeDeformation, boolean pSlim) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(pCubeDeformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("ear", CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 1.0F, pCubeDeformation), PartPose.ZERO);
        partdefinition.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, pCubeDeformation, 1.0F, 0.5F), PartPose.offset(0.0F, 0.0F, 0.0F));
        float f = 0.25F;
        if (pSlim) {
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, pCubeDeformation), PartPose.offset(5.0F, 2.5F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, pCubeDeformation), PartPose.offset(-5.0F, 2.5F, 0.0F));
            partdefinition.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, pCubeDeformation.extend(0.25F)), PartPose.offset(5.0F, 2.5F, 0.0F));
            partdefinition.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, pCubeDeformation.extend(0.25F)), PartPose.offset(-5.0F, 2.5F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation.extend(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation.extend(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        }

        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation), PartPose.offset(1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation.extend(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, pCubeDeformation.extend(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, pCubeDeformation.extend(0.25F)), PartPose.ZERO);
        return meshdefinition;
    }

    public PlayerLikeModel(ModelPart part) {
        super(part);
    }

    public void prepareMobModel(M pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
        this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        this.leftArmPose = HumanoidModel.ArmPose.EMPTY;
        ItemStack itemstack = pEntity.getItemInHand(InteractionHand.MAIN_HAND);
//        if (itemstack.is(Items.BOW) && pEntity.isAggressive()) {
//            if (pEntity.getMainArm() == HumanoidArm.RIGHT) {
//                this.rightArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
//            } else {
//                this.leftArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
//            }
//        }

        super.prepareMobModel(pEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setupAnim(M pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        ItemStack itemstack = pEntity.getMainHandItem();
//        if (pEntity.isAggressive() && (itemstack.isEmpty() || !itemstack.is(Items.BOW))) {
//            float f = Mth.sin(this.attackTime * (float)Math.PI);
//            float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
//            this.rightArm.zRot = 0.0F;
//            this.leftArm.zRot = 0.0F;
//            this.rightArm.yRot = -(0.1F - f * 0.6F);
//            this.leftArm.yRot = 0.1F - f * 0.6F;
//            this.rightArm.xRot = (-(float)Math.PI / 2F);
//            this.leftArm.xRot = (-(float)Math.PI / 2F);
//            this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
//            this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
//            AnimationUtils.bobArms(this.rightArm, this.leftArm, pAgeInTicks);
//        }

    }

    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        float f = pSide == HumanoidArm.RIGHT ? 1.0F : -1.0F;
        ModelPart modelpart = this.getArm(pSide);
        modelpart.x += f;
        modelpart.translateAndRotate(pPoseStack);
        modelpart.x -= f;
    }
}
