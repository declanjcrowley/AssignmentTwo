package ie.cit.djcit.assignmenttwo;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import ie.cit.djcit.dialog.ListDialog;
import ie.cit.djcit.dialog.NotificationFragment;


public class BaseActivity extends AppCompatActivity implements NotificationFragment.dialogListener, ListDialog.ListDialogListener{

    String TAG = "test";
    BaseView baseView;

    Button polyButton;
    Button lineButton;
    int eventFlag = 0;

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_base);

        LayoutInflater inflater = getLayoutInflater();
        baseView = (BaseView) inflater.inflate(R.layout.view_base, null);
        setContentView(baseView);

        lineButton = (Button) findViewById(R.id.bttnLine);
        lineButton.setTag(0);
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((int) lineButton.getTag() == 0) {
                    lineButton.setText(R.string.finish);
                    eventFlag = 12;
                    lineButton.setTag(1);
                    BaseActivity.this.findViewById(R.id.bttnPolygon).setVisibility(View.INVISIBLE);
                } else if ((int) lineButton.getTag() == 1) {
                    polyButton.setText(R.string.ok);
                    eventFlag = 0;
                    lineButton.setTag(2);
                } else {
                    lineButton.setTag(0);
                    lineButton.setText(R.string.line);
                    BaseActivity.this.findViewById(R.id.bttnPolygon).setVisibility(View.VISIBLE);
                    Log.d(TAG,"line finish 1");
                    baseView.invalidate();
                    Log.d(TAG,"line finish 2");
                }
            }
        });

        polyButton = (Button) findViewById(R.id.bttnPolygon);
        polyButton.setTag(0);
        polyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((int) polyButton.getTag() == 0) {
                    polyButton.setText(R.string.finish);
                    polyButton.setTag(1);
                    eventFlag = 12;
                    BaseActivity.this.findViewById(R.id.bttnLine).setVisibility(View.INVISIBLE);
                } else if ((int) polyButton.getTag() == 1) {
                    polyButton.setText(R.string.ok);
                    eventFlag = 0;
                    polyButton.setTag(2);
                } else {
                    polyButton.setText(R.string.polygon);
                    polyButton.setTag(0);
                    BaseActivity.this.findViewById(R.id.bttnLine).setVisibility(View.VISIBLE);
                    Log.d(TAG,"poly finish 1");
                    baseView.invalidate();
                    Log.d(TAG,"poly finish 2");
                }
            }
        });

        seekBar = (SeekBar) findViewById(R.id.simpleSeekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(BaseActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void notificationListener(DialogFragment dialog) {
        android.util.Log.d(TAG, "BA.notificationListener");
    }

    public void listDialogSelection(DialogFragment dialog, String string){
        android.util.Log.d(TAG, "BA.listDialogSelection");
    }

    public void onClickAlertBtn(View view) {
        android.util.Log.d(TAG, "BA.onClickAlertBtn");
        ListDialog listDialog = ListDialog.newInstance();
        listDialog.show(getFragmentManager(), "dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        android.util.Log.d(TAG, "BA.onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        android.util.Log.d(TAG, "BA.onOptionsSelected");
        switch (item.getItemId()) {
            case R.id.one:
                return true;
            case R.id.two:
                return true;
            case R.id.three:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {

            float[] pos = new float[2];

            if(event.getAction() == MotionEvent.ACTION_DOWN){
                pos[0] = event.getX() - 0;
                pos[1] = event.getY() - 80;
                event.setLocation(pos[0], pos[1]);
                android.util.Log.d(TAG,"BA.oTE " + pos[0] + ":" + pos[1]);
            } else if(event.getAction() == MotionEvent.ACTION_UP){
                pos[0] = event.getX() - 0;
                pos[1] = event.getY() - 80;
                event.setLocation(pos[0], pos[1]);
                android.util.Log.d(TAG,"BA.oTE " + pos[0] + ":" + pos[1]);
            }

            return true;
        }catch(Exception e){
            android.util.Log.d(TAG, "BA.oTE " + e.toString());
            return false;
        }
    }
}
