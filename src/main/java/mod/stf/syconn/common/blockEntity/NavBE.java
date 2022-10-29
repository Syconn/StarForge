package mod.stf.syconn.common.blockEntity;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.blockEntity.ApplicationBE;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.util.data.DirectionalTexture;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import mod.stf.syconn.common.containers.NavContainer;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NavBE extends ApplicationBE<NavContainer> {

    private Schematic ship;
    private List<BlockPos> schematic = null;

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, null);
    }

    public NavBE(BlockPos pWorldPosition, BlockState pBlockState, BasicApplication<NavContainer> application) {
        super(ModBlockEntities.NAV_BE.get(), pWorldPosition, pBlockState, application);
    }

    public void setShip(Schematic ship) {
        this.ship = ship;
        update(worldPosition, getBlockState());
        createBlockImage();
    }

    public Schematic getShip() {
        return ship;
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag tag = super.saveData();
        tag.put("ship", ship.saveSchematic());
        if (blockImage != null)
            tag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("ship", ship.saveSchematic());
        if (!blockImage.isEmpty())
            tag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        ship = Schematic.readSchematic(tag.getCompound("ship"));
        if (tag.contains("blockimage"))
            blockImage = NbtUtil.readServerImageList(tag.getCompound("blockimage"));
        super.load(tag);
    }

    public void createBlockImage(){
        blockImage = new HashMap<>();
        if (schematic != null) {
            try {
                for (BlockPos pos : schematic){
                    BlockState state = level.getBlockState(pos);
                    DirectionalTexture[] sprites = new DirectionalTexture[Direction.values().length];
                    for (int i = 0; i < Direction.values().length; i++){
                        if (!Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).isEmpty())
                            sprites[i] = new DirectionalTexture(Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).get(0).getSprite(), Direction.from3DDataValue(i));
                    }
                    NativeImage background = NativeImage.read(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png")).getInputStream());
                    if (sprites.length == 6)
                        blockImage.put(pos, new ServerPixelImage(background, sprites));
                }
                update(worldPosition, getBlockState());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    public Map<BlockPos, ServerPixelImage> getImages(){
        return blockImage;
    }

    @Override
    public void tickServer() {

    }

    @Override
    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Nav Computer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new NavContainer(pContainerId, worldPosition, pPlayerInventory, pPlayer);
    }
}
