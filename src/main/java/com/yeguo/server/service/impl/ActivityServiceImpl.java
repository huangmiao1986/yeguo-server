package com.yeguo.server.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yeguo.server.cache.ActivityCached;
import com.yeguo.server.dao.UserCommentDAO;
import com.yeguo.server.dao.UserDAO;
import com.yeguo.server.dao.UserFansDAO;
import com.yeguo.server.dao.UserFollowDAO;
import com.yeguo.server.dao.UserPraiseDAO;
import com.yeguo.server.dao.UserPublishDAO;
import com.yeguo.server.dao.UserQuestionDAO;
import com.yeguo.server.log.LoggerFactory;
import com.yeguo.server.model.User;
import com.yeguo.server.model.UserPublish;
import com.yeguo.server.model.request.PublishRequest;
import com.yeguo.server.model.response.BaseResponse;
import com.yeguo.server.model.response.PublishData;
import com.yeguo.server.model.response.PublishResponse;
import com.yeguo.server.service.ActivityService;
import com.yeguo.server.util.ConfigUtils;
import com.yeguo.server.util.RandomNumUtil;
@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Resource
    private UserDAO userDAO;
    @Resource
    private UserCommentDAO userCommentDAO;
    @Resource
    private UserFansDAO userFansDAO;
    @Resource
    private UserFollowDAO userFollowDAO;
    @Resource
    private UserPraiseDAO userPraiseDAO;
    @Resource
    private UserPublishDAO userPublishDAO;
    @Resource
    private UserQuestionDAO userQuestionDAO;
    
    private static String WEIXIN_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    
    private static Logger logger = LoggerFactory.getServerInfoLogger(ActivityServiceImpl.class);
    
    @Override
    public PublishResponse publishContent(PublishRequest req,String basePath) {
        User user = userDAO.getUserById(req.getUser_id());
        if(user != null) {
            if(user.getType() != (short)1) {
                return new PublishResponse("1","user type can't publish ");
            }
            UserPublish publish = new UserPublish();
            PublishData publishData = new PublishData();
            Date date = new Date();
            publish.setCreateTime(date);
            publish.setPraise(0);
            publish.setPublishDesc(req.getPublish_desc());
            publish.setUpdateTime(date);
            publish.setUserId(req.getUser_id());
            int publishId = userPublishDAO.addUserPublish(publish);
            publishData.setPraise("0");
            publishData.setPublish_desc(req.getPublish_desc());
            publishData.setPublish_id(publishId+"");
            List<String> images = new ArrayList<String>(); 
            if(publishId > 0) {
                List<String> mediaIds = req.getMedia_ids();
                if(mediaIds != null && mediaIds.size() > 0) {
                    for(String mediaId:mediaIds) {
                        String mediaUrl = String.format(WEIXIN_MEDIA_URL, req.getAccess_token(),mediaId);
                        String fileName = req.getUser_id()+(System.currentTimeMillis()+".jpg");
                        String savePath = basePath+"/"+publishId;
                        try {
                            download(mediaUrl, fileName,savePath);
                        } catch (Exception e) {
                            logger.error("download 【"+mediaId+"】error:"+e.toString());
                            boolean delRet = deleteDir(new File(savePath));
                            if(!delRet) {
                                logger.error("delete 【"+savePath+"】error");
                            }
                            throw new RuntimeException();
                        }
                        userPublishDAO.adduserPublishImgs(fileName, publishId);
                        images.add(ConfigUtils.getProperty("PHOTO_URL")+publishId+"/"+fileName);
                    }
                }
            }
            publishData.setImages(images);
            return new PublishResponse("0","",publishData);
        } else {
            return new PublishResponse("1","user is not exist");
        }
    }   
    
    private static void download(String urlString, String fileName,String savePath) throws Exception {
        // 构造URL  
        URL url = new URL(urlString); 
        // 打开连接  
        URLConnection con = url.openConnection();  
        //设置请求超时为5s  
        con.setConnectTimeout(5*1000);  
        // 输入流  
        InputStream is = con.getInputStream();  
      
        // 1K的数据缓冲  
        byte[] bs = new byte[1024];  
        // 读取到的数据长度  
        int len;  
        // 输出的文件流  
       File sf=new File(savePath);  
       if(!sf.exists()){  
           sf.mkdirs();  
       }  
       OutputStream os = new FileOutputStream(sf.getPath()+"/"+fileName);
        // 开始读取  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
        // 完毕，关闭所有链接  
        os.close();  
        is.close();  
    }
    
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
           //递归删除目录中的子目录下
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    @Override
    public BaseResponse sendPhoneCode(String userId, String phoneNum) {
        User user = userDAO.getUserById(Long.parseLong(userId));
        
        if(user == null) {
            return new PublishResponse("1","user is not exist");
        }
        
        User phoneUser = userDAO.getUserByPhoneNum(phoneNum);
        if(phoneUser != null && phoneUser.getUserId() != Long.parseLong(userId)) {
            return new BaseResponse("2", "phone num is used");
        } else {
            String phoneCode = RandomNumUtil.getValidateNumber();
            //发送短信
            ActivityCached.addPhoneCode(phoneNum, phoneCode);
        }
        return new BaseResponse("0","");
    }
}
