package com.myvpn.simple.ui

import android.content.Context
import android.content.SharedPreferences

class AppExclusionManager(context: Context) {

    enum class ExclusionMode(val value: String) {
        BLACKLIST("blacklist"),  // 排除模式：默认所有应用走代理，排除列表中的应用不走
        WHITELIST("whitelist");  // 允许模式：默认所有应用不走代理，只有列表中的应用走

        companion object {
            fun fromValue(value: String): ExclusionMode =
                values().find { it.value == value } ?: BLACKLIST
        }
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var exclusionMode: ExclusionMode
        get() {
            val mode = prefs.getString(EXCLUSION_MODE_KEY, ExclusionMode.BLACKLIST.value)
            return ExclusionMode.fromValue(mode ?: ExclusionMode.BLACKLIST.value)
        }
        set(mode) {
            prefs.edit().putString(EXCLUSION_MODE_KEY, mode.value).apply()
        }

    var excludedApps: Set<String>
        get() = prefs.getStringSet(APP_LIST_KEY, emptySet()) ?: emptySet()
        set(packageNames) {
            prefs.edit().putStringSet(APP_LIST_KEY, packageNames.toSet()).apply()
        }

    fun isAppExcluded(packageName: String): Boolean =
        excludedApps.contains(packageName)

    fun addExcludedApp(packageName: String) {
        excludedApps = excludedApps + packageName
    }

    fun removeExcludedApp(packageName: String) {
        excludedApps = excludedApps - packageName
    }

    fun clearExcludedApps() {
        prefs.edit().remove(APP_LIST_KEY).apply()
    }

    companion object {
        private const val PREF_NAME = "MY_VPN"
        private const val APP_LIST_KEY = "EXCLUDED_APPS"
        private const val EXCLUSION_MODE_KEY = "exclusion_mode"
    }
}
