package com.examison.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    List<SplashModel> models;
    int screenPosition = 0;

    TextView tvPrev, tvSkip, tvNext, tvFinish;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutorial);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        tvPrev = findViewById(R.id.tvPrev);
        tvSkip = findViewById(R.id.tvSkip);
        tvNext = findViewById(R.id.tvNext);
        tvFinish = findViewById(R.id.tvFinish);

        //withInternet();

        tvPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrevScreen();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSkipScreen();
            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextScreen();
            }
        });

        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishScreen();
            }
        });

        models = new ArrayList<>();

   /*     models.add(new SplashModel("R.drawable.tutorail1"));
        models.add(new SplashModel("R.drawable.tutorail2"));
        models.add(new SplashModel("R.drawable.tutorail3"));
*/
        final SplashAdapter splashAdapter = new SplashAdapter(TutorialActivity.this);
        viewPager.setAdapter(splashAdapter);

        //screenCount = splashAdapter.getCount();

        dotscount = splashAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(TutorialActivity.this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                screenPosition = position;

                if ((position + 1) == dotscount) {
                    tvNext.setVisibility(View.GONE);
                    tvFinish.setVisibility(View.VISIBLE);
                } else {
                    tvNext.setVisibility(View.VISIBLE);
                    tvFinish.setVisibility(View.GONE);
                }

                if (position != 0) {
                    tvPrev.setVisibility(View.VISIBLE);
                    tvSkip.setVisibility(View.GONE);
                } else {
                    tvPrev.setVisibility(View.GONE);
                    tvSkip.setVisibility(View.VISIBLE);
                }

                //
                // Toast.makeText(Splashscreen.this, "count : "+count + " position : " + position, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onPrevScreen() {
        if (screenPosition != 0) {
            viewPager.setCurrentItem(screenPosition - 1);
        }
    }

    private void onNextScreen() {
        if (screenPosition != (dotscount - 1)) {
            viewPager.setCurrentItem(screenPosition + 1);
        }
    }

    private void onSkipScreen() {
        SessionManager.setBoolean(SessionManager.IS_TUTORIAL_SEEN,true,TutorialActivity.this);
        Intent intent = new Intent(TutorialActivity.this, BaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

    private void onFinishScreen() {
        SessionManager.setBoolean(SessionManager.IS_TUTORIAL_SEEN,true,TutorialActivity.this);
        Intent intent = new Intent(TutorialActivity.this, BaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

    class SplashModel {
        String image;

        public SplashModel(String image) {
            this.image = image;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    class SplashAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;
        private Context context;
        private Integer [] images = {R.drawable.tutorial1, R.drawable.tutorial2, R.drawable.tutorial3};

        public SplashAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_slider_layout, null);
            ImageView imgImage = view.findViewById(R.id.imgImage);
            Picasso.get().load(images[position]).error(R.drawable.bg_splash).into(imgImage);

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }
    }
}