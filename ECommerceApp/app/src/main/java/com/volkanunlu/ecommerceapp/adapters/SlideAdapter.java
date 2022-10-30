package com.volkanunlu.ecommerceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.volkanunlu.ecommerceapp.R;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public SlideAdapter(Context context) {
        this.context = context;
    }

    int [] imagesArray={  //Bir image dizisi oluşturdum ve sırası ile resimlerimi verdim.
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
    };

    int [] headerArray={ //Bir header dizisi oluşturdum ve sırası ile header yazılarımı verdim.
            R.string.first_screen,
            R.string.second_screen,
            R.string.third_screen
    };

    int[] contentArray={ //Bir content dizisi oluşturdum ve sırası ile içerik yazılarımı verdim.
            R.string.content,
            R.string.content,
            R.string.content
    };

    @Override
    public int getCount() {
        return headerArray.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(ConstraintLayout) object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //casting işlemi kullandık.
        //Bu kısımda görünümlerim yani xml ile değerlerimi bağladım adaptör sayesinde.

        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView=view.findViewById(R.id.slider_img);
        TextView heading=view.findViewById(R.id.heading);
        TextView description=view.findViewById(R.id.description);

        imageView.setImageResource(imagesArray[position]);
        heading.setText(headerArray[position]);
        description.setText(contentArray[position]);
        container.addView(view); //Ekleme işlemini gerçekleştirdik görünüme.

        return view; //Ve ekleme sonrası ekrana döndürüyorum.

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}


