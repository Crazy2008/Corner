package com.jiao.luo.Response;

import com.jiao.luo.R;
import com.jiao.luo.utils.Constant;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ResComment implements MultiItemEntity {

    /**
     * returnCode : 1
     * data : {"list":[{"id":"24","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501732080","content":"I ","support":"35","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"12","commentid":"special-1-1","userid":"9","username":"18110950113","creat_at":"1501577302","content":"我再来一个品论吧","support":"5","user":{"userid":"9","phpssouid":"2","username":"18110950113","password":"9eead9e4481cbd68c3dad01f04c0f54d","encrypt":"M5ccPp","nickname":"wudao2010","regdate":"1501575170","lastdate":"1501666963","regip":"36.5.194.78","lastip":"36.5.194.78","loginnum":"0","email":"fasdfaf@qq.com","groupid":"2","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"","from":"","mobile":"","HeadPic":null}},{"id":"16","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501659768","content":"内容内容","support":"4","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}},{"id":"25","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501835595","content":"You ","support":"4","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"11","commentid":"special-1-1","userid":"9","username":"18110950113","creat_at":"1501575186","content":"合肥法撒旦撒爱上是","support":"3","user":{"userid":"9","phpssouid":"2","username":"18110950113","password":"9eead9e4481cbd68c3dad01f04c0f54d","encrypt":"M5ccPp","nickname":"wudao2010","regdate":"1501575170","lastdate":"1501666963","regip":"36.5.194.78","lastip":"36.5.194.78","loginnum":"0","email":"fasdfaf@qq.com","groupid":"2","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"","from":"","mobile":"","HeadPic":null}},{"id":"14","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501578537","content":"再评论下试试","support":"3","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}},{"id":"22","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501731967","content":"The ","support":"3","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"23","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501732023","content":"You ","support":"2","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"13","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501578478","content":"不错不粗哦","support":"1","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}},{"id":"15","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501659768","content":"内容内容","support":"1","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}}],"count":12}
     * jumpUrl : null
     * code : null
     * type : null
     * message : 接口请求成功
     * status : null
     */

    private int returnCode;
    private DataBean data;
    private Object jumpUrl;
    private Object code;
    private Object type;
    private String message;
    private Object status;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(Object jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public static class DataBean {
        /**
         * list : [{"id":"24","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501732080","content":"I ","support":"35","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"12","commentid":"special-1-1","userid":"9","username":"18110950113","creat_at":"1501577302","content":"我再来一个品论吧","support":"5","user":{"userid":"9","phpssouid":"2","username":"18110950113","password":"9eead9e4481cbd68c3dad01f04c0f54d","encrypt":"M5ccPp","nickname":"wudao2010","regdate":"1501575170","lastdate":"1501666963","regip":"36.5.194.78","lastip":"36.5.194.78","loginnum":"0","email":"fasdfaf@qq.com","groupid":"2","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"","from":"","mobile":"","HeadPic":null}},{"id":"16","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501659768","content":"内容内容","support":"4","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}},{"id":"25","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501835595","content":"You ","support":"4","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"11","commentid":"special-1-1","userid":"9","username":"18110950113","creat_at":"1501575186","content":"合肥法撒旦撒爱上是","support":"3","user":{"userid":"9","phpssouid":"2","username":"18110950113","password":"9eead9e4481cbd68c3dad01f04c0f54d","encrypt":"M5ccPp","nickname":"wudao2010","regdate":"1501575170","lastdate":"1501666963","regip":"36.5.194.78","lastip":"36.5.194.78","loginnum":"0","email":"fasdfaf@qq.com","groupid":"2","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"","from":"","mobile":"","HeadPic":null}},{"id":"14","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501578537","content":"再评论下试试","support":"3","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}},{"id":"22","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501731967","content":"The ","support":"3","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"23","commentid":"special-1-1","userid":"6","username":"你们","creat_at":"1501732023","content":"You ","support":"2","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}},{"id":"13","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501578478","content":"不错不粗哦","support":"1","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}},{"id":"15","commentid":"special-1-1","userid":"8","username":"wudg","creat_at":"1501659768","content":"内容内容","support":"1","user":{"userid":"8","phpssouid":"1","username":"wudg","password":"26313145afbf4d6113313ad105d04d1d","encrypt":"N3zZ3","nickname":"wudg","regdate":"1501487541","lastdate":"1502159120","regip":"223.210.21.106","lastip":"36.7.71.241","loginnum":"20","email":"123@qq.com","groupid":"8","areaid":"0","amount":"0.00","point":"0","modelid":"10","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"167,173,174","from":"","mobile":"18110950113","HeadPic":"/2017/08/03/20170803161617_67_VOKPDXJ5.jpg"}}]
         * count : 12
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public  static  class ListBean implements MultiItemEntity {



            //是否点赞
            private boolean isSup;

            public boolean isSup() {
                return isSup;
            }

            public void setSup(boolean sup) {
                isSup = sup;
            }

            public String getIsWhat() {
                return isWhat;
            }

            public void setIsWhat(String isWhat) {
                this.isWhat = isWhat;
            }

            /**
             * id : 24
             * commentid : special-1-1
             * userid : 6
             * username : 你们
             * creat_at : 1501732080
             * content : I
             * support : 35
             * user : {"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1502441361","regip":"36.5.193.228","lastip":"36.5.194.213","loginnum":"54","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}
             */


            private String isWhat;
            private String id;
            private String commentid;
            private String userid;
            private String username;
            private String creat_at;
            private String content;
            private String support;
            private UserBean user;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCommentid() {
                return commentid;
            }

            public void setCommentid(String commentid) {
                this.commentid = commentid;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getCreat_at() {
                return creat_at;
            }

            public void setCreat_at(String creat_at) {
                this.creat_at = creat_at;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSupport() {
                return support;
            }

            public void setSupport(String support) {
                this.support = support;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            @Override
            public int getItemType() {
                if (getIsWhat() == Constant.ONE)
                    return R.layout.activity_comment_one;
                if (getIsWhat() == Constant.NORMAL)
                    return R.layout.activity_comment_item;
                if (getIsWhat() == Constant.HORIZONTAL)
                    return R.layout.activity_comment_horizontal;
                return 0;

            }

            public class UserBean {
                /**
                 * userid : 6
                 * phpssouid : 1
                 * username : 你们
                 * password : bd8c0f69ace2d4782f6313a88d8e176f
                 * encrypt : 7MPhT
                 * nickname : 你们
                 * regdate : 1500954303
                 * lastdate : 1502441361
                 * regip : 36.5.193.228
                 * lastip : 36.5.194.213
                 * loginnum : 54
                 * email :
                 * groupid : 0
                 * areaid : 0
                 * amount : 0.00
                 * point : 0
                 * modelid : 0
                 * message : 0
                 * islock : 0
                 * vip : 0
                 * overduedate : 0
                 * siteid : 1
                 * connectid :
                 * catesid : 220,168,175
                 * from :
                 * mobile : 18555334112
                 * HeadPic : http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg
                 */

                private String userid;
                private String phpssouid;
                private String username;
                private String password;
                private String encrypt;
                private String nickname;
                private String regdate;
                private String lastdate;
                private String regip;
                private String lastip;
                private String loginnum;
                private String email;
                private String groupid;
                private String areaid;
                private String amount;
                private String point;
                private String modelid;
                private String message;
                private String islock;
                private String vip;
                private String overduedate;
                private String siteid;
                private String connectid;
                private String catesid;
                private String from;
                private String mobile;
                private String HeadPic;

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getPhpssouid() {
                    return phpssouid;
                }

                public void setPhpssouid(String phpssouid) {
                    this.phpssouid = phpssouid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getEncrypt() {
                    return encrypt;
                }

                public void setEncrypt(String encrypt) {
                    this.encrypt = encrypt;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getRegdate() {
                    return regdate;
                }

                public void setRegdate(String regdate) {
                    this.regdate = regdate;
                }

                public String getLastdate() {
                    return lastdate;
                }

                public void setLastdate(String lastdate) {
                    this.lastdate = lastdate;
                }

                public String getRegip() {
                    return regip;
                }

                public void setRegip(String regip) {
                    this.regip = regip;
                }

                public String getLastip() {
                    return lastip;
                }

                public void setLastip(String lastip) {
                    this.lastip = lastip;
                }

                public String getLoginnum() {
                    return loginnum;
                }

                public void setLoginnum(String loginnum) {
                    this.loginnum = loginnum;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getGroupid() {
                    return groupid;
                }

                public void setGroupid(String groupid) {
                    this.groupid = groupid;
                }

                public String getAreaid() {
                    return areaid;
                }

                public void setAreaid(String areaid) {
                    this.areaid = areaid;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getPoint() {
                    return point;
                }

                public void setPoint(String point) {
                    this.point = point;
                }

                public String getModelid() {
                    return modelid;
                }

                public void setModelid(String modelid) {
                    this.modelid = modelid;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getIslock() {
                    return islock;
                }

                public void setIslock(String islock) {
                    this.islock = islock;
                }

                public String getVip() {
                    return vip;
                }

                public void setVip(String vip) {
                    this.vip = vip;
                }

                public String getOverduedate() {
                    return overduedate;
                }

                public void setOverduedate(String overduedate) {
                    this.overduedate = overduedate;
                }

                public String getSiteid() {
                    return siteid;
                }

                public void setSiteid(String siteid) {
                    this.siteid = siteid;
                }

                public String getConnectid() {
                    return connectid;
                }

                public void setConnectid(String connectid) {
                    this.connectid = connectid;
                }

                public String getCatesid() {
                    return catesid;
                }

                public void setCatesid(String catesid) {
                    this.catesid = catesid;
                }

                public String getFrom() {
                    return from;
                }

                public void setFrom(String from) {
                    this.from = from;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getHeadPic() {
                    return HeadPic;
                }

                public void setHeadPic(String HeadPic) {
                    this.HeadPic = HeadPic;
                }
            }
        }
    }
}
