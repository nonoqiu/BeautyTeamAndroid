package company.zzw.john.beautyteam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import company.zzw.john.beautyteam.R;
import company.zzw.john.beautyteam.activity.CreateProject;
import company.zzw.john.beautyteam.activity.CreateRadioStation;
import company.zzw.john.beautyteam.activity.CreateTeam;
import company.zzw.john.beautyteam.domain.GroupDetails;
import company.zzw.john.beautyteam.domain.GroupId;
import company.zzw.john.beautyteam.utils.SpTools;
import company.zzw.john.beautyteam.webserver.HttpServer;

/**
 * Created by john on 2016/4/5.
 */
public class TeamFragment extends BaseFragment {

    @ViewInject(R.id.lv_team)
    private ListView lv_team;//team listview

    @ViewInject(R.id.tv_addTeam)
    private TextView tv_addTeam;// + menu

    private TeamListAdapter teamListAdapter;//listAdaoter


    PopupMenu popupMenu = null;  //弹出菜单

    private HttpServer http;  //httpsever类
    private Gson gson;  //gson

    private List<GroupId.ListEntity> lists = new ArrayList<>();
    private String jsonCache;


    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        ViewUtils.inject(this, view);

        http = new HttpServer();

        gson = new Gson();


        initView();
        initEvent();

        return view;
    }

    private void initEvent() {
        //弹出菜单的事件
        tv_addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(tv_addTeam);
            }
        });
        //
    }

    private void initView() {


    }

    @Override
    public void initData() {
        //设置listview的事件
        teamListAdapter = new TeamListAdapter();
        lv_team.setAdapter(teamListAdapter);

        //从本地获取数据
        jsonCache = SpTools.getString(getContext(), "userGroup", null);
        if (!TextUtils.isEmpty(jsonCache)){
            //有数据解析数据
            GroupId  groupId=pareseJson(jsonCache);

            //处理数据
            processData(groupId);
        }
        //从网络获取数据,解析,处理
        getDataFromNet();

        super.initData();

    }

    private void getDataFromNet() {
        http.log_in("111111@qq.com", "123456", login); //只登陆一次

    }

    private class TeamListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.lv_team_item, null);
                holder = new ViewHolder();

                holder.tv_list_team_name = (TextView) convertView.findViewById(R.id.tv_list_team_name);
                holder.tv_list_team_desc = (TextView) convertView.findViewById(R.id.tv_list_team_desc);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //设置数据：（并且别忘了在调用数据后通知adapter的地方进行更新界面notify）
            GroupId.ListEntity list = lists.get(position);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.POST, "https://www.obisoft.com.cn/api/GroupDetails/"+list.GroupId, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    Log.d("GroupDetails", "onSuccess: "+responseInfo.result);
                    String jsonData=responseInfo.result;
                    //保存到本地数据
                    SpTools.setString(getContext(),"GroupDetails",null);
                    GroupDetails groupDetails=gson.fromJson(jsonData,GroupDetails.class);
                    holder.tv_list_team_name.setText(groupDetails.Object.GroupName);
                    holder.tv_list_team_desc.setText(groupDetails.Object.GroupDescription);

                }

                @Override
                public void onFailure(HttpException e, String s) {

                }
            });

            return convertView;
        }

        private class ViewHolder {
            TextView tv_list_team_name;
            TextView tv_list_team_desc;
        }
    }

    /**
     *  show popupMenu
     * @param view
     */
    public void showPopupMenu(View view) {
        //创建popupMenu对象
        popupMenu = new PopupMenu(getContext(), view);
        //将菜单布局加载到popup菜单中
        mActivity.getMenuInflater().inflate(R.menu.menu_team, popupMenu.getMenu());
        //给菜单的菜单项的点击事件绑定事件监听器
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_create_team:
                        Intent createTeam = new Intent(getContext(), CreateTeam.class);
                        startActivity(createTeam);
                        break;
                    case R.id.item_create_RadioStation:
                        Intent createRadioStation = new Intent(getContext(), CreateRadioStation.class);
                        startActivity(createRadioStation);
                        break;
                    case R.id.item_create_TeamProjects:
                        Intent createProject = new Intent(getContext(), CreateProject.class);
                        startActivity(createProject);
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

    RequestCallBack<String> login = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("login", "onSuccess: " + responseInfo.result);
            http.userGroup(userGroup);
        }

        @Override
        public void onFailure(HttpException e, String s) {
            Log.d("login", "onFailure: " + e.toString());
        }
    };

    /**
     * 解析数据
     */
    private GroupId pareseJson(String jsonData) {
        //解析json数据
        GroupId groupId = gson.fromJson(jsonData, GroupId.class);
        return groupId;
    }
    /**
     * 处理数据
     */
    private void processData(GroupId gruopId){
        //设置team列表的listView数据
        setListViewData(gruopId);
    }

    private void setListViewData(GroupId gruopId) {
        lists=gruopId.List;
        //设置好数据后更新list
        teamListAdapter.notifyDataSetChanged();
    }

    /**
     * get groupId
     */
    RequestCallBack<String> userGroup = new RequestCallBack<String>() {
        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            Log.d("userGroup", responseInfo.result);
            //请求数据成功
            String jsondata = responseInfo.result;
            //将数据保存到本地
            SpTools.setString(getContext(), "userGroup", jsondata);
            //解析数据
            GroupId groupId = pareseJson(jsondata);
            //处理数据
            lists = groupId.List;

        }

        @Override
        public void onFailure(HttpException e, String s) {
            Log.d("userGroup", "onFailure: 网络连接失败 ");
        }
    };




}
