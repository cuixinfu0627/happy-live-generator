package com.happy.live.common.frame.entity;

import java.io.Serializable;

public class BaseEntity<PK> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private PK id ;

    /**
     * 创建时间
     */
    private Long createTime ;

    /**
     * 最后一次修改时间
     */
    private Long updateTime ;

    /**
     * 状态
     */
    private Byte status ;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}
