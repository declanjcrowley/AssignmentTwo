package ie.cit.djcit.assignmenttwo;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ie.cit.djcit.dialog.ListDialog;
import ie.cit.djcit.dialog.NotificationFragment;


public class BaseActivity extends AppCompatActivity implements NotificationFragment.dialogListener, ListDialog.ListDialogListener{

    String TAG = "test";

    Button polyButton;
    Button lineButton;
    ToggleButton tgglBtn;
    int eventFlag = 0;

    private SeekBar seekBar;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        setContentView(R.layout.activity_base);


        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float f1, float f2){
                Intent intentsa = new Intent(getApplicationContext(), PaintActivity.class);
                int color = setTgglBtnColor( tgglBtn.isChecked() );
                intentsa.putExtra("color", color);
                startActivity(intentsa);
                finish();
                return true;
            }
        });

        downLoadService.start();

        tgglBtn = (ToggleButton) findViewById(R.id.tgglBtn);
        tgglBtn.setChecked(true);


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

    public int setTgglBtnColor(boolean b){

        if(tgglBtn.isChecked()){
            Log.d(TAG,"BA.tggl red : " + R.color.coolRed);
            return R.color.coolRed;

        }else {
            Log.d(TAG,"BA.tggl green : " + R.color.coolGreen );
            return R.color.green;
        }
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
            case R.id.paint:
                Intent intentsa = new Intent(getApplicationContext(), PaintActivity.class);
                int color = setTgglBtnColor( tgglBtn.isChecked() );
                intentsa.putExtra("color", color);
                startActivity(intentsa);
                finish();
                return true;
            case R.id.two:
                //downloadRSSFeed();
                return true;
            case R.id.three:
                Toast.makeText(BaseActivity.this, "Unused menu option",
                        Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {

            gestureDetector.onTouchEvent(event);

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


    Thread downLoadService = new Thread(new Runnable() {


        // After call for background.start this run method call
        public void run() {
            try {


                Log.d(TAG, "Thread  0 " );
                URL url = new URL("https://www.rte.ie/news/rss/news-headlines.xml");//http://syndication.indianexpress.com/rss/latest-news.xml");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                Log.d(TAG, "Thread  1 " );
                con.connect();

                Log.d(TAG, "Thread  2 " );
                InputStream is = con.getInputStream();
                Log.d(TAG, isStream(is));


            } catch (Throwable t) {
                // just end the background thread
                Log.d(TAG, "Thread  exception " + t);                       }
        }



    });

    String isStream(InputStream is){
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"), 8 * 1024);
                while ((line = reader.readLine()) != null) {
                    sb.append(line);// .append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Log.d(TAG,"BA.downloadRSSFeed " + sb.toString());
            return sb.toString();
        }
        return "qw";
    }
}
