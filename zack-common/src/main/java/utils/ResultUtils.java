package utils;

import common.BaseResponse;
import common.ErrorCode;

/**
 * @author TangYong
 * @date 2022年08月28日 19:32
 */
public class ResultUtils {

    private ResultUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static <T> BaseResponse<T> successs(T data) {
        return new BaseResponse<>(0, "ok", "", data);
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), errorCode.getDescription(), null);
    }

    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), message, description);
    }

    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, message, description);
    }
}
