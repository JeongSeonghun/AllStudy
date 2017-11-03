package com.jshstudy.common.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.jshstudy.common.R;
import com.jshstudy.common.manager.FileManager;
import com.jshstudy.common.manager.LogFileManager;


/**
 * Created by EMGRAM on 2017-10-16.
 */

public class FileLogUtil {

    private static FileLogUtil fileLogUtil;
    private FileManager fileManager;
    private LogFileManager logFileManager;

    private Executor executor = Executors.newSingleThreadExecutor();

    private File saveFile;
    private String timeFormat;

    public static FileLogUtil getInstance(){
        if(fileLogUtil == null){
            fileLogUtil = new FileLogUtil();
        }
        return fileLogUtil;
    }

    private FileLogUtil(){
    }

    public void init(Context ctx){
        //fileManager = new FileManager(ctx);
        logFileManager = new LogFileManager();
        timeFormat = ctx.getString(R.string.log_time);
        setLogFile(StringUtil.getSplitLastString(ctx.getPackageName(), "."));
    }

    public void init(Context ctx, String appName){
        //fileManager = new FileManager(ctx);
        logFileManager = new LogFileManager();
        timeFormat = ctx.getString(R.string.log_time);
        setLogFile(appName);
    }

    public void init(){
        logFileManager = new LogFileManager();
        setLogFile("logs");
        logFileManager.setLogFile(saveFile);
    }

    public void init(String appName){
        logFileManager = new LogFileManager();
        setLogFile(appName);
        logFileManager.setLogFile(saveFile);
    }

    private void setLogFile(String title){
        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmm");
        String fileName = "AllStudy_Log_"+title+format.format(new Date())+".txt";

        saveFile = new File(Environment.getExternalStorageDirectory().getPath()+"/AllStudy/", fileName);
    }

    public void append(String msg){

        //if(fileManager != null) fileManager.addExternal(saveFile, msg.getBytes());

        if(logFileManager != null) executor.execute(new AppendRun(msg));
    }

    public void close(){
        if(fileManager != null)fileManager.closeStream();
    }

    private class AppendRun implements Runnable{

        String text;

        public AppendRun(String text){
            this.text = appendTime() + text;
        }

        private String appendTime(){
            String append = "";

            if(timeFormat != null && !timeFormat.isEmpty()){
                SimpleDateFormat format = new SimpleDateFormat(timeFormat);
                append = format.format(new Date());
            }

            return append;
        }

        @Override
        public void run() {
            logFileManager.write(text);
        }
    }

}
