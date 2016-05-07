package company.zzw.john.beautyteam.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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
@ContentView(R.layout.createteam_activity)
public class CreateTeam extends Activity {

    @ViewInject(R.id.et_pwd)
    private EditText et_TeamName;

    @ViewInject(R.id.et_email)
    private EditText et_TeamDesc;

    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @ViewInject(R.id.tv_common_title)
    private TextView tv_common_title;

    @ViewInject(R.id.rg_teamtype)
    private RadioGroup rg_teamtype;


    private String teamName;
    private String teamDesc;
    private String teamType = "1";

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
        //设置单选按钮的监听事件
        rg_teamtype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_work) {
                    teamType = "1";
                }
                if (checkedId == R.id.rb_study) {
                    teamType = "2";
                }
            }
        });


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
        tv_common_title.setText("Create Team");

    }

    /**
     * 创建team
     */
    RequestCallBack<String> createTeam = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("createTeam", responseInfo.result);
            Toast.makeText(getApplicationContext(), "恭喜你创建成功", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void onFailure(HttpException e, String s) {
            Log.d("createTeam", "网络连接失败");
        }
    };
    /**
     * 登录的回调
     */
    RequestCallBack<String> login = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            http.createTeam(teamName, teamDesc, teamType, createTeam);
        }

        @Override
        public void onFailure(HttpException e, String s) {

        }
    };
}
