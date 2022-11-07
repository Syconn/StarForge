package mod.stf.syconn.common.entity;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.VectorTools;
import mod.stf.syconn.api.util.data.DirectionalTexture;
import mod.stf.syconn.api.util.data.ServerPixelImage;
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
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MovingBlock extends Entity {

    private static final EntityDataAccessor<Optional<BlockState>> DATA_BLOCK_STATE = SynchedEntityData.defineId(MovingBlock.class, EntityDataSerializers.BLOCK_STATE);
    private double distance;
    private BlockPos start;
    private Direction direction;
    private double speed = 0;

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
    }

    //TODO NOT SAVING BLOCK STATE

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_BLOCK_STATE, Optional.empty());
    }

    @Override
    public void tick() {
        super.tick();
        if (start != null) {
            if (position().distanceTo(VectorTools.getVec3(start)) < distance){
                double x = getX() + (direction.getStepX() * speed);
                double y = getY() + (direction.getStepY() * speed);
                double z = getZ() + (direction.getStepZ() * speed);

                List<Entity> entity = level.getEntities(this, this.getBoundingBox().inflate(0, 1, 0));
                entity.forEach((e) -> {
                    if (e.isAlive()){
                        double x2 = getX() + (direction.getStepX() * speed);
                        double y2 = getY() + 1 + (direction.getStepY() * speed);
                        double z2 = getZ() + (direction.getStepZ() * speed);
                        e.moveTo(x2, y2, z2);
                    }
                });

                setPos(x, y, z);
            } else {
                if (this.entityData.get(DATA_BLOCK_STATE).isPresent()) {
                    level.setBlock(getOnPos().above(), this.entityData.get(DATA_BLOCK_STATE).get(), 2);
                    discard();
                }
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.speed = pCompound.getDouble("speed");
        this.distance = pCompound.getDouble("distance");
        this.start = NbtUtils.readBlockPos(pCompound.getCompound("start"));
        this.direction = Direction.from3DDataValue(pCompound.getInt("direction"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putDouble("speed", this.speed);
        pCompound.putDouble("distance", this.distance);
        pCompound.putInt("direction", this.direction.get3DDataValue());
        pCompound.put("start", NbtUtils.writeBlockPos(this.start));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    //TODO UNDER BLOCK RENDER DISPATCHER THERE IS RENDER SINGLE BLOCK

    public BlockState getBlockState(){
        return this.getEntityData().get(DATA_BLOCK_STATE).orElse(null);
    }

    public ServerPixelImage getTexture() throws IOException {
        BlockState state = this.getEntityData().get(DATA_BLOCK_STATE).orElse(null);
        NativeImage background = NativeImage.read(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png")).getInputStream());
        if (state != null) {
            DirectionalTexture[] sprites = new DirectionalTexture[Direction.values().length];
            for (int i = 0; i < Direction.values().length; i++) {
                if (!Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).isEmpty())
                    sprites[i] = new DirectionalTexture(Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).get(0).getSprite(), Direction.from3DDataValue(i));
            }
            return new ServerPixelImage(background, sprites);
        }
        return new ServerPixelImage(background);
    }
}
