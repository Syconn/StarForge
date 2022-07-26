package mod.stf.syconn.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class MovableLightBlock {

    public static void createLightSource(BlockPos pos, Level level, ItemStack stack, int brightness){
        level.setBlock(pos, Blocks.LIGHT.defaultBlockState().setValue(BlockStateProperties.LEVEL, brightness), 2);
        stack.getOrCreateTag().put("lightPos", NbtUtils.writeBlockPos(pos));
    }

    public static boolean hasLightSource(ItemStack stack){
        return stack.getOrCreateTag().contains("lightPos");
    }

    public static boolean stillExists(Level level, ItemStack stack){
        if (hasLightSource(stack)){
            BlockPos lastPos = NbtUtils.readBlockPos(stack.getOrCreateTag().getCompound("lightPos"));
            return level.getBlockState(lastPos).getBlock() instanceof LightBlock;
        }
        return false;
    }

    public static boolean playerMoved(ItemStack stack, BlockPos pos){
        if (hasLightSource(stack)){
            BlockPos lastPos = NbtUtils.readBlockPos(stack.getOrCreateTag().getCompound("lightPos"));
            return !lastPos.equals(pos);
        }
        return true;
    }

    public static void moveLightSource(BlockPos newPos, Level level, ItemStack stack, int brightness){
        BlockPos lastPos = NbtUtils.readBlockPos(stack.getOrCreateTag().getCompound("lightPos"));
        stack.getOrCreateTag().put("lightPos", NbtUtils.writeBlockPos(newPos));
        level.setBlock(newPos, Blocks.LIGHT.defaultBlockState().setValue(BlockStateProperties.LEVEL, brightness), 2);
        level.setBlock(lastPos, Blocks.AIR.defaultBlockState(), 2);
    }

    public static void removeLightSource(ItemStack stack, Level level){
        BlockPos pos = NbtUtils.readBlockPos(stack.getOrCreateTag().getCompound("lightPos"));
        stack.getOrCreateTag().remove("lightPos");
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        System.out.println(level.getBlockState(pos).getBlock());
        System.out.println(stack.getOrCreateTag().contains("lightPos"));
    }
}
