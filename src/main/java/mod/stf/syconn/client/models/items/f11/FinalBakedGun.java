package mod.stf.syconn.client.models.items.f11;

import com.mojang.math.Quaternion;
import com.mojang.math.Transformation;
import mod.stf.syconn.client.utils.BakedQuadUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.DynamicBucketModel;
import net.minecraftforge.client.model.ItemTextureQuadConverter;
import net.minecraftforge.client.model.SimpleModelState;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class FinalBakedGun implements BakedModel {

    private BakedModel parentModel;
    private BakedModel extra;
    private ItemStack tmpItemStack;
    private Map<ModelKey, List<BakedQuad>> quadCache = new HashMap<>();
    private static final float NORTH_Z_COVER = 7.496f / 16f;

    public FinalBakedGun(BakedModel parent, BakedModel extra) {
        parentModel = parent;
        this.extra = extra;
        tmpItemStack = null;
    }

    public FinalBakedGun setCurrentItemStack(ItemStack itemStack)
    {
        this.tmpItemStack = itemStack;
        return this;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return parentModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return parentModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return parentModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return parentModel.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@NotNull IModelData data) {
        return parentModel.getParticleIcon(data);
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return parentModel.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.parentModel.getOverrides();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        ArrayList<BakedQuad> list = new ArrayList<>();

        list.addAll(parentModel.getQuads(state, null, rand));


        return list;
    }

    public class ModelKey {

        private boolean largeScope;
        private boolean noScope;

        public ModelKey(boolean largeScope, boolean noScope) {
            this.largeScope = largeScope;
            this.noScope = noScope;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ModelKey modelKey = (ModelKey) o;
            return largeScope == modelKey.largeScope && noScope == modelKey.noScope;
        }

        @Override
        public int hashCode() {
            return Objects.hash(largeScope, noScope);
        }
    }
}
