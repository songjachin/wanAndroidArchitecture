package com.songjachin.wanandroid.homefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.songjachin.wanandroid.R;
import com.songjachin.wanandroid.databinding.FragmentTextBinding;

public class HomeFragment extends Fragment {
    FragmentTextBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_text, container, false);
        mBinding.homeTxtTitle.setText(getString(R.string.menu_navigation));
        return mBinding.getRoot();
    }
}
