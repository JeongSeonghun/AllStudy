package jsh.example.com.allstudy.data.engdata;

/**
 * Created by shun6 on 2017-09-05.
 */

public class EngData {
    private String type;
    private String kor;

    public EngData(String type, String kor){
        this.type = type;
        this.kor = kor;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setKor(String kor){
        this.kor = kor;
    }

    public String getKor(){
        return kor;
    }
}
