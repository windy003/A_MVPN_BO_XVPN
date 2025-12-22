package cn.gov.xivpn2.service

import android.net.VpnService
import android.util.Log

/**
 * 占位服务类，用于满足 libxivpn.so 初始化时的类依赖要求
 * 实际的 VPN 功能由 com.myvpn.simple.SimpleVPNService 实现
 */
open class XiVPNService : VpnService(), SocketProtect {
    companion object {
        private const val TAG = "XiVPNService"
    }

    override fun protectFd(fd: Int) {
        Log.d(TAG, "protectFd called: $fd")
        // 这是占位实现，实际保护逻辑在 SimpleVPNService 中
        protect(fd)
    }
}
