package com.elatienda.kaytamarka.bloodbank.view.fragment.home_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elatienda.kaytamarka.bloodbank.R;
import com.elatienda.kaytamarka.bloodbank.view.fragment.BaseFragment;

import butterknife.ButterKnife;

public class ProfileFragment extends BaseFragment {

    public ProfileFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //setUpActivity();
        ButterKnife.bind(this,view);




        return view;
    }

//    @Override
//    public void onBack() {
//        super.onBack();
//    }

}
