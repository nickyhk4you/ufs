package com.ufs.campaign.domain;

import com.ufs.campaign.annotation.CsvField;

public class UFSTrialServiceDTO {

    @CsvField(name = "skuCode")
    private String stdskucode;

    @CsvField(name = "skuName")
    private String stdskuname;

    @CsvField(name = "trialServiceUrl")
    private String url;

    public String getStdskucode() {
        return stdskucode;
    }

    public void setStdskucode(String stdskucode) {
        this.stdskucode = stdskucode;
    }

    public String getStdskuname() {
        return stdskuname;
    }

    public void setStdskuname(String stdskuname) {
        this.stdskuname = stdskuname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
