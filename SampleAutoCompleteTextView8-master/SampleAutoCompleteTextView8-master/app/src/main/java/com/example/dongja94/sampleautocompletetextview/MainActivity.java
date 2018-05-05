package com.example.dongja94.sampleautocompletetextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView editView;
    CustomAdapter mAdapter;
    int[] resIds = { R.drawable.gallery_photo_1 , R.drawable.gallery_photo_2 ,
            R.drawable.gallery_photo_3 , R.drawable.gallery_photo_4 ,
            R.drawable.gallery_photo_5 , R.drawable.gallery_photo_6 ,
            R.drawable.gallery_photo_7 , R.drawable.gallery_photo_8 ,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
//        mAdapter = new MyAdapter(this, android.R.layout.simple_list_item_1);
        mAdapter = new CustomAdapter();
        editView.setAdapter(mAdapter);

        initData();
    }

    private void initData() {
        String[] items = getResources().getStringArray(R.array.items);
        for (int i =0 ; i < items.length; i++) {
            String name = items[i];
            int imageId = resIds[i % resIds.length];
            Photo p = new Photo();
            p.name = name;
            p.imageId = imageId;
            mAdapter.add(p);
        }
    }

}
