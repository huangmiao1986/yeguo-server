package com.yeguo.server.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeguo.server.cache.ActivityCached;
import com.yeguo.server.model.request.PublishRequest;
import com.yeguo.server.model.response.BaseResponse;
import com.yeguo.server.service.ActivityService;
import com.yeguo.server.util.JsonUtil;
import com.yeguo.server.util.ValidateCode;

@Controller
public class ActivityController {
    
    @Resource
    private ActivityService activityService;
    
    @RequestMapping(value = "/user/publish", method = RequestMethod.POST,consumes={"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String downloadPhoto(
            @RequestBody String body,
            HttpServletRequest request
            ){
        
        String basePath = request.getSession().getServletContext().getRealPath("/upload/publish_photo");
        try {
            PublishRequest req =  (PublishRequest) JsonUtil.fromJson(body, PublishRequest.class);
            return JsonUtil.toJson(activityService.publishContent(req, basePath));
        } catch (Exception e) {
            return JsonUtil.toJson(new BaseResponse("1","pulish fail"));
        }
    }
    
    @RequestMapping(value = "/user/code", method = RequestMethod.GET,consumes={"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String getUserCode(
             @RequestParam(required = true) String user_id,
            HttpServletRequest request,
            HttpServletResponse response
            ){
        if(StringUtils.isNotBlank(user_id)) {
            ValidateCode vCode = new ValidateCode(120,40,5,100);
            ActivityCached.addValidateCode(user_id, vCode.getCode());
            try {
                vCode.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return JsonUtil.toJson(new BaseResponse("1","create code error"));
            }
            return JsonUtil.toJson(new BaseResponse("0",""));
        } else {
            return JsonUtil.toJson(new BaseResponse("1","user id is null"));
        }
    }
    
    @RequestMapping(value = "/user/phonecode", method = RequestMethod.GET,consumes={"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String getPhoneCode(
             @RequestParam(required = true) String user_id,
             @RequestParam(required = true) String phone_num,
            HttpServletRequest request
            ){
        if(StringUtils.isNotBlank(user_id) && StringUtils.isNotBlank(phone_num)) {
            
        } else {
            return JsonUtil.toJson(new BaseResponse("1","user id or phone num is null"));
        }
    }
}
