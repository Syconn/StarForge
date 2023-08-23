package mod.stf.syconn.util;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.RotationHelper;
import mod.stf.syconn.block.NavigationalComputer;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.common.entity.MovingBlock;
import mod.stf.syconn.common.entity.MovingBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class FlightController {
    private final int speed;
    private List<TripPath.Directions> flightPath;

    public FlightController(TripPath flightPath, int speed) {
        this.flightPath = flightPath.getFlightPath();
        this.speed = speed;
    }

    public FlightController(CompoundTag tag) {
        flightPath = NbtUtil.readDirections(tag.getCompound("d"));
        speed = tag.getInt("s");
    }

    private void takeOff(Level l, NavBE be, TripPath.Directions d){
        // TODO ROTATE ENTITIES
        List<BlockID> rs = RotationHelper.rotateAllIDs(be.getShip().getBlockIDs(), be.getDir(), d.dir());
        ShipBody moved = new ShipBody(rs);
        be.setDir(d.dir());
        for (BlockID pos : rs){
            if (pos.state().getBlock() instanceof NavigationalComputer){
                MovingBlock m = new MovingBlockEntity(l, pos.state(), pos.pos(), Mths.distanceToPos(moved.getCenter(), d.pos()), d.dir(), 20, (NavBE) l.getBlockEntity(pos.pos()));
                l.addFreshEntity(m);
            } else {
                MovingBlock m = new MovingBlock(l, pos.state(), pos.pos(), Mths.distanceToPos(moved.getCenter(), d.pos()), d.dir(), 20);
                l.addFreshEntity(m);
            }
        }
        List<Entity> entities = l.getEntities(null, new AABB(be.getShip().pos1.getPos(), be.getShip().pos2.getPos()).inflate(0, 1, 0));
        entities.forEach(System.out::println);
    }

    public void start(Level l, NavBE be){
        takeOff(l, be, flightPath.remove(0));
    }

    public List<TripPath.Directions> getFlightPath() {
        return flightPath;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.put("d", NbtUtil.writeDirections(flightPath));
        tag.putInt("s", speed);
        return tag;
    }
}
