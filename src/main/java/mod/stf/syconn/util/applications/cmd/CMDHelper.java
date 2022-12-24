package mod.stf.syconn.util.applications.cmd;

import mod.stf.syconn.api.util.Mths;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class CMDHelper {

    public boolean isTrue(String s){
        return s.toLowerCase().matches("true") || s.matches("0");
    }

    @Nullable
    public Direction getDir(String s){
        for (Direction direction : Direction.values()) {
            if (direction.getName().equalsIgnoreCase(s)) {
                return direction;
            }
        }
        return null;
    }

    @Nullable
    public Player getPlayer(String s){
        for (PlayerInfo playerInfo : Minecraft.getInstance().getConnection().getOnlinePlayers()){
            if (playerInfo.getProfile().getName().equalsIgnoreCase(s)){
                return Minecraft.getInstance().level.getPlayerByUUID(playerInfo.getProfile().getId());
            }
        }
        return null;
    }

    public BlockPos getBlockPos(String[] s, int x){
        return CMDTools.getBlockPos(Mths.splitArray(s, x, x + 3));
    }
}
