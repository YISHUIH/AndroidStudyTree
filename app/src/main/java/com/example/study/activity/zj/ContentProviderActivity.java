package com.example.study.activity.zj;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.study.BaseActivity;
import com.example.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentProviderActivity extends BaseActivity {

    @BindView(R.id.add_man)
    Button addMan;
    @BindView(R.id.query_man)
    Button queryMan;
    @BindView(R.id.add_dog)
    Button addDog;
    @BindView(R.id.query_dog)
    Button queryDog;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.delete_man)
    Button deleteMan;
    @BindView(R.id.delete_dog)
    Button deleteDog;
    private ContentResolver contentResolver;
    private Uri uri;
    private Cursor query;
    private ContentValues values;
    private ContentObserver contentObserver;

    public static void start(Context context) {
        Intent starter = new Intent(context, ContentProviderActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        ButterKnife.bind(this);
        contentResolver = getContentResolver();
        contentObserver = new ContentObserver(new Handler()) {
            @Override
            public boolean deliverSelfNotifications() {
                Log.i("cp", "deliverSelfNotifications");
                return true;
            }

            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.i("cp", "onChange---  selfChange:"+selfChange);
            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.i("cp", "onChange---  selfChange:"+selfChange+"   uri:"+uri.toString());
            }
        };
        uri = Uri.parse("content://com.example.study.contentprovider/man");
        contentResolver.registerContentObserver(uri, false,contentObserver );
    }

    @OnClick({R.id.add_man, R.id.query_man, R.id.add_dog, R.id.query_dog, R.id.delete_man, R.id.delete_dog})
    public void onViewClicked(View view) {
        StringBuffer buffer = new StringBuffer();
        switch (view.getId()) {
            case R.id.add_man:
                uri = Uri.parse("content://com.example.study.contentprovider/man");
                insert(uri, new ContentValues(), "小明");
                break;
            case R.id.query_man:
                uri = Uri.parse("content://com.example.study.contentprovider/man");
                query = contentResolver.query(uri, null, null, null, null, null);
                while (query.moveToNext()) {
                    int id = query.getInt(0);
                    String name = query.getString(1);
                    String s = " id:" + id + "   name:" + name;
                    buffer.append(s + "\n");
                    Log.i("cp", s);
                }

                break;
            case R.id.delete_man:
                uri = Uri.parse("content://com.example.study.contentprovider/man");
                contentResolver.delete(uri, null, null);
                break;
            case R.id.add_dog:
                uri = Uri.parse("content://com.example.study.contentprovider/dog");
                insert(uri, new ContentValues(), "秋田");
                break;
            case R.id.query_dog:
                uri = Uri.parse("content://com.example.study.contentprovider/dog");
                query = contentResolver.query(uri, null, null, null, null, null);
                while (query.moveToNext()) {
                    int id = query.getInt(0);
                    String name = query.getString(1);
                    String s = " id:" + id + "   name:" + name;
                    buffer.append(s + "\n");
                    Log.i("cp", s);
                }
                break;
            case R.id.delete_dog:
                uri = Uri.parse("content://com.example.study.contentprovider/dog");
                contentResolver.delete(uri, null, null);
                break;

            default:
        }
        if (null != query) {
            query.close();
            query=null;
        }
        tv.setText(buffer.toString());
    }

    private void insert(Uri uri, ContentValues values, String name) {
        values.put("name", name);
        contentResolver.insert(uri, values);
    }

    @Override
    protected void onStop() {
        super.onStop();
        contentResolver.unregisterContentObserver(contentObserver);
    }
}
