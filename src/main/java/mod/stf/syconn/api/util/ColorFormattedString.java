package mod.stf.syconn.api.util;

import java.util.ArrayList;
import java.util.List;

public class ColorFormattedString {

    private List<ColoredString> strings = new ArrayList<>();

    public ColorFormattedString() {
    }

    public ColorFormattedString(List<ColoredString> string) {
        this.strings = string;
    }

    public ColorFormattedString(ColoredString string) {
        String[] text = string.getString().split(" ");
        for (String str : text) {
            boolean found = false;
            if (str.length() > 2){
                for (ColorCodes c : ColorCodes.values()){
                    if (str.substring(0, 3).matches(c.getCode())) {
                        strings.add(new ColoredString(str.substring(3), c.getColor()));
                        found = true;
                    }
                }
            }
            if (!found) strings.add(new ColoredString(str, string.getColor()));
        }
    }

    public void addString(ColoredString string){
        strings.add(string);
    }

    public void addString(String string){
        strings.add(new ColoredString(string));
    }

    public List<ColoredString> getString() {
        return strings;
    }

    public String combinedString(){
        String s = "";
        for (ColoredString string : strings){
            s += " " + string.getString();
        }
        return s;
    }
}
