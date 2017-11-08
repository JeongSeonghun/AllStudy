package com.jshstudy.bleapp.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

/**
 * Created by EMGRAM on 2017-11-03.
 */

public class BLEDeviceScanner {

    public static final int ERROR_ETC = -1;
    public static final int ERROR_BLUETOOTH_FAIL = 1;
    public static final int ERROR_AREADY_SCAN = 2;
    public static final int ERROR_NO_BLUETOOTH = 3;
    public static final int ERROR_BLE_NO_SUPPORT = 4;

    private BluetoothAdapter adapter;
    private BluetoothLeScanner scanner;
    private ScanCallback scanCallback;

    private BLEDeviceScanCallback callback;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private boolean isEnable = false;
    private long period = 10000; //basic

    public BLEDeviceScanner(Context ctx, BLEDeviceScanCallback callback){
        BluetoothManager manager = (BluetoothManager)ctx.getSystemService(Context.BLUETOOTH_SERVICE);

        adapter = manager.getAdapter();

        this.callback = callback;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            scanner = adapter.getBluetoothLeScanner();
            scanCallback = createScanCallback();
        }

        checkEnable(ctx);
    }

    public static boolean checkBleSupport(Context ctx){
        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        return ctx.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    private boolean checkBluetoothEnable(){
        // <uses-permission android:name="android.permission.BLUETOOTH" />
        return adapter != null && adapter.isEnabled();
    }

    private void checkEnable(Context ctx){
        if(!checkBleSupport(ctx)){
            callback.onScanFail(ERROR_BLE_NO_SUPPORT);
            return;
        }

        if(!checkBluetoothEnable()){
            callback.onScanFail(ERROR_NO_BLUETOOTH);
        }

        isEnable = true;
    }

    public void setPeriod(long period){
        this.period = period;
    }

    public void scanStart(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanStop();
            }
        }, period);

        scan();
    }

    public void scanStart(long period){
        setPeriod(period);

        scanStart();
    }

    public void scan(){
        if(!isEnable) return;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            scanScanner();
        }else{
            scanAdapter();
        }
    }

    private void scanAdapter(){

        if(adapter != null){
            // <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
            adapter.startLeScan(leScanCallback);
        }
    }

    private void scanScanner(){
        if(scanner != null){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                scanner.startScan(scanCallback);
        }
    }

    public void scanStop(){
        if(scanner != null){
            adapter.stopLeScan(leScanCallback);
        }else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                scanner.stopScan(scanCallback);
        }
    }

    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            callback.onScanResult(device, rssi, scanRecord);
        }
    };

    private ScanCallback createScanCallback(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            return new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        callback.onScanResult(result.getDevice(), result.getRssi(), result.getScanRecord().getBytes());
                    }

                }
                // 스캔 종료 전체 결과
                @Override
                public void onBatchScanResults(List<ScanResult> results) {
                    super.onBatchScanResults(results);
                }

                @Override
                public void onScanFailed(int errorCode) {
                    super.onScanFailed(errorCode);
                    callback.onScanFail(errorCode);
                }
            };
        }

        return null;
    }

    public interface BLEDeviceScanCallback {
        void onScanResult(BluetoothDevice device, int rssi, byte[] scanRecord);
        void onScanFail(int errorCode);
    }
}
