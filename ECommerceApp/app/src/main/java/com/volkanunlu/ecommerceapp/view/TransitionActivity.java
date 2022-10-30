package com.volkanunlu.ecommerceapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.volkanunlu.ecommerceapp.R;
import com.volkanunlu.ecommerceapp.adapters.SlideAdapter;
import com.volkanunlu.ecommerceapp.databinding.ActivityTransitionBinding;

public class TransitionActivity extends AppCompatActivity {

    ActivityTransitionBinding binding;

    ViewPager viewPager;
    LinearLayout linearLayout;
    SlideAdapter slideAdapter;
    TextView[] dots;
    Button btnrouter;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //status bar gizleme
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding=ActivityTransitionBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        viewPager=binding.slider;
        linearLayout=binding.linearlayout;
        btnrouter=binding.startedBtn;
        addDots(0);

        btnrouter.setOnClickListener(new View.OnClickListener() { //Butonuma tıklandığında bir intent ile activity geçişi sağladığım listener
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TransitionActivity.this,RegistirationActivity.class);
                startActivity(intent);
                finish();

            }
        });
        viewPager.addOnPageChangeListener(pageChangeListener); //sayfa arası geçişlerimi adapter tarafına bağladım.(buton ve dot ayarları)
        //Call adapter , adaptörümü bağladım.
        slideAdapter=new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);



    }

    private void addDots(int position){ //Nokta kısımlarını dahil ediyorum.Tıklamaya göre slider ayarı sağlayacağım.

        dots=new TextView[3];
        linearLayout.removeAllViews();
        for (int i=0; i< dots.length; i++){

            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            linearLayout.addView(dots[i]);
        }
        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.orange));

        }
    }


    //Sayfa değişimleri için bir listener yarattık.
    ViewPager.OnPageChangeListener pageChangeListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) { //3.slide olduğunda butonumu görünür kılacağım.

            addDots(position);
            if (position==0){
            btnrouter.setVisibility(View.INVISIBLE);
            }
            else if (position==1){
                btnrouter.setVisibility(View.INVISIBLE);
            }
            else{
                animation= AnimationUtils.loadAnimation(TransitionActivity.this,R.anim.slide_anim);
                btnrouter.setAnimation(animation);
                btnrouter.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}