package company.zzw.john.beautyteam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;

import company.zzw.john.beautyteam.R;

/**
 * Created by john on 2016/4/5.
 */
public class MainFragment extends BaseFragment {
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        ViewUtils.inject(this,view);
        return view;
    }
}
