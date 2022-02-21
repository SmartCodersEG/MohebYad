package moheb.privatead;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JsonObjectGetter extends AsyncTask<Void, Void, MyAd[]> {

    private final String TAG = getClass().getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final String jsonUrl;
    private final JsonObjectGetListener listener;
    private Exception exception;

    public JsonObjectGetter(Context context, String jsonUrl, JsonObjectGetListener listener) {
        this.context = context;
        this.jsonUrl = jsonUrl;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (context == null || listener == null || jsonUrl == null) {
            Log.d(TAG, "onPreExecute: context == null || listener == null || jsonUrl == null");
            cancel(true);
        } else if (!isNetworkAvailable(context)) {
            listener.onError("Please check your network connection");
            cancel(true);
        } else if (jsonUrl.isEmpty()) {
            listener.onError("Please provide a valid JSON URL");
            cancel(true);
        }
    }

    @Override
    protected MyAd[] doInBackground(Void... voids) {
        try {
            URL url = new URL(jsonUrl);
            InputStream is = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = bufferedReader.read()) != -1) {
                sb.append((char) cp);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            MyAd[] myAd = new MyAd[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                myAd[i] = new MyAd(
                        object.getString("appIconStr"),
                        object.getString("appDescription"),
                        object.getString("appTitle"),
                        object.getString("appRating"),
                        object.getString("appSize"),
                        object.getString("appCategory"),
                        object.getString("appBtnText"),
                        object.getString("appCover"),
                        object.getString("btnColor"),
                        object.getString("url")
                );
            }
            return myAd;
        } catch (Exception e) {
            this.exception = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(MyAd[] myAds) {
        super.onPostExecute(myAds);
        if (listener != null && myAds != null) {
            listener.onSuccess(myAds);
        } else {
            Objects.requireNonNull(listener).onError(exception.toString());
        }
    }

    private boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = null;
            if (connectivityManager == null) {
                return false;
            } else {
                network = connectivityManager.getActiveNetwork();
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                if (networkCapabilities == null) {
                    return false;
                }
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
            }
        } else {
            if (connectivityManager == null) {
                return false;
            }
            if (connectivityManager.getActiveNetworkInfo() == null) {
                return false;
            }
            return connectivityManager.getActiveNetworkInfo().isConnected();
        }
    }
}

//ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
//NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
//return activeNetworkInfo != null && activeNetworkInfo.isConnected();