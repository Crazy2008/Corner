package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public class ResPubComment {


    /**
     * returnCode : 1
     * data : {"list":[{"id":"42","commentid":"content_254-5611-1","userid":"6","username":"你们","creat_at":"1502765479","content":"You ","support":"2","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1503017965","regip":"36.5.193.228","lastip":"36.5.198.108","loginnum":"67","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"},"is_support":-1}],"count":1}
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

    public static class DataBean {
        /**
         * list : [{"id":"42","commentid":"content_254-5611-1","userid":"6","username":"你们","creat_at":"1502765479","content":"You ","support":"2","user":{"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1503017965","regip":"36.5.193.228","lastip":"36.5.198.108","loginnum":"67","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"},"is_support":-1}]
         * count : 1
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

        public static class ListBean {
            /**
             * id : 42
             * commentid : content_254-5611-1
             * userid : 6
             * username : 你们
             * creat_at : 1502765479
             * content : You
             * support : 2
             * user : {"userid":"6","phpssouid":"1","username":"你们","password":"bd8c0f69ace2d4782f6313a88d8e176f","encrypt":"7MPhT","nickname":"你们","regdate":"1500954303","lastdate":"1503017965","regip":"36.5.193.228","lastip":"36.5.198.108","loginnum":"67","email":"","groupid":"0","areaid":"0","amount":"0.00","point":"0","modelid":"0","message":"0","islock":"0","vip":"0","overduedate":"0","siteid":"1","connectid":"","catesid":"220,168,175","from":"","mobile":"18555334112","HeadPic":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/04/20170804160502_644_TWHAVFIN.jpg"}
             * is_support : -1
             */

            private String id;
            private String commentid;
            private String userid;
            private String username;
            private String creat_at;
            private String content;
            private String support;
            private UserBean user;
            private int is_support;

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

            public int getIs_support() {
                return is_support;
            }

            public void setIs_support(int is_support) {
                this.is_support = is_support;
            }

            public static class UserBean {
                /**
                 * userid : 6
                 * phpssouid : 1
                 * username : 你们
                 * password : bd8c0f69ace2d4782f6313a88d8e176f
                 * encrypt : 7MPhT
                 * nickname : 你们
                 * regdate : 1500954303
                 * lastdate : 1503017965
                 * regip : 36.5.193.228
                 * lastip : 36.5.198.108
                 * loginnum : 67
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
