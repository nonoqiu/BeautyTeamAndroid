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
@ContentView(R.layout.createproject_activity)
public class CreateProject extends Activity {

    @ViewInject(R.id.et_pwd)
    private EditText et_ProjectName;

    @ViewInject(R.id.et_email)
    private EditText et_ProjectDesc;

    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @ViewInject(R.id.tv_common_title)
    private TextView tv_common_title;


    private String projectName;
    private String projectDesc;
    private int code;

    private HttpServer http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewUtils.inject(this);


        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        //确定的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectDesc = et_ProjectName.getText().toString().trim();
                projectName = et_ProjectDesc.getText().toString().trim();
                if (projectDesc.equals("") || projectName.equals("")) {
                    Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    http = new HttpServer();
                    http.createProject(projectName, projectDesc, createProject);
                    finish();
                }

            }
        });

    }

    private void initData() {

    }

    private void initView() {
        tv_common_title.setText("Create Project");

    }

    RequestCallBack<String> createProject = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("createProject", responseInfo.result);
        }

        @Override
        public void onFailure(HttpException e, String s) {
            Log.d("createProject", "网络连接失败");
        }
    };
}
