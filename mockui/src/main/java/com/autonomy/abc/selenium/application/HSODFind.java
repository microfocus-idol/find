package com.autonomy.abc.selenium.application;

import com.autonomy.abc.selenium.control.Window;
import com.autonomy.abc.selenium.navigation.HSODFindElementFactory;

public class HSODFind implements Application<HSODFindElementFactory> {
    private HSODFindElementFactory factory;

    public HSODFind(Window window) {
        inWindow(window);
    }

    @Override
    public HSODFindElementFactory elementFactory() {
        return factory;
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.HOSTED;
    }

    @Override
    public Application<HSODFindElementFactory> inWindow(Window window) {
        Window window1 = window;
        this.factory = new HSODFindElementFactory(window.getSession().getDriver());
        return this;
    }
}
