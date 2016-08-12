package com.yeguo.server.service;

import com.yeguo.server.model.request.PublishRequest;
import com.yeguo.server.model.response.BaseResponse;
import com.yeguo.server.model.response.PublishResponse;

public interface ActivityService {
    /**
     * 发布内容
     * 
     * @param req
     * @param basePath
     * @return
     */
    public PublishResponse publishContent(PublishRequest req,String basePath);
    /**
     * 发送手机验证码
     * 
     * @param userId
     * @param phoneNum
     * @return
     */
    public BaseResponse sendPhoneCode(String userId,String phoneNum);
}
