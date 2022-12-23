package mod.stf.syconn.util;

import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.util.applications.cmd.RotateCMD;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class TripPath {

    private List<BlockPos> path = new ArrayList<>();
    private Direction facing;
    private final ShipBoundary boundary;

    public TripPath(List<BlockPos> ship, BlockPos start, BlockPos end, Direction f, Level level) {
        this.facing = f;
        this.boundary = new ShipBoundary(ship);
        genPath(start, end, level);
    }

    public TripPath(List<BlockPos> path, ShipBoundary boundary, Direction d) {
        this.path = path;
        this.boundary = boundary;
        this.facing = d;
    }

    private void genPath(BlockPos s, BlockPos e, Level l){
        int x = s.getX(), y = s.getY(), z = s.getZ();
        rotate(Direction.EAST);
        while (x < e.getX()){
            if (!getBoundary().collision(l)) {
                x++;
                boundary.move(1, 0, 0);
            } else {
                path.add(boundary.getCenter());
                rotate(Direction.SOUTH);
                while (boundary.testCollision(l, 1, 0, 0)){
                    z++;
                    boundary.move(0, 0, 1);
                }
                path.add(boundary.getCenter());
                rotate(Direction.EAST);
            }
        }
        path.add(boundary.getCenter());
        rotate(Direction.SOUTH);
        while (z < e.getZ()){
            if (!getBoundary().collision(l)) {
                z++;
                boundary.move(0, 0, 1);
            } else {
                path.add(boundary.getCenter());
                rotate(Direction.EAST);
                while (boundary.testCollision(l, 0, 0, 1)){
                    x++;
                    boundary.move(1, 0, 0);
                }
                path.add(boundary.getCenter());
                rotate(Direction.SOUTH);
            }
        }
        path.add(boundary.getCenter());
    }

    public void rotate(Direction d){
        boundary.rotate(facing, d);
        facing = d;
    }

    public ShipBoundary getBoundary() {
        return boundary;
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

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.put("positions", NbtUtil.writeBlockPositions(path));
        tag.put("direction", NbtUtil.writeDirection(facing));
        tag.put("boarder", boundary.save());
        return tag;
    }

    public static TripPath read(CompoundTag tag){
        return new TripPath(NbtUtil.readBlockPositions(tag.getCompound("positions")), ShipBoundary.read(tag.getCompound("boarder")), NbtUtil.readDirection(tag.getCompound("direction")));
    }
}
