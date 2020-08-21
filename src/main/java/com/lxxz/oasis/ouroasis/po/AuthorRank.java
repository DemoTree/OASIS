package com.lxxz.oasis.ouroasis.po;

import java.io.Serializable;
import lombok.Data;

/**
 * author_rank
 * @author 
 */
@Data
public class AuthorRank {
    private String author;

    private Integer pubCount;

    private Integer citeCount;

}