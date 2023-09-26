package mod.stf.syconn.block;

import mod.stf.syconn.world.data.ChunkManager;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class Probe extends Block {

    public static final BooleanProperty ORBIT = BooleanProperty.create("orbit");

    public Probe() { // TODO ANIMATE FLY UP?
        super(Properties.of(Material.METAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(ORBIT, false));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(ORBIT)) Stream.of(Block.box(-3, 16, -2, 19, 23, 20), Block.box(3, 8, 3, 13, 18, 13),
                Block.box(1, 3, 1, 15, 8, 15)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        return box(1, 0, 1, 15, 16, 15);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND) {
            if (pLevel.getBlockState(new BlockPos(pPos.getX(), pLevel.getMaxBuildHeight() - 1, pPos.getZ())).isAir()) {
                pLevel.setBlock(new BlockPos(pPos.getX(), 100, pPos.getZ()), pState.setValue(ORBIT, true), 2);
                pLevel.getCapability(ChunkManager.CHUNKS).ifPresent(cap -> cap.changeProbeLocation(pPos, new BlockPos(pPos.getX(), pLevel.getMaxBuildHeight() - 1, pPos.getZ())));
                pLevel.removeBlock(pPos, true);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        if (!pLevel.isClientSide) {
            pLevel.removeBlock(pHit.getBlockPos(), false);
            int y = pLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pHit.getBlockPos().getX(), pHit.getBlockPos().getZ());
            pLevel.getCapability(ChunkManager.CHUNKS).ifPresent(cap -> cap.changeProbeLocation(pHit.getBlockPos(), new BlockPos(pHit.getBlockPos().getX(), y, pHit.getBlockPos().getZ())));
            pLevel.setBlock(new BlockPos(pHit.getBlockPos().getX(), y, pHit.getBlockPos().getZ()), pState.setValue(ORBIT, false), 2);
        }
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        if (!pLevel.isClientSide) pLevel.getCapability(ChunkManager.CHUNKS).ifPresent(cap -> cap.removeProbe(pPos));
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(ORBIT, false);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ORBIT);
    }
}