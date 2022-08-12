package com.example.carefree_sole;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class Individual_Fragment extends Fragment {
    BluetoothSPP bluetooth;

    public Individual_Fragment(BluetoothSPP bluetoothSPP) {
            bluetooth = bluetoothSPP;
    }

    Button B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12;
    int state[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual_, container, false);

        B1 = view.findViewById(R.id.B1);
        B2 = view.findViewById(R.id.B2);
        B3 = view.findViewById(R.id.B3);
        B4 = view.findViewById(R.id.B4);
        B5 = view.findViewById(R.id.B5);
        B6 = view.findViewById(R.id.B6);
        B7 = view.findViewById(R.id.B7);
        B8 = view.findViewById(R.id.B8);
        B9 = view.findViewById(R.id.B9);
        B10 = view.findViewById(R.id.B10);
        B11 = view.findViewById(R.id.B11);
        B12 = view.findViewById(R.id.B12);

        B1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendCommand(1);
        }
    });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(2);
            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(3);
            }
        });

        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(4);
            }
        });

        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(5);
            }
        });

        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(6);
            }
        });

        B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(7);
            }
        });

        B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(8);
            }
        });

        B9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(9);
            }
        });

        B10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(10);
            }
        });

        B11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(11);
            }
        });

        B12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(12);
            }
        });
        return view;
    }

    public void sendCommand(int btnNumber)
    {
        btnNumber-=1;
        if(state[btnNumber] == 0) {
            bluetooth.send( "V" + (btnNumber+1) + "_Start", true);
            Toast.makeText(getContext(),"V" + (btnNumber+1) + "_Start",Toast.LENGTH_SHORT).show();
            state[btnNumber] = 1;
        }
        else
        {
            bluetooth.send("V" + (btnNumber+1) + "_Stop", true);
            Toast.makeText(getContext(),"V" + (btnNumber+1) + "_Stop",Toast.LENGTH_SHORT).show();
            state[btnNumber] = 0;
        }
    }

}