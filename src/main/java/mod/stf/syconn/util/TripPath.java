package mod.stf.syconn.util;

import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.network.messages.MessageRotate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;

import java.util.ArrayList;
import java.util.List;

public class TripPath {

    private List<BlockPos> path = new ArrayList<>();
    private List<BlockPos> renderTest = new ArrayList<>();
    private Direction facing;
    private final ShipHead[] head; // North, East, SOUTH, WEST

    public TripPath(List<BlockPos> ship, BlockPos start, BlockPos end, Direction f, Level level) {
        this.facing = f;
        head = new ShipHead[] {new ShipHead(ship, Direction.NORTH), new ShipHead(ship, Direction.EAST), new ShipHead(ship, Direction.SOUTH), new ShipHead(ship, Direction.WEST)};
        genPath(getHead(f), end, level);
        simplifyPath();
    }

    public TripPath(List<BlockPos> path, List<BlockPos> rt, Direction d, ShipHead[] head) {
        this.path = path;
        this.renderTest = rt;
        this.facing = d;
        this.head = head;
    }

    private void genPath(ShipHead h, BlockPos e, Level l){
        int x = h.getFront().getX();
        int y = h.getFront().getY();
        int z = h.getFront().getZ();
        while (x < e.getX()){
            x++;
            BlockPos center = new BlockPos(x, y, z);
            if (!colliding(center, h, l)){
                path.add(center);
            } else {
                while (colliding(center, getHead(Direction.SOUTH), l)) {

                }
            }
        }
    }

    private boolean colliding(BlockPos center, ShipHead h, Level l){
        boolean colliding = false;
        for (BlockPos pos : BlockPos.betweenClosed(center.getX() - h.getXHorizontal(), center.getY() - h.getVertical(), center.getZ() - h.getZHorizontal(), center.getX() + h.getXHorizontal(), center.getY() + h.getVertical(), center.getZ() + h.getZHorizontal())) {
            if (l.getBlockState(pos).getBlock() != Blocks.AIR || l.getBlockState(pos).getBlock() != Blocks.CAVE_AIR || l.getBlockState(pos).getBlock() != Blocks.VOID_AIR) {
                colliding = true;
            }
        }
        return colliding;
    }

    private void simplifyPath(){
        int i = 0;
        // TODO - ADD A BLOCK WHERE IT START TURN
        while (i < path.size() - 1){
            int d = Mths.distanceToPos(path.get(i), path.get(i + 1));

            if (d == 1) {
                path.remove(i);
            } else {
                i++;
            }
        }
    }

    public BlockPos getTarget(){
        return path.get(path.size() - 1);
    }

    public BlockPos getPoint(int i){
        return path.get(i);
    }

    public int getTotalPoints() {
        return path.size();
    }

    public ShipHead getHead(Direction dir) {
        return switch (dir){
            case NORTH -> head[0];
            case EAST -> head[1];
            case SOUTH -> head[2];
            default -> head[3];
        };
    }

    public boolean hasHead(){
        return head != null;
    }

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.put("positions", NbtUtil.writeBlockPositions(path));
        tag.put("render", NbtUtil.writeBlockPositions(renderTest));
        tag.put("direction", NbtUtil.writeDirection(facing));
        tag.put("n", head[0].write());
        tag.put("e", head[1].write());
        tag.put("s", head[2].write());
        tag.put("w", head[3].write());
        return tag;
    }

    public static TripPath read(CompoundTag tag){
        return new TripPath(NbtUtil.readBlockPositions(tag.getCompound("positions")), NbtUtil.readBlockPositions(tag.getCompound("render")), NbtUtil.readDirection(tag.getCompound("direction")), new ShipHead[] {ShipHead.read(tag.getCompound("n")), ShipHead.read(tag.getCompound("e")), ShipHead.read(tag.getCompound("s")), ShipHead.read(tag.getCompound("w"))});
    }

    public static class ShipHead {
        private int left, right, up, down;
        private final Direction facing;
        private final BlockPos front;

        public ShipHead(List<BlockPos> positions, Direction d) {
            setHorizontal(positions, d.getAxis());
            for (BlockPos pos : positions){
                int horizontal = d.getAxis() == Direction.Axis.Z ? pos.getX() : pos.getZ();
                if (left > horizontal)
                    left = horizontal;
                if (right < horizontal)
                    right = horizontal;
                if (up < pos.getY())
                    up = pos.getY();
                if (down > pos.getY())
                    down = pos.getY();
            }
            front = findFront(positions, d);
            facing = d;
        }

        public ShipHead(int left, int right, int up, int down, Direction d, BlockPos pos) {
            this.left = left;
            this.right = right;
            this.up = up;
            this.down = down;
            this.facing = d;
            this.front = pos;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public int getUp() {
            return up;
        }

        public int getDown() {
            return down;
        }

        public int getXHorizontal() {
            return switch (facing) {
                case NORTH, SOUTH -> (right - left) / 2;
                default -> 0;
            };
        }

        public int getZHorizontal() {
            return switch (facing) {
                case WEST, EAST -> (right - left) / 2;
                default -> 0;
            };
        }

        public int getVertical() {
            return (up - down) / 2;
        }

        public BlockPos getFront() {
            return front;
        }

        public BlockPos getFront(BlockPos diff) {
            return new BlockPos(diff.getX() - front.getX(), diff.getY() - front.getY(), diff.getZ() - front.getZ());
        }

        private void setHorizontal(List<BlockPos> positions, Direction.Axis axis){
            if (axis == Direction.Axis.Z){
                left = positions.get(0).getX();
                right = positions.get(0).getX();
            }
            if (axis == Direction.Axis.X){
                left = positions.get(0).getZ();
                right = positions.get(0).getZ();
            }
            up = positions.get(0).getY();
            down = positions.get(0).getY();
        }

        private BlockPos findFront(List<BlockPos> positions, Direction d) {
            int xMax = positions.get(0).getX(), xMin = positions.get(0).getX(), yMax = positions.get(0).getY(), yMin = positions.get(0).getY(), zMax = positions.get(0).getZ(), zMin = positions.get(0).getZ();
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
            return switch (d) {
                case SOUTH -> new BlockPos(xMin + ((xMax - xMin) / 2), yMin, zMax);
                case NORTH -> new BlockPos(xMin + ((xMax - xMin) / 2), yMin, zMin);
                case WEST -> new BlockPos(xMin, yMin, zMin + ((zMax - zMin) / 2));
                default -> new BlockPos(xMax, yMin, zMin + ((zMax - zMin) / 2));
            };
        }

        public CompoundTag write(){
            CompoundTag tag = new CompoundTag();
            tag.putInt("l", this.left);
            tag.putInt("r", this.right);
            tag.putInt("u", this.up);
            tag.putInt("d", this.down);
            tag.put("dr", NbtUtil.writeDirection(this.facing));
            tag.put("f", NbtUtils.writeBlockPos(this.front));
            return tag;
        }

        public static ShipHead read(CompoundTag tag){
            return new ShipHead(tag.getInt("l"), tag.getInt("r"), tag.getInt("u"), tag.getInt("d"), NbtUtil.readDirection(tag.getCompound("dr")), NbtUtils.readBlockPos(tag.getCompound("f")));
        }
    }
}
