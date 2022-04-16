package com.skuridov.tp3.tp3spring.service;

import com.skuridov.tp3.tp3spring.dto.Document.BookForm;
import com.skuridov.tp3.tp3spring.model.Document.Book;
import com.skuridov.tp3.tp3spring.model.Document.Document;
import com.skuridov.tp3.tp3spring.model.Loan.Loan;
import com.skuridov.tp3.tp3spring.model.User.Member;
import com.skuridov.tp3.tp3spring.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemberService {
    private BookRepository bookRepository;
    private LoanRepository loanRepository;
    private FineRepository fineRepository;
    private MemberRepository memberRepository;
    private DocumentRepository documentRepository;

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

    public void loanDocument(long memberId, long documentId) throws Exception {
        Optional<Member> memberOpt = memberRepository.findMemberByIdWithFineList(memberId);
        Optional<Document> documentOpt = documentRepository.findById(documentId);
        if(!memberOpt.isEmpty() && !documentOpt.isEmpty()) {
            Member member = memberOpt.get();
            member.setLoanList(memberRepository.findMemberByIdWithLoanList(memberId).get().getLoanList());
            Document document = documentOpt.get();
            Loan loan = new Loan(LocalDate.now(), LocalDate.now().plusDays(document.getLoanLength()), member, document);
            document.setNbCopies(document.getNbCopies() - 1);
            member.getLoanList().add(loan);
            memberRepository.save(member);
            documentRepository.save(document);
            loanRepository.save(loan);
        }
        else{
            throw new Exception("Entities not found");
        }
    }


}
