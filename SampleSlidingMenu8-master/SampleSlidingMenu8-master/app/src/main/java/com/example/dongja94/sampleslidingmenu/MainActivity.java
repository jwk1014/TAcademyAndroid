package com.example.dongja94.sampleslidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements MenuFragment.OnMenuSelectListener {

    SlidingMenu mSlidingMenu;

    private static final String TAG_MAIN = "main";
    private static final String TAG_ONE = "one";
    private static final String TAG_TWO = "two";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_layout);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.menu_container, new MenuFragment()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment(), TAG_MAIN).commit();
        }

        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        mSlidingMenu.setSecondaryMenu(R.layout.menu_secondary);

        mSlidingMenu.setBehindWidthRes(R.dimen.menu_width);
        mSlidingMenu.setShadowDrawable(R.drawable.shadow);
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);

        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);

        setSlidingActionBarEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuSelected(MenuFragment.Item item) {
        switch (item.menuId) {
            case MenuFragment.MENU_MAIN:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
                emptyBackStack();
                break;
            case MenuFragment.MENU_ONE:
                Fragment old = getSupportFragmentManager().findFragmentByTag(TAG_ONE);
                if (old == null) {
                    emptyBackStack();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.left_in, R.anim.set1, R.anim.left_in, R.anim.left_out)
                            .replace(R.id.container, new OneFragment(), TAG_ONE).addToBackStack(null).commit();
                }
                break;
            case MenuFragment.MENU_TWO:
                Fragment oldtwo = getSupportFragmentManager().findFragmentByTag(TAG_TWO);
                if (oldtwo == null) {
                    emptyBackStack();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.left_in, R.anim.set1, R.anim.left_in, R.anim.left_out)
                            .replace(R.id.container, new TwoFragment(), TAG_TWO).addToBackStack(null).commit();
                }
                break;
        }

        showContent();
    }

    private void emptyBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
