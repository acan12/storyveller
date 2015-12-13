package com.xzone.app.storyveller.model;

/**
 * Created by arysuryawan on 9/18/15.
 */
public class NavDrawerItemDummy {

    private boolean showNotify;
    private String title;

    public NavDrawerItemDummy(){
    }

    public NavDrawerItemDummy(boolean showNotify, String title){
        this.showNotify = showNotify;
        this.title = title;

    }


    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
