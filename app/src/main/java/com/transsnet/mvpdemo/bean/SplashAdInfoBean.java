package com.transsnet.mvpdemo.bean;

import java.io.Serializable;

/**
 * Author:  zengfeng
 * Time  :  2020/7/13 14:25
 * Des   :
 */
public class SplashAdInfoBean implements Serializable {
    private String adNo;
    private String bootPageName;
    private String bootPagePic;
    private String bootPageJumpType;
    private String bootPageUrl;
    private String startTime ; //生效时间
    private String endTime;   //失效时间

    public String getAdNo() {
        return adNo;
    }

    public void setAdNo(String adNo) {
        this.adNo = adNo;
    }

    public String getBootPageName() {
        return bootPageName;
    }

    public void setBootPageName(String bootPageName) {
        this.bootPageName = bootPageName;
    }

    public String getBootPagePic() {
        return bootPagePic;
    }

    public void setBootPagePic(String bootPagePic) {
        this.bootPagePic = bootPagePic;
    }

    public String getBootPageJumpType() {
        return bootPageJumpType;
    }

    public void setBootPageJumpType(String bootPageJumpType) {
        this.bootPageJumpType = bootPageJumpType;
    }

    public String getBootPageUrl() {
        return bootPageUrl;
    }

    public void setBootPageUrl(String bootPageUrl) {
        this.bootPageUrl = bootPageUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
