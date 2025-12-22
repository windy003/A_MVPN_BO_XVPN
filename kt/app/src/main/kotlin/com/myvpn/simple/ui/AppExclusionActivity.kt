package com.myvpn.simple.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myvpn.simple.R
import com.myvpn.simple.SimpleVPNService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppExclusionActivity : AppCompatActivity(),
    AppExclusionAdapter.OnAppExclusionChangeListener {

    private lateinit var spinnerMode: Spinner
    private lateinit var textModeDescription: TextView
    private lateinit var editSearch: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: AppExclusionAdapter
    private var appExclusionManager: AppExclusionManager? = null
    private lateinit var executorService: ExecutorService
    private var allApps: MutableList<AppInfo> = mutableListOf()

    private var vpnService: SimpleVPNService? = null
    private var isServiceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as SimpleVPNService.VPNBinder
            vpnService = binder.service
            appExclusionManager = binder.appExclusionManager
            isServiceBound = true

            setupUI()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            vpnService = null
            appExclusionManager = null
            isServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_exclusion)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "应用排除设置"
        }

        initViews()
        executorService = Executors.newSingleThreadExecutor()

        // 绑定服务
        val serviceIntent = Intent(this, SimpleVPNService::class.java)
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun initViews() {
        spinnerMode = findViewById(R.id.spinner_exclusion_mode)
        textModeDescription = findViewById(R.id.text_mode_description)
        editSearch = findViewById(R.id.edit_search)
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recycler_view)

        // 设置RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AppExclusionAdapter()
        adapter.setOnAppExclusionChangeListener(this)
        recyclerView.adapter = adapter

        // 设置搜索框
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.setSearchQuery(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupUI() {
        val manager = appExclusionManager ?: return

        // 设置模式选择器
        val modes = arrayOf("排除模式 (默认所有走代理)", "允许模式 (默认所有不走代理)")
        val modeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modes)
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMode.adapter = modeAdapter

        // 设置当前模式
        val currentMode = manager.exclusionMode
        spinnerMode.setSelection(
            if (currentMode == AppExclusionManager.ExclusionMode.BLACKLIST) 0 else 1
        )
        updateModeDescription(currentMode)

        spinnerMode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val mode = if (position == 0)
                    AppExclusionManager.ExclusionMode.BLACKLIST
                else
                    AppExclusionManager.ExclusionMode.WHITELIST
                manager.exclusionMode = mode
                updateModeDescription(mode)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // 加载应用列表
        loadAppList()
    }

    private fun updateModeDescription(mode: AppExclusionManager.ExclusionMode) {
        textModeDescription.text = when (mode) {
            AppExclusionManager.ExclusionMode.BLACKLIST ->
                "排除模式：默认情况下所有应用都会通过VPN连接，选中的应用将不走代理"
            AppExclusionManager.ExclusionMode.WHITELIST ->
                "允许模式：默认情况下所有应用都不会通过VPN连接，只有选中的应用才走代理"
        }
    }

    private fun loadAppList() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        executorService.execute {
            val apps = mutableListOf<AppInfo>()
            val pm = packageManager
            val excludedApps = appExclusionManager?.excludedApps ?: emptySet()

            try {
                val packages = pm.getInstalledPackages(0)
                for (packageInfo in packages) {
                    val appInfo = packageInfo.applicationInfo

                    // 只跳过本应用
                    if (appInfo.packageName == packageName) {
                        continue
                    }

                    val appName = appInfo.loadLabel(pm).toString()
                    val isExcluded = excludedApps.contains(appInfo.packageName)
                    val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0

                    apps.add(
                        AppInfo(
                            appName = appName,
                            packageName = appInfo.packageName,
                            icon = appInfo.loadIcon(pm),
                            isSystemApp = isSystemApp,
                            isExcluded = isExcluded
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            allApps = apps

            runOnUiThread {
                adapter.setAppList(allApps)
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onAppExclusionChanged(packageName: String, isExcluded: Boolean) {
        appExclusionManager?.let { manager ->
            if (isExcluded) {
                manager.addExcludedApp(packageName)
            } else {
                manager.removeExcludedApp(packageName)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!executorService.isShutdown) {
            executorService.shutdown()
        }
        if (isServiceBound) {
            unbindService(serviceConnection)
            isServiceBound = false
        }
    }
}
