package com.tunaPasta17.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tunaPasta17.R;
import com.tunaPasta17.util.ARouterPath;

import androidx.fragment.app.Fragment;

@Route(path = ARouterPath.Fragment.FRAGMENT_JUMPTEST)
public class ArouterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arouterfragment, container, false);
        return view ;
    }
}
