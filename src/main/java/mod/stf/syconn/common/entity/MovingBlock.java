package mod.stf.syconn.common.entity;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.VectorTools;
import mod.stf.syconn.api.util.data.DirectionalTexture;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.TeleportCommand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityTeleportEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MovingBlock extends Entity {

    protected static final EntityDataAccessor<Optional<BlockState>> DATA_BLOCK_STATE = SynchedEntityData.defineId(MovingBlock.class, EntityDataSerializers.BLOCK_STATE);
    protected double distance;
    protected BlockPos start;
    protected Direction direction;
    protected boolean update = false;
    protected double speed = 0;
    protected int endX, endY, endZ;

    public MovingBlock(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MovingBlock(Level pLevel, BlockState state, BlockPos start, double distance, Direction direction, double speed) {
        super(ModEntities.MOVING_BLOCK.get(), pLevel);
        setPos(start.getX() + 0.5, start.getY(), start.getZ() + 0.5);
        this.getEntityData().set(DATA_BLOCK_STATE, Optional.ofNullable(state));
        this.distance = distance;
        this.start = start;
        this.direction = direction;
        this.speed = speed / 100;
        endX = (int) (getX() + (direction.getStepX() * distance));
        endY = (int) (getY() + (direction.getStepY() * distance));
        endZ = (int) (getZ() + (direction.getStepZ() * distance));
    }

    //TODO NOT SAVING BLOCK STATE

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_BLOCK_STATE, Optional.empty());
    }

    @Override
    public void tick() {
        super.tick();
        if (update && !level.isClientSide){
            arrived();
        } else if (start != null && !level.isClientSide) {
            if (endX != (int) getX() || endY != (int) getY() || endZ != (int) getZ()){
                double x = getX() + (direction.getStepX() * speed);
                double y = getY() + (direction.getStepY() * speed);
                double z = getZ() + (direction.getStepZ() * speed);

                List<Entity> entity = level.getEntities(this, this.getBoundingBox().inflate(0, 1, 0));
                entity.forEach((e) -> {
                    if (e.isAlive() && !(e instanceof MovingBlock)){
                        if (e instanceof Player) {
                            e.setDeltaMovement((speed * 1.85) * direction.getStepX(), (speed * 1.85) * direction.getStepY(), (speed * 1.85) * direction.getStepZ());
                            e.hurtMarked = true;
                        } else {
                            double x2 = getX() + (direction.getStepX() * speed);
                            double y2 = getY() + 1 + (direction.getStepY() * speed);
                            double z2 = getZ() + (direction.getStepZ() * speed);
                            e.moveTo(x2, y2, z2);
                        }
                    }
                });

                setPos(x, y, z);
            } else {
                if (this.entityData.get(DATA_BLOCK_STATE).isPresent()) {
                    level.setBlock(getOnPos().above(), this.entityData.get(DATA_BLOCK_STATE).get(), 2);
                    update=true;
                }
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    protected void arrived(){
        discard();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.speed = pCompound.getDouble("speed");
        this.distance = pCompound.getDouble("distance");
        this.start = NbtUtils.readBlockPos(pCompound.getCompound("start"));
        this.direction = Direction.from3DDataValue(pCompound.getInt("direction"));
        this.update = pCompound.getBoolean("update");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putDouble("speed", this.speed);
        pCompound.putDouble("distance", this.distance);
        pCompound.putInt("direction", this.direction.get3DDataValue());
        pCompound.put("start", NbtUtils.writeBlockPos(this.start));
        pCompound.putBoolean("update", this.update);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public BlockState getBlockState(){
        return this.getEntityData().get(DATA_BLOCK_STATE).orElse(null);
    }
}
