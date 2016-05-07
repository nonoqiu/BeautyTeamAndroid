package company.zzw.john.beautyteam.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import company.zzw.john.beautyteam.R;

/**
 * Created by john on 2016/4/5.
 */
public class NoticeFragment extends BaseFragment {

    @ViewInject(R.id.tv_addNotice)
    private TextView tv_addNotice;

    @ViewInject(R.id.lv_notice)
    private ExpandableListView lv_notice;

    private PopupMenu popupMenu;

    private NoticeListAdapter noticeListAdapter;

    //listview的数据
    private List<String> parent = null;
    private Map<String, List<String>> map = null;


    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);//加载fragment布局
        ViewUtils.inject(this, view);//注入view和事件


        initDatas();
        initView();
        initEvent();

        return view;


    }

    private void initDatas() {
        parent = new ArrayList<String>();
        parent.add("parent1");
        parent.add("parent2");
        parent.add("parent3");

        map = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("child1-1");
        map.put("parent1", list1);

        List<String> list2 = new ArrayList<String>();
        list2.add("child2-1");
        map.put("parent2", list2);

        List<String> list3 = new ArrayList<String>();
        list3.add("child3-1");

        map.put("parent3", list3);
        super.initData();
    }

    private void initEvent() {
        tv_addNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(tv_addNotice);
            }
        });
    }

    private void initView() {
        noticeListAdapter = new NoticeListAdapter(getContext());
        lv_notice.setAdapter(noticeListAdapter);
    }

    @Override
    public void initData() {

    }

    /**
     * 得到数据
     */
//    RequestCallBack<String>

    /**
     * 显示弹出菜单的方法
     *
     * @param view
     */
    public void showPopupMenu(View view) {
        //创建popupMenu对象
        popupMenu = new PopupMenu(getContext(), view);
        //将菜单布局加载到popup对象中
        mActivity.getMenuInflater().inflate(R.menu.menu_notice, popupMenu.getMenu());
        //给菜单的菜单项的点击事件绑定事件监听器
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_create_personalEvent:
                        break;
                    case R.id.item_create_personalTask:
                        break;
                    case R.id.item_create_import:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        //让菜单显示
        popupMenu.show();
    }

    /**
     * 内部Adapter类
     */
    private class NoticeListAdapter extends BaseExpandableListAdapter {


        //父组件
        private class ExpandGroupHolder {
            TextView tv_notice_listitem_name;
            TextView tv_notice_listitem_style;
        }

        //子组件-holder
        private class ExPandListHolder {

        }

        private LayoutInflater mGroupInflater;//用于加载group的布局xml
        private LayoutInflater mChildInflater;//用于加载listitem的布局xml
        //自定义的构造函数


        public NoticeListAdapter(Context context) {
            this.mGroupInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mChildInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            return map.get(key).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        //得到子item需要关联的数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return map.get(key).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //得到子item的id
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ExpandGroupHolder holder = null; //清空临时变量holder
            if (convertView == null) {
                convertView = mGroupInflater.inflate(R.layout.lv_notice_item, null);
                holder = new ExpandGroupHolder();
                holder.tv_notice_listitem_name = (TextView) convertView.findViewById(R.id.tv_notice_listitem_name);
                holder.tv_notice_listitem_style = (TextView) convertView.findViewById(R.id.tv_notice_listitem_style);
                convertView.setTag(holder);
            } else {
                holder = (ExpandGroupHolder) convertView.getTag();
            }
            //设置数据，并且不要忘了notifyDataSetChanged（）；方法更新界面

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ExPandListHolder holder = null; //清空临时变量holder
            if (convertView == null) {
                convertView = mChildInflater.inflate(R.layout.lv_notice_item_child, null);
                holder = new ExPandListHolder();
                convertView.setTag(holder);
            } else {
                holder = (ExPandListHolder) convertView.getTag();
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
