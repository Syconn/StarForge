package mod.stf.syconn.util;

import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.Mths;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.Schematic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class ShipBody extends Schematic {
    public MutablePos pos1;
    public MutablePos pos2;

    public ShipBody(List<BlockID> body) {
        super(body);
        findBoarders();
    }

    public ShipBody(Schematic body) {
        super(body.getBlockIDs());
        findBoarders();
    }

    public ShipBody(CompoundTag tag) {
        super(NbtUtil.readBlockIDS(tag.getCompound("blocks")));
        pos1 = MutablePos.read(tag.getCompound("pos1"));
        pos2 = MutablePos.read(tag.getCompound("pos2"));
    }

    public void move(int x, int y, int z) {
        List<BlockID> positions = new ArrayList<>();
        for (BlockID id : blockIDs){
            positions.add(new BlockID(id.state(), Mths.moveBlockPos(id.pos(), x, y, z)));
        }
        setBody(positions);
    }

    public void setBody(List<BlockID> positions){
        blockIDs = positions;
        findBoarders();
    }

    public void findBoarders(){
        int xMax = blockIDs.get(0).pos().getX(), xMin = blockIDs.get(0).pos().getX(), yMin = blockIDs.get(0).pos().getY(), yMax = blockIDs.get(0).pos().getY(), zMax = blockIDs.get(0).pos().getZ(), zMin = blockIDs.get(0).pos().getZ();
        for (BlockID id : blockIDs){
            BlockPos pos = id.pos();
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
        pos1 = new MutablePos(xMin, yMin, zMin);
        pos2 = new MutablePos(xMax, yMax, zMax);
    }

    public BlockPos getCenter(){
        return Mths.getCenter(pos1.getPos(), pos2.getPos());
    }

    public CompoundTag save(){
        CompoundTag tag = new CompoundTag();
        tag.put("blocks", NbtUtil.writeBlockIDS(blockIDs));
        tag.put("pos1", pos1.save());
        tag.put("pos2", pos2.save());
        return tag;
    }
}
