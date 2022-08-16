package mod.stf.syconn.api.util.data;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;

public record DirectionalTexture(TextureAtlasSprite texture,
                                 Direction direction) {

    public TextureAtlasSprite getTexture() {
        return texture;
    }

    public int x() {
        switch (direction) {
            default:
                return 48;
            case SOUTH:
                return 16;
            case EAST:
                return 32;
            case DOWN:
                return 32;
            case UP:
                return 16;
            case WEST:
                return 0;
        }
    }

    public int y() {
        switch (direction) {
            default:
                return 16;
            case EAST:
                return 16;
            case DOWN:
                return 0;
            case UP:
                return 0;
            case WEST:
                return 16;
        }
    }
}
