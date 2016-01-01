package mni.bkash.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ali Ahmed on 12/5/2015.
 */
public class PrePost extends Activity implements View.OnClickListener{
    public void onClick(View v) {
        Intent intent=new Intent("android.intent.action.numberInput");
        switch (v.getId()){
            case R.id.btn_postpaid:
                startActivity(intent);
                break;
            case R.id.btn_prepaid:
                startActivity(intent);
                break;
            case R.id.btn_mainmenu:
                finish();
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_post);
    }

}
