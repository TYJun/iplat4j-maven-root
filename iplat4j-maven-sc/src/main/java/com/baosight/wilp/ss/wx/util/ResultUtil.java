package com.baosight.wilp.ss.wx.util;


import com.baosight.wilp.ss.wx.constant.DefalutConst;

public class ResultUtil {
    /**
     * @return Result<?>
     * @
     * @Description 成功无数据返回
     * @date 2022/9/17 14:50
     */
    public static Result<?> successNoData() {
        return success(null);
    }

    /**
     * @param data
     * @return Result<T>
     * @Description 成功带数据返回
     * @date 2022/9/17 14:50
     */
    public static <T> Result<T> success(T data) {
        return returnResult(DefalutConst.SUCCESS_CODE, DefalutConst.SUCCESS_MSG, data);
    }

    /**
     * @return Result<T>
     * @Description 适配旧版的返回
     * @date 2022/9/17 14:50
     */
    public static <T> Result<T> fail(String msg) {
        return returnResult(DefalutConst.ERROR_CODE, msg, null);
    }

    public static <T> Result<T> returnResult(int code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
