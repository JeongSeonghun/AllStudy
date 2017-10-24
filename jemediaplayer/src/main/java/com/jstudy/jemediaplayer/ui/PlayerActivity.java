package com.jstudy.jemediaplayer.ui;

import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;

import com.jstudy.jemediaplayer.R;
import com.jstudy.jemediaplayer.player.JEPlayer;

import java.io.File;

public class PlayerActivity extends AppCompatActivity {
    /**
     * TextureView ??
     *
     */
    private TextureView v_play;
    private File playFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        v_play = (TextureView) findViewById(R.id.v_play);
    }

    private void init(){

    }

    private void createTextureView(){
        JEPlayer player = JEPlayer.getInstance();

        SurfaceTexture sfTexture = v_play.getSurfaceTexture();
        Surface sf = new Surface(sfTexture);

        player.init(playFile, sf, null);
    }
}
