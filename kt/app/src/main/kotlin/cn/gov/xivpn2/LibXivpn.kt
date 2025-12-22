package cn.gov.xivpn2

import cn.gov.xivpn2.service.SocketProtect

object LibXivpn {
    @JvmStatic
    external fun xivpn_version(): String

    @JvmStatic
    external fun xivpn_start(
        config: String,
        socksPort: Int,
        fd: Int,
        logFile: String,
        asset: String,
        protect: SocketProtect
    ): String

    @JvmStatic
    external fun xivpn_stop()

    @JvmStatic
    external fun xivpn_init()
}
