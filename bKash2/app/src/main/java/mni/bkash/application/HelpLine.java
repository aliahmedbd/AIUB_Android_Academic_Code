package mni.bkash.application;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

/**
 * Created by Ali Ahmed on 12/5/2015.
 */
public class HelpLine extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpline);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_website:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bkash.com/bn/support/contact-us"));
                startActivity(browserIntent);
                break;
            case R.id.btn_mainmenu:
                finish();
        }

    }
}
