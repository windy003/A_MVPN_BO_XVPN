package com.myvpn.simple.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;

public class AppExclusionManager {
    private static final String PREF_NAME = "MY_VPN";
    private static final String APP_LIST_KEY = "EXCLUDED_APPS";
    private static final String EXCLUSION_MODE_KEY = "exclusion_mode";
    
    public enum ExclusionMode {
        BLACKLIST("blacklist"),  // 排除模式：默认所有应用走代理，排除列表中的应用不走
        WHITELIST("whitelist");  // 允许模式：默认所有应用不走代理，只有列表中的应用走
        
        private final String value;
        
        ExclusionMode(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static ExclusionMode fromValue(String value) {
            for (ExclusionMode mode : values()) {
                if (mode.value.equals(value)) {
                    return mode;
                }
            }
            return BLACKLIST; // 默认排除模式
        }
    }
    
    private final SharedPreferences prefs;
    private final Context context;
    
    public AppExclusionManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    
    public ExclusionMode getExclusionMode() {
        String mode = prefs.getString(EXCLUSION_MODE_KEY, ExclusionMode.BLACKLIST.getValue());
        return ExclusionMode.fromValue(mode);
    }
    
    public void setExclusionMode(ExclusionMode mode) {
        prefs.edit().putString(EXCLUSION_MODE_KEY, mode.getValue()).apply();
    }
    
    public Set<String> getExcludedApps() {
        return prefs.getStringSet(APP_LIST_KEY, new HashSet<>());
    }
    
    public void setExcludedApps(Set<String> packageNames) {
        prefs.edit().putStringSet(APP_LIST_KEY, new HashSet<>(packageNames)).apply();
    }
    
    public boolean isAppExcluded(String packageName) {
        return getExcludedApps().contains(packageName);
    }
    
    public void addExcludedApp(String packageName) {
        Set<String> apps = new HashSet<>(getExcludedApps());
        apps.add(packageName);
        setExcludedApps(apps);
    }
    
    public void removeExcludedApp(String packageName) {
        Set<String> apps = new HashSet<>(getExcludedApps());
        apps.remove(packageName);
        setExcludedApps(apps);
    }
    
    public void clearExcludedApps() {
        prefs.edit().remove(APP_LIST_KEY).apply();
    }
}