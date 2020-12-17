package com.first.memorandum.entity;

public class Notes extends AbstactEntity {

    private String userNo;
    private String topic;
    private String content;
    private String noteNo;
    private Integer version;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(String noteNo) {
        this.noteNo = noteNo;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "userNo='" + userNo + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", noteNo='" + noteNo + '\'' +
                ", version=" + version +
                '}';
    }
}
