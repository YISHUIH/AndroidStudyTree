package com.example.study.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObservable;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.study.db.DBOpenHelper;

import static android.content.UriMatcher.NO_MATCH;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/24 15:57    <br>
 * Description: ContentProvider  <br>
 */
public class MyContentProvider extends ContentProvider {

    private DBOpenHelper dbOpenHelper = null;
    private SQLiteDatabase db = null;
    public static final String AUTHORITY = "com.example.study.contentprovider";
    public static final int CODE_MAN = 1;
    public static final int CODE_DOG = 2;
    static UriMatcher sUriMatcher = null;
    static {
        sUriMatcher = new UriMatcher(NO_MATCH);
        //权限
        //路径，可以是任何值
        //匹配的时候会返回的code
        sUriMatcher.addURI(AUTHORITY, "man", CODE_MAN);
        sUriMatcher.addURI(AUTHORITY, "dog", CODE_DOG);
    }


    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(getContext());
        db = dbOpenHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        Cursor query = db.query(tableName, null, null, null, null, null, null);
        query.moveToFirst();
        return query;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTableName(uri);
        db.insert(tableName, null, values);
        Log.i("name",Thread.currentThread().getName());
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        db.delete(tableName, selection, selectionArgs);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        db.update(tableName, values, selection, selectionArgs);
        return 0;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case CODE_MAN:
                tableName = DBOpenHelper.TABLE_MAN_NAME;
                break;
            case CODE_DOG:
                tableName = DBOpenHelper.TABLE_DOG_NAME;
                break;
            default:
                tableName=DBOpenHelper.TABLE_MAN_NAME;
        }
        return tableName;
    }
}

