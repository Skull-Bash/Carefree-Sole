package com.example.carefree_sole;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class Mode_Fragment extends Fragment {

    BluetoothSPP bluetooth;
    Button B1,B2,B3,B4,B5;

    public Mode_Fragment(BluetoothSPP bluetoothSPP) {
        bluetooth = bluetoothSPP;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mode_, container, false);

        B1 = view.findViewById(R.id.mode_1);
        B2 = view.findViewById(R.id.mode_2);
        B3 = view.findViewById(R.id.mode_3);
        B4 = view.findViewById(R.id.mode_4);
        B5 = view.findViewById(R.id.mode_5);

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

        return view;
    }

    public void sendCommand(int btnNumber)
    {
            String s =  "M" + btnNumber + "_Start";
            bluetooth.send( s, true);
            if(s.equals("M1_Start"))
            {
                Toast.makeText(getContext(),"Brain Mode",Toast.LENGTH_SHORT).show();
            }
            else if(s.equals("M2_Start"))
            {
                Toast.makeText(getContext(),"Digestive Mode",Toast.LENGTH_SHORT).show();
            }
            else if(s.equals("M3_Start"))
            {
                Toast.makeText(getContext(),"Heart Mode",Toast.LENGTH_SHORT).show();
            }
            else if(s.equals("M4_Start"))
            {
                Toast.makeText(getContext(),"Headache Mode",Toast.LENGTH_SHORT).show();
            }
            else if(s.equals("M5_Start"))
            {
                Toast.makeText(getContext(),"Throat Mode",Toast.LENGTH_SHORT).show();
            }
    }

}