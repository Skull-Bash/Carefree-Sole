package com.example.carefree_sole;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    Button Connect;
    BluetoothSPP bluetooth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetooth = new BluetoothSPP(this);

        Connect = findViewById(R.id.connect_button);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewpager);

        viewPager2.setAdapter(new FragmentAdapter(this,bluetooth));

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position)
                {
                    case 0:
                        tab.setText("Individuals");
                        break;
                    case 1:
                        tab.setText("Modes");
                        break;
                }
            }
        }).attach();


        if (!bluetooth.isBluetoothAvailable())
        {
            Toast.makeText(getApplicationContext(),"Turn On Bluetooth!",Toast.LENGTH_LONG).show();
            finish();
        }

        bluetooth.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {
                Connect.setText("Connected With : " + name);
            }

            @Override
            public void onDeviceDisconnected() {
                Connect.setText("Connection Lost!!!!");
            }

            @Override
            public void onDeviceConnectionFailed() {
                Connect.setText("Unable To Connect!!");
            }
        });

        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED)
                    bluetooth.disconnect();
                else
                {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent,BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
    }

    public void onStart() {
        super.onStart();
        if (!bluetooth.isBluetoothEnabled())
            bluetooth.enable();
        else
        {
            if (!bluetooth.isServiceAvailable()) {
                bluetooth.setupService();
                bluetooth.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        bluetooth.stopService();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bluetooth.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK)
                bluetooth.setupService();
        } else {
            Toast.makeText(getApplicationContext(),"Bluetooth is not turned on",Toast.LENGTH_LONG).show();
            finish();
        }
    }




}