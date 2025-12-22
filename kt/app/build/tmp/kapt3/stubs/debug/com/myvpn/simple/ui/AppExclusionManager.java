package com.myvpn.simple.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007J\u0006\u0010\u0019\u001a\u00020\u0017J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0007J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007R0\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "packageNames", "", "", "excludedApps", "getExcludedApps", "()Ljava/util/Set;", "setExcludedApps", "(Ljava/util/Set;)V", "mode", "Lcom/myvpn/simple/ui/AppExclusionManager$ExclusionMode;", "exclusionMode", "getExclusionMode", "()Lcom/myvpn/simple/ui/AppExclusionManager$ExclusionMode;", "setExclusionMode", "(Lcom/myvpn/simple/ui/AppExclusionManager$ExclusionMode;)V", "prefs", "Landroid/content/SharedPreferences;", "addExcludedApp", "", "packageName", "clearExcludedApps", "isAppExcluded", "", "removeExcludedApp", "Companion", "ExclusionMode", "app_debug"})
public final class AppExclusionManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREF_NAME = "MY_VPN";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String APP_LIST_KEY = "EXCLUDED_APPS";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String EXCLUSION_MODE_KEY = "exclusion_mode";
    @org.jetbrains.annotations.NotNull
    public static final com.myvpn.simple.ui.AppExclusionManager.Companion Companion = null;
    
    public AppExclusionManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myvpn.simple.ui.AppExclusionManager.ExclusionMode getExclusionMode() {
        return null;
    }
    
    public final void setExclusionMode(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.ui.AppExclusionManager.ExclusionMode mode) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Set<java.lang.String> getExcludedApps() {
        return null;
    }
    
    public final void setExcludedApps(@org.jetbrains.annotations.NotNull
    java.util.Set<java.lang.String> packageNames) {
    }
    
    public final boolean isAppExcluded(@org.jetbrains.annotations.NotNull
    java.lang.String packageName) {
        return false;
    }
    
    public final void addExcludedApp(@org.jetbrains.annotations.NotNull
    java.lang.String packageName) {
    }
    
    public final void removeExcludedApp(@org.jetbrains.annotations.NotNull
    java.lang.String packageName) {
    }
    
    public final void clearExcludedApps() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionManager$Companion;", "", "()V", "APP_LIST_KEY", "", "EXCLUSION_MODE_KEY", "PREF_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\n"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionManager$ExclusionMode;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "BLACKLIST", "WHITELIST", "Companion", "app_debug"})
    public static enum ExclusionMode {
        /*public static final*/ BLACKLIST /* = new BLACKLIST(null) */,
        /*public static final*/ WHITELIST /* = new WHITELIST(null) */;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String value = null;
        @org.jetbrains.annotations.NotNull
        public static final com.myvpn.simple.ui.AppExclusionManager.ExclusionMode.Companion Companion = null;
        
        ExclusionMode(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.myvpn.simple.ui.AppExclusionManager.ExclusionMode> getEntries() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionManager$ExclusionMode$Companion;", "", "()V", "fromValue", "Lcom/myvpn/simple/ui/AppExclusionManager$ExclusionMode;", "value", "", "app_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.myvpn.simple.ui.AppExclusionManager.ExclusionMode fromValue(@org.jetbrains.annotations.NotNull
            java.lang.String value) {
                return null;
            }
        }
    }
}