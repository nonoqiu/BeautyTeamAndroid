package company.zzw.john.beautyteam.webserver;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by john on 2016/4/15.
 */
public class HttpServer {

    //服务器根目录
    public static String path = "https://www.obisoft.com.cn/api/";
    HttpUtils http = new HttpUtils();

    public void HttpServer() {
        if (http == null) {
            http = new HttpUtils();
        }
        http.configCurrentHttpCacheExpiry(1000 * 15);// 设置超时时间
    }

    /**
     * 获得hello world的地址
     */
    public void getHelloWorld(RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        http.send(HttpRequest.HttpMethod.GET, path, params, requestCallBack);
    }

    /**
     * Logging
     */
    public void Logging(String data, String des, String platform, String version, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("HappenTime", data);//unnecessary
        params.addBodyParameter("Description", des);//unnecessary
        params.addBodyParameter("HappenPlatform", platform);
        params.addBodyParameter("version", version);
        http.send(HttpRequest.HttpMethod.POST, path + "Log/", params, requestCallBack);
    }

    /**
     * 注册Api
     */
    public void register(String email, String pwd, String confirmpwd, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("Email", email);
        params.addBodyParameter("PassWord", pwd);
        params.addBodyParameter("ConfirmPassword", confirmpwd);
        http.send(HttpRequest.HttpMethod.POST, path + "Register/", params, requestCallBack);
    }

    /**
     * Log in api
     */
    public void log_in(String email, String pwd, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("email", email);
        params.addBodyParameter("password", pwd);
        http.send(HttpRequest.HttpMethod.POST, path + "login/", params, requestCallBack);
    }

    /**
     * Forgot Password Api
     */
    public void forgotPassWord(String email, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("Email", email);
        params.addBodyParameter("ByEmailNotBySms", String.valueOf(true));
        http.send(HttpRequest.HttpMethod.POST, path + "ForgotPassword/", params, requestCallBack);
    }

    /**
     * Chang Password
     */
    public void changPassWord(String oldpwd, String pwd, String newpwd, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("OldPassword", oldpwd);
        params.addBodyParameter("NewPassword", pwd);
        params.addBodyParameter("ConfirmPassword", newpwd);
        http.send(HttpRequest.HttpMethod.POST, path + "ChangePassword/", params, requestCallBack);
    }

    /**
     * currentuser
     */
    public void currentuser(RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        http.send(HttpRequest.HttpMethod.GET, path + "currentuser/", params, requestCallBack);
    }

    /**
     * Login status
     */
    public void loginstatus(RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        http.send(HttpRequest.HttpMethod.GET, path + "loginstatus/", params, requestCallBack);
    }

    /**
     * Logoff
     */
    public void logoff(RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();//只需要post就可以
        http.send(HttpRequest.HttpMethod.POST, path + "logoff/", params, requestCallBack);
    }

    /**
     * latestversion
     */
    public void latestversion(String platform, String currentVersion, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("Platform", platform);
        params.addBodyParameter("CurrentVersion", currentVersion);
        http.send(HttpRequest.HttpMethod.GET, path + "latestversion/", params, requestCallBack);
    }

    /**
     * SchoolList
     */
    public void schoolList(RequestCallBack requestCallBack) {
        RequestParams params = new RequestParams();
        http.send(HttpRequest.HttpMethod.GET, path + "SchoolList/", params, requestCallBack);
    }

    /**
     * SchoolDetail
     */
    public void SchoolDetail(String id, RequestCallBack requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        http.send(HttpRequest.HttpMethod.GET, path + "SchoolDetail/", params, requestCallBack);
    }

    /**
     * bind school
     */
    public void bindschool(String id, RequestCallBack requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        http.send(HttpRequest.HttpMethod.POST, path + "BindSchool/", params, requestCallBack);
    }

    /**
     * Set School Account API---教务处登录（账号，密码）
     */
    public void setSchoolAccount(String Account, String Password, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("Account", Account);
        params.addBodyParameter("Password", Password);
        http.send(HttpRequest.HttpMethod.POST, path + "SetSchoolAccount/", params, requestCallBack);
    }

    /**
     * Create Team API
     */
    public void createTeam(String TeamName, String TeamDescription, String GroupType, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("teamName", TeamName);
        params.addBodyParameter("TeamDescription", TeamDescription);
        params.addBodyParameter("GroupType", GroupType);
        http.send(HttpRequest.HttpMethod.POST, path + "CreateTeam/", params, requestCallBack);
    }

    /**
     * Create Radio Station
     */
    public void creatRadioStation(String RadioStationName, String RadioStationDescription, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("RadioStationName", RadioStationName);
        params.addBodyParameter("RadioStationDescription", RadioStationDescription);
        http.send(HttpRequest.HttpMethod.POST, path + "CreateRadioStation/", params, requestCallBack);
    }

