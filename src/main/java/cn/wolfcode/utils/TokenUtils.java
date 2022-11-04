package cn.wolfcode.utils;

import cn.wolfcode.domain.LoginUser;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/4 14:35
 */
public class TokenUtils {


    private static final Map<String, LoginUser> MOCK_DB = new HashMap<>();
    public static String randomToken(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void setUser(String token, LoginUser loginUser){
        MOCK_DB.put(token, loginUser);
    }

    public static LoginUser getUser(String token){
        return MOCK_DB.get(token);
    }

    public static void close() {
        MOCK_DB.clear();
    }

    public static void remove(String token) {
        MOCK_DB.remove(token);
    }

}
