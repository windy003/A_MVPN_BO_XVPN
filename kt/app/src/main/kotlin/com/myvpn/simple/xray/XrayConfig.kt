package com.myvpn.simple.xray

data class XrayConfig(
    var inbounds: List<Inbound>? = null,
    var outbounds: List<Outbound>? = null,
    var log: Log = Log(),
    var routing: Routing? = null
) {
    data class Log(
        var loglevel: String = "warning"
    )

    data class Inbound(
        var protocol: String = "",
        var port: Int = 0,
        var listen: String = "",
        var settings: Map<String, Any>? = null,
        var sniffing: Sniffing? = null
    )

    data class Outbound(
        var tag: String = "",
        var protocol: String = "",
        var settings: Any? = null,
        var streamSettings: StreamSettings? = null
    )

    data class Sniffing(
        var enabled: Boolean = false,
        var destOverride: List<String>? = null,
        var routeOnly: Boolean = false
    )

    data class StreamSettings(
        var network: String = "",
        var security: String = "",
        var tlsSettings: TlsSettings? = null
    )

    data class TlsSettings(
        var serverName: String = "",
        var allowInsecure: Boolean = false
    )

    data class Routing(
        var rules: List<RoutingRule>? = null
    )

    data class RoutingRule(
        var type: String = "",
        var outboundTag: String = "",
        var network: String = ""
    )
}
