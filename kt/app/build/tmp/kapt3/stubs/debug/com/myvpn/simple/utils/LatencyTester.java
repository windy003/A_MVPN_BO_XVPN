package com.myvpn.simple.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J \u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/myvpn/simple/utils/LatencyTester;", "", "()V", "TAG", "", "TIMEOUT", "", "testLatency", "host", "port", "testLatencyWithRetry", "retries", "app_debug"})
public final class LatencyTester {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "LatencyTester";
    private static final int TIMEOUT = 3000;
    @org.jetbrains.annotations.NotNull
    public static final com.myvpn.simple.utils.LatencyTester INSTANCE = null;
    
    private LatencyTester() {
        super();
    }
    
    /**
     * 测试节点延迟
     * @param host 服务器地址
     * @param port 端口号
     * @return 延迟时间(ms)，-1表示测试失败
     */
    public final int testLatency(@org.jetbrains.annotations.NotNull
    java.lang.String host, int port) {
        return 0;
    }
    
    /**
     * 测试节点延迟（带重试）
     * @param host 服务器地址
     * @param port 端口号
     * @param retries 重试次数，默认1次（总共测试2次取最小值）
     * @return 延迟时间(ms)，-1表示测试失败
     */
    public final int testLatencyWithRetry(@org.jetbrains.annotations.NotNull
    java.lang.String host, int port, int retries) {
        return 0;
    }
}