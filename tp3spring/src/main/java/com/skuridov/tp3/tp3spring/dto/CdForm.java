package com.skuridov.tp3.tp3spring.dto;

import com.skuridov.tp3.tp3spring.model.Document.Cd;
import lombok.Data;

import static com.skuridov.tp3.tp3spring.model.Document.Cd.CD_LOAN_LENGTH;

@Data
public class CdForm extends DocumentForm{
    private final String singer;
    private final int nbOfTracks;
    private final String genre;

    public CdForm(String id, String title, int publicationYear, String language, String singer, int nbOfTracks, String genre, int nbCopies) {
        super(id, title, publicationYear, language, CD_LOAN_LENGTH, nbCopies);
        this.singer = singer;
        this.nbOfTracks = nbOfTracks;
        this.genre = genre;
    }

    public CdForm(Cd cd){
        this(Long.toString(cd.getId()), cd.getTitle(), cd.getPublicationYear(), cd.getLanguage(), cd.getSinger(), cd.getNumberOfTracks(), cd.getGenre(), cd.getNbCopies());
    }

    public Cd toCd(){
        return new Cd(getTitle(), getPublicationYear(), getLanguage(), getSinger(), getNbOfTracks(), getGenre(), getNbCopies());
    }
}
