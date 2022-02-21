package moheb.privatead;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyAdsView extends LinearLayout {

    private final Context context;
    private final ImageView imageViewAppIcon;
    private final TextView textViewAppName;
    private final TextView textViewAppTitle;
    private final TextView textViewAppRating;
    private final TextView textViewAppSize;
    private final TextView textViewAppCategory;
    private final TextView textViewAppBtnText;
    private final ImageView imageViewAppCover;
    private final RelativeLayout buttonbtncolor;
    private String url;

    //private  LinearLayout rv_rl;

    public MyAdsView(final Context context) {
        super(context);

        this.context = context;
        inflate(context, R.layout.banner_adview, this);
        imageViewAppIcon = findViewById(R.id.appIcon);
        textViewAppName = findViewById(R.id.adText);
        textViewAppTitle = findViewById(R.id.apptitle);
        textViewAppRating = findViewById(R.id.apprating);
        textViewAppSize = findViewById(R.id.appsize);
        textViewAppCategory = findViewById(R.id.appcategory);
        textViewAppBtnText = findViewById(R.id.btnactiontext);
        imageViewAppCover = findViewById(R.id.coverbg);
        buttonbtncolor = findViewById(R.id.btncolor);
        //rv_rl = findViewById(R.id.rv_rl);

        // Button privacypolicy = findViewById(R.id.privacypolicy);
        this.setBackgroundColor(Color.parseColor("#00ffffff"));

        //privacypolicy.setOnClickListener(new OnClickListener() {
        //  @Override
        //  public void onClick(View view) {
        //     String url = (context.getResources().getString(R.string.adprivacyurl));
        //     Intent i = new Intent(Intent.ACTION_VIEW);
        //     i.setData(Uri.parse(url));
        //     context.startActivity(i);
        //   }
        //   });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setValues(int appIcon, String appCover, String adText, String adTitle, String appRating, String appSize, String appCategory, String appBtnText, String btnColor, String url) {
        imageViewAppIcon.setImageDrawable(context.getResources().getDrawable(appIcon));
        textViewAppName.setText(adText);
        textViewAppTitle.setText(adTitle);


        Picasso.get()
                .load(appCover)
                .placeholder(R.drawable.ic_waiting)
                .into(imageViewAppCover);

        textViewAppRating.setText(appRating);
        textViewAppSize.setText(appSize);
        textViewAppCategory.setText(appCategory);
        textViewAppBtnText.setText(appBtnText);
        buttonbtncolor.setBackgroundColor(Color.parseColor(btnColor));
        textViewAppName.setSelected(true);

        this.url = url;
        setOnClick();
    }

    public void setValues(String appIconStr, String appCover, String adText, String adTitle, String appRating, String appSize, String appCategory, String appBtnText, String btnColor, String url) {
        Picasso.get()
                .load(appCover)
                .fit()
                .placeholder(R.drawable
                        .ic_waiting)
                .into(imageViewAppIcon);
        Picasso.get()
                .load(appIconStr)
                .placeholder(R.drawable
                        .ic_waiting)
                .into(imageViewAppCover);

        textViewAppName.setText(adText);
        textViewAppTitle.setText(adTitle);
        textViewAppRating.setText(appRating);
        textViewAppSize.setText(appSize);
        textViewAppCategory.setText(appCategory);
        textViewAppBtnText.setText(appBtnText);
        textViewAppName.setText(adText.toLowerCase());
        //buttonbtncolor.setText(appBtnText);
        buttonbtncolor.setBackgroundColor(Color.parseColor(btnColor));
        textViewAppName.setSelected(true);

        this.url = url;
        setOnClick();
    }

    private void setOnClick() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

}
