package com.example.interviewdemo;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class HomeEntity {

    private List<ListBean> list;
    private List<ListSlideBean> list_slide;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<ListSlideBean> getList_slide() {
        return list_slide;
    }

    public void setList_slide(List<ListSlideBean> list_slide) {
        this.list_slide = list_slide;
    }

    public static class ListBean implements MultiItemEntity {
        /**
         * contentID : 2164992
         * title : 市委常委会会议召开
         * photo :
         * photo2 :
         * photo3 :
         * contentUrl : http://app.huangshannews.cn/a/a/g/content_2817.shtml
         * doc_type : 0
         * date : 1小时前
         */

        private String contentID;
        private String title;
        private String photo;
        private String photo2;
        private String photo3;
        private String contentUrl;
        private String doc_type;
        private String date;

        public String getContentID() {
            return contentID;
        }

        public void setContentID(String contentID) {
            this.contentID = contentID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhoto2() {
            return photo2;
        }

        public void setPhoto2(String photo2) {
            this.photo2 = photo2;
        }

        public String getPhoto3() {
            return photo3;
        }

        public void setPhoto3(String photo3) {
            this.photo3 = photo3;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }

        public String getDoc_type() {
            return doc_type;
        }

        public void setDoc_type(String doc_type) {
            this.doc_type = doc_type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public int getItemType() {
            return Integer.valueOf(doc_type);
        }
    }

    public static class ListSlideBean {
        /**
         * contentID : 2166380
         * title : 谁是黄山日报粉丝“锦鲤”，1888元大奖等你来拿！
         * photo : http://183.166.129.70:81/images/2019/1/23/20191231548215630301_24_t1080.jpg
         * contentUrl : https://mp.weixin.qq.com/s/QcmpOzH7l5dJxsQvB7mVAg
         */

        private String contentID;
        private String title;
        private String photo;
        private String contentUrl;

        public String getContentID() {
            return contentID;
        }

        public void setContentID(String contentID) {
            this.contentID = contentID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }
    }
}
