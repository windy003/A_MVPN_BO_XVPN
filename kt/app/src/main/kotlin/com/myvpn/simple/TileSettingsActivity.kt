package com.myvpn.simple

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.util.Log

/**
 * 磁贴设置中转页面
 * 当用户长按快速设置磁贴时，会打开这个页面，然后自动跳转到主界面
 */
class TileSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "TileSettingsActivity started - long press detected")

        // 立即跳转到主界面
        val intent = Intent(this, MainActivity::class.java).apply {
            // 清除任务栈，确保返回时不会回到这个页面
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)

        Log.d(TAG, "MainActivity launched, finishing TileSettingsActivity")

        // 关闭当前页面
        finish()
    }

    companion object {
        private const val TAG = "TileSettingsActivity"
    }
}
