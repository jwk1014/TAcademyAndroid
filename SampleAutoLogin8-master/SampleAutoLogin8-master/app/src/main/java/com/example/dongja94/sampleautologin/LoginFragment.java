package com.example.dongja94.sampleautologin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    EditText idView, passwordView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        idView = (EditText)view.findViewById(R.id.edit_id);
        passwordView = (EditText)view.findViewById(R.id.edit_password);
        Button btn = (Button)view.findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = idView.getText().toString();
                final String password = passwordView.getText().toString();
                NetworkManager.getInstance().login(id, password, new NetworkManager.OnResultListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("ok")) {
                            PropertyManager.getInstance().setId(id);
                            PropertyManager.getInstance().setPassword(password);
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        } else {
                            // ...
                        }
                    }

                    @Override
                    public void onFail(int code) {
                        // ...
                    }
                });
            }
        });

        btn = (Button)view.findViewById(R.id.btn_signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).pushSingUpFragment();
            }
        });
        return view;
    }


}
