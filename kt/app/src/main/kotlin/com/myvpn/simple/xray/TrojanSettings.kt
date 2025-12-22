package com.myvpn.simple.xray

data class TrojanSettings(
    var servers: MutableList<TrojanServer> = mutableListOf()
) {
    data class TrojanServer(
        var address: String,
        var port: Int,
        var password: String
    )
}
