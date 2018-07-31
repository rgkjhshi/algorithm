package com.mk.algorithm.dto;

import java.io.Serializable;

/**
 * 基本响应对象, 包含错误代码和错误描述信息, 返回的具体数据可包含在 data 字段中, data采用范型, 可根据需要定义类型
 * <p>使用举例:</p>
 * <pre>
 * 仅创建一个空响应对象:
 *     BaseResponse<T> response = new BaseResponse<T>();
 * 通过数据创建对象, 此时响应码和响应信息都是空的:
 *     BaseResponse<T> response = new BaseResponse<T>(data);
 * 自定义响应码和响应描述信息:
 *     BaseResponse<T> response = new BaseResponse<T>(code, message);
 * </pre></p>
 *
 * @author song.shi
 * @since 2018-07-31
 */
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -4887606088067040646L;

    /**
     * 响应码
     */
    private String returnCode;

    /**
     * 响应信息
     */
    private String returnMessage;

    /**
     * 数据
     */
    private T data;

    public BaseResponse() {
    }


    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(String code, String message) {
        this.returnCode = code;
        this.returnMessage = message;
    }

    public BaseResponse(String returnCode, String returnMessage, T data) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.data = data;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResponse{");
        sb.append("returnCode='").append(returnCode).append('\'');
        sb.append(", returnMessage='").append(returnMessage).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
