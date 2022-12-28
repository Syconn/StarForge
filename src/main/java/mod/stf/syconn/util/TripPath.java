package mod.stf.syconn.util;

import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.Schematic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class TripPath {

    private List<BlockPos> flightPath = new ArrayList<>();
    private Direction facing;
    private final ShipBoundary boundary;

    public TripPath(List<BlockPos> ship, BlockPos end, Direction f, Level level) {
        this.facing = f;
        this.boundary = new ShipBoundary(ship);
        genPath(end, level);
    }

    public TripPath(List<BlockPos> flightPath, ShipBoundary boundary, Direction d) {
        this.flightPath = flightPath;
        this.boundary = boundary;
        this.facing = d;
    }

    private void genPath(BlockPos e, Level l) {  //0, 122, 183
        Direction endDir = facing;
        if (boundary.getCenter().getX() < e.getX()){
            moveL(Direction.EAST, Direction.SOUTH, MutablePos.Way.X, MutablePos.Way.Z, boundary.getCenter().getX(), e.getX(), l);
        }
        if (boundary.getCenter().getZ() < e.getZ()){
            moveL(Direction.EAST, Direction.SOUTH, MutablePos.Way.X, MutablePos.Way.Z, boundary.getCenter().getX(), e.getX(), l);
        }
        rotate(endDir);
    }

    private void moveL(Direction fd, Direction ad, MutablePos.Way mw, MutablePos.Way aw, int p, int e, Level l) {
        rotate(fd);
        while (p < e){
            if (!getBoundary().collision(l)) {
                p++;
                move(mw, 1);
            } else {
                flightPath.add(boundary.getCenter());
                rotate(ad);
                while (boundary.testCollision(l, mw, 1)){
                    move(aw, 1);
                }
                flightPath.add(boundary.getCenter());
            }
        }
        flightPath.add(boundary.getCenter());
    }

    private void moveG(Direction fd, Direction ad, MutablePos.Way mw, MutablePos.Way aw, int p, int e, Level l) {
        rotate(fd);
        while (p > e){
            if (!getBoundary().testCollision(l, mw, -1)) {
                p++;
                move(MutablePos.Way.X, -1);
            } else {
                flightPath.add(boundary.getCenter());
                rotate(ad);
                while (boundary.testCollision(l, mw, -1)){
                    move(aw, -1);
                }
                flightPath.add(boundary.getCenter());
            }
        }
        flightPath.add(boundary.getCenter());
    }

    private void rotate(Direction d){
        if (facing != d) {
            boundary.rotate(boundary.getSchem(), facing, d);
            facing = d;
        }
    }

    private void move(MutablePos.Way way, int f){
        boundary.move(way.getX() * f, way.getY() * f, way.getZ() * f);
    }

    public ShipBoundary getBoundary() {
        return boundary;
    }

    public BlockPos getTarget(){
        return flightPath.get(flightPath.size() - 1);
    }

    public BlockPos getPoint(int i){
        return flightPath.get(i);
    }

    public int getTotalPoints() {
        return flightPath.size();
    }

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.put("positions", NbtUtil.writeBlockPositions(flightPath));
        tag.put("direction", NbtUtil.writeDirection(facing));
        tag.put("boarder", boundary.save());
        return tag;
    }

    public static TripPath read(CompoundTag tag){
        return new TripPath(NbtUtil.readBlockPositions(tag.getCompound("positions")), ShipBoundary.read(tag.getCompound("boarder")), NbtUtil.readDirection(tag.getCompound("direction")));
    }
}
