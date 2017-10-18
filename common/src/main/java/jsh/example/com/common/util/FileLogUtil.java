package jsh.example.com.common.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import jsh.example.com.common.manager.FileManager;


/**
 * Created by EMGRAM on 2017-10-16.
 */

public class FileLogUtil {

    private static FileLogUtil fileLogUtil;
    private FileManager fileManager;

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
        setLogFile();
    }

    private void setLogFile(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmm");
        String fileName = "AllStudy_Log_"+format.format(new Date())+".txt";

        saveFile = new File(Environment.getExternalStorageDirectory().getPath()+"/AllStudy/", fileName);
    }

    public void append(String msg){
        if(fileManager == null) return;
        
        fileManager.addExternal(saveFile, msg.getBytes());
    }

    public void close(){
        fileManager.closeStream();
    }
}
