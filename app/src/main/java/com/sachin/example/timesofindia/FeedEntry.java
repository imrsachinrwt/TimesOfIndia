package com.sachin.example.timesofindia;

public class FeedEntry {
    private String newsTitle;
    private String title;
    private String desc;
    private String pubDate;
    private String copyRight;

    @Override
    public String toString() {
        return   "\nnewsTitle=" + newsTitle + '\n' +
                " title=" + title + '\n' +
                " desc=" + desc + '\n' +
                " pubDate=" + pubDate + '\n' +
                " copyRight='" + copyRight+"\n";
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }
}
