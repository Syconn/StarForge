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
        genPath(new BlockPos(-40, 120, 145), level);
    }

    public TripPath(List<BlockPos> flightPath, ShipBoundary boundary, Direction d) {
        this.flightPath = flightPath;
        this.boundary = boundary;
        this.facing = d;
    }

    private void genPath(BlockPos e, Level l) {  //-40, 120, 145                               //0, 122, 183
        Direction endDir = facing;
        int attempts = 1000;
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
