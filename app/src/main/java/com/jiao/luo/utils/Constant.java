package com.jiao.luo.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class Constant {


    public static String FRAGMENT_DATA = "FRAGMENT_DATA";
    public static String BASE_URL = "http://api.jiaoluo.hejiashop.com";

    // 菜单话题列表
    public static String MENU_LIST = BASE_URL + "/main/typelist.html";
    //获取手机短信验证码
    public static String GET_SMSCODE = BASE_URL + "/login/getsmscode.html";
    //用户注册
    public static String RIGSITER = BASE_URL + "/login/register.html";
    //用户登录
    public static String LOGIN = BASE_URL + "/login/index.html";
    //角落板块列表
    public static String CATELIST = BASE_URL + "/member/catelist.html";
    //    上传用户板块选择信息
    public static String UPLOAD_CATELIST = BASE_URL + "/member/choise.html";
    // 发现置顶及选择板块三个话题信息
    public static String FINDFIXED = BASE_URL + "/main/findfixed.html";
    // 发现中间三个话题
    public static String FINDMIDDLE = BASE_URL + "/main/findmiddle.html";
    //    关注/取消关注话题
    public static String FOLLOWADD = BASE_URL + "/member/followadd.html";
    //    话题详情
    public static String FOLLOWNEW = BASE_URL + "/main/follownew.html";
    // 收藏/取消收藏文章
    public static String NEWSFAV = BASE_URL + "/member/newsfav.html";
    //发现下拉更新话题
    public static String FINDLIST = BASE_URL + "/main/findlist.html";
    //    获取大操场列表
    public static String SPECIAL_LIST = BASE_URL + "/special/special_list.html";
    // 大操场专题详细数据
    public static String SPECIAL_DETAIL = BASE_URL + "/special/special_detail.html";
    //    大操场评论列表数据
    public static String COMMENTS = BASE_URL + "/special/comments.html";
    //    关注话题列表
    public static String FOLLOWLIST = BASE_URL + "/member/followlist.html";
    //关注话题是否选择推送
    public static String FOLLOWPUSH = BASE_URL + "/member/followpush.html";
    //用户中心 - 我喜欢的
    public static String FAVLIST = BASE_URL + "/member/favlist.html";
    //    订阅
    public static String SUBSCRIBE = BASE_URL + "/main/subscribe.html";
    //    文章评论列表
    public static String COMMENTLIST = BASE_URL + "/member/commentlist.html";
    //    发布评论
    public static String COMMENTADD = BASE_URL + "/member/commentadd.html";

    //点赞/取消点赞评论
    public static String COMMENTSUPPORT = BASE_URL + "/member/commentsupport.html";







    //  对大操场的投票选项进行投票
    public static String ADDVOTE = BASE_URL + "/special/vote.html";
    //获取大操场投票结果
    public static String OPTION_RESULT = BASE_URL + "/special/option_result.html";
    //添加大操场评论
    public static String ADD_COMMENTS = BASE_URL + "/special/add_comments.html";
    //点赞或者取消点赞评论
    public static String ADDSUPPORT = BASE_URL + "/special/support.html";

    //上传头像
    public static String EDITIMG = BASE_URL + "/center/editimg.html";
    //    用户退出
    public static String LOGOUT = BASE_URL + "/login/logout.html";

    //    搜索话题
    public static String SEARCH = BASE_URL + "/main/search.html";
    //修改密码
    public static String EDITPWD = BASE_URL + "/center/editpwd.html";
    // 用户中心 - 我喜欢的 - 搜索
    public static String FAVSEARCH = BASE_URL + "/member/favsearch.html";
    //关注话题搜索
    public static String FOLLOWSEARCH = BASE_URL + "/member/followsearch.html";

    //重置密码
    public static String RESETPWD = BASE_URL + "/login/resetpwd.html";



    public static String TOKEN = "";
    public static int USERID = -1;
    //用户选择板块信息
    public static String CATEIDSID = "";
    public static String NICKNAME = "";
    public static String HEADPIC = "";
    //是否登录
    public static Boolean ISLOGIN = false;
    public static int Code=101;

    public static void clearLoginData() {
        TOKEN = "";
        USERID = -1;
        CATEIDSID = "";
        NICKNAME = "";
        HEADPIC = "";
        ISLOGIN = false;
    }

    public static List<Integer> CATEIDSID_LIST = new ArrayList<>();


    public static final String MOBILE = "MOBILE";
    public static final String PASSWORD = "PASSWORD";
    public static final String TYPEID = "PASSWORD";
    public static final String SUBJECTID = "SUBJECTID";
    public static final String TITLE = "TITLE";
    public static final String SID = "SID";
    public static final String SP_HISTORY = "SP_HISTORY";
    public  static final int RequestCode=100;

    public static  String keyword="";

    public static final String T = "T";
    public static final String U = "U";
    public static final String H = "H";
    public static final String N= "N";
    public static final String I= "I";


    public static final String PAGE= "PAGE";
    public static  String eqid= "";





    public static final String ONE = "ONE";
    public static final String NORMAL = "NORMAL";
    public static final String HORIZONTAL = "HORIZONTAL";
    public static  Boolean  ISREGISTER = false;
    public static  Boolean  ISLOSE = false;




    public static final String URL = "url";
    public static final String COMMENT_COUNT = "comment_count";
    public static final String FAV_COUNT = "fav_count";
    public static final String VOTE = "VOTE";
    public static final String ID = "ID";
    public static final String NEWID = "NEWID";
    public static final String NEWSID = "NEWSID";


    public static  final String IS_FOLLOW="IS_FOLLOW";



    public static final String SMSCODE = "SMSCODE";

    //话题详情adapter的类型  0  代表 图片    1代表视频
    public static final int PIC = 0;
    public static final int VIDIO = 1;
    
    public static  void clearLoginSPData(Context context){
        SPTools.saveString(context,Constant.T,"");
        SPTools.saveInt(context,Constant.U,-1);
        SPTools.saveBoolean(context,Constant.I,false);
        SPTools.saveString(context,Constant.H,"");
        SPTools.saveString(context,Constant.N,"");

    }


}
