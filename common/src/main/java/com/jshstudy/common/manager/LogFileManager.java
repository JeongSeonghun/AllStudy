package com.jshstudy.common.manager;

import com.jshstudy.common.util.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by EMGRAM on 2017-11-02.
 * only text file
 */

public class LogFileManager {

    public File logFile;

    public LogFileManager(){
        // 기본 로그 파일
    }

    public LogFileManager(File logFile){
        setLogFile(logFile);
    }

    public void setLogFile(File logFile){
        this.logFile = logFile;

        if(!logFile.exists()){
            if(!logFile.mkdirs()){
                LogUtil.dLog("fail make log file");
            }
        }
    }

    public void write(String text){
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(logFile);
            bw= new BufferedWriter(fw);

            bw.write(text);
            // fw.write(text);

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // stream : byte 단위, reader : char 단위
    // => stream 사용시 영어, 숫자는 안깨져도 한글은...
    // read : 읽을 내용 없을 시 -1
    public void read(){
        FileReader fr;
        BufferedReader br;
        int length = 1000;
        int size = -1;

        try {
            fr = new FileReader(logFile);
            br = new BufferedReader(fr);

            // --- 1 ---
            char[] chars = new char[length];
            while((size =br.read(chars)) != -1){
                String temp = new String(chars, 0, size);
                LogUtil.dLog(temp + "\n");
            }

            // --- 2 한줄 씩 ---
            // 개행 문자 포함되어야...(java ="\n", 스트림 = "\r\n")
            // 보내는 쪽에어 개행 문자 추가...
//            String line;
//            while((line = br.readLine()) != null){
//                LogUtil.dLog(line +"\n");
//            }

            // fw 사용 1
//            char ch = (char)fr.read();
            // fw 사용 2
//            while((size  =fr.read(chars)) != -1){
//                String str = new String(chars, 0, size);
//            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
