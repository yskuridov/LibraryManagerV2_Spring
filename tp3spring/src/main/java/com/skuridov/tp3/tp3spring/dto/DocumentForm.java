package com.skuridov.tp3.tp3spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public abstract class DocumentForm {
    private String id;
    private String title;
    private String language;
    private int loanLength;
    private int nbCopies;

    public DocumentForm(String id, String title, String language, int loanLength, int nbCopies){
        this.id = id;
        this.title = title;
        this.language = language;
        this.loanLength = loanLength;
        this.nbCopies = nbCopies;
    }

}
