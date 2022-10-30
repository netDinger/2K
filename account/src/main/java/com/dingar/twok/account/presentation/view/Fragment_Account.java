package com.dingar.twok.account.presentation.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.account.R;
import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.di.component.AccountComponent;
import com.dingar.twok.account.di.component.ComponentProviderAccount;
import com.dingar.twok.account.presentation.contract.AccountContract;

import javax.inject.Inject;

public class Fragment_Account extends Fragment implements AccountContract.View {

    @Inject
    AccountContract.Presenter presenter; //inject the presenter

    AccountComponent accountComponent;  //component
    private TextView username,id,balance,points,coupons;
    private ImageView verified;
    private TextView about,share,helpFeedback,privacyPolicy,language,changePhone;

    public Fragment_Account(){} //empty constructor

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initiate the component and inject the view
        accountComponent = ((ComponentProviderAccount) requireActivity().getApplicationContext()).provideAccountComponent();
        accountComponent.inject(this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        widgets(view);
        initiate();
        return view;
    }

    private void widgets(View view){
        username = view.findViewById(R.id.username);
        id = view.findViewById(R.id.id);
        RelativeLayout layout = view.findViewById(R.id.balance_layout);
        balance = layout.findViewById(R.id.balance);
        points = layout.findViewById(R.id.points);
        coupons = layout.findViewById(R.id.coupons);
        about = view.findViewById(R.id.about);
        share = view.findViewById(R.id.share);
        helpFeedback = view.findViewById(R.id.help);
        language = view.findViewById(R.id.language);
        privacyPolicy = view.findViewById(R.id.privacyPolicy);
        changePhone = view.findViewById(R.id.change_ph_no);
        verified = view.findViewById(R.id.verified);


        TextView logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(view1 -> {
            presenter.logout();
            Toast.makeText(requireContext(),"Logout Successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClassName(requireContext().getPackageName(),
                    "com.dingar.twok.auth.presentation.view.Activity_Login");
            startActivity(intent);
        });

        id.setOnClickListener(v->{ //on user id textview is clicked
            //copy id to clipboard
            ClipboardManager manager = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(id.getText().toString().substring(3),id.getText().toString().substring(3));
            manager.setPrimaryClip(clipData);

            Toast.makeText(requireContext(),"Copied to ClipBoard",Toast.LENGTH_SHORT).show();
        });

        balance.setOnClickListener(view1 -> startActivity(new Intent(requireContext(),Activity_Balance.class)));

        coupons.setOnClickListener(v->{
            showToast("No Coupons Yet!!!");
        });

        about.setOnClickListener(v->startActivity(new Intent(requireContext(),Activity_About.class)));

        share.setOnClickListener(v->{

            String url = "www.google.com";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,url);
            startActivity(Intent.createChooser(shareIntent,"Send message"));
        });

        changePhone.setOnClickListener(v->showToast("Not Available Yet!!!"));
        helpFeedback.setOnClickListener(v->showToast("Not Available Yet!!!"));
        language.setOnClickListener(v->showToast("Not Available Yet!!!"));
        privacyPolicy.setOnClickListener(v->startActivity(new Intent(requireContext(),ActivityPrivacyPolicy.class)));
    }

    private void initiate() {
        presenter.setView(this);        //attach the view
        presenter.loadUserBalance();    //get user balance
        presenter.loadUserPoint();      //get user point
        presenter.loadUserInfo();       //get user info
    }

    @Override
    public void onBalanceLoaded(String balance) {
        this.balance.setText(balance);
    }

    @Override
    public void onPointLoaded(String point) {
        this.points.setText(point);
    }

    @Override
    public void onUserInfoLoaded(User user) {
       username.setText(user.getName());
       String s = getResources().getString(R.string.uid)+ user.getUid();
       id.setText(s);
       verified.setImageResource(user.isVerified()?R.drawable.ic_verified:R.drawable.ic_baseline_pending_24);
    }

    @Override public void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}