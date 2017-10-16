package com.jiao.luo.Response;

import java.util.List;

/**
 * 大操场投票的详情
 */

public class ResVote {

    /**
     * returnCode : 1
     * data : {"id":2,"title":"社交行为无法理解么？？","banner":"http://119.23.242.248/phpcms/package/uploadfile/2017/0814/20170814055940976.jpg","thumb":"http://119.23.242.248/phpcms/package/uploadfile/2017/0814/20170814055940976.jpg","commentCount":"5","description":"无法理解的结束发撒旦撒旦十大大叔阿萨德阿打算十大阿萨德十大撒爱上撒DSA发十大DSA阿打算","contents":"","createtime":1501487997,"voteInfo":{"subjectid":1,"subject":"单选投票选项","ischeckbox":0,"description":"投票选项","fromdate":"2017-07-30","todate":"2017-08-06","enabled":1,"optionnumber":2,"option":[{"optionid":1,"subjectid":1,"option":"选项1","image":"","listorder":0},{"optionid":2,"subjectid":1,"option":"选项2","image":"","listorder":0}]}}
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
         * id : 2
         * title : 社交行为无法理解么？？
         * banner : http://119.23.242.248/phpcms/package/uploadfile/2017/0814/20170814055940976.jpg
         * thumb : http://119.23.242.248/phpcms/package/uploadfile/2017/0814/20170814055940976.jpg
         * commentCount : 5
         * description : 无法理解的结束发撒旦撒旦十大大叔阿萨德阿打算十大阿萨德十大撒爱上撒DSA发十大DSA阿打算
         * contents :
         * createtime : 1501487997
         * voteInfo : {"subjectid":1,"subject":"单选投票选项","ischeckbox":0,"description":"投票选项","fromdate":"2017-07-30","todate":"2017-08-06","enabled":1,"optionnumber":2,"option":[{"optionid":1,"subjectid":1,"option":"选项1","image":"","listorder":0},{"optionid":2,"subjectid":1,"option":"选项2","image":"","listorder":0}]}
         */

        private int id;
        private String title;
        private String banner;
        private String thumb;
        private String commentCount;
        private String description;
        private String contents;
        private int createtime;
        private VoteInfoBean voteInfo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public VoteInfoBean getVoteInfo() {
            return voteInfo;
        }

        public void setVoteInfo(VoteInfoBean voteInfo) {
            this.voteInfo = voteInfo;
        }

        public static class VoteInfoBean {
            /**
             * subjectid : 1
             * subject : 单选投票选项
             * ischeckbox : 0
             * description : 投票选项
             * fromdate : 2017-07-30
             * todate : 2017-08-06
             * enabled : 1
             * optionnumber : 2
             * option : [{"optionid":1,"subjectid":1,"option":"选项1","image":"","listorder":0},{"optionid":2,"subjectid":1,"option":"选项2","image":"","listorder":0}]
             */

            private int subjectid;
            private String subject;
            private int ischeckbox;
            private String description;
            private String fromdate;
            private String todate;
            private int enabled;
            private int optionnumber;
            private List<OptionBean> option;

            public int getSubjectid() {
                return subjectid;
            }

            public void setSubjectid(int subjectid) {
                this.subjectid = subjectid;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public int getIscheckbox() {
                return ischeckbox;
            }

            public void setIscheckbox(int ischeckbox) {
                this.ischeckbox = ischeckbox;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getFromdate() {
                return fromdate;
            }

            public void setFromdate(String fromdate) {
                this.fromdate = fromdate;
            }

            public String getTodate() {
                return todate;
            }

            public void setTodate(String todate) {
                this.todate = todate;
            }

            public int getEnabled() {
                return enabled;
            }

            public void setEnabled(int enabled) {
                this.enabled = enabled;
            }

            public int getOptionnumber() {
                return optionnumber;
            }

            public void setOptionnumber(int optionnumber) {
                this.optionnumber = optionnumber;
            }

            public List<OptionBean> getOption() {
                return option;
            }

            public void setOption(List<OptionBean> option) {
                this.option = option;
            }

            public static class OptionBean {
                /**
                 * optionid : 1
                 * subjectid : 1
                 * option : 选项1
                 * image :
                 * listorder : 0
                 */

                private int optionid;
                private int subjectid;
                private String option;
                private String image;
                private int listorder;

                public int getOptionid() {
                    return optionid;
                }

                public void setOptionid(int optionid) {
                    this.optionid = optionid;
                }

                public int getSubjectid() {
                    return subjectid;
                }

                public void setSubjectid(int subjectid) {
                    this.subjectid = subjectid;
                }

                public String getOption() {
                    return option;
                }

                public void setOption(String option) {
                    this.option = option;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getListorder() {
                    return listorder;
                }

                public void setListorder(int listorder) {
                    this.listorder = listorder;
                }
            }
        }
    }
}
