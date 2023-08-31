package mod.stf.syconn.common.blockEntity;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.blockEntity.ClientMenuBlockEntity;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.DirectionalTexture;
import mod.stf.syconn.api.util.data.PixelImage;
import mod.stf.syconn.common.containers.SchematicContainer;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchematicBe extends ClientMenuBlockEntity {

    private HashMap<BlockPos, PixelImage> blockImage = new HashMap<>();
    private List<BlockPos> schematic = null;

    public SchematicBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SCHEMATIC_BE.get(), pWorldPosition, pBlockState);
    }

    public void createBlockImage(){
        blockImage = new HashMap<>();
        if (schematic != null) {
            try {
                for (BlockPos pos : schematic){
                    BlockState state = level.getBlockState(pos);
                    DirectionalTexture[] sprites = new DirectionalTexture[Direction.values().length];
                    for (int i = 0; i < Direction.values().length; i++){
                        if (!Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), RandomSource.create()).isEmpty())
                            sprites[i] = new DirectionalTexture(Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), RandomSource.create()).get(0).getSprite().contents().getOriginalImage(), Direction.from3DDataValue(i));
                    }
                    NativeImage background = NativeImage.read(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png")).get().open());
                    if (sprites.length == 6)
                        blockImage.put(pos, new PixelImage(background, sprites));
                }
                update(worldPosition, getBlockState());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSchematic(List<BlockPos> schematic) {
        this.schematic = schematic;
    }

    public DynamicTexture getBlockImage(BlockPos pos) {
        if (blockImage != null)
            return blockImage.get(pos).getImageFromPixels();
        return null;
    }

    public Map<BlockPos, PixelImage> getImages(){
        return blockImage;
    }

    protected CompoundTag saveData() {
        CompoundTag pTag = new CompoundTag();
        if (blockImage != null)
            pTag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
        pTag.put("items", itemHandler.serializeNBT());
        return pTag;
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!blockImage.isEmpty())
            pTag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("blockimage"))
            blockImage = NbtUtil.readServerImageList(pTag.getCompound("blockimage"));
    }

    public void tickServer() {

    }

    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    public Component getDisplayName() {
        return Component.literal("Schematic");
    }

    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SchematicContainer(pContainerId, worldPosition, pPlayerInventory, pPlayer);
    }

    public ItemStackHandler getInventory() {
        return itemHandler;
    }
}
