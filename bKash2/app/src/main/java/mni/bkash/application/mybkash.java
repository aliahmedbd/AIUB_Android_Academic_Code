package mni.bkash.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ali Ahmed on 12/4/2015.
 */
public class mybkash extends Activity implements View.OnClickListener{
    public void onClick(View v)
    {
        Intent intent=new Intent("android.intent.action.menupin");
       // Intent intent1=new Intent("android.intent.action.MAIN");
        switch (v.getId()){
            case R.id.btn_changeatmpin:
                startActivity(intent);
                break;
            case R.id.btn_checkbalance:
                startActivity(intent);
                break;
            case R.id.btn_reqstatement:
                startActivity(intent);
                break;
            case R.id.btn_changemobilepin:
                startActivity(intent);
                break;
            case R.id.btn_mainmenu:
                //startActivity(intent1);
                finish();
                break;

        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybkash);


    }
}
