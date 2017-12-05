package com.jstudy.jemediaplayer.player;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.view.Surface;

import com.jshstudy.common.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by EMGRAM on 2017-10-20.
 *
 * MediaExtractor https://developer.android.com/reference/android/media/MediaExtractor.html#getTrackFormat(int)
 * media data(format) create?? get??
 *
 * MediaCodec
 * api 4.1
 * encoding, decoding
 * use law byte.
 * api 4.3
 * display mp4. use MediaMuxer.
 * encoding. use surface
 * encoding, decoding. vp8, vp9
 *
 *
 *
 * apply format from android
 * https://developer.android.com/guide/topics/media/media-formats.html
 *
 * 
 * http://thdev.net/609
 */

public class JEPlayer {
    private static final String TAG = JEPlayer.class.getSimpleName();

    /**
     * mime     ?
     * track    ?
     *
     */

    public static final int STATE_PLAY = 100;
    public static final int STATE_STOP = 101;
    public static final int STATE_PUASE = 102;
    public static final int STATE_STOPED = 103;
    public static final int STATE_PUASED = 104;
    public static final int STATE_NO    = 0;

    public static final int NOT_SEEK_TIME = -999;

    private final String TYPE_AUDIO = "audio/";
    private final String TYPE_VIDEO = "video/";
    private final long TIMEOUT_USEC = 10000;

    private static JEPlayer player;

    private PlayThread playThread;
    private JEPlayerListener listener;
    private ArrayList<Integer> trackList;
    private ArrayList<MediaFormat> formats;
    private Surface sf;
    private MediaCrypto mediaCrypto;
    private File playFile;
    private MediaCodec.BufferInfo mBufferInfo;

    private String Type;

    private int playTackNum = -1;
    private int playState = STATE_NO;

    private long playTime;
    private long seekTime;

    private JEPlayer(){
        trackList = new ArrayList<>();
        formats = new ArrayList<>();
        Type = TYPE_VIDEO;
    }

    public static JEPlayer getInstance(){
        if(player == null){
            player = new JEPlayer();
        }

        return player;
    }

    public void init(File file, Surface surface, JEPlayerListener listener){
        init(file, surface, null, listener);
    }

    public void init(File file, Surface surface){
        init(file, surface, null, listener);
    }

    /**
     *
     * @param file          file for media play
     * @param surface       ??
     * @param mediaCrypto   암호화된 Media decoding 시 사용????
     */
    public void init(File file, Surface surface, MediaCrypto mediaCrypto, JEPlayerListener listener){
        this.listener = listener;

        mBufferInfo = new MediaCodec.BufferInfo();
        MediaExtractor extractor = new MediaExtractor();

        playFile = file;
        sf = surface;
        this.mediaCrypto = mediaCrypto;

        try {
            extractor.setDataSource(playFile.toString());

            setTrackList(extractor);

            if(trackList.size()<=0){
                if(listener != null) listener.error("track count zero");
                return;
            }

            playTackNum = trackList.get(0);

        } catch (IOException e) {
            e.printStackTrace();
            if(listener != null) listener.error(e.getMessage());
        } finally {
            extractor.release();
        }
    }

    /**
     * set tack information
     * @param extractor
     */
    private void setTrackList(MediaExtractor extractor){
        int trackCnt = extractor.getTrackCount();

        for(int idx = 0 ; idx < trackCnt ; idx++){
            // media information of track
            MediaFormat format = extractor.getTrackFormat(idx);
            // media type ex video, audio
            String mimeType = format.getString(MediaFormat.KEY_MIME);
            // display all information of format
            LogUtil.DLog(TAG,format.toString());

            if(mimeType.startsWith(Type)){
                trackList.add(idx);
                formats.add(format);
            }

        }
    }

    public ArrayList<Integer> getTrackList(){
        return trackList;
    }
    public ArrayList<MediaFormat> getTrackFormats(){
        return formats;
    }

    public void setListener(JEPlayerListener listener){
        this.listener = listener;
    }

    public void play(){

        if(playState == STATE_PLAY) return;

        if(playThread == null){
            playThread = new PlayThread();
            playState = STATE_PLAY;
            playThread.start();
        }else{
            if(playState == STATE_PUASED){
                playThread.notify();
            }
        }

    }

    public void stop(){
        playState = STATE_STOP;
    }

