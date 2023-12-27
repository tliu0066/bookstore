package com.bookstore.Service;

import com.bookstore.Entity.Book;
import com.bookstore.Entity.MyBookList;
import com.bookstore.Repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {
    @Autowired
    private MyBookRepository myBookRepository;

    public void saveMyBooks(MyBookList myBookList){
        myBookRepository.save(myBookList);
    }

    public List<MyBookList> getAllMyBook(){
        return myBookRepository.findAll();
    }

    public void deleteById(int id){
        myBookRepository.deleteById(id);
    }
}
