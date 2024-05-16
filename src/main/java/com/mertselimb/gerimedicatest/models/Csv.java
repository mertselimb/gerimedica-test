package com.mertselimb.gerimedicatest.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Csv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private Date fromDate;
    private Date toDate;
    private Integer sortingPriority;
}
