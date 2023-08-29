package mod.stf.syconn.block;

import mod.stf.syconn.api.blockEntity.MenuBlockEntity;
import mod.stf.syconn.api.blocks.RotatableBlock;
import mod.stf.syconn.client.screen.HoloScreen;
import mod.stf.syconn.common.blockEntity.CrafterBE;
import mod.stf.syconn.common.blockEntity.HoloBE;
import mod.stf.syconn.network.Network;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
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
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class HoloProjector extends RotatableBlock implements EntityBlock {

    public HoloProjector() {
        super(BlockBehaviour.Properties.of(Material.METAL).noCollission().noOcclusion().requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACE, AttachFace.WALL).setValue(FACING, Direction.NORTH));
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

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

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new HoloBE(pPos, pState);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MenuBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, (MenuProvider) be, be.getBlockPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof HoloBE) {
                blockentity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                    for (int i = 0; i < handler.getSlots(); i++) {
                        if (handler.getStackInSlot(i) != ItemStack.EMPTY) {
                            pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), handler.getStackInSlot(i)));
                        }
                    }
                });
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }
}
