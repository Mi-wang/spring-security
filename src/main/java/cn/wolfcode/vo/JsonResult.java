package cn.wolfcode.vo;

import java.util.HashMap;

/**
 * @author 20463
 * @version 1.0
 * @date 2022/11/4 10:31
 */
public class JsonResult<T> extends HashMap<String, Object> {

    private static final String CODE_NAME = "code";
    private static final String MSG_NAME = "msg";
    private static final String DATA_NAME = "data";

    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final Integer DEFAULT_FAILED_CODE = 500;
    public static final String DEFAULT_SUCCESS_MSG = "success";
    public static final String DEFAULT_FAILED_MSG = "服务器繁忙";

    public JsonResult(Integer code, String msg, T data) {
        super.put(CODE_NAME, code);
        super.put(MSG_NAME, msg);
        if (data != null) {
            super.put(DATA_NAME, data);
        }
    }

    public JsonResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<>(DEFAULT_SUCCESS_CODE, msg, data);
    }

    public static <T> JsonResult<T> success(T data) {
        return success(DEFAULT_SUCCESS_MSG, data);
    }

    public static <T> JsonResult<T> success() {
        return success(null);
    }

    public static <T> JsonResult<T> failed(Integer code, String msg) {
        return new JsonResult<>(code, msg, null);
    }

    public static <T> JsonResult<T> failed(String msg) {
        return failed(DEFAULT_FAILED_CODE, msg);
    }

    public static <T> JsonResult<T> failed() {
        return failed(DEFAULT_FAILED_MSG);
    }



}
