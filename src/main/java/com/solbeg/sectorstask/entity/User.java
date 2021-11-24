package com.solbeg.sectorstask.entity;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean agreeToTerms;

    @Type(type = "int-array")
    @Column(name = "sector_ids",
            columnDefinition = "integer[]")
    private int[] sectorIds;

}
