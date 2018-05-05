package com.example.dongja94.samplefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    public static final String EXTRA_MESSAGE = "message";
    public static OneFragment instantiate(String message) {
        OneFragment f = new OneFragment();
        Bundle b = new Bundle();
        b.putString(EXTRA_MESSAGE, message);
        f.setArguments(b);
        return f;
    }

    public OneFragment() {
        // Required empty public constructor
    }

    public interface OnMessageListener {
        public void receiveMessage(String message);
    }

    OnMessageListener mCallback = null;

    OnMessageListener mListener;
    public void setOnMessageListener(OnMessageListener listener) {
        mListener = listener;
    }

    TextView messageView;
    EditText inputView;
    String message;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            message = b.getString(EXTRA_MESSAGE);
        } else {
            message = "Test...";
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMessageListener) {
            mCallback = (OnMessageListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        messageView = (TextView)view.findViewById(R.id.text_message);
        messageView.setText(message);
        inputView = (EditText)view.findViewById(R.id.edit_input);
        Button btn = (Button)view.findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputView.getText().toString();
                messageView.setText(message);
//                ((MainActivity)getActivity()).receiveMessage(message);
                if (mCallback != null) {
                    mCallback.receiveMessage(message);
                }
                if (mListener != null) {
                    mListener.receiveMessage(message);
                }
            }
        });
        return view;
    }


}
