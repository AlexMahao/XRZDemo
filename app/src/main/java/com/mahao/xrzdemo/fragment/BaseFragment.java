package com.mahao.xrzdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mahao.xrzdemo.utils.ProgressDialogUtil;

/**
 * Created by mdw on 2015/11/29.
 */
public class BaseFragment extends Fragment {
    public ProgressDialogUtil progress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress= new ProgressDialogUtil();
        progress.createDialog(getActivity());
    }
}
