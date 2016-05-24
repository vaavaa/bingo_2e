package regto.kz.bingo_2e.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import regto.kz.bingo_2e.R;

public class PanelLeft extends FrameLayout {

    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    private static int MIN_DISTANCE_X = 200;
    private static int MIN_DISTANCE_Y = 200;

    private Rect own_Panel;
    private RelativeLayout own_prnt;


    public PanelLeft(Context context, RelativeLayout RL, Rect rect) {
        super(context);
        init(context, RL, rect);
    }

    private void init(Context context, RelativeLayout RL, Rect rect) {

        own_Panel = rect;
        own_prnt = RL;

        //Надули
        View vw = inflate(context, R.layout.panel_left, null);

        LayoutParams params = new LayoutParams(Math.abs(rect.left - rect.right), Math.abs(rect.top - rect.bottom));
        params.leftMargin = rect.left;
        params.topMargin = rect.bottom;
        RL.addView(vw, params);
    }

}
