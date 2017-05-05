package com.example.oshinmundada.movingcircle;


import android.graphics.PointF;

/**
 * Created by oshinmundada on 26/02/17.
 */


public class Draw {

    protected float radius;
    protected int paint;
    protected float SpeedX;
    protected float SpeedY;
    protected PointF startPoint;
    protected PointF currpoint;


    protected boolean canMove;
    protected boolean isMoving;

    public Draw() {
    }

    //
    public Draw(PointF point) {
        startPoint = point;
        currpoint = point;
        radius = 0f;
        canMove = false;
        isMoving = false;
        SpeedX = 0f;
        SpeedY = 0f;
    }

    public PointF startPoint() {
        return startPoint;
    }

    public void getStart(PointF point) {
        startPoint = point;
    }

    public PointF This() {
        return currpoint;
    }

    public void setCurr(PointF point) {
        currpoint = point;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float circleRadius) {
        radius = circleRadius;
    }

    public boolean checkMoveFlag() {
        return canMove;
    }

    public void setMoveFlag(boolean isInMovableRegion) {
        canMove = isInMovableRegion;
    }

    public boolean moveFlag() {
        return isMoving;
    }

    public float getSpeedX() {
        return SpeedX;
    }

    public void setSpeedX(float xVelocityValue) {
        SpeedX = xVelocityValue;
    }

    public float getSpeedY() {
        return SpeedY;
    }

    public void setSpeedY(float yVelocityValue) {
        SpeedY = yVelocityValue;
    }

    public void setmoveFlag(boolean isAlreadyMoving) {
        isMoving = isAlreadyMoving;
    }

    public int getColor() {
        return paint;
    }

    public void setColor(int color) {
        paint = color;
    }
}
