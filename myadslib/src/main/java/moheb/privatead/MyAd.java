package moheb.privatead;

public class MyAd {
    private int appIcon;
    private String appIconStr;
    private String appDescription;
    private String appTitle;
    private String appRating;
    private String appSize;
    private String appCategory;
    private String appBtnText;
    private String appCover;
    private String btnColor;
    private String url;


//    public MyAd(int appIcon, String appCover, String appDescription, String appTitle, String appRating, String appSize, String appCategory, String appBtnText, String btnColor, String url) {
//        this.appIcon = appIcon;
//        this.appDescription = appDescription;
//        this.appTitle = appTitle;
//        this.appRating = appRating;
//        this.appSize = appSize;
//        this.appCategory = appCategory;
//        this.appCover = appCover;
//        this.appBtnText = appBtnText;
//        this.btnColor = btnColor;
//        this.url = url;
//    }

    public MyAd(String appIconStr, String appDescription, String appTitle, String appRating, String appSize, String appCategory, String appBtnText, String appCover, String btnColor, String url) {
        this.appIconStr = appIconStr;
        this.appDescription = appDescription;
        this.appTitle = appTitle;
        this.appRating = appRating;
        this.appSize = appSize;
        this.appCategory = appCategory;
        this.appBtnText = appBtnText;
        this.appCover = appCover;
        this.btnColor = btnColor;
        this.url = url;
    }

//    public MyAd(int appIcon, String appCover, String appIconStr, String appDescription, String appTitle, String appRating, String appSize, String appCategory, String appBtnText, String btnColor, String url) {
//        this.appIcon = appIcon;
//        this.appIconStr = appIconStr;
//        this.appDescription = appDescription;
//        this.appTitle = appTitle;
//        this.appRating = appRating;
//        this.appSize = appSize;
//        this.appCategory = appCategory;
//        this.appCover = appCover;
//        this.appBtnText = appBtnText;
//        this.btnColor = btnColor;
//        this.url = url;
//    }

    public String getBtnColor() {
        return btnColor;
    }

    public void setBtnColor(String btnColor) {
        this.btnColor = btnColor;
    }


    public String getAppIconStr() {
        return appIconStr;
    }

    public void setAppIconStr(String appIconStr) {
        this.appIconStr = appIconStr;
    }

    public int getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(int appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppCover() {
        return appCover;
    }

    public void setAppCover(String appCover) {
        this.appCover = appCover;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppRating() {
        return appRating;
    }

    public void setAppRating(String appRating) {
        this.appRating = appRating;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppCategory() {
        return appCategory;
    }

    public void setAppCategory(String appCategory) {
        this.appCategory = appCategory;
    }

    public String getAppBtnText() {
        return appBtnText;
    }

    public void setAppBtnText(String appBtnText) {
        this.appBtnText = appBtnText;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
