package com.example.dongja94.samplenavermovie;

import android.content.Context;
import android.text.Html;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class MovieItemView extends FrameLayout {
    public MovieItemView(Context context) {
        super(context);
        init();
    }

    ImageView iconView;
    TextView titleView, directorView;
    MovieItem mItem;
    ImageRequest imageRequest;

    private void init() {
        inflate(getContext(), R.layout.view_movie_item, this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        titleView = (TextView)findViewById(R.id.text_title);
        directorView = (TextView)findViewById(R.id.text_director);
    }

    public void setMovieItem(MovieItem item) {
        titleView.setText(Html.fromHtml(item.title));
        directorView.setText(item.director);
        ImageLoader.getInstance().displayImage(item.image, iconView);

//        if (imageRequest != null) {
////            imageRequest.cancel();
//            imageRequest = null;
//        }
//        if (!TextUtils.isEmpty(item.image)) {
//            iconView.setImageResource(R.drawable.ic_stub);
//            imageRequest = new ImageRequest(item.image);
//            NetworkManager.getInstance().getImageBitmap(imageRequest, new NetworkManager.OnResultListener<Bitmap>() {
//                @Override
//                public void onSuccess(NetworkRequest<Bitmap> request, Bitmap result) {
//                    if (request == imageRequest) {
//                        iconView.setImageBitmap(result);
//                    }
//                }
//
//                @Override
//                public void onFail(NetworkRequest<Bitmap> request, int code) {
//                    iconView.setImageResource(R.drawable.ic_error);
//                }
//            });
//        } else {
//            iconView.setImageResource(R.drawable.ic_empty);
//        }

    }
}
