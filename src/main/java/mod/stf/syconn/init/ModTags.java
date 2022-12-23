package mod.stf.syconn.init;

import mod.stf.syconn.Reference;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks
    {
        public static final TagKey<Block> OPEN_BLOCK = tag("open_block");

        private static TagKey<Block> tag(String name)
        {
            return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Reference.MOD_ID, name));
        }
    }

    public static class Items
    {

        private static TagKey<Item> tag(String name)
        {
            return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Reference.MOD_ID, name));
        }
    }
}
