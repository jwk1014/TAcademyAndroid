package com.example.dongja94.samplemediarecorder;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    SurfaceView surfaceView;
    SurfaceHolder mHolder;
    MediaRecorder mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this);

        Button btn = (Button)findViewById(R.id.btn_record);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
    }

    File mSavedFile;

    private void startRecording() {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();

            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

            mRecorder.setVideoSize(320, 240);
            mRecorder.setMaxFileSize(20 * 1024 * 1024);
            mRecorder.setMaxDuration(60 * 1000);
            if (mHolder != null) {
                mRecorder.setPreviewDisplay(mHolder.getSurface());
            }
            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "mycamera");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            mSavedFile = new File(dir, "s_" + System.currentTimeMillis() + ".mpeg");
            mRecorder.setOutputFile(mSavedFile.getAbsolutePath());

            try {
                mRecorder.prepare();
                mRecorder.start();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            mRecorder.release();
            mRecorder = null;
        }
    }

    private void stopRecording() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;

            saveToFile();
        }

    }

    private void saveToFile() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Video.Media.DISPLAY_NAME, "my test video");
        values.put(MediaStore.Video.Media.DESCRIPTION, "sample recording test");
        values.put(MediaStore.Video.Media.TITLE, "....");
        values.put(MediaStore.Video.Media.MIME_TYPE,"video/mpeg");
        values.put(MediaStore.Video.Media.DATA,mSavedFile.getAbsolutePath());
        values.put(MediaStore.Video.Media.DATE_ADDED, System.currentTimeMillis() / 1000);

        Uri uri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);

        if (uri != null) {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mHolder = null;
        stopRecording();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
