package com.first.memorandum.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class AbstactEntity implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean delFlag;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }


}
