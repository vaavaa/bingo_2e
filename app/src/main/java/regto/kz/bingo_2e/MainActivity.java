package regto.kz.bingo_2e;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import regto.kz.bingo_2e.controller.MainWindowTouchEventController;
import regto.kz.bingo_2e.view.MainGamePanel;
import regto.kz.bingo_2e.controller.MainThread;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set horizontal landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "View added");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Destroying...");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopping...");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Pausing…");
        MainThread.running = false;//this is the value for stop the loop in the run()
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
