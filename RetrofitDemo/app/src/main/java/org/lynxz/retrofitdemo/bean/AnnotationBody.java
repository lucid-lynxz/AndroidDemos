package org.lynxz.retrofitdemo.bean;

/**
 * Created by zxz on 2016/5/23.
 * 给图书做笔记
 */
public class AnnotationBody {
    String content; //笔记内容,必填
    //    String page; //页码
    //    String chapter;//章节名,与页码选填其中之一
    //    String privacy;//是否公开,默认public

    public AnnotationBody(String content) {
        this.content = content;
        //        this.page = "1";
        //        this.privacy = "private";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //    public String getPage() {
    //        return page;
    //    }
    //
    //    public void setPage(String page) {
    //        this.page = page;
    //    }
    //
    //    public String getChapter() {
    //        return chapter;
    //    }
    //
    //    public void setChapter(String chapter) {
    //        this.chapter = chapter;
    //    }
    //
    //    public String getPrivacy() {
    //        return privacy;
    //    }
    //
    //    public void setPrivacy(String privacy) {
    //        this.privacy = privacy;
    //    }
}
