package com.transsnet.mvpdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by starwei on 18-4-16.
 */

public class HomeBanner implements Serializable {
    ArrayList<BannerInfo> homeBanner;

    public ArrayList<BannerInfo> getHomeBanner() {
        return homeBanner;
    }

    public void setHomeBanner(ArrayList<BannerInfo> homeBanner) {
        this.homeBanner = homeBanner;
    }

    class BannerInfo{
        private String bannerDesc;
        private String bannerUrl;
        private String refUrl;
        private String bannerId;

        public String getBannerDesc() {
            return bannerDesc;
        }

        public void setBannerDesc(String bannerDesc) {
            this.bannerDesc = bannerDesc;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public String getUrl() {
            return refUrl;
        }

        public void setUrl(String url) {
            this.refUrl = url;
        }

        public String getBannerId() {
            return bannerId;
        }

        public void setBannerId(String bannerId) {
            this.bannerId = bannerId;
        }
    }

}
