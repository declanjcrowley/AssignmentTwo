package ie.cit.djcit.assignmenttwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PaintView extends View {

    String TAG = "test";
    int x = 10;
    int y = 10;
    int r = 400;
    Paint mPaint = new Paint();
    private int color = 0;

    public PaintView(Context context){
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Log.d(TAG, "BV.onDraw " + getColor());
        mPaint.setColor( getColor() );
        canvas.drawCircle(x,y,r,mPaint);
    }

    public void setColor(int c){color = c;}

    public int getColor(){return color;}
}
