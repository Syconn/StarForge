package mod.stf.syconn.common.entity;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.data.DirectionalTexture;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import mod.stf.syconn.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundAddMobPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.io.IOException;
import java.util.Random;

public class MovingBlock extends Entity {

    private BlockState state = null;
    private BlockPos targetPos = null;
    private double speed = 0;

    public MovingBlock(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MovingBlock(Level pLevel, BlockState state, BlockPos start, BlockPos targetPos, double speed) {
        super(ModEntities.MOVING_BLOCK.get(), pLevel);
        setPos(start.getX(), start.getY(), start.getZ());
        this.state = state;
        this.targetPos = targetPos;
        this.speed = speed;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.speed = pCompound.getDouble("speed");
        this.state = NbtUtils.readBlockState(pCompound.getCompound("state"));
        this.targetPos = NbtUtils.readBlockPos(pCompound.getCompound("pos"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putDouble("speed", this.speed);
        pCompound.put("state", NbtUtils.writeBlockState(this.state));
        pCompound.put("pos", NbtUtils.writeBlockPos(this.targetPos));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public ServerPixelImage getTexture() throws IOException {
        DirectionalTexture[] sprites = new DirectionalTexture[Direction.values().length];
        NativeImage background = NativeImage.read(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png")).getInputStream());
        for (int i = 0; i < Direction.values().length; i++) {
            if (!Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).isEmpty())
                sprites[i] = new DirectionalTexture(Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).get(0).getSprite(), Direction.from3DDataValue(i));
        }
        return new ServerPixelImage(background, sprites);
    }
}
