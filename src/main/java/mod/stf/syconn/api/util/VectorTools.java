package mod.stf.syconn.api.util;

import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class VectorTools {

    public static Vec3 getVec3(BlockPos pos){
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockPos rotate(NavBE be, BlockID id, Direction init, Direction target) {
        if (target.getAxis() != Direction.Axis.Y && init.getAxis() != Direction.Axis.Y) {
            if (init.getOpposite() == target){
                if (init.getAxis() == Direction.Axis.X){
                    int x = id.pos().getX() - be.getPos().x();
                    int z = id.pos().getZ() - be.getPos().z();
                    return new BlockPos(be.getPos().x() - x + be.getShip().evenXLen(), id.pos().getY(), id.pos().getZ() - 2 * z + 1);
                }
                if (init.getAxis() == Direction.Axis.Z){
                    int x = id.pos().getX() - be.getPos().x();
                    int z = id.pos().getZ() - be.getPos().z();
                    return new BlockPos(id.pos().getX() - 2 * x + 1, id.pos().getY(), be.getPos().z() - z + be.getShip().evenZLen());
                }
            } else {
                int x = id.pos().getX() - be.getPos().x();
                int z = id.pos().getZ() - be.getPos().z();
                if (init == Direction.EAST){
                    if (target == Direction.NORTH)
                        return new BlockPos(be.getPos().x() + z - 1, id.pos().getY(), be.getPos().z() - x);
                    return new BlockPos(be.getPos().x() - z, id.pos().getY(), be.getPos().z() + x + 1);
                } if (init == Direction.WEST){
                    if (target == Direction.SOUTH)
                        return new BlockPos(be.getPos().x() + z, id.pos().getY(), be.getPos().z() - x + 1);
                    return new BlockPos(be.getPos().x() - z + 1, id.pos().getY(), be.getPos().z() + x);
                } if (init == Direction.NORTH) {
                    if (target == Direction.WEST)
                        return new BlockPos(be.getPos().x() + z, id.pos().getY(), be.getPos().z() - x + 1);
                    return new BlockPos(be.getPos().x() - z + 1, id.pos().getY(), be.getPos().z() + x);
                }
                if (init == Direction.SOUTH) {
                    if (target == Direction.WEST)
                        return new BlockPos(be.getPos().x() - z, id.pos().getY(), be.getPos().z() + x - 1);
                    return new BlockPos(be.getPos().x() + z + 1, id.pos().getY(), be.getPos().z() - x);
                }
            }
        }
        return id.pos();
    }
}
