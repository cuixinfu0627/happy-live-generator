package com.happy.live.common.mvc.view;


import com.happy.live.common.frame.exception.ErrorCode;

public class ResultVoWrapper {

    private static int SUCCESS = 200 ;
    private static int INTERNAL_ERROR ;

    public static ResultVo buildFail(ErrorCode errorCodeBase) {
        return buildFail(errorCodeBase.getCode(), errorCodeBase.getMsg(), null);
    }
    /** 成功 **/
    public static ResultVo buildSuccess() {
        return buildSuccess(null, null);
    }

    public static ResultVo buildSuccess(Object data) {
        return buildSuccess(null, data);
    }

    public ResultVo buildSuccess(String message) {
        return buildSuccess(message, null);
    }

    public static ResultVo buildSuccess(String message, Object data) {
        return doBuild(SUCCESS, message, data);
    }

    /** 失败  **/
    public static ResultVo buildFail() {
        return buildFail(INTERNAL_ERROR, (String) null);
    }

    public ResultVo buildFail(Integer errorCode) {
        return buildFail(errorCode, (String) null);
    }

    public static ResultVo buildFail(Integer errorCode, String msg) {
        return buildFail(errorCode, msg, null);
    }

    public ResultVo buildFail(Integer errorCode, Object data) {
        return buildFail(errorCode, null, data);
    }

    public static ResultVo buildFail(Integer errorCode, String message, Object data) {
        return doBuild(errorCode, message, data);
    }

    private static ResultVo doBuild(Integer code, String message, Object data) {
        ResultVo vo = new ResultVo();
        vo.setCode(code);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

    public static boolean isSuccess(ResultVo resultVo){
        return resultVo == null || resultVo.getCode().intValue() == SUCCESS ;
    }

    static {
        INTERNAL_ERROR = ErrorCode.SERVER_INTERNAL_EXCEPTION.getCode();
    }


}
