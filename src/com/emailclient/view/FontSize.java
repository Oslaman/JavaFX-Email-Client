package com.emailclient.view;

public enum FontSize {
    Small,
    Medium,
    Big;

    public static String getCssPath(FontSize fontSize){
        switch (fontSize){
            case Small:
                return "CSS/fontSmall.css";
            case Medium:
                return "CSS/fontMedium.css";
            case Big:
                return "CSS/fontBig.css";
            default:
                return null;
        }
    }
}
