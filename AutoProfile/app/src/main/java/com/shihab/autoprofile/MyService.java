package com.shihab.autoprofile;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service implements SensorEventListener{
	float v1,v2,v3;
	AudioManager myAudioManager;
	public MyService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public void onCreate()
	{
		  Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
	}
	public void onStart(Intent intent, int startId)
	{
		//handleCommand(intent);
		
	}
    public int onStartCommand(Intent intent, int flags, int startId) {
    	Log.v("Onstart", "Main Activity call");
    	MainActivity m=new MainActivity();
    	v1=m.v1;
		v2=m.v2;
		v3=m.v3;
		String s=String.valueOf(v3);
		//m.et5.setText(s);
		Log.v("Onstart", "v1");
    	if((v1<=1.5 &&v1>=-1.5) && (v2<=1 && v2>=0) && (v3>=9.5))
		{
			//m.ringing();
		}
		else if((v1<=2.5 && v1>=-2.5) && v2<=1 && v3<=-9)
		{
			//m.silent();
		}
		else
		{
			//m.vibrate();
		}
        return Service.START_STICKY;
    }
	public void onDestroy() 
	{
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		Sensor s=event.sensor;
		if(s.getType()==Sensor.TYPE_ACCELEROMETER)
		{
			//v1=event.values[0];
			//v2=event.values[1];
			//v3=event.values[2];
		}
		
	}
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

}