    public void pause(){
        playState = STATE_PUASE;
        try {
            playThread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playState = STATE_PUASED;
    }

    public void seek(long seekTime){

        this.seekTime = seekTime;
    }

    private class PlayThread extends Thread{

        public PlayThread(){
        }

        @Override
        public void run() {
//            super.run();
            MediaExtractor extractor = null;
            MediaCodec codec = null;

            try {
                extractor = new MediaExtractor();
                extractor.setDataSource(playFile.toString());

                MediaFormat format = extractor.getTrackFormat(playTackNum);  // else createVideoFormat(ex encoding)
                String mimeType = format.getString(MediaFormat.KEY_MIME);
                int width = format.getInteger(MediaFormat.KEY_WIDTH);
                int height = format.getInteger(MediaFormat.KEY_HEIGHT);
                long duration = format.getLong(MediaFormat.KEY_DURATION);
                LogUtil.DLog("video width / height : "+ width + "/"+ height);
                LogUtil.DLog("video duration : "+ duration);

                if(listener != null) listener.prepare(width, height, duration);

                codec = MediaCodec.createByCodecName(mimeType);

                //  flag : encoding(CONFIGURE_FLAG_ENCODE), decoding(0) flag
                codec.configure(format, sf, mediaCrypto, 0);

                codec.start();

                doExtract(codec, extractor);

            } catch (IOException e) {
                e.printStackTrace();
                if(listener != null) listener.error(e.getMessage());
            }finally {
                if(extractor != null){
                    extractor.release();
                }
                if(codec != null){
                    codec.flush();
                    codec.release();
                }
            }
        }

        private void doExtract(MediaCodec codec, MediaExtractor extractor){

            // 롤리팝 미만일 때에는 getInputBuffers를 사용하여 가져와야 함
            ByteBuffer[] decoderInputBuffers = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                decoderInputBuffers = codec.getInputBuffers();
//                ByteBuffer[] outputBuffers = codec.getOutputBuffers();
            }

            while(playState != STATE_STOP){
                // ------------- pause -------------------------
//                if(playState == STATE_PUASE){
//                    if(listener!=null&&playState != STATE_PUASED){
//                        if(listener != null) listener.playing(playState, playTime);
//                    }
//
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        if(listener != null) listener.error(e.getMessage());
//                    }
//                    playState = STATE_PUASED;
//                    continue;
//                }


                // ------------ seek ------------------------

                if(seekTime != NOT_SEEK_TIME){
                    playTime = seekTime;
                    seekTime = NOT_SEEK_TIME;
                    // mode....
                    extractor.seekTo(playTime, MediaExtractor.SEEK_TO_CLOSEST_SYNC);
                    codec.flush();
                }

                // ------------ play ------------------------
                // ------------ 입력 ------------------------

                int inputBufIndex = codec.dequeueInputBuffer(TIMEOUT_USEC);

                if(inputBufIndex >=0 ){
                    // 입력 버퍼 가져옴
                    ByteBuffer inputBuf;

                    // 롤리팝 이상인 경우에는 입력 버퍼의 인덱스를 통하여 직접 가져옴
                    // 롤리팝 미만에서는 입력 버퍼의 배열을 가져와서 배열의 인덱스로 입력 버퍼를 가져옴
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        inputBuf = codec.getInputBuffer(inputBufIndex);
                    } else {
                        if(decoderInputBuffers != null) {
                            inputBuf = decoderInputBuffers[inputBufIndex];
                        } else {
                            if(listener != null) listener.error("decodeInputBuffers null!");
                            return;
                        }
                    }

                    // 샘플링...
                    // offset.....
                    int sampleSize = extractor.readSampleData(inputBuf, 0);     // data size

                    if (sampleSize > 0) {   // 스트림 중
                        // 샘플링 시간
                        long presentationTimeUs = extractor.getSampleTime();    // micro second, 현재 시간???

                        codec.queueInputBuffer(inputBufIndex, 0, sampleSize, presentationTimeUs, 0);

                        if(listener != null) listener.playing(playState, presentationTimeUs/1000);

                        // process next buffer
                        extractor.advance();
                    }else{              // 스트림 종료
                        codec.queueInputBuffer(inputBufIndex, 0, 0, 0L,
                                MediaCodec.BUFFER_FLAG_END_OF_STREAM);
                    }

                }else{
                    if(listener != null) listener.error("input buffer disable");
                }

                // ---------- 출력 ----------------
                // bufferInfo... flags, size, offset, presentationTimeUs 정보
                int decoderStatus = codec.dequeueOutputBuffer(mBufferInfo, TIMEOUT_USEC);

                if(decoderStatus == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED){
                    // buffer 정보 1번 바뀜??. api >= 21 deprecated. ByteBuffer 배열 변화, 21아래 필수
                    LogUtil.DLog("change ByteBuffer array");
                }else if(decoderStatus == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED){
                    // MediaFormat 변경된 정보. encoder에서 주로 사용
                    MediaFormat newFormat = codec.getOutputFormat();
                    LogUtil.DLog("change format : "+newFormat);
                }else if(decoderStatus == MediaCodec.INFO_TRY_AGAIN_LATER){
                    // 현재 output 사용 불가????
                    LogUtil.DLog("disable current output");
                }else if(decoderStatus>=0){
                    // data

                    // relaseOutputBuffer를 호출하면 버퍼가 texture로 전환되어 SurfaceTexture로 전달됨
                    // flag true -> Surface 바로 위에 데이터 그림
                    codec.releaseOutputBuffer(decoderStatus, true);


                }else if(decoderStatus<0){
                    // 이 상황이 발생하긴 하는지...
                    LogUtil.DLog("unexpected result dequeOutputBuffer");
                }
            }

            codec.release();

            playThread = null;
        }
    }

    public interface JEPlayerListener{
        void prepare(int width, int height, long duration);
        void playing(int playState, long playTime);
        void error(String msg);
    }
}
