package com.sistemas.appprueba;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivityVideoView extends Activity {
	public final static String EXTRA_MESSAGE = "com.sistemas.appprueba.MESSAGE";
	private VideoView video;

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String url = "http://gruposalvador.dyndns.org/android/ssa.mp4"; // your URL here
        
        /*video = (VideoView) findViewById(R.id.videoView1);*/
        MediaController controller = new MediaController(this);
        video.setVideoPath(url);
        video.setMediaController(controller);
        video.start();
                
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
    
    
    
    
}
