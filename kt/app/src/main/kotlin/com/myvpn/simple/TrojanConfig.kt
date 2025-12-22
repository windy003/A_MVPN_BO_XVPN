package com.myvpn.simple

import java.io.Serializable

data class TrojanConfig(
    var server: String = "",
    var port: Int = 0,
    var password: String = "",
    var sni: String = "",
    var allowInsecure: Boolean = false,
    var remark: String = "",
    var country: String = ""
) : Serializable {

    fun isValid(): Boolean {
        return server.isNotEmpty() && port in 1..65535 && password.isNotEmpty()
    }

    override fun toString(): String {
        return "trojan://$password@$server:$port"
    }
}
