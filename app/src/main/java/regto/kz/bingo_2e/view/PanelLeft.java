package regto.kz.bingo_2e.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import regto.kz.bingo_2e.R;
import regto.kz.bingo_2e.controller.Params;
import regto.kz.bingo_2e.controller.TimeService;

public class PanelLeft extends FrameLayout {

    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    private static int MIN_DISTANCE_X = Params.MIN_DISTANCE_X;

    private Rect own_Panel;
    private RelativeLayout own_prnt;
    private View view_Panel;


    public PanelLeft(Context context, RelativeLayout RL, Rect rect) {
        super(context);
        init(context, RL, rect);
    }

    private void init(final Context context, RelativeLayout RL, Rect rect) {

        own_Panel = rect;
        own_prnt = RL;

        //Надули
        View vw = inflate(context, R.layout.panel_left, null);
        view_Panel = vw;

        ImageButton IB = (ImageButton)view_Panel.findViewById(R.id.start_button);
        IB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startService(new Intent(context, TimeService.class));
            }
        });

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
                        if (Math.abs(deltaX) > MIN_DISTANCE_X / 3) {
                            if (own_Panel.contains((int) x1, (int) y1)) {
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
        LayoutParams params = new LayoutParams(Math.abs(rect.left - rect.right), Math.abs(rect.top - rect.bottom));
        params.leftMargin = rect.left;
        params.topMargin = rect.top;
        RL.addView(vw, params);
        AnimateView();
    }

    public void HideAndRemove_Panel(){
        this.bringToFront();
        Animation push_up = AnimationUtils.loadAnimation(getContext(), R.anim.push_up_out);
        view_Panel.startAnimation(push_up);
        view_Panel.setVisibility(View.INVISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                own_prnt.removeView(view_Panel);
                own_prnt.findViewById(R.id.button_leftpanel).setVisibility(View.VISIBLE);
            }
        }, 310);

    }
    public void AnimateView(){
        Animation push_in = AnimationUtils.loadAnimation(getContext(), R.anim.push_up_in);
        view_Panel.startAnimation(push_in);
    }

}
