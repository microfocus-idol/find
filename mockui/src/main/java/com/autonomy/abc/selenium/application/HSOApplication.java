package com.autonomy.abc.selenium.application;

import com.autonomy.abc.selenium.connections.ConnectionService;
import com.autonomy.abc.selenium.control.Window;
import com.autonomy.abc.selenium.indexes.IndexService;
import com.autonomy.abc.selenium.navigation.HSODElementFactory;
import com.autonomy.abc.selenium.promotions.HSOPromotionService;
import com.autonomy.abc.selenium.users.HSODeveloperService;
import com.autonomy.abc.selenium.users.HSOUserService;

public class HSOApplication extends SearchOptimizerApplication<HSODElementFactory> {
    private HSODElementFactory factory;

    @Override
    public HSOApplication inWindow(Window window) {
        Window window1 = window;
        this.factory = new HSODElementFactory(window.getSession().getDriver());
        return this;
    }

    public HSODElementFactory elementFactory() {
        return factory;
    }

    @Override
    public HSOPromotionService promotionService() {
        return new HSOPromotionService(this);
    }

    @Override
    public HSOUserService userService() {
        return new HSOUserService(this);
    }

    public ConnectionService connectionService() {
        return new ConnectionService(this);
    }

    public IndexService indexService() {
        return new IndexService(this);
    }

    public HSODeveloperService developerService() {
        return new HSODeveloperService(this);
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.HOSTED;
    }
}
