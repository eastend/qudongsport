package com.qudong.sport.android.ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.Toast;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.qudong.sport.android.BLECore;
import com.qudong.sport.android.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DeviceAdapter.OnDeviceItemClickListener {


    final int REQUEST_ENABLE_BT = 0x001;
    RecyclerView recyclerView;
    DeviceAdapter deviceAdapter;

    BleDevice currentDevice = null;
    BluetoothGatt currentGatt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deviceAdapter = new DeviceAdapter();
        deviceAdapter.setOnDeviceItemClickListener(this);
        recyclerView.setAdapter(deviceAdapter);
        initBLEManage();

        //判断设备是否支持ble
        if (!BleManager.getInstance().isSupportBle()) {
            toast("此设备不支持BLE");
            finish();
        }

        //判断蓝牙是否开启
        if (BleManager.getInstance().isBlueEnable()) {
            //蓝牙开启 扫描设备
            scan();
        } else {
            // BleManager.getInstance().enableBluetooth(); 此方法异步
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);
        }


    }

    //初始化BLE Manage
    private void initBLEManage() {
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setOperateTimeout(5000);
    }


    private void initScanConfig() {
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                //  .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
                //  .setDeviceName(true, names)         // 只扫描指定广播名的设备，可选
                //  .setDeviceMac(mac)                  // 只扫描指定mac的设备，可选
                //  .setAutoConnect(isAutoConnect)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
    }

    //扫描设备
    private void scan() {
        initScanConfig();
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
                toast("开始扫描");
            }

            @Override
            public void onLeScan(BleDevice bleDevice) {
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                deviceAdapter.addDevice(bleDevice);
            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
                toast("结束扫描");
            }
        });
    }

    //连接设备
    private void connectDevice(final BleDevice device) {
        BleManager.getInstance().connect(device, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                toast("开始连接" + device.getKey());
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                toast("连接失败！");
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                toast("连接成功！");
                currentGatt = gatt;
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
                // 连接后断开 当为主动断开时 isActiveDisConnected 为true
            }
        });
    }




    //读取数据
    private void read(BleDevice device) {
        BleManager.getInstance().read(
                device,
                BLECore.BASE_UUID,
                BLECore.READ_UUID,
                new BleReadCallback() {
                    @Override
                    public void onReadSuccess(byte[] data) {
                        // 读特征值数据成功
                    }

                    @Override
                    public void onReadFailure(BleException exception) {
                        // 读特征值数据失败
                    }
                });
    }

    //发送数据
    private void write(BleDevice device, byte[] data) {
        BleManager.getInstance().write(
                device,
                BLECore.BASE_UUID,
                BLECore.WRITE_UUID,
                data,
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {
                        // 发送数据到设备成功（分包发送的情况下，可以通过方法中返回的参数可以查看发送进度）
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        // 发送数据到设备失败
                    }
                });
    }


    private void toast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            //蓝牙开启 扫描设备
            scan();
        }

    }

    @Override
    public void onItemDeviceClick(BleDevice device) {
        connectDevice(device);
    }
}
