package com.example.dongja94.samplenavermovie;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dongja94 on 2015-10-20.
 */
public class NetworkManager {

    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }
    ThreadPoolExecutor mExecutor;
    Handler mHandler = new Handler(Looper.getMainLooper());

    private NetworkManager() {
        mExecutor = new ThreadPoolExecutor(5, 64, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public interface OnResultListener<T> {
        public void onSuccess(NetworkRequest<T> request, T result);
        public void onFail(NetworkRequest<T> request, int code);
    }

    public void getNaverMovies(NetworkRequest<NaverMovies> request, OnResultListener<NaverMovies> listener) {
        mExecutor.execute(new NetworkProcess<NaverMovies>(request, listener));
    }

    public void getImageBitmap(NetworkRequest<Bitmap> request, OnResultListener<Bitmap> listener) {
        mExecutor.execute(new NetworkProcess<Bitmap>(request, listener));
    }

    public <T> void getNetworkData(NetworkRequest<T> request, OnResultListener<T> listener) {
        mExecutor.execute(new NetworkProcess<T>(request, listener));
    }


    class NetworkProcess<T> implements Runnable {
        NetworkRequest<T> request;
        OnResultListener<T> listener;
        public NetworkProcess(NetworkRequest<T> request, OnResultListener<T> listener) {
            this.request = request;
            this.listener = listener;
        }

        @Override
        public void run() {
            try {
                URL url = request.getURL();
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                request.setRequstMethod(conn);
                request.setOutput(conn);
                int code = conn.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    final T object = request.parsing(is);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!request.isCancel()) {
                                listener.onSuccess(request, object);
                            }
                        }
                    });
                    return;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (!request.isCancel()) {
                        listener.onFail(request, -1);
                    }
                }
            });
        }
    }

}
