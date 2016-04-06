package company.zzw.john.beautyteam.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.zip.Inflater;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.fragment.HomeFragment;
import company.zzw.john.beautyteam.fragment.NewsFragment;
import company.zzw.john.beautyteam.fragment.PersonalFragment;

public class MainActivity extends FragmentActivity {

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    private Inflater layoutinflater;
    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, NewsFragment.class, PersonalFragment.class};
    //定义数组来存放选项的图片
    private int mImagViewArray[] ={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
