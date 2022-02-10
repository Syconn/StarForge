package mod.stf.syconn.client.models.items.f11;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import mod.stf.syconn.Reference;
import mod.stf.syconn.client.models.items.DelegatedModel;
import mod.stf.syconn.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.SimpleModelState;
import net.minecraftforge.client.model.data.IModelData;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class BakedGun implements BakedModel {
    
    private final HashMap<Pair<Item, Boolean>, CompositeBakedModel> cache = new HashMap<>();
    private final ModelBakery bakery;
    private BakedModel originalModel;
    private final BakedModel originalModelClip;
    
    public BakedGun(ModelBakery bakery, BakedModel originalModel, BakedModel originalModelClip) {
        this.bakery = bakery;
        this.originalModel = Preconditions.checkNotNull(originalModel);
        this.originalModelClip = Preconditions.checkNotNull(originalModelClip);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return originalModel.getQuads(state, side, rand);
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        return originalModel.getQuads(state, side, rand, extraData);
    }

    @Override
    public ItemOverrides getOverrides() {
        return new ItemOverrides() {
            @Nullable
            @Override
            public BakedModel resolve(BakedModel pModel, ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
                //USE ITEM STACK FOR CHECKING

                return BakedGun.this.getModel(ModItems.GUN_ITEM.get().getDefaultInstance(), true);
            }
        };
    }

    @Override
    public boolean useAmbientOcclusion() {
        return originalModel.useAmbientOcclusion();
    }

    @Override
    public boolean usesBlockLight() {
        return originalModel.usesBlockLight();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return originalModel.getParticleIcon();
    }

    @Override
    public boolean isGui3d() {
        return originalModel.isGui3d();
    }

    @Override
    public boolean isCustomRenderer() {
        return originalModel.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@Nonnull IModelData data) {
        return originalModel.getParticleIcon(data);
    }

    private CompositeBakedModel getModel(ItemStack lens, boolean clip) {
        return cache.computeIfAbsent(Pair.of(lens.getItem(), clip), p -> new CompositeBakedModel(bakery, lens, clip ? originalModelClip : originalModel));
    }

    private static class CompositeBakedModel extends DelegatedModel {
        private final List<BakedQuad> genQuads = new ArrayList<>();
        private final Map<Direction, List<BakedQuad>> faceQuads = new EnumMap<>(Direction.class);

        CompositeBakedModel(ModelBakery bakery, ItemStack lens, BakedModel gun) {
            super(gun);

            ResourceLocation lensId = Registry.ITEM.getKey(lens.getItem());
            UnbakedModel lensUnbaked = bakery.getModel(new ModelResourceLocation(lensId, "inventory"));
            ModelState transform = new SimpleModelState(new Transformation(new Vector3f(-0.4F, 0.2F, 0.0F), Vector3f.YP.rotation((float) Math.PI / 2), new Vector3f(0.625F, 0.625F, 0.625F), null));
            ResourceLocation name = new ResourceLocation(Reference.MOD_ID, "gun_with_" + lensId.toString().replace(':', '_'));

            BakedModel lensBaked;
            if (lensUnbaked instanceof BlockModel && ((BlockModel) lensUnbaked).getRootModel() == ModelBakery.GENERATION_MARKER) {
                BlockModel bm = (BlockModel) lensUnbaked;
                lensBaked = new ItemModelGenerator().generateBlockModel(Minecraft.getInstance().getAt, bm).bake(bakery, bm, ModelLoader.defaultTextureGetter(), transform, name, false)
            } else {
                lensBaked = lensUnbaked.bake(bakery, Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS)., transform, name);
            }

            for (Direction e : Direction.values()) {
                faceQuads.put(e, new ArrayList<>());
            }

            Random rand = new Random(0);
            genQuads.addAll(lensBaked.getQuads(null, null, rand));

            for (Direction e : Direction.values()) {
                rand.setSeed(0);
                faceQuads.get(e).addAll(lensBaked.getQuads(null, e, rand));
            }

            // Add gun quads
            rand.setSeed(0);
            genQuads.addAll(gun.getQuads(null, null, rand));
            for (Direction e : Direction.values()) {
                rand.setSeed(0);
                faceQuads.get(e).addAll(gun.getQuads(null, e, rand));
            }
        }

        @Nonnull
        @Override
        public List<BakedQuad> getQuads(BlockState state, Direction face, @Nonnull Random rand) {
            return face == null ? genQuads : faceQuads.get(face);
        }

        @Override
        public BakedModel handlePerspective(ItemTransforms.TransformType cameraTransformType, PoseStack poseStack) {
            super.handlePerspective(cameraTransformType, poseStack);
            return this;
        }
    }
}
