package com.kieran.practice.domain.vo.response;

import com.kieran.practice.exception.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("接口返回体")
public class ApiResult<T> {
    @ApiModelProperty("成功标识")
    private Boolean success;

    @ApiModelProperty("错误码")
    private Integer errorCode;

    @ApiModelProperty("错误消息")
    private String errorMsg;

    @ApiModelProperty("返回数据")
    private T data;

    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.TRUE);
        result.setData(null);
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.TRUE);
        result.setData(data);
        return result;
    }

    public static <T> ApiResult<T> fail(Integer errCode, String errMsg) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.FALSE);
        result.setErrorCode(errCode);
        result.setErrorMsg(errMsg);
        return result;
    }

    public static <T> ApiResult<T> fail(ErrorEnum errorEnum) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.FALSE);
        result.setErrorCode(errorEnum.getErrorCode());
        result.setErrorMsg(errorEnum.getErrorMsg());
        return result;
    }

}
