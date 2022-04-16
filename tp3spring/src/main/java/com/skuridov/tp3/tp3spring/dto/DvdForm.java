package com.skuridov.tp3.tp3spring.dto;

import lombok.Data;

import static com.skuridov.tp3.tp3spring.model.Document.Dvd.DVD_LOAN_LENGTH;

@Data
public class DvdForm extends DocumentForm{
    private final int duration;
    private final String category;
    private final String mainActor;

    public DvdForm(String id, String title, String language, int nbCopies, int duration, String category, String mainActor) {
        super(id, title, language, DVD_LOAN_LENGTH, nbCopies);
        this.duration = duration;
        this.category = category;
        this.mainActor = mainActor;
    }
}
