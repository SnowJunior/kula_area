package com.nyabwana.kula_area;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SignUpTabFragment  extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);
    }
}
