package com.example.dongja94.sampleautocompletetextview;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class ItemView extends FrameLayout {
    public ItemView(Context context) {
        super(context);
        init();
    }
    TextView nameView;
    ImageView iconView;
    private void init() {
        inflate(getContext(), R.layout.view_item, this);
        nameView = (TextView)findViewById(R.id.text_name);
        iconView = (ImageView)findViewById(R.id.image_icon);
    }

    public void setPhot(Photo data) {
        nameView.setText(data.name);
        iconView.setImageResource(data.imageId);
    }
}
