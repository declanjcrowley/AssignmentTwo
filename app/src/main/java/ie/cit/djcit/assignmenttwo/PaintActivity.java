package ie.cit.djcit.assignmenttwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class PaintActivity extends Activity {

    private String TAG = "test";
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaintView paintView = new PaintView(getBaseContext());
        setContentView(paintView);

        int color = getIntent().getExtras().getInt("color");
        Log.d(TAG,"color : " + color);
        paintView.setColor(getResources().getColor(color));

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float f1, float f2){

                Intent intentsa = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(intentsa);
                finish();
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {

            return gestureDetector.onTouchEvent(event);

        }catch(Exception e){
            android.util.Log.d(TAG, "BA.oTE " + e.toString());
            return false;
        }
    }
}
