package com.elatienda.kaytamarka.bloodbank.view.fragment.home_cycle.more_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elatienda.kaytamarka.bloodbank.R;
import com.elatienda.kaytamarka.bloodbank.view.fragment.BaseFragment;

import butterknife.ButterKnife;

public class MoreFragment extends BaseFragment {

    public MoreFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        //setUpActivity();
        ButterKnife.bind(this,view);




        return view;
    }

//    @Override
//    public void onBack() {
//        super.onBack();
//    }

}
