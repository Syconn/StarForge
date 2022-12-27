package mod.stf.syconn.api.util;

import mod.stf.syconn.common.blockEntity.NavBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class RotationHelper {

    public static Vec3 getVec3(BlockPos pos){
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockPos rotate(NavBE be, BlockID id, Direction init, Direction target) {
        return rotate(be.getPos().getBlockPos(), id.pos(), new int[] {be.getShip().evenXLen(), be.getShip().evenZLen()}, init, target);
    }

    /**
     * Rotates single Blockpos along Rotation Point to target Direction
     * @param rp Rotation Point, Normally Center
     * @param block Actual Blockpos
     * @param init Initial Direction
     * @param target End Direction
     * @return rotated blockpos
     */
    public static BlockPos rotate(BlockPos rp, BlockPos block, Direction init, Direction target) {
        return rotate(rp, block, new int[]{0, 0}, init, target);
    }

    public static BlockPos rotate(BlockPos c, BlockPos id, int[] even, Direction init, Direction target) {
        if (target.getAxis() != Direction.Axis.Y && init.getAxis() != Direction.Axis.Y) {
            if (init.getOpposite() == target){
                if (init.getAxis() == Direction.Axis.X){
                    int x = id.getX() - c.getX();
                    int z = id.getZ() - c.getZ();
                    return new BlockPos(c.getX() - x + even[0], id.getY(), id.getZ() - 2 * z + even[1]);
                }
                if (init.getAxis() == Direction.Axis.Z){
                    int x = id.getX() - c.getX();
                    int z = id.getZ() - c.getZ();
                    return new BlockPos(id.getX() - 2 * x + even[0], id.getY(),c.getZ() - z + even[1]);
                }
            } else {
                int x = id.getX() - c.getX();
                int z = id.getZ() - c.getZ();
                if (init == Direction.EAST){
                    if (target == Direction.NORTH)
                        return new BlockPos(c.getX() + z - even[1], id.getY(), c.getZ() - x);
                    return new BlockPos(c.getX() - z, id.getY(), c.getZ() + x + even[0]);
                } if (init == Direction.WEST){
                    if (target == Direction.SOUTH)
                        return new BlockPos(c.getX() + z, id.getY(), c.getZ() - x + even[0]);
                    return new BlockPos(c.getX() - z + even[1], id.getY(), c.getZ() + x);
                } if (init == Direction.NORTH) {
                    if (target == Direction.WEST)
                        return new BlockPos(c.getX() + z, id.getY(), c.getZ() - x + even[0]);
                    return new BlockPos(c.getX() - z + even[1], id.getY(), c.getZ() + x);
                }
                if (init == Direction.SOUTH) {
                    if (target == Direction.WEST)
                        return new BlockPos(c.getX() - z, id.getY(), c.getZ() + x - even[0]);
                    return new BlockPos(c.getX() + z + even[1], id.getY(), c.getZ() - x);
                }
            }
        }
        return id;
    }
}
