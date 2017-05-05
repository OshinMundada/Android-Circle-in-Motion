package com.example.oshinmundada.movingcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

/**
 * Created by oshinmundada on 26/02/17.
 */


public class SetClass extends View {

    private static final float rad = 50f;

    private Draw circ;
    private List<Draw> circopt = new ArrayList<>();
    private float x = 0f;
    private float y = 0f;
    private float x1 = 0f;
    private float y1 = 0f;
    private float radius = 0f;
    private VelocityTracker speed;
    private Paint paintwith;
    private PointF pointThis;
    private PointF delpoint;
    private PointF mvpoint;
    private String modes;
    private String touch;
    private float SpeedX = 0f;
    private float SpeedY = 0f;

    public SetClass(Context context) {
        super(context);

        paintwith = new Paint();
    }

    public SetClass(Context context, AttributeSet attrSet) {
        super(context, attrSet);
    }

    public void setColor(String color) {
        if (color == "blue")
            paintwith.setColor(Color.BLUE);
        else if (color == "green")
            paintwith.setColor(Color.GREEN);
        else if (color == "red")
            paintwith.setColor(Color.RED);
        else if (color == "black")
            paintwith.setColor(Color.BLACK);
    }

    public void setModes(String mode) {
        modes = mode;
        delpoint = new PointF();
        mvpoint = new PointF();
        if (modes == "move") {
            speed = null;
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointThis = new PointF(event.getX(), event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch = "DOWN";
                if (modes == "draw") {
                    circ = new Draw(pointThis);
                    circ.setColor(paintwith.getColor());
                } else if (modes == "move") {
                    speed = VelocityTracker.obtain();
                    speed.addMovement(event);
                    mvpoint = pointThis;
                    invalidate();
                } else if (modes == "delete") {
                    delpoint = pointThis;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                touch = "MOVE";
                if (modes == "draw") {
                    if (circ != null) {
                        circ.setCurr(pointThis);
                        circopt.add(circ);
                        invalidate();
                    }
                } else if (modes == "move") {
                    speed.addMovement(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                touch = "UP";
                if (modes == "draw") {
                    if (circ != null) {
                        circ.setCurr(pointThis);
                        circopt.add(circ);
                        invalidate();
                    }
                } else if (modes == "move") {
                    speed.addMovement(event);
                    speed.computeCurrentVelocity(1);
                    SpeedX = speed.getXVelocity();
                    SpeedY = speed.getYVelocity();
                    invalidate();
                } else if (modes == "delete") {
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                circ = null;
                speed.clear();
                break;
            default:
                circ = null;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Iterator<Draw> circleit = circopt.iterator();
        while (circleit.hasNext()) {
            Draw circle = circleit.next();
            x = circle.startPoint().x;
            y = circle.startPoint().y;

            if (modes == "move") {
                if (touch == "UP") {
                    float distance = (float) (Math.sqrt(Math.pow((mvpoint.x - x), 2) + Math.pow((mvpoint.y - y), 2)));
                    if (distance <= circle.getRadius() && !circle.checkMoveFlag()) {
                        circle.setMoveFlag(true);
                        circle.setSpeedX(0);
                        circle.setSpeedY(0);
                    } else if (!circle.checkMoveFlag()) {
                        circle.setMoveFlag(false);
                    }
                    if (!circle.moveFlag() && circle.checkMoveFlag()) {
                        circle.setSpeedX(SpeedX);
                        circle.setSpeedY(SpeedY);
                        circle.setmoveFlag(true);
                    }
                }
            } else {
                circle.setmoveFlag(false);
                circle.setMoveFlag(false);
            }


            if (modes == "move"
                    && circle.moveFlag()) {
                float circleXVelocity = circle.getSpeedX();
                float circleYVelocity = circle.getSpeedY();
                radius = circle.getRadius();
                if (x - radius + circleXVelocity <= 0 || x + radius + circleXVelocity >= canvas.getWidth()) {
                    circleXVelocity = -circleXVelocity;
                    circle.setSpeedX(circleXVelocity);
                } else if (y - radius + circleYVelocity <= 0 || y + radius + circleYVelocity >= canvas.getHeight()) {
                    circleYVelocity = -circleYVelocity;
                    circle.setSpeedY(circleYVelocity);
                }
                circle.getStart(new PointF(x + circleXVelocity, y + circleYVelocity));

            } else {
                x1 = circle.This().x;
                y1 = circle.This().y;
                if (x == x1 && y == y1) {
                    radius = rad;
                } else
                    radius = (float) (Math.sqrt(Math.pow((x1 - x), 2) + Math.pow((y1 - y), 2)));
                if (x - radius >= 0
                        && y - radius >= 0
                        && x + radius <= canvas.getWidth()
                        && y + radius <= canvas.getHeight())
                    circle.setRadius(radius);
                else
                    radius = circle.getRadius();
            }

            if (modes == "delete") {
                float initialPointDistance = (float) (Math.sqrt(Math.pow((delpoint.x - x), 2) + Math.pow((delpoint.y - y), 2)));
                float endPointDistance = (float) (Math.sqrt(Math.pow((pointThis.x - x), 2) + Math.pow((pointThis.y - y), 2)));
                if (initialPointDistance <= circle.getRadius() && endPointDistance <= circle.getRadius()) {
                    circleit.remove();
                    continue;
                }
            }

            paintwith.setColor(circle.getColor());
            canvas.drawCircle(circle.startPoint().x, circle.startPoint().y, radius, paintwith);
        }
        touch = "";
        if (modes == "move")
            invalidate();
    }
}
