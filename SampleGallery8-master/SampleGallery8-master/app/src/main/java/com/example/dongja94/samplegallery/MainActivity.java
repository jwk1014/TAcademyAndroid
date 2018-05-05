package com.example.dongja94.samplegallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Gallery;

public class MainActivity extends AppCompatActivity {

    int[] resIds = {R.drawable.gallery_photo_1 ,
            R.drawable.gallery_photo_2 ,
            R.drawable.gallery_photo_3 ,
            R.drawable.gallery_photo_4 ,
            R.drawable.gallery_photo_5 ,
            R.drawable.gallery_photo_6 ,
            R.drawable.gallery_photo_7 ,
            R.drawable.gallery_photo_8 };

    Gallery gallery;
    ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gallery = (Gallery)findViewById(R.id.gallery);
        mAdapter = new ImageAdapter();
        gallery.setAdapter(mAdapter);

        initData();

        int position = Integer.MAX_VALUE / 2;

        position = (position / resIds.length) * resIds.length;

        gallery.setSelection(position);
    }

    private void initData() {
        for (int id : resIds) {
            mAdapter.add(id);
        }
    }
}
