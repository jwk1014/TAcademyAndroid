package com.example.dongja94.samplekakaologin;

import android.content.Context;
import android.util.AttributeSet;

import com.kakao.usermgmt.LoginButton;

/**
 * Created by dongja94 on 2015-11-18.
 */
public class CustomKakaoLoginButton extends LoginButton {
    public CustomKakaoLoginButton(Context context) {
        super(context);
    }

    public CustomKakaoLoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomKakaoLoginButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeAllViews();
        inflate(getContext(), R.layout.test_kakao_login, this);
    }
}
