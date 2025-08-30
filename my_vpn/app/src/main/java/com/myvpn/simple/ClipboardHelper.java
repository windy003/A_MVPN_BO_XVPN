package com.myvpn.simple;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;

public class ClipboardHelper {
    
    public static List<TrojanConfig> readSubscriptionFromClipboard(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        
        if (clipboard != null && clipboard.hasPrimaryClip()) {
            ClipData clipData = clipboard.getPrimaryClip();
            if (clipData != null && clipData.getItemCount() > 0) {
                CharSequence text = clipData.getItemAt(0).getText();
                if (text != null) {
                    String clipboardText = text.toString().trim();
                    
                    if (clipboardText.startsWith("trojan://")) {
                        TrojanConfig config = SubscriptionParser.parseTrojanUrl(clipboardText);
                        if (config != null && config.isValid()) {
                            List<TrojanConfig> result = new ArrayList<>();
                            result.add(config);
                            return result;
                        }
                    } else if (isBase64(clipboardText)) {
                        return SubscriptionParser.parseSubscription(clipboardText);
                    }
                }
            }
        }
        
        return new ArrayList<>();
    }
    
    private static boolean isBase64(String str) {
        try {
            String decoded = new String(android.util.Base64.decode(str, android.util.Base64.DEFAULT));
            return decoded.contains("trojan://");
        } catch (Exception e) {
            return false;
        }
    }
}