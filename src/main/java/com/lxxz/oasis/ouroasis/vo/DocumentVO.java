package com.lxxz.oasis.ouroasis.vo;

import lombok.Data;

@Data
public class DocumentVO {
    //文献需要文献名、作者名、出版方、出版年、引用总数；
    private String name;

    private String author;

    private String publication;

    private int publicationYear;

    private int referenceCount;
}
