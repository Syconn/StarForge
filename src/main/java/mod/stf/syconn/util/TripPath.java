package mod.stf.syconn.util;

import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.Schematic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class TripPath {

    private List<Directions> flightPath = new ArrayList<>();
    private Direction facing;
    private final ShipBoundary boundary;

    public TripPath(List<BlockPos> ship, BlockPos end, Direction f, Level level) {
        this.facing = f;
        this.boundary = new ShipBoundary(ship);
        genPath(end, level);
    }

    public TripPath(List<Directions> flightPath, ShipBoundary boundary, Direction d) {
        this.flightPath = flightPath;
        this.boundary = boundary;
        this.facing = d;
    }

    private void genPath(BlockPos e, Level l) {  //-40, 122, 143
        Direction endDir = facing;
        while (boundary.getCenter().getX() != e.getX() || boundary.getCenter().getZ() != e.getZ()) {
            if (boundary.getCenter().getX() < e.getX()) {
                moveL(Direction.EAST, Direction.SOUTH, MutablePos.Way.X, MutablePos.Way.Z, boundary.getCenter().getX(), e.getX(), l);
            }
            if (boundary.getCenter().getZ() < e.getZ()) {
                moveL(Direction.SOUTH, Direction.EAST, MutablePos.Way.Z, MutablePos.Way.X, boundary.getCenter().getZ(), e.getZ(), l);
            }
            if (boundary.getCenter().getX() > e.getX()) {
                moveG(Direction.WEST, Direction.NORTH, MutablePos.Way.X, MutablePos.Way.Z, boundary.getCenter().getX(), e.getX(), l);
            }
            if (boundary.getCenter().getZ() > e.getZ()) {
                moveG(Direction.NORTH, Direction.WEST, MutablePos.Way.Z, MutablePos.Way.X, boundary.getCenter().getZ(), e.getZ(), l);
            }
        }
        handHeight(boundary.getCenter().getY(), e.getY(), l);
        rotate(endDir);
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
        flightPath.add(new Directions(boundary.getCenter(), flightPath.get(flightPath.size() - 1).dir));
    }

    private void moveL(Direction fd, Direction ad, MutablePos.Way mw, MutablePos.Way aw, int p, int e, Level l) {
        rotate(fd);
        while (p < e){
            if (!getBoundary().collision(l)) {
                p++;
                move(mw, 1);
            } else {
                flightPath.add(new Directions(boundary.getCenter(), fd));
                rotate(ad);
                while (boundary.testCollision(l, mw, 1)){
                    move(aw, 1);
                }
                flightPath.add(new Directions(boundary.getCenter(), ad));
                rotate(fd);
            }
        }
        flightPath.add(new Directions(boundary.getCenter(), fd));
    }

    private void moveG(Direction fd, Direction ad, MutablePos.Way mw, MutablePos.Way aw, int p, int e, Level l) {
        rotate(fd);
        while (p > e){
            if (!getBoundary().collision(l)) {
                p--;
                move(mw, -1);
            } else {
                flightPath.add(new Directions(boundary.getCenter(), fd));
                rotate(ad);
                while (boundary.testCollision(l, mw, -1)){
                    move(aw, -1);
                }
                flightPath.add(new Directions(boundary.getCenter(), ad));
                rotate(fd);
            }
        }
        flightPath.add(new Directions(boundary.getCenter(), fd));
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

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.put("ds", writeDirections(flightPath));
        tag.put("direction", NbtUtil.writeDirection(facing));
        tag.put("boarder", boundary.save());
        return tag;
    }

    public static TripPath read(CompoundTag tag){
        return new TripPath(readDirections(tag.getCompound("ds")), ShipBoundary.read(tag.getCompound("boarder")), NbtUtil.readDirection(tag.getCompound("direction")));
    }

    public static CompoundTag writeDirections(List<Directions> ds){
        CompoundTag tag = new CompoundTag();
        ListTag listTag = new ListTag();
        for (Directions d : ds){
            CompoundTag data = new CompoundTag();
            data.put("pos", d.save());
            listTag.add(data);
        }
        tag.put("map", listTag);
        return tag;
    }

    public static List<Directions> readDirections(CompoundTag tag){
        List<Directions> directions = new ArrayList<>();
        if(tag.contains("map", Tag.TAG_LIST))
        {
            ListTag list = tag.getList("map", Tag.TAG_COMPOUND);
            list.forEach(data -> {
                CompoundTag nbt = (CompoundTag) data;
                directions.add(Directions.read(nbt.getCompound("pos")));
            });
        }
        return directions;
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
