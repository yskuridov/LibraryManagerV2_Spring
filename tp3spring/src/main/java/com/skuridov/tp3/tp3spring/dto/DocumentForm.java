package com.skuridov.tp3.tp3spring.dto;


public abstract class DocumentForm {
    private final String id;
    private final String title;
    private final String language;
    private final int loanLength;
    private final int nbCopies;

    public DocumentForm(String id, String title, String language, int nbCopies){
        this.id = id;
        this.title = title;
        this.language = language;
        this.loanLength = 0;
        this.nbCopies = nbCopies;
    }

}
