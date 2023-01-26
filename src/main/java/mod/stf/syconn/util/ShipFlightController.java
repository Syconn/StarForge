package mod.stf.syconn.util;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.RotationHelper;
import mod.stf.syconn.block.NavigationalComputer;
import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.common.entity.MovingBlock;
import mod.stf.syconn.common.entity.MovingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ShipFlightController {

    private ShipBody ship;
    private final int speed;
    private Direction facing;
    private List<TripPath.Directions> flightPath;

    public ShipFlightController(ShipBody ship, Direction d, List<TripPath.Directions> flightPath, int speed) {
        this.ship = ship;
        this.flightPath = flightPath;
        this.facing = d;
        this.speed = speed;
    }

    public ShipFlightController(CompoundTag tag) {
        ship = new ShipBody(tag.getCompound("ship"));
        flightPath = NbtUtil.readDirections(tag.getCompound("d"));
        speed = tag.getInt("s");
        facing = NbtUtil.readDirection(tag.getCompound("dir"));
    }

    private void takeOff(Level l, TripPath.Directions d){
        //TODO ROTATE ENTITIES
        List<BlockID> rs = RotationHelper.rotateAllIDs(ship.getShip(), facing, d.dir());
        ShipBody moved = new ShipBody(rs);
        facing = d.dir();
        for (BlockID pos : rs){
            if (pos.state().getBlock() instanceof NavigationalComputer){
                MovingBlock m = new MovingBlockEntity(l, this, pos.state(), pos.pos(), Mths.distanceToPos(moved.getCenter(), d.pos()), d.dir(), 20, (NavBE) l.getBlockEntity(pos.pos()));
                l.addFreshEntity(m);
            } else {
                MovingBlock m = new MovingBlock(l, this, pos.state(), pos.pos(), Mths.distanceToPos(moved.getCenter(), d.pos()), d.dir(), 20);
                l.addFreshEntity(m);
            }
        }
        List<Entity> entities = l.getEntities(null, new AABB(ship.pos1.getPos(), ship.pos2.getPos()).inflate(0, 1, 0));
        entities.forEach(System.out::println);
    }

    public void start(Level l){
        takeOff(l, flightPath.remove(0));
    }

    public void reachDestination(Level l, ShipBody ship){
        if (!flightPath.isEmpty()) {
            this.ship = ship;
            //takeOff(l, flightPath.remove(0));
            for (BlockID id : ship.getShip()) {
                l.setBlock(id.pos(), Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.put("ship", ship.save());
        tag.put("d", NbtUtil.writeDirections(flightPath));
        tag.putInt("s", speed);
        tag.put("dir", NbtUtil.writeDirection(facing));
        return tag;
    }
}
