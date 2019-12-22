package com.ufs.campaign.deliverytemplate;

import com.ufs.campaign.typology.UFSTypology;

public class BaseDeliveryTemplate {

    protected UFSTypology ufsTypology;

    public UFSTypology getUfsTypology() {
        return ufsTypology;
    }

    public void setUfsTypology(UFSTypology ufsTypology) {
        this.ufsTypology = ufsTypology;
    }
}
