package company.zzw.john.beautyteam.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基本的Fragment
 * Created by john on 2016/4/5.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {

    public Activity mActivity;


    /**
     * fragment创建
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }


    /**
     * 处理fragment的布局
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initViews(inflater, container, savedInstanceState);
    }


    /**
     * 依附的activity创建完成
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }


    //子类必须实现的初始化布局的方法
    public abstract View initViews(LayoutInflater inflater, ViewGroup container,

                                   Bundle savedInstanceState);

    //初始化数据，可以不实现
    public void initData() {

    }

}
