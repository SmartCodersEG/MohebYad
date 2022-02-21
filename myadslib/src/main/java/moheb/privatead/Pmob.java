package moheb.privatead;

import android.content.Context;
import android.widget.LinearLayout;

public class Pmob {
    private final String theNumber = "theNumber";
    private MyAd[] myAds;
    private int aNumber = 0;

//    public Pmob(final Context context, final LinearLayout linearLayout, MyAd... myAds) {
//        this.myAds = myAds;
//        spInit(context);
//        MyAdsView myAdsView = new MyAdsView(context);
//        myAdsView.setValues(
//                myAds[aNumber].getAppIcon(), myAds[aNumber].getAppCover(), myAds[aNumber].getAppDescription(), myAds[aNumber].getAppTitle(), myAds[aNumber].getAppRating(), myAds[aNumber].getAppSize(), myAds[aNumber].getAppCategory(), myAds[aNumber].getAppBtnText(), myAds[aNumber].getBtnColor(), myAds[aNumber].getUrl()
//        );
//        if (linearLayout != null)
//            linearLayout.addView(myAdsView);
//        setaNumber();
//    }

    public Pmob(final Context context, final LinearLayout linearLayout, String url) {
        spInit(context);
        new JsonObjectGetter(context, url, new JsonObjectGetListener() {
            @Override
            public void onSuccess(MyAd[] myAds) {
                Pmob.this.myAds = myAds;
                MyAdsView myAdsView = new MyAdsView(context);
                myAdsView.setValues(
                        myAds[aNumber].getAppIconStr(), myAds[aNumber].getAppCover(), myAds[aNumber].getAppDescription(), myAds[aNumber].getAppTitle(), myAds[aNumber].getAppRating(), myAds[aNumber].getAppSize(), myAds[aNumber].getAppCategory(), myAds[aNumber].getAppBtnText(), myAds[aNumber].getBtnColor(), myAds[aNumber].getUrl()
                );
                if (linearLayout != null)
                    linearLayout.addView(myAdsView);
                setaNumber();
            }

            @Override
            public void onError(String error) {
                //Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    public boolean showInterAd(Context context, boolean finish) {
        if (myAds != null) {
            new MyAdsInter(context, myAds[aNumber], finish);
            setaNumber();
            return true;
        }
        return false;
    }

    private void spInit(Context context) {
        FayazSP.init(context);
        aNumber = FayazSP.getInt(theNumber, 0);
    }

    private void setaNumber() {
        aNumber++;
        if (aNumber == myAds.length) {
            aNumber = 0;
        }
        FayazSP.put(theNumber, aNumber);
    }
}
