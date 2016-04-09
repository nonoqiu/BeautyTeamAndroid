package company.zzw.john.beautyteam.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ContentView;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.fragment.NoticeFragment;
import company.zzw.john.beautyteam.fragment.TeamFragment;
import company.zzw.john.beautyteam.fragment.MainFragment;
@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    private LayoutInflater layoutinflater;
    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {NoticeFragment.class, TeamFragment.class, MainFragment.class};
    //定义数组来存放选项的图片
    private int mImagViewArray[] ={R.drawable.selector_tab_home,R.drawable.selector_tab_news,
            R.drawable.selector_tab_personal};
    //定义选项卡的文字
    private String mTextView[] ={"Notice","Team","Main"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        com.lidroid.xutils.ViewUtils.inject(this);
        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        layoutinflater= LayoutInflater.from(this);
        mTabHost= (FragmentTabHost) findViewById(R.id.tab_content);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realTabContent);
        //得到fragment的个数
        int count=fragmentArray.length;
        for (int i=0;i<count;i++){
            //为每一个Tab设置文字以及图片
            TabHost.TabSpec tabSpec=mTabHost.newTabSpec(mTextView[i]).setIndicator(getTabImageView(i));
            //将Tab按钮添加到Tab选项卡中
            mTabHost.addTab(tabSpec,fragmentArray[i],null);
        }
        mTabHost.getTabWidget().setDividerDrawable(null);
    }
    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabImageView(int index){
        View view=layoutinflater.inflate(R.layout.tab_item,null);
        ImageView iv_image= (ImageView) view.findViewById(R.id.iv_image);
        iv_image.setImageResource(mImagViewArray[index]);
        TextView tv_text= (TextView) view.findViewById(R.id.tv_text);
        tv_text.setText(mTextView[index]);
        return view;
    }
}
