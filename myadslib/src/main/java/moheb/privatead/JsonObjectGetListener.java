package moheb.privatead;

public interface JsonObjectGetListener {
    void onSuccess(MyAd[] myAds);

    void onError(String error);
}
