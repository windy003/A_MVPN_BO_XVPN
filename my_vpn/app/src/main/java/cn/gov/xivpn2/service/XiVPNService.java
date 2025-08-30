package cn.gov.xivpn2.service;

import android.net.VpnService;
import android.util.Log;

/**
 * 占位服务类，用于满足 libxivpn.so 初始化时的类依赖要求
 * 实际的 VPN 功能由 com.myvpn.simple.SimpleVPNService 实现
 */
public class XiVPNService extends VpnService implements SocketProtect {
    private static final String TAG = "XiVPNService";
    
    @Override
    public void protectFd(int fd) {
        Log.d(TAG, "protectFd called: " + fd);
        // 这是占位实现，实际保护逻辑在 SimpleVPNService 中
        this.protect(fd);
    }
}