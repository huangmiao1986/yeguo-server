package com.yeguo.server.base;

public class CustomerContextHolder {

    public static final String DATA_SOURCE_WRITE = "dataSource_write";
    
    public static final String DATA_SOURCE_READ = "dataSource_read";  
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(); 
    
    public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);  
    }  
    
    public static String getCustomerType() {  
        return contextHolder.get();  
    }
    
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  

}
