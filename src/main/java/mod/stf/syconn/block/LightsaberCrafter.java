package mod.stf.syconn.block;

import mod.stf.syconn.api.blocks.InventoryBlock;
import mod.stf.syconn.api.util.TabAble;
import mod.stf.syconn.common.blockEntity.CrafterBE;
import mod.stf.syconn.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class LightsaberCrafter extends InventoryBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<States> MODE = EnumProperty.create("state", States.class);

    public LightsaberCrafter() {
        super(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(MODE, States.HILT));
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(MODE, States.HILT);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, MODE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CrafterBE(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public enum States implements StringRepresentable, TabAble {

        CRYSTAL("cr", 1, Items.QUARTZ),
        HILT("h", 2,Items.IRON_INGOT),
        CONSTRUCTION("co", 3, Items.IRON_PICKAXE);

        private final String name;
        private final int id;
        private final Item item;

        States(String name, int id, Item item) {
            this.name = name;
            this.id = id;
            this.item = item;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public Item icon() {
            return item;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
