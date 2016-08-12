package com.yeguo.server.cache;

public class ActivityCached  extends DBSecondLevelCached{
    
       public static void addValidateCode(String userId,String code) {
           getRedisOperator().setex(String.format(USER_VALIDATE_CODE, userId), 600, code, 0);
       }
       
       public static String getValidateCode(String userId) {
           return getRedisOperator().get(String.format(USER_VALIDATE_CODE, userId), 0);
       }
       
       public static void addPhoneCode(String phoneNum,String code) {
           getRedisOperator().setex(String.format(PHONE_CODE, phoneNum), 180, code, 0);
       }
       
       public static String getPhoneCode(String phoneNum) {
           return getRedisOperator().get(String.format(PHONE_CODE, phoneNum), 0);
       }
       
       
}
