package company.zzw.john.beautyteam.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by john on 2016/4/8.
 */
public class MyPagerAdapter extends PagerAdapter {

    private ArrayList<ImageView> views;

    public MyPagerAdapter(ArrayList<ImageView> views) {
        this.views = views;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //从viewpager中移除
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //获取view
        View child=views.get(position);
        //添加view
        container.addView(child);
        //返回view
        return child;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    /**
     *过滤和缓存的作用
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}
