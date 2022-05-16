package com.teampp.plugin.help;

public class URLHelper {
    public static String convertStringForUrl(String string) {
        string = string.replace(" ", "%20");
        string = string.replace("ä", "ae");
        string = string.replace("ö", "oe");
        string = string.replace("ü", "ue");
        string = string.replace("Ä", "AE");
        string = string.replace("Ö", "OE");
        string = string.replace("ß", "ssss");
        return string.replace("ü", "UE");
    }

    public static String convertUrlString(String string) {
        string = string.replace("ae", "ä");
        string = string.replace("oe", "ö");
        string = string.replace("ue", "ü");
        string = string.replace("AE", "Ä");
        string = string.replace("OE", "Ö");
        string = string.replace("ssss", "ß");
        return string.replace("UE", "Ü");
    }


}
