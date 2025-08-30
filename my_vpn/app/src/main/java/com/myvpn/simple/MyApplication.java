package com.myvpn.simple;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import cn.gov.xivpn2.LibXivpn;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        try {
            System.loadLibrary("xivpn");
            LibXivpn.xivpn_init();
            Log.i(TAG, "libxivpn initialized successfully");
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize libxivpn", e);
        }
        
        // Create notification channels
        createNotificationChannels();
    }
    
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getSystemService(NotificationManager.class);
            
            NotificationChannel vpnChannel = new NotificationChannel(
                "VPN_CHANNEL",
                "VPN Service",
                NotificationManager.IMPORTANCE_LOW
            );
            vpnChannel.setDescription("VPN connection notifications");
            manager.createNotificationChannel(vpnChannel);
        }
    }
}