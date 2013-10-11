package com.sistemas.appprueba;

import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity implements OnCompletionListener,
SurfaceHolder.Callback{
	public final static String EXTRA_MESSAGE = "com.sistemas.appprueba.MESSAGE";
	private MediaPlayer mediaPlayer;
	private ArrayList<String> videoList = new ArrayList<String>();
	private SurfaceHolder holder;
	private int currentVideo = 0;


    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //String url = "http://gruposalvador.dyndns.org/android/ssa.mp4"; // your URL here
        videoList.add("http://gruposalvador.dyndns.org/android/ssjods.mp4");
        videoList.add("http://gruposalvador.dyndns.org/android/ssa.mp4");
        
        SurfaceView surface = (SurfaceView) findViewById(R.id.surface);
        holder = surface.getHolder();
        holder.addCallback(this);

        currentVideo = 0;


                
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /*Metodo llamado cuando el usuario presiona el boton de SEND*/
    public void sendMsg(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText edittext = (EditText) findViewById(R.id.edit_message);
    	String message = edittext.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }

    @Override
    protected void onPause() {
	    super.onPause();
	    mediaPlayer.stop();
	    mediaPlayer.release();
	    finish();
    }

    
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		playVideo((String) videoList.get(0));

	}
	
	private void playVideo(String videoPath) {
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setDisplay(holder);
			mediaPlayer.setDataSource(videoPath);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IllegalArgumentException e) {
			Log.d("MEDIA_PLAYER", e.getMessage());
		} catch (IllegalStateException e) {
			Log.d("MEDIA_PLAYER", e.getMessage());
		} catch (IOException e) {
			Log.d("MEDIA_PLAYER", e.getMessage());
		}
	}



	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("media player", "play next video");

	}


	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		Log.d("player", "playback complete");
		currentVideo++;
		if (currentVideo > videoList.size() - 1) {
			currentVideo = 0;
		}
		mediaPlayer.release();
		playVideo((String) videoList.get(currentVideo));

	}
    
    
    
    
}
