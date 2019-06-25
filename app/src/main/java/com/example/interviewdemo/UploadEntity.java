package com.example.interviewdemo;

public class UploadEntity {

    /**
     * status : 1
     * message : 文件上传成功
     * result : {"number":0,"url":"http://img.juyun.tv/images//tools/15614570041_b.jpg"}
     */

    private int status;
    private String message;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * number : 0
         * url : http://img.juyun.tv/images//tools/15614570041_b.jpg
         */

        private int number;
        private String url;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
