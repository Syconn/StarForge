package mod.stf.syconn.common.blockEntity;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.math.Vector3f;
import mod.stf.syconn.Reference;
import mod.stf.syconn.api.blockEntity.ApplicationBE;
import mod.stf.syconn.api.util.AnchorPos;
import mod.stf.syconn.api.util.BlockID;
import mod.stf.syconn.api.util.NbtUtil;
import mod.stf.syconn.api.util.applications.BasicApplication;
import mod.stf.syconn.api.util.data.DirectionalTexture;
import mod.stf.syconn.api.util.data.Schematic;
import mod.stf.syconn.api.util.data.ServerPixelImage;
import mod.stf.syconn.client.rendering.blockentity.NavRender;
import mod.stf.syconn.common.containers.NavContainer;
import mod.stf.syconn.init.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NavBE extends ApplicationBE<NavContainer> {

    private Schematic ship;
    private AnchorPos pos;

    private Map<BlockID, double[]> position;

    protected HashMap<BlockID, ServerPixelImage> blockImage = new HashMap<>();

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

    public AnchorPos getAnchor(){
        return pos;
    }

    public double[] getPosition(BlockID state) {
        return position.get(state);
    }

    @Override
    protected CompoundTag saveData() {
        CompoundTag tag = super.saveData();
        if (ship != null)
            tag.put("ship", ship.saveSchematic());
        if (blockImage != null)
            tag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        if (ship != null)
            tag.put("ship", ship.saveSchematic());
        if (!blockImage.isEmpty())
            tag.put("blockimage", NbtUtil.writeServerImageList(blockImage));
        if (pos != null)
            tag.put("pos", pos.save());
        if (position != null)
            tag.put("positions", NbtUtil.writePositions(position));
        super.saveAdditional(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("ship"))
            ship = Schematic.readSchematic(tag.getCompound("ship"));
        if (tag.contains("blockimage"))
            blockImage = NbtUtil.readServerImageList(tag.getCompound("blockimage"));
        if (tag.contains("pos"))
            pos = AnchorPos.read(tag.getCompound("pos"));
        if (tag.contains("positions"))
            position = NbtUtil.readPositions(tag.getCompound("positions"));
        super.load(tag);
    }

    public NativeImage getBlockImage(BlockID state) {
        if (blockImage != null)
            return blockImage.get(state).getImageFromPixels();
        return null;
    }

    @Nullable
    public Map<BlockID, ServerPixelImage> getImages(){
        return blockImage;
    }

    public void createBlockImage(){
        blockImage = new HashMap<>();
        position = new HashMap<>();
        if (ship != null) {
            try {
                pos = anchorPos(ship.getBlocks());
                for (int b = 0; b < ship.getStates().size(); b++){
                    BlockState state = ship.getStates().get(b);
                    DirectionalTexture[] sprites = new DirectionalTexture[Direction.values().length];
                    for (int i = 0; i < Direction.values().length; i++){
                        if (!Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).isEmpty())
                            sprites[i] = new DirectionalTexture(Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getQuads(state, Direction.from3DDataValue(i), new Random()).get(0).getSprite(), Direction.from3DDataValue(i));
                    }
                    NativeImage background = NativeImage.read(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Reference.MOD_ID, "textures/entity/block.png")).getInputStream());
                    if (sprites.length == 6)
                        blockImage.put(new BlockID(state, ship.getBlocks().get(b)), new ServerPixelImage(background, sprites));
                    position.put(new BlockID(state, ship.getBlocks().get(b)), genPosition(ship.getBlocks().get(b), pos));
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

    @Override
    public void tickServer() {

    }

    @Override
    protected ItemStackHandler createHandler() {
        return new ItemStackHandler(0) {
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
