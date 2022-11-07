package mod.stf.syconn.block;

import mod.stf.syconn.api.blocks.RotatableWallBlock;
import mod.stf.syconn.client.screen.HoloScreen;
import mod.stf.syconn.common.blockEntity.HoloBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class HoloProjector extends RotatableWallBlock implements EntityBlock {

    public HoloProjector() {
        super(BlockBehaviour.Properties.of(Material.METAL).noCollission().noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACE, AttachFace.WALL).setValue(FACING, Direction.NORTH));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(FACE) == AttachFace.CEILING)
            return Block.box(3, 15, 3, 13, 16, 13);
        else if (pState.getValue(FACE) == AttachFace.FLOOR)
            return Block.box(3, 0, 3, 13, 1, 13);
        else {
            if (pState.getValue(FACING) == Direction.NORTH)
                return Block.box(3, 3, 15, 13, 13, 16);
            if (pState.getValue(FACING) == Direction.SOUTH)
                return Block.box(3, 3, 0, 13, 13, 1);
            if (pState.getValue(FACING) == Direction.WEST)
                return Block.box(15, 3, 3, 16, 13, 13);
            return Block.box(0, 3, 3, 1, 13, 13);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new HoloBE(pPos, pState);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            //DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> Minecraft.getInstance().setScreen(new HoloScreen((HoloBE) pLevel.getBlockEntity(pPos), pPos));)
            Minecraft.getInstance().setScreen(new HoloScreen((HoloBE) pLevel.getBlockEntity(pPos), pPos));
        }

        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return (lvl, pos, blockState, t) -> {
            if (t instanceof HoloBE tile) {
                tile.tickServer(level);
            }
        };
    }
}
