package com.happy.live.common.mvc.utils;

/**

 * */
public enum FileUploadParam {

    DEFAULT(0 ,"default","/banana/default/"),
    UNIT_IMG(2,"aavatar","/banana/aavatar/"),
    USER_IMG(1,"wallpaper","/banana/wallpaper/");

    private int type;
    private String sourceDir;//图片 音频 视频资源上传地址
    private String webUrl;//图片 音频 视频资源查看地址

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public FileUploadParam setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
        return this;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public FileUploadParam setWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return this;
    }

    public static FileUploadParam getByType(int type){
        for (FileUploadParam fileUploadParam: values()) {
            if (fileUploadParam.type == type) {
                return fileUploadParam;
            }
        }
        return null;
    }
    FileUploadParam(int type, String sourceDir, String webUrl) {
        this.type = type;
        this.sourceDir = sourceDir;
        this.webUrl = webUrl;
    }

}
