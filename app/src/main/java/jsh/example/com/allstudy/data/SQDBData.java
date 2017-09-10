package jsh.example.com.allstudy.data;

/**
 * Created by shun6 on 2017-09-10.
 */

public class SQDBData {
    private int idx = -1;
    private String str = "";
    private int i = -1;

    public SQDBData(int idx, String str, int i){
        this.idx = idx;
        this.str = str;
        this.i = i;
    }

    public int getIndex(){
        return idx;
    }

    public String getStr(){
        return str;
    }

    public int getI(){
        return i;
    }
}
