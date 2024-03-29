package com.dingar.twok.lottery.presentation.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dingar.twok.lottery.R;

public class Fragment_Lottery extends Fragment {

    View item_dice,item_phae,item_2d,item_3d,item_2k,item_body;

    public Fragment_Lottery() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__lottery,container,false);
        // Inflate the layout for this fragment
        item_dice = view.findViewById(R.id.diceLottery);
        item_phae = view.findViewById(R.id.phae);
        item_2d = view.findViewById(R.id.twoDLottery);
        item_3d = view.findViewById(R.id.threeDLottery);
        item_2k = view.findViewById(R.id.twoK);
        item_body = view.findViewById(R.id.more);
        onClicks();
    return view;
    }

    //handle all onclick events
    private void onClicks(){
        item_dice.setOnClickListener(view ->
                toNextActivity("com.dingar.twok.dice.presentation.view.Activity_DiceBet"));

        item_2d.setOnClickListener(view ->
                toNextActivity("com.dingar.twok.twoD.presentation.view.Activity_DiceBet"));

        item_3d.setOnClickListener(view ->
                toNextActivity("com.dingar.twok.threeD.presentation.view.Activity_DiceBet"));


        item_phae.setOnClickListener(view ->
                toNextActivity("com.dingar.twok.phae.presentation.view.Activity_DiceBet"));


        item_2k.setOnClickListener(view ->
                toNextActivity("com.dingar.twok.twoK.presentation.view.Activity_DiceBet"));

        item_body.setOnClickListener(view ->
                Toast.makeText(requireContext(), "Not Available Yet", Toast.LENGTH_SHORT).show());

    }


    private void toNextActivity(@NonNull String url){
        Intent intent = new Intent();
        intent.setClassName(requireContext().getPackageName(),url);
        startActivity(intent);
    }
}