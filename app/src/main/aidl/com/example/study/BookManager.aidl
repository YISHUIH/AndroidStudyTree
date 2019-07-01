// BookManager.aidl
package com.example.study;

// Declare any non-default types here with import statements
import com.example.study.entity.Book;
interface BookManager {

        List<com.example.study.entity.Book> getBookList();

        void addBook(in com.example.study.entity.Book book);
        com.example.study.entity.Book getBookByName(in String name);

}
