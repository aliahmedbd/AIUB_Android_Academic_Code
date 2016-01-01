package mni.bkash.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ali Ahmed on 12/5/2015.
 */
public class buyairtime extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyairtime);

    }

    public void onClick(View v) {
        Intent intent=new Intent("android.intent.action.prepost");
        switch (v.getId()){
            case R.id.btn_robi:
                startActivity(intent);
                break;
            case R.id.btn_airtel:
                startActivity(intent);
                break;
            case R.id.btn_banglalink:
                startActivity(intent);
                break;
            case R.id.btn_gp:
                startActivity(intent);
                break;
            case R.id.btn_mainmenu:
                finish();
                break;

        }
    }
}
