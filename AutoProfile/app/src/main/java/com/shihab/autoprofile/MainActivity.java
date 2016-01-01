package com.shihab.autoprofile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener{

	SensorManager sensorManager;
	Sensor sensorss ;
	Sensor s1;
	//Boolean flag=true;
	EditText et1,et2,et3,et4,et5;
	int i=0;
	AudioManager myAudioManager;
	public float v1,v2,v3;
	public View view;
	Intent serviceIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorss =sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		s1= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		et1=(EditText) findViewById(R.id.editText1);
		et2=(EditText) findViewById(R.id.editText2);
		et3=(EditText) findViewById(R.id.editText3);
		et4=(EditText) findViewById(R.id.editText4);
		et5=(EditText) findViewById(R.id.editText5);
		serviceIntent=new Intent(MainActivity.this, MyService.class);
		startService(serviceIntent);
		//startNewService(view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		// TODO Auto-generated method stub
		Sensor ss=event.sensor;
		float seVal=0;
		
		if(ss.getType()==Sensor.TYPE_PROXIMITY)
		{
			seVal=event.values[0];
			et1.setText("p= "+event.values[0]);
		}	
		else if(ss.getType()==Sensor.TYPE_ACCELEROMETER)
		{
			et2.setText(String.valueOf(event.values[0]));
			et3.setText(String.valueOf(event.values[1]));
			et4.setText(String.valueOf(event.values[2]));
			v1=event.values[0];
			v2=event.values[1];
			v3=event.values[2];
			
			if((v1<=1.5 &&v1>=-1.5) && (v2<=1 && v2>=0) && (v3>=9.5))
			{
				ringing();
			}
			else if((v1<=2.5 && v1>=-2.5) && v2<=1 && v3<=-9)
			{
				silent();
			}
			else
			{
				vibrate();
			}
		}
	}
	/*public void startNewService(View view) 
	{
		startService(new Intent(this, MyService.class));
	}

	public void stopNewService(View view) 
	{
		stopService(new Intent(this, MyService.class));
    }*/

	public void ringing()
	{
		myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		myAudioManager.setStreamVolume(AudioManager.STREAM_RING,myAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING), AudioManager.FLAG_PLAY_SOUND);
		SystemClock.sleep(25);		
	}
	public void vibrate() 
	{
		myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		Vibrator myVibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);		
		SystemClock.sleep(50);
		myVibrator.cancel();
	}
	public void silent()
	{
		myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensorManager.registerListener(this,sensorss,SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this,s1,SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void onDestroy()
	{
		super.onDestroy();
		//stopNewService(view);
	}

}
