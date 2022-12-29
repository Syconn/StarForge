package mod.stf.syconn.util;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.init.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public record ShipBoundary(ShipBody ship) {

    public void rotate(Schematic s, Direction i, Direction t) {
        BlockPos c = ship.getCenter();
        ship.pos1.rotate(c, s, i, t);
        ship.pos2.rotate(c, s, i, t);
    }

    public void move(int x, int y, int z) {
        ship.pos1.move(x, y, z);
        ship.pos2.move(x, y, z);
    }

    public boolean collision(Level l) {
        for (BlockPos pos : getBoundary()) {
            BlockState bs = l.getBlockState(pos);
            if (!bs.is(ModTags.Blocks.OPEN_BLOCK)) {
                boolean match = false;
                for (BlockID pos3 : ship.getShip())
                    if (pos3.pos().equals(pos)) {
                        match = true;
                    }
                if (!match)
                    return true;
            }
        }
        return false;
    }

    public boolean testCollision(Level l, MutablePos.Way way, int f) {
        for (BlockPos pos : getBoundary(way.getX() * f, way.getY() * f, way.getZ() * f)) {
            BlockState bs = l.getBlockState(pos);
            if (!bs.is(ModTags.Blocks.OPEN_BLOCK)) {
                boolean match = false;
                for (BlockID pos3 : ship.getShip())
                    if (pos3.pos().equals(pos)) {
                        match = true;
                    }
                if (!match)
                    return true;
            }
        }
        return false;
    }

    public Iterable<BlockPos> getBoundary() {
        return BlockPos.betweenClosed(ship.pos1.getPos(), ship.pos2.getPos());
    }

    public Iterable<BlockPos> getBoundary(int x, int y, int z) {
        return BlockPos.betweenClosed(ship.pos1.of(x, y, z).getPos(), ship.pos2.of(x, y, z).getPos());
    }

    public Schematic getSchem() {
        return new Schematic(ship.getShip());
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.put("blocks", NbtUtil.writeBlockIDS(this.ship.getShip()));
        return tag;
    }

    public static ShipBoundary read(CompoundTag tag) {
        return new ShipBoundary(new ShipBody(tag));
    }
}
