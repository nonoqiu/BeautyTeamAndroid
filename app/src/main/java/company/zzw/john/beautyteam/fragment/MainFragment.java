package company.zzw.john.beautyteam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.activity.LoginActivity;
import company.zzw.john.beautyteam.activity.MainActivity;


/**
 * Created by john on 2016/4/5.
 */
public class MainFragment extends BaseFragment {


    @ViewInject(R.id.btn_SignOut)
    private Button btn_SignOut;

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ViewUtils.inject(this, view);


        initEvent();

        return view;


    }

    private void initEvent() {
        //退出登录的监听事件
        btn_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
                getActivity().finish();
            }
        });
    }
}
