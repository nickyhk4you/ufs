package com.ufs.campaign.domain;

public class JobAndTrigger {
    private String job_NAME;
    private String job_GROUP;
    private String job_CLASS_NAME;
    private String trigger_NAME;
    private String trigger_GROUP;
    private String cron_EXPRESSION;
    private String time_ZONE_ID;
    private String trigger_STATE;

    public String getTrigger_STATE() {
        return trigger_STATE;
    }

    public void setTrigger_STATE(String trigger_STATE) {
        this.trigger_STATE = trigger_STATE;
    }

    public String getJob_NAME() {
        return job_NAME;
    }

    public void setJob_NAME(String job_NAME) {
        this.job_NAME = job_NAME;
    }

    public String getJob_GROUP() {
        return job_GROUP;
    }

    public void setJob_GROUP(String job_GROUP) {
        this.job_GROUP = job_GROUP;
    }

    public String getJob_CLASS_NAME() {
        return job_CLASS_NAME;
    }

    public void setJob_CLASS_NAME(String job_CLASS_NAME) {
        this.job_CLASS_NAME = job_CLASS_NAME;
    }

    public String getTrigger_NAME() {
        return trigger_NAME;
    }

    public void setTrigger_NAME(String trigger_NAME) {
        this.trigger_NAME = trigger_NAME;
    }

    public String getTrigger_GROUP() {
        return trigger_GROUP;
    }

    public void setTrigger_GROUP(String trigger_GROUP) {
        this.trigger_GROUP = trigger_GROUP;
    }

    public String getCron_EXPRESSION() {
        return cron_EXPRESSION;
    }

    public void setCron_EXPRESSION(String cron_EXPRESSION) {
        this.cron_EXPRESSION = cron_EXPRESSION;
    }

    public String getTime_ZONE_ID() {
        return time_ZONE_ID;
    }

    public void setTime_ZONE_ID(String time_ZONE_ID) {
        this.time_ZONE_ID = time_ZONE_ID;
    }
}
