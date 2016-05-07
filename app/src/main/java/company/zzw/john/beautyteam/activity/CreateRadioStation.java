package company.zzw.john.beautyteam.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.webserver.HttpServer;

/**
 * Created by john on 2016/5/4.
 */
@ContentView(R.layout.createradiostation_activity)
public class CreateRadioStation extends Activity {

    @ViewInject(R.id.et_pwd)
    private EditText et_TeamName;

    @ViewInject(R.id.et_email)
    private EditText et_TeamDesc;

    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @ViewInject(R.id.tv_common_title)
    private TextView tv_common_title;


    private String teamName;
    private String teamDesc;
    private int code;

    private HttpServer http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewUtils.inject(this);

        http = new HttpServer();

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        //确定的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamDesc = et_TeamName.getText().toString().trim();
                teamName = et_TeamDesc.getText().toString().trim();
                if (teamDesc.equals("") || teamName.equals("")) {
                    Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    http.log_in("111111@qq.com", "123456", login);

                }

            }
        });

    }

    private void initData() {

    }

    private void initView() {

        tv_common_title.setText("Create Radio Station");
    }

    RequestCallBack<String> createRadioStation = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("createRadioStation", responseInfo.result);
            Toast.makeText(getApplicationContext(), "恭喜你创建成功", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void onFailure(HttpException e, String s) {
            Log.d("createRadioStation", "网络连接失败");
        }
    };

    RequestCallBack<String> login = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            http.creatRadioStation(teamName, teamDesc, createRadioStation);
        }

        @Override
        public void onFailure(HttpException e, String s) {

        }
    };


}
