package regto.kz.bingo_2e.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import regto.kz.bingo_2e.R;

public class PanelRight extends FrameLayout {

    static final int MIN_DISTANCE = 100;


    public PanelRight(Context context){
        super(context);
        init(context);
    }

    public PanelRight(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public PanelRight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(final Context ct) {
        FrameLayout Relative_Lock = (FrameLayout)inflate(getContext(), R.layout.panel_right, null);
        this.addView(Relative_Lock);
    }
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        float x1=0;
        float x2=0;
        boolean handled = false;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                handled=true;
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    //Do what we need to do

                }
                handled=true;
                break;
            default:
                // do nothing
                break;
        }
        return super.onTouchEvent(event) || handled;
    }

}
