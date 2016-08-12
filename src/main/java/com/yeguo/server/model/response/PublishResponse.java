package com.yeguo.server.model.response;

public class PublishResponse extends BaseResponse{
    private PublishData data;
    
    public PublishResponse(String ret,String errinfo) {
        super(ret, errinfo);
    }
    
    public PublishResponse(String ret,String errinfo,PublishData data) {
        super(ret, errinfo);
        this.data = data;
    }

    public PublishData getData() {
        return data;
    }

    public void setData(PublishData data) {
        this.data = data;
    }
    
}
