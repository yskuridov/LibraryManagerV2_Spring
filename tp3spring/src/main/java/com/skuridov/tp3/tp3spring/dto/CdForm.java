package com.skuridov.tp3.tp3spring.dto;

import lombok.Data;

import static com.skuridov.tp3.tp3spring.model.Document.Cd.CD_LOAN_LENGTH;

@Data
public class CdForm extends DocumentForm{
    private final String singer;
    private final int nbOfTracks;
    private final String genre;

    public CdForm(String id, String title, String language, int nbCopies, String singer, int nbOfTracks, String genre) {
        super(id, title, language, CD_LOAN_LENGTH, nbCopies);
        this.singer = singer;
        this.nbOfTracks = nbOfTracks;
        this.genre = genre;
    }
}
