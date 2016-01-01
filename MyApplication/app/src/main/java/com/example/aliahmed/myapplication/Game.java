package com.example.aliahmed.myapplication;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View {

	static int  Score=0;
	public static int Gamelife=5;
	Paint paint= new Paint();
	Boolean first=true,onTouch=false;
	int speed=15,barX,width,height;
	int level=0;
	
	GameThread gameThread =new GameThread(this);
	Ball b;
	Bar ba;
	GameObject ball=new Ball(560, 560);
	GameObject bar= new Bar(1,2);
	
	ArrayList<GameObject> gameObjectsList = new ArrayList<GameObject>();
	
	//GameThread gameThread=new GameThread(this);
	public Game(Context context) {

		super(context);
		gameObjectsList.add(ball);
		gameObjectsList.add(bar);
		gameThread.setRunning(true);
		gameThread.start();
		
	}
	public void Level2() {
		gameObjectsList.add(new Bricks(0,(width)/5,height/10,(height/10)*2,1));
		gameObjectsList.add(new Bricks(0,(width)/5,(height/10)*2,(height/10)*3,2));
		
		gameObjectsList.add(new Bricks((width/5),(width/5)*2,height/10,(height/10)*2,1));
		gameObjectsList.add(new Bricks((width/5),(width/5)*2,(height/10)*2,(height/10)*3,2));
		
		gameObjectsList.add(new Bricks((width/5)*4,(width/5)*5-1 ,height/10,(height/10)*2,1));
		gameObjectsList.add(new Bricks((width/5)*4,(width/5)*5-1 ,(height/10)*2,(height/10)*3,2));
		

		gameObjectsList.add(new Bricks((width/5)*3,(width/5)*4 ,(height/10)*3,(height/10)*4,2));
		gameObjectsList.add(new Bricks((width/5)*4,(width/5)*5-1 ,(height/10)*3,(height/10)*4,2));
		
		b=(Ball)gameObjectsList.get(0);
		ba= (Bar)gameObjectsList.get(1);
		level++;
		onTouch=true;
	}
	
	public void Level1(){
		gameObjectsList.add(new Bricks(0,(width)/5,height/10,(height/10)*2,1));
		gameObjectsList.add(new Bricks((width/5),(width/5)*2,height/10,(height/10)*2,1));
		gameObjectsList.add(new Bricks((width/5)*2,(width/5)*3 ,height/10,(height/10)*2,1));
		gameObjectsList.add(new Bricks((width/5)*3,(width/5)*4 ,height/10,(height/10)*2,1));
		gameObjectsList.add(new Bricks((width/5)*4,(width/5)*5-1 ,height/10,(height/10)*2,1));
		
		
		gameObjectsList.add(new Bricks(0,(width)/5,(height/10)*2,(height/10)*3,2));
		gameObjectsList.add(new Bricks((width/5),(width/5)*2,(height/10)*2,(height/10)*3,2));
		gameObjectsList.add(new Bricks((width/5)*2,(width/5)*3 ,(height/10)*2,(height/10)*3,2));
		gameObjectsList.add(new Bricks((width/5)*3,(width/5)*4 ,(height/10)*2,(height/10)*3,2));
		gameObjectsList.add(new Bricks((width/5)*4,(width/5)*5-1 ,(height/10)*2,(height/10)*3,2));
		
		gameObjectsList.add(new Bricks(0,(width)/5,(height/10)*3,(height/10)*4,1));
		gameObjectsList.add(new Bricks((width/5),(width/5)*2,(height/10)*3,(height/10)*4,1));
		gameObjectsList.add(new Bricks((width/5)*2,(width/5)*3 ,(height/10)*3,(height/10)*4,1));
		gameObjectsList.add(new Bricks((width/5)*3,(width/5)*4 ,(height/10)*3,(height/10)*4,1));
		gameObjectsList.add(new Bricks((width/5)*4,(width/5)*5-1 ,(height/10)*3,(height/10)*4,1));
		
		b=(Ball)gameObjectsList.get(0);
		ba= (Bar)gameObjectsList.get(1);
		onTouch=true;
		level++;
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.onTouchEvent(event);
		if(onTouch){
			
			if (event.getAction()==event.ACTION_DOWN){
				if(event.getX() <= width/2-100){
					if(ba.x<=0){
						speed=0;
					}else {
						speed=-15;
					}
				}else if(event.getX() >= width/2+100){
					if(ba.x1>=width){
						speed=0;
					}else {
						speed=15;
					}
					
				}
				
				ba.x+=speed;
				ba.x1+=speed;
				
			}
		}
		return true;
	}

	Collision c= new Collision();
	@SuppressWarnings("static-access")
	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas) {
		if(first){
			//Gamelife=5;
			canvas.drawColor(Color.WHITE);
			width=canvas.getWidth();
			height=canvas.getHeight();
			Level1();
			Log.d("game", ""+gameObjectsList.size());
			first=false;
			
		}else {
			Log.d("game", "in Life" + Gamelife);
			//Gamelife=5;
			
			//ball.onDraw(canvas);
			//bar.onDraw(canvas);
			//ball.update();
			if(gameObjectsList.size()>2){
				//Gamelife=1;
				if(Gamelife != 0){
					paint.setColor(Color.BLUE);
					paint.setTextSize(40);
					canvas.drawText("S: "+String.valueOf(Score), width-130, 35, paint);
					paint.setColor(Color.RED);
					canvas.drawText("L: "+String.valueOf(Gamelife), 20, 35, paint);
					for(int i=0;i<gameObjectsList.size();i++){
						gameObjectsList.get(i).onDraw(canvas);
					}
					b.update();
					c.ballWithBar(gameObjectsList);
					c.ballWithBric(gameObjectsList);
				
				}
				else{
					paint.setColor(Color.RED);
					paint.setTextSize(80);
					canvas.drawText("GAME OVER", width/2-200, height/2, paint);
				}
			}else{
				try {
					
					gameThread.setRunning(false);
					gameThread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameThread.setRunning(true);
				if(level<2){
					Level2();
					ball.x=560;
					ball.y=60;
				}else{
					paint.setColor(Color.RED);
					paint.setTextSize(80);
					canvas.drawText("END", width/2-60, height/2, paint);
				}
			}
		}
		invalidate();
	}
}
