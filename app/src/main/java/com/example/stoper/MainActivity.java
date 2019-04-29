package com.example.stoper;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private boolean running;
    private int mseconds=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)//jesli to musialo sie wykonac to zamknalem aktywnosc i jest rozne od null czyli cos tam jest
        {//jesli cos tam jest to biore te rzeczy z bundla
            running=savedInstanceState.getBoolean("running",running);
            mseconds = savedInstanceState.getInt("mseconds",mseconds);
        }
        runTimer();
    }


    public void onClickStart(View view)
    {
        running = true;
    }
    public void onClickStop(View view)
    {
        running = false;
    }
    public void onClickReset(View view)
    {
        running = false;
        mseconds=0;


    }

    private  void runTimer()
    {
        final Handler handler = new Handler();
        final TextView timeview = (TextView)findViewById(R.id.time_view);

        handler.post(new Runnable() {//funkcja z tego wykonuje sie odrazu
            @Override
            public void run() {
                int hours = mseconds/36000;
                int minutes = (mseconds/600)%60;
                int secs = (mseconds/10)%60;
                int ms = mseconds%10;
                String time = String.format("%d:%02d:%02d:%d",hours,minutes,secs,ms);

                timeview.setText(time);
                if(running)
                {
                    mseconds++;
                }

                handler.postDelayed(this,100);//ile ma czekac
            }

        });

    }
    @Override
    protected  void onSaveInstanceState(Bundle savedInstanceState) {//jesli chcialem wylaczyc aktywnosc to zapisuja mi sie tu rzeczy
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("mseconds",mseconds);
        savedInstanceState.putBoolean("running",running);
    }
}
