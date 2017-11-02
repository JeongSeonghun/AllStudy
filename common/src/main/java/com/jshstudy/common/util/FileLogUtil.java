package com.jshstudy.common.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    public static FileLogUtil getInstance(){
        if(fileLogUtil == null){
            fileLogUtil = new FileLogUtil();
        }
        return fileLogUtil;
    }

    private FileLogUtil(){
    }

    public void init(Context context){
        fileManager = new FileManager(context);
        setLogFile(StringUtil.getSplitLastString(context.getPackageName(), "."));
    }

    public void init(Context ctx, String appName){
        fileManager = new FileManager(ctx);
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

        if(fileManager != null) fileManager.addExternal(saveFile, msg.getBytes());

        if(logFileManager != null) executor.execute(new AppendRun(msg));
    }

    public void close(){
        if(fileManager != null)fileManager.closeStream();
    }

    class AppendRun implements Runnable{

        String text;

        public AppendRun(String text){
            long curTime = new Date().getTime();
            this.text = text;
        }

        @Override
        public void run() {
            logFileManager.write(text);
        }
    }

}
