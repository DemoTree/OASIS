package com.lxxz.oasis.ouroasis.po;

import lombok.Data;

import java.util.List;

@Data
public class Document {

    private String documentTitle;

    private String publicationTitle;

    private int publicationYear;

    private String theAbstract;

    private String pdfLink;

    private int referenceCount;

    private List<Author> authors;

    private List<String> keywords;

}
