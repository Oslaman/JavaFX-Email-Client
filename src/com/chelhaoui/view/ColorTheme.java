package com.chelhaoui.view;

public enum ColorTheme {
    Light,
    Default,
    Dark;

    public static String getCssPath(ColorTheme colorTheme){
        switch (colorTheme){
            case Default:
                return "CSS/default.css";
            case Light:
                return "CSS/themeLight.css";
            case Dark:
                return "CSS/themeDark.css";
            default:
                return null;
        }
    }
}
