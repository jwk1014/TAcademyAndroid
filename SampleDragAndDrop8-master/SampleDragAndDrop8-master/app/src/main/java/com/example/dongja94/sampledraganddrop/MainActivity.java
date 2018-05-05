package com.example.dongja94.sampledraganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;

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

        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        TextView tv = (TextView)findViewById(R.id.text_item1);
        tv.setOnLongClickListener(mLongClickListener);
        findViewById(R.id.text_item2).setOnLongClickListener(mLongClickListener);
        findViewById(R.id.text_item3).setOnLongClickListener(mLongClickListener);

        listView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED :
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED :
                        listView.setBackgroundColor(Color.GREEN);
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED :
                        listView.setBackgroundColor(Color.TRANSPARENT);
                        return true;
                    case DragEvent.ACTION_DROP :
                        float x = event.getX();
                        float y = event.getY();
                        String text = event.getClipData().getItemAt(0).getText().toString();
                        int position = listView.pointToPosition((int)x, (int)y);
                        if (position != ListView.INVALID_POSITION) {
                            mAdapter.insert(text, position);
                        } else {
                            mAdapter.add(text);
                        }
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED :
                        boolean isDrop = event.getResult();
                        if (isDrop) {
                            Toast.makeText(MainActivity.this, "My", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                }
                return false;
            }
        });

    }

    View.OnLongClickListener mLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            String text = ((TextView)v).getText().toString();
            ClipData.Item item = new ClipData.Item(text);
            ClipData data = new ClipData(text, new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},item);
            v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
            return true;
        }
    };
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
