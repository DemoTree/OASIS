package com.lxxz.oasis.ouroasis.po;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
public class AuthorTable1Entity {



    private String docTitle;

    private String authors;

    private String authorsAff;

    private String key;
}
