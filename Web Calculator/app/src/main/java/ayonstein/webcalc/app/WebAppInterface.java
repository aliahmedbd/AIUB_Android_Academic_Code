package ayonstein.webcalc.app;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class WebAppInterface {
	String num1="";
	String num2="";
	Double result;
	String res;
	boolean opSet=false;
	String op;
	WebCalculatorActivity wa=null;
    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        wa=(WebCalculatorActivity)c;
    }
    public void addNum(String num) {
    	
        if(opSet){
        	num2+=num;
        }
        else
        	num1+=num;
    	//Toast.makeText(wa, num, Toast.LENGTH_LONG).show();
        
    }
    public void addOperator(String opr) {
    	op = opr;
    	opSet = true;
    	
    }
    public String getResult(){
    	if(op.equals("+"))
    		result=Double.valueOf(num1)+Double.valueOf(num2);
    	else if(op.equals("-"))
    		result=Double.valueOf(num1)-Double.valueOf(num2);
    	else if(op.equals("*"))
    		result=Double.valueOf(num1)*Double.valueOf(num2);
    	else if(op.equals("/"))
    		result=Double.valueOf(num1)/Double.valueOf(num2);
    	
    	res=result.toString();
    	num1="";
    	num2="";
    	opSet=false;
    	return res;
    }
    public void reload(){
    	wa.mHandler.post(new Runnable() {
			public void run() {
				WebAppInterface.this.wa.myWebView.reload();
				Log.d("try", "reload");
			}
		});
	}
    public void testMethod(){
    	Log.d("changed event","success");
    	//wa.button.setText("changed name");
    	/*wa.mHandler.post(new Runnable() {
			public void run() {
				Button btn= (Button)WebAppInterface.this.wa.findViewById(R.id.button1);
				btn.setText("new Changed");
			}
		});*/
    	wa.runOnUiThread(new Runnable() {
			public void run() {
				Button btn= (Button)WebAppInterface.this.wa.findViewById(R.id.button1);
				btn.setText("Changed");
			}
		});
	}
    
}

