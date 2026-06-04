package librarysystem.controller;

public class ThemeController {
    static public String redwine = "#8B0000";
    static public String bgColor = "#F4F4F4";
    static public String subtitleColor = "#5A5A5A";
    static public void switchToDarkMode() {
        redwine = redwine == "#FF6347"? "#8B0000" : "#FF6347";
        bgColor = bgColor == "#F4F4F4"? "#333333" : "#F4F4F4";
        subtitleColor = subtitleColor == "#5A5A5A"? "#CCCCCC" : "#5A5A5A";
    }
}
