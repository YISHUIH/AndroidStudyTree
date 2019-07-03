package com.example.study.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/24 15:39    <br>
 * Description: TODO 请输入此类的功能   <br>
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    /**
     * 数据库的名字
     */
    private static final String DB_NAME = "customer.db";
    /**
     * 版本号
     */
    private static int sDBVersion = 1;

    /**
     * 表名
     */

    public static final String TABLE_MAN_NAME = "man";
    public static final String TABLE_DOG_NAME = "dog";


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, sDBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists " + TABLE_MAN_NAME + "(_id integer primary key autoincrement,name text)");
        db.execSQL("create table if not exists " + TABLE_DOG_NAME + "(_id integer primary key autoincrement,name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
