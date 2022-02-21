package moheb.privatead;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MyAdsInter {

    private View interAdView;
    private ImageView imageViewAppIcon;
    private TextView textViewAppName;
    private TextView textViewAppTitle;
    private TextView textViewAppRating;
    private TextView textViewAppSize;
    private TextView textViewAppCategory;
    private TextView textViewAppBtnText;
    private ImageView imageViewAppCover;

    @SuppressLint({"InflateParams", "UseCompatLoadingForDrawables"})
    public MyAdsInter(final Context context, final MyAd myAd, final boolean finish) {
        if (interAdView == null) {
            interAdView = LayoutInflater.from(context).inflate(R.layout.inter_adview, null);
            imageViewAppIcon = interAdView.findViewById(R.id.appIcon);
            imageViewAppCover = interAdView.findViewById(R.id.coverbg);
            textViewAppName = interAdView.findViewById(R.id.adText);
            textViewAppTitle = interAdView.findViewById(R.id.apptitle);
            textViewAppRating = interAdView.findViewById(R.id.apprating);
            textViewAppSize = interAdView.findViewById(R.id.appsize);
            textViewAppCategory = interAdView.findViewById(R.id.appcategory);
            textViewAppBtnText = interAdView.findViewById(R.id.btnactiontext);

            Button privacypolicy = interAdView.findViewById(R.id.privacypolicy);
            privacypolicy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = (context.getResources().getString(R.string.adprivacyurl));
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
            });
        }

        if (myAd.getAppIconStr() == null) {
            imageViewAppIcon.setImageDrawable(context.getResources()
                    .getDrawable(myAd.getAppIcon()));
        } else {
            Picasso.get().load(myAd.getAppIconStr())
                    .placeholder(R.drawable.ic_waiting)
                    .into(imageViewAppIcon);
        }

        if (myAd.getAppIconStr() == null) {
            imageViewAppCover.setImageDrawable(context.getResources()
                    .getDrawable(myAd.getAppIcon()));
        } else {
            Picasso.get().load
                    (myAd.getAppCover())
                    .placeholder(R.drawable.ic_waiting)
                    .into(imageViewAppCover);
        }

        textViewAppName.setText(myAd.getAppDescription());
        textViewAppTitle.setText(myAd.getAppTitle());
        textViewAppRating.setText(myAd.getAppRating());
        textViewAppSize.setText(myAd.getAppSize());
        textViewAppCategory.setText(myAd.getAppCategory());
        textViewAppBtnText.setText(myAd.getAppBtnText());

        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(interAdView);
        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        //dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button actionbtn = dialog.findViewById(R.id.actionbtn);
        actionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(myAd.getUrl()));
                context.startActivity(browse);
                dialog.dismiss();
            }
        });

        Button cancelbtn = dialog.findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        final TextView textView = dialog.findViewById(R.id.textView123);
        final AtomicInteger n = new AtomicInteger(10);
        final Runnable counter = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                textView.setText(Integer.toString(n.get()));
                if (n.getAndDecrement() >= 1)
                    handler.postDelayed(this, 1000);
                else {
                    dialog.dismiss();
                }
            }
        };
        handler.postDelayed(counter, 1000);
        dialog.show();

    }
}
