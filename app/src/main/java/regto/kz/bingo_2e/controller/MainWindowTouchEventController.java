package regto.kz.bingo_2e.controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import regto.kz.bingo_2e.R;
import regto.kz.bingo_2e.view.MainGamePanel;
import regto.kz.bingo_2e.view.PanelLeft;
import regto.kz.bingo_2e.view.PanelRight;

public class MainWindowTouchEventController extends RelativeLayout {

    private static int MIN_DISTANCE_X = Params.MIN_DISTANCE_X;
    private static int MIN_DISTANCE_Y = Params.MIN_DISTANCE_Y;

    private Rect l_rect = new Rect();
    private Rect r_rect = new Rect();
    private Rect t_rect = new Rect();
    private Rect b_rect = new Rect();

    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    private Context cnx;
    private RelativeLayout mainView;

    public MainWindowTouchEventController(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public MainWindowTouchEventController(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MainWindowTouchEventController(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        int mheight = 0;
        int mwidth = 0;

        //Сохранили контекст
        cnx = context;

        //Надули
        View vw = inflate(context, R.layout.activity_main_controller, null);
        mainView = (RelativeLayout) vw;

        LinearLayout llsf = (LinearLayout) vw.findViewById(R.id.gmeSurface);
        llsf.addView(new MainGamePanel(context));
        addView(vw);


        mheight = getScreenHeight();
        mwidth = getScreenWidth();

        l_rect.set(0, 0, MIN_DISTANCE_X, mheight);
        r_rect.set(mwidth - MIN_DISTANCE_X, 0, mwidth, mheight);
        t_rect.set(0, 0, mwidth, MIN_DISTANCE_Y);
        b_rect.set(0, mheight - MIN_DISTANCE_Y, mwidth, mheight);

        findViewById(R.id.button_leftpanel).setOnClickListener(mPanelListener);
        findViewById(R.id.button_rightpanel).setOnClickListener(mPanelListener);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

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
                    if (l_rect.contains((int) x1, (int) y1)) CreateLeftPanel();
                    if (r_rect.contains((int) x1, (int) y1))
                        Toast.makeText(getContext(), "Right Rect", Toast.LENGTH_SHORT).show();
                }
                if (Math.abs(deltaY) > MIN_DISTANCE_Y / 3) {

                    if (t_rect.contains((int) x1, (int) y1))
                        Toast.makeText(getContext(), "Top Rect", Toast.LENGTH_SHORT).show();
                    if (b_rect.contains((int) x1, (int) y1))
                        Toast.makeText(getContext(), "Bottom Rect", Toast.LENGTH_SHORT).show();
                }
                handled = true;
                break;
            default:
                // do nothing
                break;
        }
        return super.onTouchEvent(event) || handled;
    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener mPanelListener = new View.OnClickListener() {
        public void onClick(View v) {
            v.setVisibility(View.INVISIBLE);
            if (v.getId() == R.id.button_leftpanel) {
                CreateLeftPanel();
            }
            if (v.getId() == R.id.button_rightpanel) {
                CreateRightPanel();
            }

        }
    };

    private int getResourceByID(String ResType, String ResName) {
        Resources resources = getContext().getResources();
        return resources.getIdentifier(ResName, ResType,
                getContext().getPackageName());
    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private void CreateLeftPanel() {
        if (mainView.findViewById(R.id.l_panel) == null) {
            PanelLeft pl = new PanelLeft(cnx, mainView, l_rect);
            invalidate();
        }
    }

    private void CreateRightPanel() {
        if (mainView.findViewById(R.id.r_panel) == null) {
            PanelRight pr = new PanelRight(cnx, mainView, l_rect);
            invalidate();
        }
    }

}