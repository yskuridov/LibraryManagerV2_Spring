package com.skuridov.tp3.tp3spring.service;

import com.skuridov.tp3.tp3spring.dto.Document.BookForm;
import com.skuridov.tp3.tp3spring.model.Document.Book;
import com.skuridov.tp3.tp3spring.repository.BookRepository;
import com.skuridov.tp3.tp3spring.repository.FineRepository;
import com.skuridov.tp3.tp3spring.repository.LoanRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberService {
    private BookRepository bookRepository;
    private LoanRepository loanRepository;
    private FineRepository fineRepository;

    public List<BookForm> getBooksByTitle(String title){
        List<BookForm> booksDTO = new ArrayList<>();
        for(Book b : bookRepository.findAllByTitleContainingIgnoreCase(title)){
            booksDTO.add(new BookForm(b));
        }
        return booksDTO;
    }

    public List<BookForm> getBooksByAuthor(String author){
        List<BookForm> booksDTO = new ArrayList<>();
        for(Book b : bookRepository.findAllByAuthorIsLikeIgnoreCase(author)){
            booksDTO.add(new BookForm(b));
        }
        return booksDTO;
    }

    public List<BookForm> getBooksByGenre(String genre){
        List<BookForm> booksDTO = new ArrayList<>();
        for(Book b : bookRepository.findAllByGenre(genre)){
            booksDTO.add(new BookForm(b));
        }
        return booksDTO;
    }

    public List<BookForm> getBooksByYear(int year){
        List<BookForm> booksDTO = new ArrayList<>();
        for(Book b : bookRepository.findAllByPublicationYear(year)){
            booksDTO.add(new BookForm(b));
        }
        return booksDTO;
    }


}
