package com.reactlibrary.rnwifi.mappers;

import android.net.wifi.ScanResult;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.List;

public class WifiScanResultsMapper {
    private WifiScanResultsMapper() {
    }

    private static String parseSSID(final ScanResult scanResult) {
        if (scanResult.SSID.equals("")) {
            return "(hidden SSID)";
        }

        return scanResult.SSID;
    }

    private static String channelWidthToString(int channelWidth) {
        switch (channelWidth) {
            case ScanResult.CHANNEL_WIDTH_20MHZ:
                return "CHANNEL_WIDTH_20MHZ";
            case ScanResult.CHANNEL_WIDTH_40MHZ:
                return "CHANNEL_WIDTH_40MHZ";
            case ScanResult.CHANNEL_WIDTH_80MHZ:
                return "CHANNEL_WIDTH_80MHZ";
            case ScanResult.CHANNEL_WIDTH_80MHZ_PLUS_MHZ:
                return "CHANNEL_WIDTH_80MHZ_PLUS_MHZ";
            case ScanResult.CHANNEL_WIDTH_160MHZ:
                return "CHANNEL_WIDTH_160MHZ";
// TODO how to conditionally add this based on API level?
//            case ScanResult.CHANNEL_WIDTH_320MHZ:
//                return "CHANNEL_WIDTH_320MHZ";
            default:
                return "UNKNOWN";
        }
    }

    public static WritableArray mapWifiScanResults(final List<ScanResult> scanResults) {
        final WritableArray wifiArray = new WritableNativeArray();

        for (ScanResult result : scanResults) {
            final WritableMap wifiObject = new WritableNativeMap();
            wifiObject.putString("SSID", parseSSID(result));
            wifiObject.putString("BSSID", result.BSSID);
            wifiObject.putString("capabilities", result.capabilities);
            wifiObject.putString("channelWidth", channelWidthToString(result.channelWidth));
            wifiObject.putInt("frequency", result.frequency);
            wifiObject.putInt("level", result.level);
            wifiObject.putDouble("timestamp", result.timestamp);
            wifiArray.pushMap(wifiObject);
        }

        return wifiArray;
    }
}
