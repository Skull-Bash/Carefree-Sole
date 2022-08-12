package com.example.carefree_sole;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class FragmentAdapter extends FragmentStateAdapter {
    BluetoothSPP bluetooth;
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity,BluetoothSPP bluetoothSPP) {
        super(fragmentActivity);
        bluetooth = bluetoothSPP;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0: return new Individual_Fragment(bluetooth);
            default: return new Mode_Fragment(bluetooth);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
