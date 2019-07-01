package com.example.study.activity.zj;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.study.BaseActivity;
import com.example.study.BookManager;
import com.example.study.R;
import com.example.study.entity.Book;
import com.example.study.service.AIDLService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AIDLServiceActivity extends BaseActivity {

    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.bindService)
    Button bindService;
    @BindView(R.id.unbindService)
    Button unbindService;
    @BindView(R.id.bt_1)
    Button bt1;
    @BindView(R.id.bt_2)
    Button bt2;
    @BindView(R.id.bt_3)
    Button bt3;
    @BindView(R.id.tv)
    TextView tv;

    public static void start(Context context) {
        Intent starter = new Intent(context, AIDLServiceActivity.class);
        context.startActivity(starter);
    }

    boolean isBind = false;
    Intent intent;
    ServiceConnection conn;
    /**
     *     书本管理对象
     */
    private BookManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_service);
        ButterKnife.bind(this);
        intent = new Intent(this, AIDLService.class);
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                isBind = true;
                Log.i("life", "ServiceConnection-----onServiceConnected");
               manager= BookManager.Stub.asInterface(service);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBind = false;
                Log.i("life", "ServiceConnection----onServiceDisconnected");
            }
        };

    }

    @OnClick({R.id.start, R.id.stop, R.id.bindService, R.id.unbindService, R.id.bt_1, R.id.bt_2,R.id.bt_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                startService(intent);
                break;
            case R.id.stop:
                stopService(intent);
                break;
            case R.id.bindService:
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                if (isBind) {
                    unbindService(conn);
                    isBind = false;
                }
                break;
            case R.id.bt_1:
                try {
                    List<Book> bookList = manager.getBookList();
                    tv.setText(bookList.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.bt_2:
                try {
                    manager.addBook(new Book("归隐"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_3:
                try {
                    Book book = manager.getBookByName("归隐");
                    tv.setText(book.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }


}
