package com.lxxz.oasis.ouroasis.vo;

import lombok.Data;

import java.util.List;

@Data
public class DocumentForm {
    /*
    Title
    Authors
    Affiliations
    PublicTitle
    Year
    PdfLink
    AuthorKeyWord
    ReferenceCount
    Publisher
    DocID
    PaperAbstract
     */
    private String title;

    private List<String> authors;

    private List<String> affiliations;

    private String publicTitle;

    private int year;

    private String pdfLink;

    private List<String> keywords;

    private int referenceCount;

    private String publisher;

    private String docID;

    private String paperAbstract;
}
