package company.zzw.john.beautyteam.activity;

import android.app.Activity;
import android.content.Intent;
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

import org.json.JSONObject;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.webserver.HttpServer;


/**
 * Created by john on 2016/4/14.
 */

@ContentView(R.layout.activity_login)
public  class LoginActivity extends Activity {

    @ViewInject(R.id.et_pwd)
    private EditText et_pwd;

    @ViewInject(R.id.et_email)
    private EditText et_email;

    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @ViewInject(R.id.tv_register)
    private TextView tv_register;

    private String email;
    private String pwd;
    private int code;

    private HttpServer http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewUtils.inject(this);

        initView();//初始化布局
        initData();
        initEvent();//初始化事件
    }

    private void initData() {


    }

    private void initEvent() {

        //登录的点解事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd = et_pwd.getText().toString().trim();
                email = et_email.getText().toString().trim();
                if (pwd.equals("") || email.equals("")) {
                    Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    http = new HttpServer();
                    http.log_in(email, pwd, login);
//                    Log.d("aaa","code="+code);

                }
            }
        });
        //注册的点击事件
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    RequestCallBack<String> login = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("login", responseInfo.result);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(responseInfo.result);
                code = jsonObject.getInt("StatusCode");
                Log.d("login", code + "");
                if (code == 200) {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Intent Main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(Main);
                    finish();
                } else if (code == 403) {
                    Toast.makeText(getApplicationContext(), "锁住帐户或登录失败", Toast.LENGTH_SHORT).show();
                } else if (code == 406){
                    Toast.makeText(getApplicationContext(), "您输入的格式不正确", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailure(HttpException e, String s) {
            Log.d("login","网络连接失败");
        }
    };

    private void initView() {


    }
}
