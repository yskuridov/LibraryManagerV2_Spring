package com.skuridov.tp3.tp3spring.service;

import com.skuridov.tp3.tp3spring.dto.Document.BookForm;
import com.skuridov.tp3.tp3spring.model.Document.Book;
import com.skuridov.tp3.tp3spring.model.Document.Document;
import com.skuridov.tp3.tp3spring.model.Fine.Fine;
import com.skuridov.tp3.tp3spring.model.Loan.Loan;
import com.skuridov.tp3.tp3spring.model.User.Member;
import com.skuridov.tp3.tp3spring.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        Member member = getMemberFromOptional(memberId);
        Document document = getDocumentFromOptional(documentId);
        Loan loan = new Loan(LocalDate.now(), LocalDate.now().plusDays(document.getLoanLength()), member, document);
        document.setNbCopies(document.getNbCopies() - 1);
        member.getLoanList().add(loan);
        memberRepository.save(member);
        documentRepository.save(document);
        loanRepository.save(loan);
    }

    public void returnDocument(long memberId, long documentId) throws Exception{
        Member member = getMemberFromOptional(memberId);
        Document document = getDocumentFromOptional(documentId);
        Loan loan = findLoan(member, document);
        member.setDebt(member.getDebt() + calculateFee(loan));
        member.getLoanList().remove(loan);
        document.setNbCopies(document.getNbCopies() + 1);
        memberRepository.save(member);
        documentRepository.save(document);
        loanRepository.delete(loan);
    }

    private Loan findLoan(Member member, Document document) throws Exception {
        for(Loan l : member.getLoanList()){
            if(l.getDocument().equals(document)){
                return l;
            }
        }
        throw new Exception("The document wasn't loaned by that member");
    }

    private double calculateFee(Loan loan){
        int differenceInDays;
        differenceInDays = (int) (ChronoUnit.DAYS.between(loan.getDateDue(), LocalDate.now()));
        double fee = 0.00;
        if(differenceInDays > loan.getDocument().getLoanLength()){
            fee = differenceInDays * Fine.FINE_AMOUNT;
        }
        return fee;
    }

    private Member getMemberFromOptional(long id) throws Exception{
        Optional<Member> memberOpt = memberRepository.findMemberByIdWithFineList(id);
        if(memberOpt.isEmpty()){
            throw new Exception("Member entity not found");
        }
        Member member = memberOpt.get();
        member.setLoanList(memberRepository.findMemberByIdWithLoanList(id).get().getLoanList());
        return member;
    }

    private Document getDocumentFromOptional(long id) throws Exception{
        Optional<Document> documentOpt = documentRepository.findById(id);
        if(documentOpt.isEmpty()){
            throw new Exception("Document entity not found");
        }
        Document document = documentOpt.get();
        return document;
    }
}
