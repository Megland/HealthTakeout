package com.jyl.healthytakeout.entity;

public class Message {
    private int messageid;
    private int senderuserno;
    private String sendername;
    private int receiveruserno;
    private String messagecontent;
    private String sendtime;
    public Message(int messageid, int senderuserno, String sendername, int receiveruserno, String messagecontent,
                   String sendtime) {
        super();
        this.messageid = messageid;
        this.senderuserno = senderuserno;
        this.sendername = sendername;
        this.receiveruserno = receiveruserno;
        this.messagecontent = messagecontent;
        this.sendtime = sendtime;
    }
    public int getMessageid() {
        return messageid;
    }
    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }
    public int getSenderuserno() {
        return senderuserno;
    }
    public void setSenderuserno(int senderuserno) {
        this.senderuserno = senderuserno;
    }
    public String getSendername() {
        return sendername;
    }
    public void setSendername(String sendername) {
        this.sendername = sendername;
    }
    public int getReceiveruserno() {
        return receiveruserno;
    }
    public void setReceiveruserno(int receiveruserno) {
        this.receiveruserno = receiveruserno;
    }
    public String getMessagecontent() {
        return messagecontent;
    }
    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent;
    }
    public String getSendtime() {
        return sendtime;
    }
    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }
}
