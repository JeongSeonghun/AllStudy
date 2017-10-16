package jsh.example.com.allstudy.manager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by EMGRAM on 2017-10-13.
 */

public class CPManager extends ContentProvider{
    // CP(Context provider), URI, 데이터 외부 공개(DB, 내부 배열, 네트워크 등, 대부분 DB)
    // 국제 표준 URI- RFC 2396
    // content://authority/path/id
    // content: uri임을 알림, authority : 정보제공자 명칭(ex 페키지 명), path : 정보의 종류를 지정하는 가상의 경로
    // , id : 원하는 정보 지정(전체 정보를 원할 시 생략 가능)

    public CPManager(){

    }

    // parse 성능상 문제로 예외처리 안함
    static final Uri CONTENT_URI = Uri.parse("content://jsh.example.com.allstudy/cp");
    static final int ALLWORD = 1;
    static final int ONEWORD = 2;

    //
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("jsh.example.com.allstudy", "cp", 1);
        uriMatcher.addURI("jsh.example.com.allstudy", "cp/*", 2);
    }


    // CP 로드시 호출. ex) DB open
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    // MIME 타입 조사
    // 단수 vnd.회사명.cursor.item/타입
    // 복수 vnd.회사명.cursor.dir/타입
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // match : uri 분석 후 등록된 정수 코드 리턴, 없을 시 -1
        if(uriMatcher.match(uri) == ALLWORD){
            return "vnd.AllStudy.Data.cursor.item/word";
        }
        if(uriMatcher.match(uri) == ONEWORD){
            return "vnd.AllStudy.Data.cursor.dir/words";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
