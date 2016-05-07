package company.zzw.john.beautyteam.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONException;
import org.json.JSONObject;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.utils.SpTools;
import company.zzw.john.beautyteam.webserver.HttpServer;


/**
 * Created by john on 2016/4/14.
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends Activity {

    @ViewInject(R.id.btn_register)
    private Button btn_register;

    @ViewInject(R.id.et_username)
    private EditText et_username;

    @ViewInject(R.id.et_pwd)
    private EditText et_pwd;

    HttpServer http;

    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        http = new HttpServer();

        initEvent();
    }

    private void initEvent() {
        //注册按钮的点击事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et_username.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();

                if (pwd.equals("") || user.equals("")) {
                    Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    http.register(user, pwd, pwd, register);

                }
            }
        });
    }

    RequestCallBack<String> register = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("register", "json=" + responseInfo.result);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(responseInfo.result);
                code = jsonObject.getInt("StatusCode");
                Log.d("register", "code=" + code);
                if (code == 200) {
                    Toast.makeText(getApplicationContext(), "恭喜您注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (code == 409) {
                    Toast.makeText(getApplicationContext(), "您的电子邮箱已经注册，注册失败,请重新输入", Toast.LENGTH_SHORT).show();
                } else if (code == 406){
                    Toast.makeText(getApplicationContext(), "您的输入格式不正确，密码要求至少6位数",Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(HttpException e, String s) {

        }
    };
}
