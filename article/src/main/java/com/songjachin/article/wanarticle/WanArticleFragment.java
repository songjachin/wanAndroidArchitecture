package com.songjachin.article.wanarticle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.songjachin.article.R;
import com.songjachin.article.databinding.FragmentWanArticleBinding;

public class WanArticleFragment extends Fragment {
    FragmentWanArticleBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wan_article, container, false);
        //mBinding.string.setText(getString(R.string.menu_navigation));
        return mBinding.getRoot();
    }
}
