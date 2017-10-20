package com.jstudy.jemediaplayer.player;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;

import com.jshstudy.common.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by EMGRAM on 2017-10-20.
 *
 * MediaExtractor https://developer.android.com/reference/android/media/MediaExtractor.html#getTrackFormat(int)
 * media data create?? get??
 *
 * MediaCodec
 * encoding, decoding
 *
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
        MediaCodec codec = null;

        String mimeType = "video/avc";  // H.264를 나타내는 "video/avc"
//        String mimeType = MediaFormat.MIMETYPE_VIDEO_H263;
        int width = 0;
        int height = 0;

        MediaFormat format = MediaFormat.createVideoFormat(mimeType,  width,  height);
//        format = createExatractor(null).getTrackFormat(0);
//        mimeType = format.getString(MediaFormat.KEY_MIME);
//        LogUtil.DLog("video screen size w/h : "+ format.getInteger(MediaFormat.KEY_WIDTH) + "/"+ format.getInteger(MediaFormat.KEY_WIDTH));
//        long duration = format.getLong(MediaFormat.KEY_DURATION);
//        LogUtil.DLog("video duration : "+ duration + "ms");


        // --- 여기 부터 재생????? ---

        try {
            //create decoder
            codec = MediaCodec.createDecoderByType(mimeType);

//            codec.configure(format,null,null,MediaCodec.CONFIGURE_FLAG_DECODE);
            codec.configure(format,null,null,0);

            codec.start();

            doExtract();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(codec != null){
                codec.stop();
                codec.release();
                codec = null;
            }
        }
    }

    private MediaExtractor createExtractor(File file){
        MediaExtractor extractor = null;

        try {
            extractor = new MediaExtractor();
            extractor.setDataSource(file.getAbsolutePath());
        } catch (IOException e) { // getAbsolutePath
            e.printStackTrace();
        }
        // extractor 사용시 추가?....
//        finally {
//            if(extractor != null){
//                extractor.release();
//                extractor = null;
//            }
//        }

        return extractor;
    }

    private ArrayList<Integer> getVedioTrackList(MediaExtractor extractor){
        ArrayList<Integer> trackList = new ArrayList<>();

        int trackCnt = extractor.getTrackCount();
        for(int idx =0 ; idx < trackCnt ; idx++){
            MediaFormat mediaFormat = extractor.getTrackFormat(idx);

            String mime = mediaFormat.getString(MediaFormat.KEY_MIME);

            // add only video track
            if(mime.startsWith("video/")){

                trackList.add(idx);
            }
        }

        return trackList;
    }

    public void play(){

    }

    private void doExtract(){

    }

    private class PlayThread extends Thread{
        public PlayThread(){

        }
    }
}
