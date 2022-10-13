package mod.stf.syconn.api.util;

import java.util.ArrayList;
import java.util.List;

public class ColorFormattedString {

    private List<ColoredString> strings = new ArrayList<>();

    public ColorFormattedString(List<ColoredString> string) {
        this.strings = string;
    }

    public ColorFormattedString(ColoredString string) {
        String[] text = string.getString().split(" ");
        for (String str : text) {
            boolean found = false;
            if (str.length() > 2){
                for (ColorCodes c : ColorCodes.values()){
                    if (str.substring(0, 2).matches(c.getCode())) {
                        strings.add(new ColoredString(str, c.getColor()));
                        found = true;
                    }
                }
            }
            if (!found) strings.add(new ColoredString(str, string.getColor()));
        }
    }

    public List<ColoredString> getString() {
        return strings;
    }
}
