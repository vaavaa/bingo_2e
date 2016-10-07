/**
 *
 */
package regto.kz.bingo_2e.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.LinkedList;

import regto.kz.bingo_2e.App;
import regto.kz.bingo_2e.MainActivity;
import regto.kz.bingo_2e.R;
import regto.kz.bingo_2e.controller.GameObjects;
import regto.kz.bingo_2e.controller.MainThread;
import regto.kz.bingo_2e.model.Droid;


public class MainGamePanel extends SurfaceView implements
        SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private MainThread thread;
    private LinkedList<GameObjects> gObjects = new LinkedList<>();
    private App app;
    private Bitmap scaledBG;
    private Paint bitmapPaint;

    public MainGamePanel(Context context) {
        super(context);

        getHolder().setFormat(PixelFormat.TRANSPARENT);
        //getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);    // necessary
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);

        // create the game loop thread
        thread = new MainThread(getHolder(), this);

        app = ((App) ((MainActivity) context).getApplication());
        //set main thread in global variable
        app.setMainThreadLink(thread);

        // make the GamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.board_0);
        //float scale = (float)background.getWidth()/(float)getWidth();
        //int newWidth = Math.round(background.getWidth()/scale);
        //int newHeight = Math.round(background.getHeight()/scale);
        int newWidth = getWidth();
        int newHeight = getHeight();
        scaledBG = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
        bitmapPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
        setWillNotDraw(false);

        holder.setFixedSize(newWidth, newHeight);


        // at this point the surface is created and
        // we can safely start the game loop
        //if it is the first time the thread starts
        if (thread.getState() == Thread.State.NEW) {
            thread.setRunning(true);
            thread.start();
        }
        //after a pause it starts the thread again
        else if (thread.getState() == Thread.State.TERMINATED) {
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start(); // Start a new thread
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");
        // tell the thread to shut down and wait for it to finish
        // this is a clean shutdown
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Droid droid;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            // create droid and load bitmap
            droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), (int) event.getX(), (int) event.getY());
            gObjects.add(droid);

            // delegating event handling to the droid
            //droid.handleActionDown((int) event.getX(), (int) event.getY());
            //Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // the gestures
//            if (droid.isTouched()) {
//                // the droid was picked up and is being dragged
//                droid.setX((int) event.getX());
//                droid.setY((int) event.getY());
//            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // touch was released
//            if (droid.isTouched()) {
//                droid.setTouched(false);
//            }
        }
        return true;
    }

    public void render(Canvas canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(scaledBG, 0, 0, bitmapPaint); // draw the background
        if (gObjects.size() > 0) {
            for (int i = 0; i < gObjects.size(); i++) {
                gObjects.get(i).draw(canvas);
            }
        }
    }

    /**
     * This is the game update method. It iterates through all the objects
     * and calls their update method if they have one or calls specific
     * engine's update method.
     */
    public void update() {
        if (gObjects.size() > 0) {
            for (int i = 0; i < gObjects.size(); i++) {
                gObjects.get(i).update();
            }
        }
    }

}
