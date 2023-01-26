package mod.stf.syconn.api.util;

import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.util.MutablePos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class RotationHelper {

    /**
     * Rotates all Blockpos's along Rotation Point to target Direction
     * @param list List of Blockpos's
     * @param i Initial Rotation Direction
     * @param t Target Rotation Direction
     * @return List of Rotated Blockpos's
     */
    public static List<BlockPos> rotateAll(List<BlockPos> list, Direction i, Direction t) {
        List<BlockPos> positions = new ArrayList<>();
        for (BlockPos pos : list){
            positions.add(rotate(findCenter(list), pos, new int[] {evenXLen(list), evenZLen(list)}, i, t));
        }
        return positions;
    }

    /**
     * Rotates all Blockpos's along Rotation Point to target Direction
     * @param list List of BlockID's
     * @param i Initial Rotation Direction
     * @param t Target Rotation Direction
     * @return List of Rotated BlockID's
     */
    public static List<BlockID> rotateAllIDs(List<BlockID> list, Direction i, Direction t) {
        if (!i.equals(t)) {
            List<BlockID> positions = new ArrayList<>();
            for (BlockID pos : list){
                positions.add(new BlockID(pos.state(), rotate(findCenterID(list), pos.pos(), new int[] {evenXLenID(list), evenZLenID(list)}, i, t)));
            }
            return positions;
        }
        return list;
    }

    /**
     * Rotates single Blockpos along Rotation Point to target Direction
     * @param list List of BlockPos to get Center
     * @param block Rotating BlockPos
     * @param init Initial Direction
     * @param target End Direction
     * @return rotated BlockPos
     */
    public static BlockPos rotate(List<BlockPos> list, BlockPos block, Direction init, Direction target) {
        return rotate(findCenter(list), block, new int[]{0, 0}, init, target);
    }

    public static BlockPos rotate(NavBE be, BlockID id, Direction init, Direction target) {
        return rotate(be.getPos().getBlockPos(), id.pos(), new int[] {be.getShip().evenXLen(), be.getShip().evenZLen()}, init, target);
    }

    /**
     * Rotates single Blockpos along Rotation Point to target Direction
     * @param rp Rotation Point, Normally Center
     * @param block Rotating Blockpos
     * @param init Initial Direction
     * @param target End Direction
     * @return rotated BlockPos
     */
    public static BlockPos rotate(BlockPos rp, BlockPos block, Direction init, Direction target) {
        return rotate(rp, block, new int[]{0, 0}, init, target);
    }

    /**
     * Rotation Logic
     * @param c Rotation Point
     * @param id Rotating BlockPos
     * @param even 0, 1 If Even Length
     * @param init Starting Direction
     * @param target Target Direction
     * @return Rotated BlockPos
     */
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

    private static BlockPos findCenter(List<BlockPos> positions){
        int xMax = positions.get(0).getX(), xMin = positions.get(0).getX(), yMin = positions.get(0).getY(), yMax = positions.get(0).getY(), zMax = positions.get(0).getZ(), zMin = positions.get(0).getZ();
        for (BlockPos pos : positions){
            if (xMin > pos.getX())
                xMin = pos.getX();
            if (xMax < pos.getX())
                xMax = pos.getX();
            if (yMin > pos.getY())
                yMin = pos.getY();
            if (yMax < pos.getY())
                yMax = pos.getY();
            if (zMin > pos.getZ())
                zMin = pos.getZ();
            if (zMax < pos.getZ())
                zMax = pos.getZ();
        }
        return Mths.getCenter(new BlockPos(xMin, yMin, zMin), new BlockPos(xMax, yMax, zMax));
    }

    private static BlockPos findCenterID(List<BlockID> positions){
        int xMax = positions.get(0).pos().getX(), xMin = positions.get(0).pos().getX(), yMin = positions.get(0).pos().getY(), yMax = positions.get(0).pos().getY(), zMax = positions.get(0).pos().getZ(), zMin = positions.get(0).pos().getZ();
        for (BlockID id : positions){
            BlockPos pos = id.pos();
            if (xMin > pos.getX())
                xMin = pos.getX();
            if (xMax < pos.getX())
                xMax = pos.getX();
            if (yMin > pos.getY())
                yMin = pos.getY();
            if (yMax < pos.getY())
                yMax = pos.getY();
            if (zMin > pos.getZ())
                zMin = pos.getZ();
            if (zMax < pos.getZ())
                zMax = pos.getZ();
        }
        return Mths.getCenter(new BlockPos(xMin, yMin, zMin), new BlockPos(xMax, yMax, zMax));
    }

    private static int evenXLen(List<BlockPos> blockIDs){
        BlockPos pos = blockIDs.get(0);
        double bX = pos.getX(), sX = pos.getX();
        for (BlockPos pos2 : blockIDs) {
            if (pos2.getX() > bX) bX = pos2.getX();
            if (pos2.getX() < sX) sX = pos2.getX();
        }
        return (int) (((int) bX + sX) % 2);
    }

    private static int evenXLenID(List<BlockID> blockIDs){
        BlockPos pos = blockIDs.get(0).pos();
        double bX = pos.getX(), sX = pos.getX();
        for (BlockID id : blockIDs) {
            BlockPos pos2 = id.pos();
            if (pos2.getX() > bX) bX = pos2.getX();
            if (pos2.getX() < sX) sX = pos2.getX();
        }
        return (int) (((int) bX + sX) % 2);
    }

    private static int evenZLen(List<BlockPos> blockIDs){
        BlockPos pos = blockIDs.get(0);
        double bY = pos.getZ(), sY = pos.getZ();
        for (BlockPos pos2 : blockIDs) {
            if (pos2.getZ() > bY) bY = pos2.getZ();
            if (pos2.getZ() < sY) sY = pos2.getZ();
        }
        return (int) (((int) bY + sY) % 2);
    }

    private static int evenZLenID(List<BlockID> blockIDs){
        BlockPos pos = blockIDs.get(0).pos();
        double bY = pos.getZ(), sY = pos.getZ();
        for (BlockID id : blockIDs) {
            BlockPos pos2 = id.pos();
            if (pos2.getZ() > bY) bY = pos2.getZ();
            if (pos2.getZ() < sY) sY = pos2.getZ();
        }
        return (int) (((int) bY + sY) % 2);
    }
}
