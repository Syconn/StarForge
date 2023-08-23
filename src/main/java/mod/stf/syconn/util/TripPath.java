package mod.stf.syconn.util;

import mod.stf.syconn.api.util.NbtUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

import static mod.stf.syconn.api.util.NbtUtil.readDirections;
import static mod.stf.syconn.api.util.NbtUtil.writeDirections;

public class TripPath {

    private List<Directions> flightPath = new ArrayList<>();
    private final ShipBoundary boundary;
    private Direction facing;

    public TripPath(ShipBody ship, BlockPos end, Direction facing, Level level) {
        this.boundary = new ShipBoundary(ship);
        this.facing = facing;
        genPath(end, level);
    }

    public TripPath(List<Directions> flightPath, Direction facing, ShipBoundary boundary) {
        this.flightPath = flightPath;
        this.boundary = boundary;
        this.facing = facing;
    }

    private void genPath(BlockPos e, Level l) {
        System.out.println(boundary.ship().getCenter());
        while (boundary.ship().getCenter().getX() != e.getX() || boundary.ship().getCenter().getZ() != e.getZ()) {
            if (boundary.ship().getCenter().getX() < e.getX()) {
                moveL(Direction.EAST, Direction.SOUTH, MutablePos.Way.X, MutablePos.Way.Z, boundary.ship().getCenter().getX(), e.getX(), l);
            }
            if (boundary.ship().getCenter().getZ() < e.getZ()) {
                moveL(Direction.SOUTH, Direction.EAST, MutablePos.Way.Z, MutablePos.Way.X, boundary.ship().getCenter().getZ(), e.getZ(), l);
            }
            if (boundary.ship().getCenter().getX() > e.getX()) {
                moveG(Direction.WEST, Direction.NORTH, MutablePos.Way.X, MutablePos.Way.Z, boundary.ship().getCenter().getX(), e.getX(), l);
            }
            if (boundary.ship().getCenter().getZ() > e.getZ()) {
                moveG(Direction.NORTH, Direction.WEST, MutablePos.Way.Z, MutablePos.Way.X, boundary.ship().getCenter().getZ(), e.getZ(), l);
            }
        }
        //handHeight(boundary.ship().getCenter().getY(), e.getY(), l);
        rotate(facing);
    }

    private void handHeight(int p, int e, Level l){
        while (p > e){
            if (!getBoundary().collision(l)) {
                move(MutablePos.Way.Y, -1);
                p--;
            } else {
                p = e;
            }
        }
        while (p < e){
            if (!getBoundary().collision(l)) {
                move(MutablePos.Way.Y, 1);
                p++;
            } else {
                p = e;
            }
        }
        if (!flightPath.isEmpty())
            flightPath.add(new Directions(boundary.ship().getCenter(), flightPath.get(flightPath.size() - 1).dir));
    }

    private void moveL(Direction fd, Direction ad, MutablePos.Way mw, MutablePos.Way aw, int p, int e, Level l) {
        rotate(fd);
        while (p < e){
            if (!getBoundary().collision(l)) {
                p++;
                move(mw, 1);
            } else {
                flightPath.add(new Directions(boundary.ship().getCenter(), fd));
                rotate(ad);
                while (boundary.testCollision(l, mw, 1)){
                    move(aw, 1);
                }
                flightPath.add(new Directions(boundary.ship().getCenter(), ad));
                rotate(fd);
            }
        }
        flightPath.add(new Directions(boundary.ship().getCenter(), fd));
    }

    private void moveG(Direction fd, Direction ad, MutablePos.Way mw, MutablePos.Way aw, int p, int e, Level l) {
        rotate(fd);
        while (p > e){
            if (!getBoundary().collision(l)) {
                p--;
                move(mw, -1);
            } else {
                flightPath.add(new Directions(boundary.ship().getCenter(), fd));
                rotate(ad);
                while (boundary.testCollision(l, mw, -1)){
                    move(aw, -1);
                }
                flightPath.add(new Directions(boundary.ship().getCenter(), ad));
                rotate(fd);
            }
        }
        flightPath.add(new Directions(boundary.ship().getCenter(), fd));
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
        return flightPath.get(flightPath.size() - 1).pos();
    }

    public Directions getDirections(int i){
        return flightPath.get(i);
    }

    public int getTotalPoints() {
        return flightPath.size();
    }

    public List<Directions> getFlightPath() {
        return flightPath;
    }

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.put("ds", writeDirections(flightPath));
        tag.put("boarder", boundary.save());
        tag.put("d", NbtUtil.writeDirection(facing));
        return tag;
    }

    public static TripPath read(CompoundTag tag){
        return new TripPath(readDirections(tag.getCompound("ds")), NbtUtil.readDirection(tag.getCompound("d")), ShipBoundary.read(tag.getCompound("boarder")));
    }

    public record Directions(BlockPos pos, Direction dir) {
        public CompoundTag save(){
            CompoundTag tag = new CompoundTag();
            tag.put("p", NbtUtils.writeBlockPos(pos));
            tag.put("d", NbtUtil.writeDirection(dir));
            return tag;
        }

        public static Directions read(CompoundTag tag){
            return new Directions(NbtUtils.readBlockPos(tag.getCompound("p")), NbtUtil.readDirection(tag.getCompound("d")));
        }
    }
}
