package mod.stf.syconn.block;

import mod.stf.syconn.api.blocks.RotatableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class HyperDrive extends RotatableBlock {

    public HyperDrive() {
        super(Properties.of(Material.METAL).noOcclusion().strength(5.0F, 6.0F).sound(SoundType.METAL));
    }
}
