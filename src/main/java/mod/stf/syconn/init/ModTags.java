package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static TagKey<Block> HIDE_FACE_BLOCK = BlockTags.create(Reference.loc("hide_face_blocks"));
        public static TagKey<Block> DONT_RENDER_BLOCK = BlockTags.create(Reference.loc("dont_render_block"));
        public static TagKey<Block> NOT_GROUND_BLOCK = BlockTags.create(Reference.loc("not_ground_block"));
    }
}
