package com.yeguo.server.model.request;

import java.util.List;

public class PublishRequest {
    private long user_id;
    private String publish_desc;
    private List<String> media_ids;
    private String access_token;
    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    public String getPublish_desc() {
        return publish_desc;
    }
    public void setPublish_desc(String publish_desc) {
        this.publish_desc = publish_desc;
    }
    public List<String> getMedia_ids() {
        return media_ids;
    }
    public void setMedia_ids(List<String> media_ids) {
        this.media_ids = media_ids;
    }
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    
}
