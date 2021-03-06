package regto.kz.bingo_2e.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import regto.kz.bingo_2e.R;
import regto.kz.bingo_2e.controller.Params;

public class PanelTop extends FrameLayout {

    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    private static int MIN_DISTANCE_Y = Params.MIN_DISTANCE_Y;

    private Rect own_Panel;
    private RelativeLayout own_prnt;
    private View view_Panel;


    public PanelTop(Context context, RelativeLayout RL, Rect rect) {
        super(context);
        init(context, RL, rect);
    }

    private void init(final Context context, RelativeLayout RL, Rect rect) {

        own_Panel = rect;
        own_prnt = RL;

        //Надули
        View vw = inflate(context, R.layout.panel_top, null);
        vw.bringToFront();
        view_Panel = vw;


        vw.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                boolean handled = false;
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        handled = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();
                        float deltaX = x2 - x1;
                        float deltaY = y2 - y1;
                        if (Math.abs(deltaY) > MIN_DISTANCE_Y / 3) {
                            if (own_Panel.contains((int)(own_Panel.left + x1), (int)(own_Panel.top+ y1))) {
                                HideAndRemove_Panel();
                            }
                        }
                        handled = true;
                        break;
                    default:
                        // do nothing
                        break;
                }
                return handled;
            }
        });
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Math.abs(rect.left - rect.right), Math.abs(rect.top - rect.bottom));
        params.topMargin = rect.top;
        params.setMarginStart(0);
        own_prnt.addView(vw, params);
        AnimateView();
    }

    public void HideAndRemove_Panel(){
        this.bringToFront();
        Animation push_up = AnimationUtils.loadAnimation(getContext(), R.anim.push_up_out_top);
        view_Panel.startAnimation(push_up);
        view_Panel.setVisibility(View.INVISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                own_prnt.removeView(view_Panel);
                own_prnt.findViewById(R.id.button_toppanel).setVisibility(View.VISIBLE);
            }
        }, 310);

    }
    public void AnimateView(){
        Animation push_in = AnimationUtils.loadAnimation(getContext(), R.anim.push_up_in_top);
        view_Panel.startAnimation(push_in);
    }
    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