    /**
     * Delete Group  ---id  means group id
     */
    public void deleteGroup(String id, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        http.send(HttpRequest.HttpMethod.POST, path + "DeleteGroup/", params, requestCallBack);
    }

    /**
     * Join Group
     */
    public void joinGroup(String id, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        http.send(HttpRequest.HttpMethod.POST, path + "JoinGroup/", params, requestCallBack);
    }

    /**
     * Leave Group
     */
    public void leaveGroup(String id, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        http.send(HttpRequest.HttpMethod.POST, path + "LeaveGroup/", params, requestCallBack);
    }

    /**
     * List User's Groups Joined or Created
     */
    public void userGroup(RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        http.send(HttpRequest.HttpMethod.POST, path + "Groupsijoined/", params, requestCallBack);
    }

    /**
     * Make a user to be Admin in a group
     */
    public void toAdmin(String TargetUserId, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("TargetUserId", TargetUserId);
        http.send(HttpRequest.HttpMethod.POST, path + "SetAdmin/", params, requestCallBack);
    }

    /**
     * Get Group Details
     */
    public void getGroupDetails(String GroupId, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("GroupId", GroupId);
        http.send(HttpRequest.HttpMethod.POST, path + "GroupDetails/", params, requestCallBack);
    }

    /**
     * BootUser
     */
    public void bootUser(String TargetUserId, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("TargetUserId", TargetUserId);
        http.send(HttpRequest.HttpMethod.POST, path + "BootUser/", params, requestCallBack);
    }

    /**
     * Create Project
     */
    public void createProject(String ProjectName, String ProjectDescription, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("ProjectName", ProjectName);
        params.addBodyParameter("ProjectDescription", ProjectDescription);
        http.send(HttpRequest.HttpMethod.POST, path + "CreateProject/", params, requestCallBack);
    }

    /**
     * Delete Project
     */
    public void deleteProject(String Projectid, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("Projectid", Projectid);
        http.send(HttpRequest.HttpMethod.POST, path + "DeleteProject/", params, requestCallBack);
    }

    /**
     * Create Personal Task
     */
    public void createPersonalTask(String TaskName, String TaskContent, String DeadLine, String NoticeBefore, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("TaskName", TaskName);
        params.addBodyParameter("TaskContent", TaskContent);
        params.addBodyParameter("DeadLine", DeadLine);
        params.addBodyParameter("NoticeBefore", NoticeBefore);
        http.send(HttpRequest.HttpMethod.POST, path + "CreatePersonalTask/", params, requestCallBack);
    }

    /**
     * Create Personal Event
     */
    public void createPersonalEvent(String EventName, String EventContent, String HappenTime, String EndTime, String NoticeBefore, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("EventName", EventName);
        params.addBodyParameter("EventContent", EventContent);
        params.addBodyParameter("HappenTime", HappenTime);
        params.addBodyParameter("EndTime", EndTime);
        params.addBodyParameter("NoticeBefore", NoticeBefore);
        http.send(HttpRequest.HttpMethod.POST, path + "CreatePersonalEvent/", params, requestCallBack);
    }

    /**
     * Delete Personal Task
     */
    public void deletePersonalTask(String Taskid, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("Taskid", Taskid);
        http.send(HttpRequest.HttpMethod.POST, path + "DeletePersonalTask/", params, requestCallBack);
    }

    /**
     * Delete Personal Event
     */
    public void deletePersonalEvent(String Eventid, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("Eventid", Eventid);
        http.send(HttpRequest.HttpMethod.POST, path + "DeletePersonalEvent/", params, requestCallBack);
    }

    /**
     * Personal Task Details
     */
    public void personalTaskDetails(String ObisoftUserId, String ObisoftUser, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("ObisoftUserId", ObisoftUserId);
        params.addBodyParameter("ObisoftUser", ObisoftUser);
        http.send(HttpRequest.HttpMethod.POST, path + "PersonalTaskDetails/", params, requestCallBack);
    }

    /**
     * Personal Event Details
     */
    public void personalEventDetails(String ObisoftUserId, String ObisoftUser, RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("ObisoftUserId", ObisoftUserId);
        params.addBodyParameter("ObisoftUser", ObisoftUser);
        http.send(HttpRequest.HttpMethod.POST, path + "PersonalEventDetails/", params, requestCallBack);
    }

    /**
     * Get All User's Notic
     */
    public void getAllUserNotice(RequestCallBack<String> requestCallBack) {
        RequestParams params = new RequestParams();
        http.send(HttpRequest.HttpMethod.POST, path + "AllNoticeForMe /", params, requestCallBack);

    }


}
