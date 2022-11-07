package mod.stf.syconn.block;

import mod.stf.syconn.api.blocks.RotatableWallBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class HyperDrive extends RotatableWallBlock {

    public HyperDrive() {
        super(Properties.of(Material.METAL).noOcclusion().strength(5.0F, 6.0F).sound(SoundType.METAL));
    }
}
