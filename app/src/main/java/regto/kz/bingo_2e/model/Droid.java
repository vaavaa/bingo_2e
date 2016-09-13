/**
 * 
 */
package regto.kz.bingo_2e.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;

import regto.kz.bingo_2e.controller.GameObjects;
import regto.kz.bingo_2e.view.DrawingObjects.SplashDrawable;


/**
 * This is a test droid that is dragged, dropped, moved, smashed against
 * the wall and done other terrible things with.
 * Wait till it gets a weapon!
 * 
 * @author impaler
 *
 */
public class Droid extends GameObjects{
	private Bitmap bitmap;	// the actual bitmap
	private Bitmap init_bitmap;	// the actual bitmap
	private int x;			// the X coordinate
	private int y;			// the Y coordinate
	private int x_pos;			// the X coordinate for chip place
	private int y_pos;			// the Y coordinate for chip place
	private boolean touched;	// if droid is touched/picked up
	private SplashDrawable AnimatedSplash;
	private Speed speed;
	private String label;
	private int value;
	private int dstWidth;
	private int	dstHeight;
	private Paint mPaint;


	public Droid(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.init_bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.AnimatedSplash = new SplashDrawable(x,y,3);
		this.speed = new Speed();
		dstHeight = bitmap.getHeight()*3;
		dstWidth = bitmap.getWidth()*3;
		mPaint = new Paint();
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public void draw(Canvas canvas) {
		AnimatedSplash.draw(canvas);
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), mPaint);
	}


	public int getX_pos() {
		return x_pos;
	}

	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}

	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}


	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Method which updates the droid's internal state every tick
	 */
	public void update() {
		bitmap = Bitmap.createScaledBitmap(init_bitmap, dstWidth, dstHeight, false);

		if (init_bitmap.getHeight()> bitmap.getHeight()) AnimatedSplash.update();
		else {

			dstWidth = dstWidth - (int)dstWidth/3;
			dstHeight = dstHeight - (int)dstHeight/3;
		}
		if (!touched) {
//			x += (speed.getXv() * speed.getxDirection());
//			y += (speed.getYv() * speed.getyDirection());
		}
	}
	
	
	/**
	 * Handles the {@link MotionEvent ACTION_DOWN} event. If the event happens on the
	 * bitmap surface then the touched state is set to <code>true</code> otherwise to <code>false</code>
	 * @param eventX - the event's X coordinate
	 * @param eventY - the event's Y coordinate
	 */
	public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bitmap.getWidth()) && (eventX <= (x + bitmap.getWidth()))) {
			if (eventY >= (y - bitmap.getHeight()) && (y <= (y + bitmap.getHeight()))) {
				// droid touched
				setTouched(true);
			} else {
				setTouched(false);
			}
		} else {
			setTouched(false);
		}

	}
}
