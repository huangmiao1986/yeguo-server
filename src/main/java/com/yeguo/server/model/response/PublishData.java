package com.yeguo.server.model.response;

import java.util.List;

public class PublishData {
    private String publish_id;
    private String publish_desc;
    private String praise;
    private List<String> images;
    private UserData user;
    private String is_follow;
    public String getPublish_id() {
        return publish_id;
    }
    public void setPublish_id(String publish_id) {
        this.publish_id = publish_id;
    }
    public String getPublish_desc() {
        return publish_desc;
    }
    public void setPublish_desc(String publish_desc) {
        this.publish_desc = publish_desc;
    }
    public String getPraise() {
        return praise;
    }
    public void setPraise(String praise) {
        this.praise = praise;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
    public UserData getUser() {
        return user;
    }
    public void setUser(UserData user) {
        this.user = user;
    }
    public String getIs_follow() {
        return is_follow;
    }
    public void setIs_follow(String is_follow) {
        this.is_follow = is_follow;
    }
    
}
