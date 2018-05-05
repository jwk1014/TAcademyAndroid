package com.example.dongja94.samplefragmenttab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {


    public Tab1Fragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    FragmentTabHost tabHost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        tabHost = (FragmentTabHost)view.findViewById(R.id.tabHost);
        tabHost.setup(getActivity(),getChildFragmentManager(), R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("ch1").setIndicator("CH1"), Child1Fragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("ch2").setIndicator("CH2"), Child2Fragment.class, null);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_f1, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_f1) {
            Toast.makeText(getContext(), "F1M1 selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
