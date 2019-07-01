package com.example.study.service;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.study.BaseService;
import com.example.study.BookManager;
import com.example.study.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/21 13:37    <br>
 * Description: AIDLService   <br>
 */
public class AIDLService extends BaseService {

    private List<Book> bookList;

    @Override
    public void onCreate() {
        super.onCreate();
        bookList = new ArrayList<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return stub.asBinder();
    }

    final BookManager.Stub stub = new BookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            bookList.add(book);
        }

        @Override
        public Book getBookByName(String name) throws RemoteException {
            if (bookList.size()==0){
                return null;
            }

            for (Book b : bookList) {
                if (b.getName().equals(name)){
                    return b;
                }
            }
            return null;
        }

    };

}
