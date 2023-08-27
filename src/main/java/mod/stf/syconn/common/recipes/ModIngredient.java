package mod.stf.syconn.common.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public class ModIngredient extends Ingredient {
    private final Value itemList;
    private final int count;

    protected ModIngredient(Stream<? extends Value> itemList, int count)
    {
        super(itemList);
        this.itemList = null;
        this.count = count;
    }

    private ModIngredient(Value itemList, int count)
    {
        super(Stream.of(itemList));
        this.itemList = itemList;
        this.count = count;
    }

    public int getCount()
    {
        return this.count;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer()
    {
        return Serializer.INSTANCE;
    }

    public static ModIngredient fromJson(JsonObject object)
    {
        Ingredient.Value value = valueFromJson(object);
        int count = GsonHelper.getAsInt(object, "count", 1);
        return new ModIngredient(Stream.of(value), count);
    }

    public JsonElement toJson()
    {
        JsonObject object = this.itemList.serialize();
        object.addProperty("count", this.count);
        return object;
    }

    public static ModIngredient of(ItemLike provider, int count)
    {
        return new ModIngredient(new Ingredient.ItemValue(new ItemStack(provider)), count);
    }

    public static ModIngredient of(ItemStack stack, int count)
    {
        return new ModIngredient(new Ingredient.ItemValue(stack), count);
    }

    public static ModIngredient of(TagKey<Item> tag, int count)
    {
        return new ModIngredient(new Ingredient.TagValue(tag), count);
    }

    public static ModIngredient of(ResourceLocation id, int count)
    {
        return new ModIngredient(new UnknownValue(id), count);
    }

    public static class Serializer implements IIngredientSerializer<ModIngredient>
    {
        public static final ModIngredient.Serializer INSTANCE = new ModIngredient.Serializer();

        @Override
        public ModIngredient parse(FriendlyByteBuf buffer)
        {
            int itemCount = buffer.readVarInt();
            int count = buffer.readVarInt();
            Stream<ItemValue> values = Stream.generate(() -> new ItemValue(buffer.readItem())).limit(itemCount);
            return new ModIngredient(values, count);
        }

        @Override
        public ModIngredient parse(JsonObject object)
        {
            return ModIngredient.fromJson(object);
        }

        @Override
        public void write(FriendlyByteBuf buffer, ModIngredient ingredient)
        {
            buffer.writeVarInt(ingredient.getItems().length);
            buffer.writeVarInt(ingredient.count);
            for(ItemStack stack : ingredient.getItems())
            {
                buffer.writeItem(stack);
            }
        }
    }

    /**
     * Allows ability to define an ingredient from another mod without adding it as a dependency in
     * the development environment. Serializes the data to be read by the regular
     * {@link ItemValue}. Only use this for generating data.
     */
    @SuppressWarnings("ClassCanBeRecord")
    public static class UnknownValue implements Ingredient.Value
    {
        private final ResourceLocation id;

        public UnknownValue(ResourceLocation id)
        {
            this.id = id;
        }

        @Override
        public Collection<ItemStack> getItems()
        {
            return Collections.emptyList();
        }

        @Override
        public JsonObject serialize()
        {
            JsonObject object = new JsonObject();
            object.addProperty("item", this.id.toString());
            return object;
        }
    }
}
