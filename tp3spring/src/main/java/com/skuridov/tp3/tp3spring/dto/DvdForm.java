package com.skuridov.tp3.tp3spring.dto;

import com.skuridov.tp3.tp3spring.model.Document.Dvd;
import lombok.Data;

import static com.skuridov.tp3.tp3spring.model.Document.Dvd.DVD_LOAN_LENGTH;

@Data
public class DvdForm extends DocumentForm{
    private final int duration;
    private final String category;
    private final String mainActor;

    public DvdForm(String id, String title, int publicationYear, String language, int duration, String category, String mainActor, int nbCopies) {
        super(id, title, publicationYear, language, DVD_LOAN_LENGTH, nbCopies);
        this.duration = duration;
        this.category = category;
        this.mainActor = mainActor;
    }

    public DvdForm(Dvd dvd){
        this(Long.toString(dvd.getId()), dvd.getTitle(), dvd.getPublicationYear(), dvd.getLanguage(), dvd.getDurationInMinutes(), dvd.getCategory(), dvd.getMainActor(), dvd.getNbCopies());
    }

    public Dvd toDvd(){
        return new Dvd(getTitle(), getPublicationYear(), getLanguage(), getDuration(), getCategory(), getMainActor(), getNbCopies());
    }
}
