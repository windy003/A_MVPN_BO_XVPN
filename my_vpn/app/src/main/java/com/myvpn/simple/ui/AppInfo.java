package com.myvpn.simple.ui;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private final String appName;
    private final String packageName;
    private final Drawable icon;
    private final boolean isSystemApp;
    private boolean isExcluded;
    
    public AppInfo(String appName, String packageName, Drawable icon, boolean isSystemApp, boolean isExcluded) {
        this.appName = appName;
        this.packageName = packageName;
        this.icon = icon;
        this.isSystemApp = isSystemApp;
        this.isExcluded = isExcluded;
    }
    
    public String getAppName() {
        return appName;
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public Drawable getIcon() {
        return icon;
    }
    
    public boolean isExcluded() {
        return isExcluded;
    }
    
    public void setExcluded(boolean excluded) {
        this.isExcluded = excluded;
    }
    
    public boolean isSystemApp() {
        return isSystemApp;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        AppInfo appInfo = (AppInfo) obj;
        return packageName.equals(appInfo.packageName);
    }
    
    @Override
    public int hashCode() {
        return packageName.hashCode();
    }
}