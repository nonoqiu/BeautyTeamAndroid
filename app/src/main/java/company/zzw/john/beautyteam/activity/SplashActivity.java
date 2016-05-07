package company.zzw.john.beautyteam.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.utils.MyConstants;
import company.zzw.john.beautyteam.utils.SpTools;
import company.zzw.john.beautyteam.webserver.HttpServer;


/**
 * Created by john on 2016/4/8.
 */
public class SplashActivity extends Activity {


    private AnimationSet as;//动画集
    private ImageView iv_mainview;//第一个图片

    private HttpServer http;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        initViews();//初始化布局

        startAnimation();//开始播放动画

        initData();

        initEvent();//初始化事件
    }

    private void initData() {

        http = new HttpServer();
//        http.getHelloWorld(get);
//        http.register("1256618738@qq.com", "111111", "111111", get);
//        http.log_in("1256618728@qq.com","000000",get);
//        http.forgotPassWord("1256618728@qq.com",get);
//        http.changPassWord("000000", "000001", "000001", get);
    }

    RequestCallBack<String> get = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("aaa", responseInfo.result);

        }

        @Override
        public void onFailure(HttpException e, String s) {
            Toast.makeText(getApplicationContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
            Log.d("aaa", "faild");
        }
    };

    RequestCallBack<String> register = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("register", responseInfo.result);
        }

        @Override
        public void onFailure(HttpException e, String s) {
            Log.d("aaa", "faild2");
        }
    };

    RequestCallBack<String> change = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("aaa", responseInfo.result);
        }

        @Override
        public void onFailure(HttpException e, String s) {

        }
    };
    RequestCallBack<String> login = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("aaa", responseInfo.result);
        }

        @Override
        public void onFailure(HttpException e, String s) {

        }
    };


    private void initEvent() {

        //1.监听动画播完的事件--只是一处用的事件，用匿名类对象，多处声明为成员变量
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //监听动画播完
                //2.判断进入哪一个界面
                if (SpTools.getBoolean(getApplicationContext(), MyConstants.ISSETUP, false)) {
                    //true,设置过，直接进入主界面
                    Intent login = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(login);
                } else {
                    //false,没有经过设置，进入向导界面
                    Intent guide = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(guide);
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


    /**
     * 开始播放动画--旋转，缩放，渐变
     */
    private void startAnimation() {
        //false 代表动画集中每种动画都采用自己的动画插入器（数学函数）
        as = new AnimationSet(false);

        //旋转动画，锚点
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);//设置动画播放时间--2秒
        ra.setFillAfter(true);//设置动画播放完成之后，停留在当前状态
        as.addAnimation(ra);//将旋转动画，添加到动画集
        //渐变动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);//由完全透明到不透明
        aa.setDuration(2000);//设置动画播放时间--2秒
        aa.setFillAfter(true);//设置动画播放完成之后，停留在当前状态
        as.addAnimation(aa);//将渐变动画，添加到动画集
        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);//设置动画播放时间--2秒
        sa.setFillAfter(true);//设置动画播放完成之后，停留在当前状态
        as.addAnimation(sa);//将渐变动画，添加到动画集
        //开始播放动画
        iv_mainview.startAnimation(as);

        //动画播完进入下一个界面--向导界面和主界面
    }

    private void initViews() {
        //设置主界面
        setContentView(R.layout.activity_splash);
        //获取背景图片
        iv_mainview = (ImageView) findViewById(R.id.iv_splash_main);


    }
}
