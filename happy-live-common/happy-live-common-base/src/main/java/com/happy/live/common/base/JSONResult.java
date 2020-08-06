package com.happy.live.common.base;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于描述json返回
 * @author huying
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JSONResult implements Serializable{
        
	private static final long serialVersionUID = 8016922767506632719L;
	/**
     * 成功状态
     */
    public static final int STATUS_SUCCESS = 1;
    /**
     * 失败状态
     */
    public static final int STATUS_FAILD_DEFAULT = 0;
    /**
     * 返回结果状态 1 成功   0 失败
     */
    private int status ;
    
    /**
     * 如果发生错误，错误消息
     */
    private String message;
    
    /**
     * 如果发生错误，错误消息
     */
    private String errorCode;
    
	/**
     * 如果发生错误并与某输入项有关系则需要标记field
     */
    private String field ;
    
    /**
     * 返回的数据
     */
    private Map<String, Object> data;

    public int getStatus() {
        return status;
    }

    public JSONResult setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JSONResult setMessage(String message) {
        this.message = message==null?"":message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public JSONResult setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public String getField() {
        return field;
    }

    public JSONResult setField(String field) {
        this.field = field==null?"":field;
        return this;
    }
    
    /**
     * 获取错误代码
     * @return
     */
    public String getErrorCode() {
		return errorCode;
	}

    /**
     * 设置错误代码
     * @return
     */
	public JSONResult setErrorCode(String errorCode) {
		this.errorCode = errorCode==null?"":errorCode;
        return this;
	}


    /**
     * 返回无数据成功
     * @return
     */
    public static JSONResult success() {
        return success(null);
    }

    /**
     * 返回有数据成功
     * @param data
     * @return
     */
    public static JSONResult success(Map<String, Object> data) {
        return getInstance(STATUS_SUCCESS, "ok","", data);
    }
    
    /**
     * 返回无数据失败
     * @param message
     * @return
     */
	
	public static JSONResult faild() {
    	return faild(STATUS_FAILD_DEFAULT, "", "",new HashMap(1,1));
    }

    /**
     * 返回无数据失败
     * @param message
     * @return
     */
	public static JSONResult faild(String message) {
    	return faild(STATUS_FAILD_DEFAULT, "", message,new HashMap(1,1));
    }
    
    /**
     * 返回无数据失败类型json 包含信息 失败类型 失败消息
     * @param field 失败类型 
     * @param message  失败消息
     * @return
     */
	public static JSONResult faild(String field, String message) {
        return faild(STATUS_FAILD_DEFAULT, field, message,new HashMap(1,1)) ;
    }

    /**
     * 返回失败类型json 包含信息 失败状态 失败类型 失败消息 数据
     * @param field 失败类型
     * @param message 失败消息
     * @param data
     * @return
     */
    public static JSONResult faild(String field, String message, Map<String, Object> data) {
    	return faild(STATUS_FAILD_DEFAULT, field, message,data) ;
    }

  

    /**
     * 返回失败类型json 包含信息 失败状态 失败类型 失败消息 数据
     * @param status
     * @param field
     * @param message
     * @param data
     * @return
     */
    private static JSONResult faild(int status, String field, String message, Map<String, Object> data) {
    	if(!(status <= STATUS_FAILD_DEFAULT)){
    		return null ;
    	}
        return getInstance(status, field, message, data);
    }

    /**
     * 获取json对象
     * @param status
     * @param field
     * @param message
     * @param data
     * @return
     */
    private static JSONResult getInstance(int status, String field, String message, Map<String, Object> data) {
        JSONResult result = new JSONResult();
        result.setStatus(status);
        result.setField(field);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 为json结果添加返回数据
     * @param key
     * @param value
     * @return
     */
    public JSONResult addValue(String key, Object value) {
        if (data == null) {
            setData(new HashMap<String, Object>());
        }
        getData().put(key, value);
        return this;
    }
    
    public String jsonStr(){
    	return JSONHandler.getJsonStr(this) ;
    }
    
    public static void main(String[] args) {
		System.out.println(new JSONResult().jsonStr() );
	}


}
