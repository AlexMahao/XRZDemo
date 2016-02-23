package com.mahao.xrzdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.utils.Urls;


/**
 *详情主题展示
 */
public class ThemeWebFragment extends Fragment {

    private String id;
    private WebView web;

    public static ThemeWebFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id",id);
        ThemeWebFragment fragment = new ThemeWebFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=getArguments().getString("id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme_web, container, false);
        web = (WebView) view.findViewById(R.id.theme_web);
        web.loadUrl(String.format(Urls.THEME_WEB,id));
        return view;
    }


}
