package mni.bkash.application;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ali Ahmed on 12/4/2015.
 */
public class SendMoney extends Activity implements View.OnClickListener{
    public String history="";
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmoney);
    }
    String number,amount,refNumber,pin;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            //Log.d(TAG, "Response: " + data.toString());
            uriContact = data.getData();


            retrieveContactNumber();


        }
    }
    private void retrieveContactNumber() {

        String contactNumber = null;

        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();

        // Log.d(TAG, "Contact ID: " + contactID);

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactNumber = contactNumber.replace(" ","");
            contactNumber = contactNumber.replace("-","");

            EditText txt5=(EditText)findViewById(R.id.txt_enternumber);
            txt5.setText(contactNumber+"");
        }

        cursorPhone.close();

        // Log.d(TAG, "Contact Phone Number: " + contactNumber);
    }

    public void onClick(View v) {
        EditText txt=(EditText)findViewById(R.id.txt_amount);
        EditText txt1=(EditText)findViewById(R.id.txt_enternumber);
        EditText txt2=(EditText)findViewById(R.id.txt_enterreference);
        EditText txt3=(EditText)findViewById(R.id.txt_pin);
        switch (v.getId()){
            case R.id.btn_contact:


                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
                break;
            case R.id.btn_cancel:

                txt.setText("");

                txt1.setText("");

                txt2.setText("");

                txt3.setText("");
                break;
            case R.id.btn_send:

                number = txt1.getText() + "";
                amount = txt.getText() + "";
                refNumber = txt2.getText() + "";
                pin = txt3.getText() + "";
                if(number=="" || amount=="" || pin==""){
                    Toast.makeText(getBaseContext(),"Please fill up blank field",Toast.LENGTH_SHORT).show();


                }
                else{
                    history="*247# \n 1 \n"+"Mobile:"+txt1.getText().toString()+"\n"+"Amount:"+txt.getText()+"\n"+"Reference:"+txt2.getText()+"\n"+"PIN:"+txt3.getText()+"";
                    Toast.makeText(getBaseContext(),history,Toast.LENGTH_SHORT).show();

                }


        }


    }
}
