package mni.bkash.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BKashActivity extends Activity {
    //public static String history="*247#";
    public void onClick(View v) {
        // TextView txt=(TextView)findViewById(R.id.);
        Context context=getApplicationContext();
        switch (v.getId()){

            case R.id.btn_sendmoney:
               // history=history+"\n 1";
                Intent intent=new Intent("android.intent.action.SendMoney");
                startActivity(intent);
                break;
            case R.id.btn_cashout:
                //history=history+"\n 4";
                Intent intent2=new Intent("android.intent.action.cashout");
                startActivity(intent2);
                break;
            case R.id.btn_mybkash:
                //history=history+"\n 5";
                Intent intent3=new Intent("android.intent.action.mybkash");
                startActivity(intent3);
                //txt.setText("From My bkash");
                break;
            case R.id.btn_buyairtime:
                //history=history+"\n 2";
                Intent intent4=new Intent("android.intent.action.buyairtme");
                startActivity(intent4);
                //txt.setText("From My bkash");
                break;
            case R.id.btn_payment:
                //history=history+"\n 3";
                Intent intent5=new Intent("android.intent.action.payment");
                startActivity(intent5);
                //txt.setText("From My bkash");
                break;
            case R.id.btn_helpline:
                //history=history+"\n 6";
                Intent intent6=new Intent("android.intent.action.helpline");
                startActivity(intent6);
                //txt.setText("From My bkash");
                break;


        }

    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}