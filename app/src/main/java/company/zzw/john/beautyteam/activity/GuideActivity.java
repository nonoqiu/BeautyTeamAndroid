package company.zzw.john.beautyteam.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.util.ArrayList;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.adapter.MyPagerAdapter;
import company.zzw.john.beautyteam.utils.DensityUtil;
import company.zzw.john.beautyteam.utils.MyConstants;
import company.zzw.john.beautyteam.utils.SpTools;
import company.zzw.john.beautyteam.webserver.HttpServer;


/**
 * Created by john on 2016/4/8.
 * 设置向导界面，采用Viewpager界面的切换
 */
public class GuideActivity extends Activity {

    private ViewPager vp_guide;
    private LinearLayout ll_points;//点的容器
    private View tv_redpoint;
    private Button bt_startexp;
    private ArrayList<ImageView> pc_guides;
    private MyPagerAdapter adpter;
    private int distance;
    private HttpServer http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();//初始化界面

        initData();//初始化数据

        initEvent();//初始化组件事件
    }

    private void initData() {
        //viewpager adapter  list
        //图片的数据
        int[] pics = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        //定义viewpager使用的容器
        pc_guides = new ArrayList<>();
        //初始化容器中的数据
        for (int i = 0; i < pics.length; i++) {
            ImageView iv_temp = new ImageView(this);
            iv_temp.setImageResource(pics[i]);
            //添加界面中的数据包
            pc_guides.add(iv_temp);
            //给点的容器添加灰色点
            View v_point = new View(getApplicationContext());
            v_point.setBackgroundResource(R.drawable.gray_point);
            //设置点的大小
            int dip = 10;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtil.dip2px(getApplicationContext(), dip),
                    DensityUtil.dip2px(getApplicationContext(), dip));//单位是px
            //设置点与点之间的间隙--并且第一个点不用设置
            if (i != 0) //过滤第一个点
                params.leftMargin = 20;//px
            v_point.setLayoutParams(params);
            //把灰色的点添加到线性布局中
            ll_points.addView(v_point);
        }
        //创建Viewpager的适配器
        adpter = new MyPagerAdapter(pc_guides);
        //设置适配器
        if (vp_guide == null) {
            Log.d("fiend", "null");
        } else {
            vp_guide.setAdapter(adpter);
        }
        //获取网络数据
        http=new HttpServer();
        http.getHelloWorld(getHelloWorldin);

    }

    private void initEvent() {
        /**
         * 监听灰点的布局完成，触发的结果
         */
        tv_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //打印出灰点之间的距离
                distance = (ll_points.getChildAt(1).getLeft() -
                        ll_points.getChildAt(0).getLeft());
                //取消界面变化而产生的回调结果
                tv_redpoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        /**
         * 给button按钮添加事件
         */
        bt_startexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存设置的状态
                SpTools.setBoolean(getApplicationContext(), MyConstants.ISSETUP, true);//保存设置完成的状态
                //进入主界面
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);//启动主界面
                finish();
            }
        });
        /**
         * 给Viewpager添加页码改变的事件
         */
        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面滑动的过程中调用的事件，position当前页面停留的位置】
            // positionOffset偏移的比例值
            // positionOffsetPixels偏移的像素
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //计算红点的左边距
                float leftMagin = distance * (position + positionOffset);
                //设置红点的左边距
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tv_redpoint.getLayoutParams();
                layoutParams.leftMargin = Math.round(leftMagin);//对float类型进行四舍五入
                //重新设置布局参数
                tv_redpoint.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                //当前的Viewpager显示的页面
                //如果ViewPager滑动到第三个页面（最后一页）,显示Button
                if (position == pc_guides.size() - 1) {
                    bt_startexp.setVisibility(View.VISIBLE);//设置按钮的显示
                } else {
                    //不是最后一页，隐藏Button按钮
                    bt_startexp.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView() {
        setContentView(R.layout.activity_guild);

        //Viewpager的组件
        vp_guide = (ViewPager) findViewById(R.id.vp_guide_pages);

        //动态加点的容器
        ll_points = (LinearLayout) findViewById(R.id.ll_guide_points);
        //红点
        tv_redpoint = findViewById(R.id.v_guide_redpoint);
        //开始体验的按钮
        bt_startexp = (Button) findViewById(R.id.bt_guild_startexp);

    }

    RequestCallBack<String> getHelloWorldin= new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("aaa",responseInfo.result);
            Log.d("aaa","dasdadasdasd");

        }

        @Override
        public void onFailure(HttpException e, String s) {
            Toast.makeText(getApplicationContext(),"网络连接失败",Toast.LENGTH_SHORT).show();
        }
    };
}
