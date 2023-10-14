package mod.stf.syconn.block;

import mod.stf.syconn.client.screen.ProbeScreen;
import mod.stf.syconn.common.blockEntity.ProbeBe;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.world.data.ChunkManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
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
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
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

public class Probe extends Block implements EntityBlock {

    public static final BooleanProperty ORBIT = BooleanProperty.create("orbit");

    public Probe() { // TODO ANIMATE FLY UP?
        super(Properties.of(Material.METAL)); // TODO NO LONGER MOVING DOWN
        this.registerDefaultState(this.stateDefinition.any().setValue(ORBIT, false));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(ORBIT)) Stream.of(Block.box(-3, 16, -2, 19, 23, 20), Block.box(3, 8, 3, 13, 18, 13),
                Block.box(1, 3, 1, 15, 8, 15)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        return box(1, 0, 1, 15, 16, 15);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND) {
            // TODO CHANGE TO pLevel.getMaxBuildHeight() - 1
            if (!pPlayer.isShiftKeyDown()) {
                if (!pLevel.isClientSide) {
                    BlockPos topPos = new BlockPos(pPos.getX(), 100, pPos.getZ());
                    if (pLevel.getBlockState(topPos).isAir() && !pPos.equals(topPos)) {
                        pLevel.setBlock(topPos, pState.setValue(ORBIT, true), 2);
                        pLevel.getBlockEntity(topPos, ModBlockEntities.PROBE_BE.get()).get().setCustomName(pLevel.getBlockEntity(pHit.getBlockPos(), ModBlockEntities.PROBE_BE.get()).get().getName());
                        pLevel.removeBlock(pPos, true);
                        return InteractionResult.CONSUME;
                    } else {
                        Component component = pLevel.getBlockEntity(pHit.getBlockPos(), ModBlockEntities.PROBE_BE.get()).get().getName();
                        pLevel.removeBlock(pHit.getBlockPos(), false);
                        int y = pLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pHit.getBlockPos().getX(), pHit.getBlockPos().getZ());
                        pLevel.getCapability(ChunkManager.CHUNKS).ifPresent(cap -> cap.removeProbe(pHit.getBlockPos()));
                        pLevel.setBlock(new BlockPos(pHit.getBlockPos().getX(), y, pHit.getBlockPos().getZ()), pState.setValue(ORBIT, false), 2);
                        pLevel.getBlockEntity(new BlockPos(pHit.getBlockPos().getX(), y, pHit.getBlockPos().getZ()), ModBlockEntities.PROBE_BE.get()).get().setCustomName(component);
                    }
                }
            } else if (pLevel.isClientSide) Minecraft.getInstance().setScreen(new ProbeScreen(pPos, pLevel.getBlockEntity(pPos, ModBlockEntities.PROBE_BE.get()).get().getName()));
        }

        return InteractionResult.PASS;
    }

    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        if (!pLevel.isClientSide) {
            Component component = pLevel.getBlockEntity(pHit.getBlockPos(), ModBlockEntities.PROBE_BE.get()).get().getName();
            pLevel.removeBlock(pHit.getBlockPos(), false);
            int y = pLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pHit.getBlockPos().getX(), pHit.getBlockPos().getZ());
            BlockPos newPos = new BlockPos(pHit.getBlockPos().getX(), y, pHit.getBlockPos().getZ());
            pLevel.setBlock(newPos, pState.setValue(ORBIT, false), 2);
            pLevel.getBlockEntity(newPos, ModBlockEntities.PROBE_BE.get()).get().setCustomName(component);
            pLevel.getCapability(ChunkManager.CHUNKS).ifPresent(cap -> cap.removeProjector(pHit.getBlockPos()));
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

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ProbeBe(pPos, pState);
    }
}