package com.myvpn.simple.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/myvpn/simple/utils/SubscriptionDownloader;", "", "()V", "CONNECT_TIMEOUT", "", "READ_TIMEOUT", "TAG", "", "downloadSubscription", "url", "isSubscriptionUrl", "", "text", "app_debug"})
public final class SubscriptionDownloader {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "SubscriptionDownloader";
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;
    @org.jetbrains.annotations.NotNull
    public static final com.myvpn.simple.utils.SubscriptionDownloader INSTANCE = null;
    
    private SubscriptionDownloader() {
        super();
    }
    
    /**
     * 从订阅链接下载内容
     * @param url 订阅链接 URL
     * @return 下载的内容（通常是 Base64 编码的节点列表），失败返回 null
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String downloadSubscription(@org.jetbrains.annotations.NotNull
    java.lang.String url) {
        return null;
    }
    
    /**
     * 判断字符串是否为订阅链接 URL
     */
    public final boolean isSubscriptionUrl(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
        return false;
    }
}