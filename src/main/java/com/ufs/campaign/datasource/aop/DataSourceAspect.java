package com.ufs.campaign.datasource.aop;

import com.ufs.campaign.annotation.DataSource;
import com.ufs.campaign.datasource.DataSourceContextHolder;
import com.ufs.campaign.datasource.DataSourceType;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataSourceAspect {
    @Before("@annotation(ds)")
    public void beforeDataSource(DataSource ds) {
        DataSourceType value = ds.value();
        DataSourceContextHolder.setDataSource(value);
    }

    @After("@annotation(ds)")
    public void afterDataSource(DataSource ds) {
        DataSourceContextHolder.clearDataSource();
    }
}
