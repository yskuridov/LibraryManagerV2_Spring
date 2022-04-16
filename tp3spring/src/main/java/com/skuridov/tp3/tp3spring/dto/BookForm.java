package com.skuridov.tp3.tp3spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.skuridov.tp3.tp3spring.model.Document.Book.BOOK_LOAN_LENGTH;

@Data
public class BookForm extends DocumentForm{
    private final String author;
    private final String editor;
    private final int pageAmount;
    private final String genre;

    public BookForm(String id, String title, String language, int nbCopies, String author, String editor, int pageAmount, String genre) {
        super(id, title, language, BOOK_LOAN_LENGTH, nbCopies);
        this.author = author;
        this.editor = editor;
        this.pageAmount = pageAmount;
        this.genre = genre;
    }
}
