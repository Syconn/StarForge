package mod.stf.syconn.client.screen.componets;

import mod.stf.syconn.util.Gettable;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.client.gui.widget.Slider;

public class MutableSlider extends Slider implements Gettable<MutableSlider> {

    private final String value;
    private final int id;

    public MutableSlider(int id, int x, int y, String prefix, double min, double max, double current, OnPress press)
    {
        //super(x, y, width, height, new TranslatableComponent(prefix), new TranslatableComponent(postfix), );
        super(x, y, new TranslatableComponent(prefix), min, max, current, press, null);
        this.value = prefix;
        this.id = id;
        updateSlider();
    }



    @Override
    public void updateSlider() {
        super.updateSlider();
        setMessage(new TextComponent(getValueInt() + " " + value));
    }

    public int getId() {
        return id;
    }
}
