package com.example.dongja94.sampleautologin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_new_signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkManager.getInstance().signup("aaa", "1111", new NetworkManager.OnResultListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("ok")) {
                            PropertyManager.getInstance().setId("aaa");
                            PropertyManager.getInstance().setPassword("1111");
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        } else {
                            //
                        }
                    }

                    @Override
                    public void onFail(int code) {
                        // ...
                    }
                });
            }
        });
        return view;
    }


}
