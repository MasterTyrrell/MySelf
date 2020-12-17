package com.first.memorandum.entity;

public class Tip extends AbstactEntity {

    private String userNo;
    private String tipNo;
    private String topic;
    private String content;
    /**
     * 000000000000000                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              0000000000000
     */
    private String timestampstr;
    private Integer tipCycle;
    private Integer status;
    private String version;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getTipNo() {
        return tipNo;
    }

    public void setTipNo(String tipNo) {
        this.tipNo = tipNo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestampstr() {
        return timestampstr;
    }

    public void setTimestampstr(String timestampstr) {
        this.timestampstr = timestampstr;
    }

    public Integer getTipCycle() {
        return tipCycle;
    }

    public void setTipCycle(Integer tipCycle) {
        this.tipCycle = tipCycle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Tip{" +
                "userNo='" + userNo + '\'' +
                ", tipNo='" + tipNo + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", timestampstr='" + timestampstr + '\'' +
                ", tipCycle=" + tipCycle +
                ", status=" + status +
                ", version='" + version + '\'' +
                '}';
    }
}
