package mod.stf.syconn.block;

import mod.stf.syconn.util.MultiBlockAlignment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MapProjector extends Block {

    public static final EnumProperty<MultiBlockAlignment> ALIGNMENT = EnumProperty.create("alignment", MultiBlockAlignment.class);

    public MapProjector() {
        super(Properties.of(Material.METAL).requiresCorrectToolForDrops().dynamicShape());
        this.registerDefaultState(this.stateDefinition.any().setValue(ALIGNMENT, MultiBlockAlignment.MID));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(0, 0, 0, 16, 8, 16);
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                if (x == 0 && z == 0) continue;
                if (!pLevel.getBlockState(pPos.offset(x, 0, z)).isAir()) return;
            }
        }
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                if (x == 0 && z == 0) continue;
                if (!pLevel.getBlockState(pPos.offset(x, 0, z)).isAir()) pLevel.setBlock(pPos.offset(x, 0, z), pState.setValue(ALIGNMENT, MultiBlockAlignment.fromAlignment(x, z)), 3);
            }
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        if (!level.isClientSide) {
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    if (!level.getBlockState(pos.offset(x, 0, z)).isAir()) return null;
                }
            }
        }
        return super.getStateForPlacement(pContext).setValue(ALIGNMENT, MultiBlockAlignment.MID);
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        if (!pLevel.isClientSide) {
            BlockPos mid = pPos.offset(pState.getValue(ALIGNMENT).getX(), 0, pState.getValue(ALIGNMENT).getZ());
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    pLevel.setBlock(mid.offset(x, 0, z), Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ALIGNMENT);
    }
}
