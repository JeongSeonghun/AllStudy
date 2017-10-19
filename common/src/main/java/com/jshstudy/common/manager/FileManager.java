package com.jshstudy.common.manager;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.jshstudy.common.util.LogUtil;


/**
 * Created by EMGRAM on 2017-10-11.
 */

public class FileManager {
    private long MAX = 500 * 1024 * 1024;
    private long MAXBUFF = 1024;
    private String saveDir;

    FileOutputStream outputStream;
    FileInputStream inputStream;
    private Context ctx;

    // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 외부저장소 권한, 읽기 권한도 포함됨(묵시적)
    public FileManager(Context ctx){
        this.ctx = ctx;
//        File tempDir = new File(ctx.getFilesDir(), "temp"); // 내부
//        File tempDir = File.createTempFile(fileName, fileExe, dir); // 파일 캐싱시, fileExe : 확장자(ex ".tmp")
        File tempDir = new File(ctx.getCacheDir(), "temp"); // 임시 캐시

        if(!tempDir.exists()){
            if(tempDir.mkdir()){
                LogUtil.DLog("dir make fail!");
            }
        }
    }

    public void write(File file, byte[] o){
        // 보안 적용된 파일관리 method : FileOutputStream, FileInputStream
        // /data/data/package명/files로 경로 고정됨
        // output mode : MODE_PRIVATE, MODE_APPEND, MODE_WORD_READABLE, MODE_WORD_WRITABLE

        try {
            if(outputStream == null){
//                outputStream = new FileOutputStream(fileName);    // fileName에는 확장자 미포함
//                outputStream = ctx.openFileOutput(fileName, mode);
                outputStream = new FileOutputStream(file);  //append true 시 이어서 받음, FileNotFoundException
            }

            outputStream.write(o);  // IOException
        } catch (IOException e) {   //FileNotFoundException 포함
            e.printStackTrace();
        }
    }

    public void write(File dir, File file){
        if(checkSize(dir, file.length())){

        }
    }

    // 저장유형 : 공용(앱제거시 유지), 전용(앱제거시 삭제)
    public void writeExternal(File file, Byte[] b){
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC); // 공용 디렉터리 ex music
        Environment.getExternalStorageDirectory();  // 전용 디렉터리(외부 저장소 최상위 위치, 이거 삭제되는 게 맞아?????)

    }

    public void add(File file, byte[] b){
        try {
            if(outputStream == null){
                outputStream = new FileOutputStream(file, true);
            }

            outputStream.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addExternal(File file, byte[] b){
        if(file == null){
            file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }

        if(!file.exists()){
            file.mkdirs();
        }

        add(file, b);
    }

    public void copy(File dir, File file){
        if(checkSize(dir, file.length())){

        }
    }

    // 앱삭제시 내부저장소 파일, getExternalFilesDir()을 통해 저장한 파일 삭제
    public void remove(File file){
//        ctx.deleteFile(fileName); // 내부저장소 파일 삭제
        file.delete();
    }

    public void move(File file){

    }

    public void read(File file){

        try {
            if(inputStream == null){
                inputStream = new FileInputStream(file);
            }

//            inputStream.read();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeStream(){
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = null;
        }

        if(outputStream != null){
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputStream = null;
        }
    }

    private boolean checkSize(File dir, long makeFileSize){
        long totalSize = 0;

        if(!dir.exists()){
            return false;
        }

        if(!dir.isDirectory()){
            totalSize = dir.length();  // byte 단위
        }else {
            File[] files = dir.listFiles();

            for(File temp : files){
                totalSize += temp.length();
            }
        }

//        dir.getFreeSpace(); // 결과 공간의 90%내를 권장

        return totalSize + makeFileSize > MAX;
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
