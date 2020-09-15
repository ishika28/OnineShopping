package com.ishika.onlineshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ishika.onlineshopping.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_image_slider_view, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.SliderAdapterVH viewHolder, int position) {
        switch (position) {

            case 1:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slider1)
                        .into(viewHolder.sliderImage);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slider2)
                        .into(viewHolder.sliderImage);
                break;
            default:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slider3)
                        .into(viewHolder.sliderImage);
                break;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView sliderImage;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            sliderImage = itemView.findViewById(R.id.sample_slider_imageView);
            this.itemView = itemView;
        }
    }
}