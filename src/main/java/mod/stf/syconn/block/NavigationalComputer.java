package mod.stf.syconn.block;

import mod.stf.syconn.common.blockEntity.NavBE;
import mod.stf.syconn.util.applications.NavigationApplication;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class NavigationalComputer extends Computer {

    public NavigationalComputer() {

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new NavBE(pPos, pState, new NavigationApplication(pPos));
    }
}
