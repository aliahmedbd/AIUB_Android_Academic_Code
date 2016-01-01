package com.example.aliahmed.myapplication;

import java.util.ArrayList;

import android.app.ApplicationErrorReport.BatteryInfo;
import android.util.Log;

public class Collision {

	Bricks brick;
	Ball ball;
	Bar bar;
	void ballWithBar(ArrayList <GameObject> gameObjectLsit){
		
		ball=(Ball)gameObjectLsit.get(0);
		if(gameObjectLsit.size()>1){
			bar =(Bar)gameObjectLsit.get(1);
			if(bar.x-10<=ball.x && bar.x1+10>= ball.x && ball.y+30>1154){
				Log.d("Msg", "Collied");
				ball.speedY=-5;
			}
		}
	}
	
	void ballWithBric(ArrayList<GameObject> gameObjectList){
		ball=(Ball)gameObjectList.get(0);
		
		
		if(gameObjectList.size()>2){
			for(int i=2;i<gameObjectList.size();i++){
				brick=(Bricks) gameObjectList.get(i);
				if(ball.x>=brick.x-25 && ball.x<= brick.x1+25 && ball.y-30<brick.y1+3 && ball.y-30>brick.y1-3){
					//gameObjectList.remove(i);
					Game.Score+=10;
					brick.life--;
					ball.speedY=5;
				}
				
				else if(ball.x+30>=brick.x-2 && ball.x+30 <= brick.x+2 && ball.y<= brick.y1+25 && ball.y>= brick.y-25 ){
					//gameObjectList.remove(i);
					Game.Score+=10;
					brick.life--;
					ball.speedX=-5;
				}
				else if(ball.x-30<= brick.x1+2 && ball.x-30>=brick.x1-2 && ball.y<= brick.y1+25 && ball.y >= brick.y-25 ){
					//gameObjectList.remove(i);
					Game.Score+=10;
					brick.life--;
					ball.speedY=5;
					ball.speedX=5;
				}else if(ball.x>=brick.x-25 && ball.x<= brick.x1+25 && ball.y+30>=brick.y-2 && ball.y+30<=brick.y+2){
					//gameObjectList.remove(i);
					Game.Score+=10;
					brick.life--;

					ball.speedY=-5;
					ball.speedX=-5;
				}
				if(brick.life==0){
					gameObjectList.remove(i);
				}
			}
		}
		
	}
}
