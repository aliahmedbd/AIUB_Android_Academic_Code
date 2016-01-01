package com.example.aliahmed.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Ball extends GameObject {

	int  speedX=5,speedY=5;
	int h,w;
	Boolean fst=true;
	public Ball(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Ball(int x,int y,int speed){
		super(x,y);
		speedX=speed;
		speedY=speed;
	}
	Paint paint= new Paint();
	
	@Override
	public void onDraw(Canvas canvas) {
		if(fst){
			h=canvas.getHeight();
			w=canvas.getWidth();
			fst=false;
		}
		paint.setColor(Color.RED);

		paint.setStyle(Style.FILL);
		canvas.drawCircle(x, y, 30, paint);
	}
	
	public void update(){
		x+=speedX;
		y+=speedY;
		if(x<30){
			speedX=5;
		}
		if(x>w-30){
			speedX=-5;
		}
		if(y >= h-30){
			speedY=5;
			Game.Gamelife++;
			x=w/2;
			y=h/2;
		}
		
		if(y<30){
			speedY=5;
		}
	}
}
