package com.qa.framework.pages;

import com.qa.framework.engine.DriverManager;
import com.qa.framework.pages.ui.CRM_CIFPage;
import com.qa.framework.pages.ui.CommonFunction;

public class PageObjectManager {

    private final DriverManager driverManager;

    private CommonFunction commonFunction;
    private CRM_CIFPage crmCifPage;

    public PageObjectManager(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public CommonFunction getCommonFunction() {
        if (commonFunction == null) {
            commonFunction = new CommonFunction(driverManager.getDriver());
        }
        return commonFunction;
    }

    public CRM_CIFPage getCrmCifPage() {
        if (crmCifPage == null) {
            crmCifPage = new CRM_CIFPage(driverManager.getDriver());
        }
        return crmCifPage;
    }
}
