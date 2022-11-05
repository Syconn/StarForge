package mod.stf.syconn.common.blockEntity;

import com.mojang.blaze3d.platform.NativeImage;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.blockEntity.ClientBlockEntity;
import mod.stf.syconn.api.blockEntity.ClientMenuBlockEntity;
import mod.stf.syconn.api.util.AnchorPos;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.data.DirectionalTexture;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import mod.stf.syconn.common.containers.SchematicContainer;
import mod.stf.syconn.init.ModBlockEntities;
import mod.stf.syconn.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

public class SchematicBe extends ClientMenuBlockEntity {
    private Schematic schematic = null;
    private AnchorPos pos;
    private Map<BlockState, double[]> position;
    protected HashMap<BlockID, ServerPixelImage> blockImage = new HashMap<>();

    public SchematicBe(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SCHEMATIC_BE.get(), pWorldPosition, pBlockState);
    }

    public void createBlockImage(){
        blockImage = new HashMap<>();
        position = new HashMap<>();
        if (schematic != null) {
            try {
                for (int b = 0; b < schematic.getStates().size(); b++){
                    BlockState state = schematic.getStates().get(b);
                    DirectionalTexture[] sprites = new DirectionalTexture[Direction.values().length];
                    for (int i = 0; i < Direction.values().length; i++){
                        if (!Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).isEmpty())
                            sprites[i] = new DirectionalTexture(Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).get(0).getSprite(), Direction.from3DDataValue(i));
                    }
                    NativeImage background = NativeImage.read(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png")).getInputStream());
                    if (sprites.length == 6)
                        blockImage.put(new BlockID(state, schematic.getBlocks().get(b)), new ServerPixelImage(background, sprites));
                    position.put(state, genPosition(schematic.getBlocks().get(b), pos));
                }
                update(worldPosition, getBlockState());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private double[] genPosition(BlockPos pos, AnchorPos anchorPos){
        double[] position = new double[6];
        double xDif = anchorPos.x() - pos.getX();
        double yDif = anchorPos.y() - pos.getY();
        double zDif = anchorPos.z() - pos.getZ();
        position[0] = xDif;
        position[1] = yDif;
        position[2] = zDif;
        position[3] = 0;
        position[4] = 0;
        position[5] = 0;
        if (xDif > 0){
            position[3] = -4;
        } else if (xDif < 0) {
            position[3] = 4;
        }
        if (zDif > 0){
            position[5] = -4;
        } else if (zDif < 0) {
            position[5] = 4;
        }
        if (yDif < 0){
            position[4] = 4 * -yDif;
        }
        return position;
    }

    private AnchorPos anchorPos(List<BlockPos> posses) {
        BlockPos pos = posses.get(0);
        double bX = pos.getX(), bY = pos.getY(), bZ = pos.getZ(), sX = pos.getX(), sY = pos.getY(), sZ = pos.getZ();
        for (BlockPos pos2 : posses) {
            if (pos2.getX() > bX) bX = pos2.getX();
            if (pos2.getY() > bY) bY = pos2.getY();
            if (pos2.getZ() > bZ) bZ = pos2.getZ();
            if (pos2.getX() < sX) sX = pos2.getX();
            if (pos2.getY() < sY) sY = pos2.getY();
            if (pos2.getZ() < sZ) sZ = pos2.getZ();
        }
        return new AnchorPos((bX + sX) / 2, sY, (bZ + sZ) / 2);
    }

    public void setSchematic(Schematic schematic) {
        this.schematic = schematic;
    }

    @Nullable
    public Map<BlockID, ServerPixelImage> getImages(){
        return blockImage;
    }

    public AnchorPos getAnchor(){
        return pos;
    }

    public double[] getPosition(BlockState state) {
        return position.get(state);
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag pTag = new CompoundTag();
        if (blockImage != null)
            pTag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
        if (pos != null)
            pTag.put("pos", pos.save());
        pTag.put("items", itemHandler.serializeNBT());
        return pTag;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!blockImage.isEmpty())
            pTag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
        if (pos != null)
            pTag.put("pos", pos.save());
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("blockimage"))
            blockImage = NbtUtil.readServerImageList(pTag.getCompound("blockimage"));
        if (pTag.contains("pos"))
            pos = AnchorPos.read(pTag.getCompound("pos"));
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
        return new TextComponent("Schematic");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SchematicContainer(pContainerId, worldPosition, pPlayerInventory, pPlayer);
    }
}
