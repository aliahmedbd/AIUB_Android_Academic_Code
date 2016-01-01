package mni.bkash.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ali Ahmed on 12/5/2015.
 */
public class Payment extends Activity implements View.OnClickListener{
    public String history;
    public void onClick(View v) {
        EditText txt1=(EditText)findViewById(R.id.txt_merchants);
        EditText txt=(EditText)findViewById(R.id.txt_amount);
        EditText txt2=(EditText)findViewById(R.id.txt_pin);
        switch (v.getId()){


            case R.id.btn_send:
                history="*247# \n 3 \n"+"Merchant:"+txt1.getText()+"\n"+"Amount:"+txt.getText()+"\n"+"PIN:"+txt2.getText();
                Toast.makeText(getBaseContext(), history, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_mainmenu:
               txt1.setText("");
                txt.setText("");
                txt2.setText("");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
    }
}
