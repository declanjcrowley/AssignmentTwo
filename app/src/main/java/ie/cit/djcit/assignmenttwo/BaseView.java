package ie.cit.djcit.assignmenttwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class BaseView extends RelativeLayout{

    String TAG = "test";
    int x = 10;
    int y = 10;
    int r = 400;
    Paint mPaint = new Paint();

    public BaseView(Context context, AttributeSet attr) {
        super(context, attr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addView(inflater.inflate(R.layout.activity_base, null));
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Log.d(TAG, "BV.onDraw ");
        mPaint.setColor(Color.RED);
        canvas.drawCircle(x,y,r,mPaint);
    }
}
