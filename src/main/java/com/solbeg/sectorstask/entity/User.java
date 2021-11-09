package com.solbeg.sectorstask.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean agreeToTerms;

//    @Type(type = "list-array")
//    @Column(name = "sector_ids",
//            columnDefinition = "integer[]")
    private int sectorIds;

}
