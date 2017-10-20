package com.jstudy.jemediaplayer.player;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by EMGRAM on 2017-10-20.
 *
 * MediaExtractor https://developer.android.com/reference/android/media/MediaExtractor.html#getTrackFormat(int)
 * media data 추출
 *
 */

public class JEPlayer {

    /**
     * mime     ?
     * track    ?
     *
     */

    PlayThread playThread;

    public JEPlayer(){

    }

    private void initMediaCodec(){
//        H.264를 나타내는 "video/avc"
        String mimeType = "video/avc";
        //mimeType = MediaFormat.MIMETYPE_VIDEO_H263;
        int width = 0;
        int height = 0;

        MediaFormat format = MediaFormat.createVideoFormat(mimeType,  width,  height);
//        format = createExatractor(null).getTrackFormat(0);

        try {
            MediaCodec codec = MediaCodec.createDecoderByType(mimeType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MediaExtractor createExatractor(File file){
        MediaExtractor extractor = new MediaExtractor();

        try {
            extractor.setDataSource(file.getAbsolutePath());
        } catch (IOException e) { // getAbsolutePath
            e.printStackTrace();
        }

        return extractor;
    }

    private ArrayList<Integer> getVedioTrackList(MediaExtractor extractor){
        ArrayList<Integer> trackList = new ArrayList<>();

        int trackCnt = extractor.getTrackCount();
        for(int idx =0 ; idx < trackCnt ; idx++){
            MediaFormat mediaFormat = extractor.getTrackFormat(idx);

            String mime = mediaFormat.getString(MediaFormat.KEY_MIME);

            if(mime.startsWith("video/")){

                trackList.add(idx);
            }
        }

        return trackList;
    }

    private class PlayThread extends Thread{
        public PlayThread(){

        }
    }
}
