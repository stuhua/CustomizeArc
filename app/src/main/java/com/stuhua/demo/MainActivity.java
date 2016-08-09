package com.stuhua.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
private DrawView dv_arc;
    private String TAG="MainActivity";
    int progress=0;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if(progress<100){
                dv_arc.setProgress(progress);
                progress++;
                Log.e(TAG,":"+progress);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dv_arc=getViewById(R.id.dv_arc);
        dv_arc.setMax(100);
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                    handler.sendEmptyMessage(0);
            }
        };
        Timer timer=new Timer();
        timer.schedule(task,1000,1000);
    }
    public <T extends View> T getViewById(int id){
        return (T)findViewById(id);
    }
}
