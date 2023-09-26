package mod.stf.syconn.item;

import mod.stf.syconn.init.ModBlocks;
import mod.stf.syconn.world.data.ChunkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class ProbeLinkItem extends Item {

    public ProbeLinkItem() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).durability(20));
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag tag = stack.getOrCreateTag();
        BlockPos hit = rayTrace(pPlayer);
        if (!hit.equals(BlockPos.ZERO) && pLevel.getBlockState(hit).is(ModBlocks.PROBE.get())) {
            if (tag.contains("blockpos")) {
                ListTag map = tag.getList("blockpos", Tag.TAG_COMPOUND);
                if (shouldAddToMap(map, hit)) {
                    CompoundTag pTag = new CompoundTag();
                    pTag.put("pos", NbtUtils.writeBlockPos(hit));
                    map.add(pTag);
                    tag.put("blockpos", map);
                }
            } else {
                ListTag map = new ListTag();
                CompoundTag pTag = new CompoundTag();
                pTag.put("pos", NbtUtils.writeBlockPos(hit));
                map.add(pTag);
                tag.put("blockpos", map);
            }
        }

        pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, ps -> ps.broadcastBreakEvent(pUsedHand));
        return InteractionResultHolder.consume(stack);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        if (level.getBlockState(pContext.getClickedPos()).is(ModBlocks.MAP_PROJECTOR.get())) {
            level.getCapability(ChunkManager.CHUNKS).ifPresent(cap -> cap.addProjectorOptions(pContext.getClickedPos(), getBlockPos(pContext.getItemInHand().getOrCreateTag())));
            pContext.getPlayer().getItemInHand(pContext.getHand()).getOrCreateTag().remove("blockpos");
            return InteractionResult.SUCCESS;
        }
        return super.useOn(pContext);
    }

    private BlockPos rayTrace(Player player) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 100; //distance
        Vec3 vec3d1 = vec3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        HitResult rts = player.getLevel().clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, player));

        return rts.getType() == HitResult.Type.BLOCK ? ((BlockHitResult) rts).getBlockPos() : BlockPos.ZERO;
    }

    private List<BlockPos> getBlockPos(CompoundTag tag) {
        List<BlockPos> list = new ArrayList<>();
        if (tag.contains("blockpos")) {
            ListTag map = tag.getList("blockpos", Tag.TAG_COMPOUND);
            map.forEach(nbt -> list.add(NbtUtils.readBlockPos(((CompoundTag) nbt).getCompound("pos"))));
        }
        return list;
    }

    private boolean shouldAddToMap(ListTag map, BlockPos pos) {
        for (Tag tag : map) if (NbtUtils.readBlockPos(((CompoundTag) tag).getCompound("pos")).equals(pos)) return false;
        return true;
    }
}
