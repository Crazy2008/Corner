package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class ResVoteResult {

    /**
     * returnCode : 1
     * data : {"total":8,"votes":5,"options":[{"optionid":3,"option":"选项1","image":"","vote":4},{"optionid":4,"option":"选项2","image":"","vote":3},{"optionid":5,"option":"选项3","image":"","vote":1}]}
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
         * total : 8
         * votes : 5
         * options : [{"optionid":3,"option":"选项1","image":"","vote":4},{"optionid":4,"option":"选项2","image":"","vote":3},{"optionid":5,"option":"选项3","image":"","vote":1}]
         */

        private int total;
        private int votes;
        private List<OptionsBean> options;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        public List<OptionsBean> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBean> options) {
            this.options = options;
        }

        public static class OptionsBean {
            /**
             * optionid : 3
             * option : 选项1
             * image :
             * vote : 4
             */

            private int optionid;
            private String option;
            private String image;
            private int vote;

            public int getOptionid() {
                return optionid;
            }

            public void setOptionid(int optionid) {
                this.optionid = optionid;
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

            public int getVote() {
                return vote;
            }

            public void setVote(int vote) {
                this.vote = vote;
            }
        }
    }
}
