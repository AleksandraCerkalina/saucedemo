package come.saucedemo.utils;

public class Helper {

    // "$3.44" -> "3.44" -> from String convert to Double (metod nazivaetsja kasting)
    // static metodi mozhno vizivatj iz ljuboj 4asti progi
    public static double convertStringWithDollarToDouble(String amount) {
        return Double.parseDouble(amount.replace("$", ""));
    }
}
