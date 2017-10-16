package com.jiao.luo.model;

/**
 * Created by Administrator on 2017/8/22.
 */

public class Vote {
    private String title;
    private Boolean isCheck;
    private int optionid;
    private int subjectid;

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public int getOptionid() {
        return optionid;
    }

    public void setOptionid(int optionid) {
        this.optionid = optionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public Vote(String title, Boolean isCheck, int optionid,int subjectid) {
        this.title = title;
        this.isCheck = isCheck;
        this.optionid = optionid;
        this.subjectid=subjectid;
    }
}
