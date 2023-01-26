package mod.stf.syconn.common.entity;

import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.block.NavigationalComputer;
import mod.stf.syconn.init.ModEntities;
import mod.stf.syconn.util.ShipFlightController;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;

public class MovingBlock extends Entity {

    protected static final EntityDataAccessor<Optional<BlockState>> DATA_BLOCK_STATE = SynchedEntityData.defineId(MovingBlock.class, EntityDataSerializers.BLOCK_STATE);
    protected BlockPos start;
    protected BlockPos end;
    protected Direction direction;
    protected double distance;
    protected boolean update = false;
    protected double speed;
    protected ShipFlightController controller;

    public MovingBlock(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MovingBlock(Level pLevel, ShipFlightController controller, BlockState state, BlockPos start, int distance, Direction direction, double speed) {
        super(ModEntities.MOVING_BLOCK.get(), pLevel);
        setPos(start.getX() + 0.5, start.getY(), start.getZ() + 0.5);
        this.getEntityData().set(DATA_BLOCK_STATE, Optional.of(state));
        this.controller = controller;
        this.start = start;
        this.direction = direction;
        this.speed = speed / 100;
        this.distance = distance;
        this.end = new BlockPos(start.getX() + direction.getStepX() * distance, start.getY() + direction.getStepY() * distance, start.getZ() + direction.getStepZ() * distance);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_BLOCK_STATE, Optional.empty());
    }

    @Override
    public void tick() {
        super.tick();
        if (update && !level.isClientSide){
            arrived();
        } else if (start != null && !level.isClientSide && end != null) {
            if (end.getX() > getX()){
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

                setPos(getX() + (speed * direction.getStepX()), getY() + (speed * direction.getStepY()), getZ() + (speed * direction.getStepZ()));
            } else {
                if (this.entityData.get(DATA_BLOCK_STATE).isPresent()) {
                    if (shouldPlace())
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

    public boolean shouldPlace() {
        return true;
    }

    protected void arrived(){
        discard();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.speed = pCompound.getDouble("speed");
        this.start = NbtUtils.readBlockPos(pCompound.getCompound("start"));
        this.direction = Direction.from3DDataValue(pCompound.getInt("direction"));
        this.update = pCompound.getBoolean("update");
        this.controller = new ShipFlightController(pCompound.getCompound("controller"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putDouble("speed", this.speed);
        pCompound.putInt("direction", this.direction.get3DDataValue());
        pCompound.put("start", NbtUtils.writeBlockPos(this.start));
        pCompound.putBoolean("update", this.update);
        pCompound.put("controller", controller.save());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public BlockState getBlockState(){
        return this.getEntityData().get(DATA_BLOCK_STATE).orElse(null);
    }
}
