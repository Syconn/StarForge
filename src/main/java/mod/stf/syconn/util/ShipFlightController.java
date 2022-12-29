package mod.stf.syconn.util;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.RotationHelper;
import mod.stf.syconn.common.entity.MovingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ShipFlightController {

    private final ShipBody ship;
    private final int speed;
    private List<TripPath.Directions> flightPath;

    public ShipFlightController(ShipBody ship, List<TripPath.Directions> flightPath, int speed) {
        this.ship = ship;
        this.flightPath = flightPath;
        this.speed = speed;
    }

    public ShipFlightController(CompoundTag tag) {
        ship = new ShipBody(tag.getCompound("ship"));
        flightPath = NbtUtil.readDirections(tag.getCompound("d"));
        speed = tag.getInt("s");
    }

//    private void land(Level l, ){
//        for ()
//    }

    private void takeOff(Level l, TripPath.Directions d, Direction i){
        //TODO ROTATE ENTITIES
        List<BlockID> rs = RotationHelper.rotateAllIDs(ship.getShip(), i, d.dir());
        for (BlockID pos : rs){
            MovingBlock m = new MovingBlock(l, this, pos.state(), Mths.moveBlockPos(pos.pos(), 0, 8, 0), d.pos(), d.dir(), speed);
            l.addFreshEntity(m);
        }
    }

    public void start(Level l){
        takeOff(l, flightPath.get(0), ship.getFacing());
        flightPath.remove(0);
    }

    public void reachDestination(Level l){
        System.out.println("ARRIVED");
//        if (!flightPath.isEmpty()) {
//            takeOff(l, flightPath.get(0), ship.getFacing());
//            flightPath.remove(0);
//        }
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.put("ship", ship.save());
        tag.put("d", NbtUtil.writeDirections(flightPath));
        tag.putInt("s", speed);
        return tag;
    }
}
