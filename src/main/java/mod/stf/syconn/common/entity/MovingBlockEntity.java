package mod.stf.syconn.common.entity;

import mod.stf.syconn.api.blockEntity.ApplicationBE;
import mod.stf.syconn.api.containers.ApplicationContainer;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.common.containers.NavContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MovingBlockEntity extends MovingBlock {

    private NavBE be;

    public MovingBlockEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MovingBlockEntity(Level pLevel, BlockState state, BlockPos start, double distance, Direction direction, double speed, NavBE be) {
        super(pLevel, state, start, distance, direction, speed);
        this.be = be;
    }

    @Override
    protected void arrived() {
        if (level.getBlockEntity(getOnPos().above()) instanceof NavBE be2){
            be2.setUp(be, direction, distance);
            super.arrived();
        }
    }
}
