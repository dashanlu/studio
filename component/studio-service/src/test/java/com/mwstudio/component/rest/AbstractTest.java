package com.mwstudio.component.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;

@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    @Qualifier("studiodbDataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
