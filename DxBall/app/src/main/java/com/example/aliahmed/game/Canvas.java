package com.example.aliahmed.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Ahmed on 12/17/2015.
 */
class MyCanvas extends View implements Runnable{


   public static boolean gameOver;
    public static boolean newLife;
    public static int life;

    public  static int canvasHeight,canvasWidth;
    Paint paint;
    float barWidth=200;
    float brickX=0, brickY=50;
    int score=0;
    Ball myBall;
    Bar myBar;
    float left,right,top,bottom;
    float downX,downY,upX,upY;
    boolean leftPos,rightPos,first=true;
    int min_distance=50;
    int ballSpeed;
    public static int checkWidth=0;
    Bricks brick1,brick2,brick3,brick4,brick5,brick6,brick7,brick8,brick9,brick10,brick11,brick12,brick13,brick14,brick15;

    Bricks a1,a2;
    ArrayList<Bricks> bricks=new ArrayList<Bricks>();


    public MyCanvas(Context context) {
        super(context);
        paint=new Paint();
        //myBall.move();
        myBar=new Bar();
        life=3;
        gameOver=false;
        newLife=true;

    }


//Draw ca
    @Override
    protected void onDraw(Canvas canvas) {

        canvasHeight=canvas.getHeight();
        canvasWidth=canvas.getWidth();
        if(first==true) {
            first=false;
            for(int i=0; i<15; i++){
                int c;
                if(brickX>=canvas.getWidth()) {
                    brickX = 0;
                    brickY += 30;
                }
                if(i%2==0)
                    c = Color.RED;
                else
                    c = Color.BLACK;
                bricks.add(new Bricks(brickX,brickY,brickX+canvas.getWidth()/5,brickY+30,c));
                brickX+=canvas.getWidth()/5;
            }
            myBall=new Ball(canvas.getWidth()/2,canvas.getHeight()/2,Color.RED,20);
            myBall.bounce(canvas);

            left = getWidth()/2-(barWidth/2);
            top = getHeight() - 20;
            right = getWidth()/2+(barWidth/2);
            bottom = getHeight();

            myBar.setBottom(bottom);
            myBar.setLeft(left);
            myBar.setRight(right);
            myBar.setTop(top);
            checkWidth = canvas.getWidth();

            myBall.setDx(4);
            myBall.setDy(4);
            Log.d("", bricks.size() + "");

        }
        if(newLife){
            newLife = false;
            //new ball
            ballSpeed = 5;
            myBall=new Ball(canvas.getWidth()/2,canvas.getHeight()-50,Color.GREEN,20);
            myBall.setDx(ballSpeed);
            myBall.setDy(-ballSpeed);
        }

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        //Ball
        canvas.drawCircle(myBall.getX(), myBall.getY(), myBall.getRadius(), myBall.getPaint());
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Score: "+score,10,30,paint);

        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: "+life,canvas.getWidth()-110,40,paint);


        //Bar
        canvas.drawRect(myBar.getLeft(), myBar.getTop(), myBar.getRight(), myBar.getBottom(), myBar.getPaint());

        //bricks
        for(int i=0;i<bricks.size();i++){
            canvas.drawRect(bricks.get(i).getLeft(),bricks.get(i).getTop(),bricks.get(i).getRight(),bricks.get(i).getBottom(),bricks.get(i).getPaint());
        }

        if(gameOver){
            paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

            paint.setColor(Color.RED);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("GAME OVER",canvas.getWidth()/2-110,canvas.getHeight()/2,paint);
            canvas.drawText("FINAL SCORE: "+score,canvas.getWidth()/2-150,canvas.getHeight()/2+60,paint);
            gameOver = false;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((GameCanvas)getContext()).finish();
        }


        this.ballBrickCollision(bricks,myBall,canvas);
        this.ballBarCollision(myBar,myBall, canvas);
        myBall.ballBoundaryChech(canvas);

        myBall.move();

        myBar.moveBar(leftPos,rightPos);
        this.run();


    }
    public void ballBarCollision(Bar myBar,Ball myBall,Canvas canvas){
        if(((myBall.getY()+myBall.getRadius())>=myBar.getTop())&&((myBall.getY()+myBall.getRadius())<=myBar.getBottom())&& ((myBall.getX())>=myBar.getLeft())&& ((myBall.getX())<=myBar.getRight())) {
            myBall.setDy(-(myBall.getDy()));

        }

    }
    public void ballBrickCollision(ArrayList<Bricks> br ,Ball myBall,Canvas canvas){
        for(int i=0;i<br.size();i++) {
            if (((myBall.getY() - myBall.getRadius()) <= br.get(i).getBottom()) && ((myBall.getY() + myBall.getRadius()) >= br.get(i).getTop()) && ((myBall.getX()) >= br.get(i).getLeft()) && ((myBall.getX()) <= br.get(i).getRight())) {
               br.remove(i);
                score+=1;
                myBall.setDy(-(myBall.getDy()));
            }
        }

    }


//Moving Bar
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                downX=event.getX();
                downY=event.getY();
                return true;

            }
            case MotionEvent.ACTION_UP:{
                upX=event.getX();
                upY=event.getY();

                float deltaX=downX-upX;
                float deltaY=downY-upY;

                if(Math.abs(deltaX) > Math.abs(deltaY)){
                    if(Math.abs(deltaX) > min_distance) {
                        if (deltaX < 0) {
                            //left=left+100;
                            //right=right+100;
                            leftPos=true;
                            rightPos=false;
                            myBar.moveBar(leftPos, rightPos);
                            return true;
                        }
                        if (deltaX > 0) {
                            leftPos=false;
                            rightPos=true;
                            myBar.moveBar(leftPos,rightPos);
                            //Right to left
                            return true;

                        }
                    }
                    else{
                        return  false;
                    }
                }
                else{
                    if(Math.abs(deltaY) > min_distance) {
                        if (deltaY < 0) {
                            //top to bottom
                            return true;
                        }
                        if (deltaY > 0) {
                            //bottom to top
                            return true;

                        }
                    }
                    else{
                        return  false;
                    }
                }

            }

        }
        return super.onTouchEvent(event);
    }



    @Override
    public void run() {

        invalidate();
    }
    public void collision(){

    }

}
