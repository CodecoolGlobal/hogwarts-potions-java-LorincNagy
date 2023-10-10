package com.codecool.hogwarts_potions.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;//Student entitásban lévő mező, ami hivatkozik a teremre, az "room" nevű mező. Ez azért fontos, mert ez alapján azonosítja a Hibernate, hogy hogyan kell összekapcsolni a két entitást az adatbázisban.

}
//Student osztály helyesen van definiálva az entitásként való kezeléshez a Spring Data JPA számára. Az osztály tartalmazza az @Entity annotációt, amely jelzi, hogy ez egy entitás, valamint az @Id és @GeneratedValue annotációkat az egyedi azonosítóhoz.
//
//Az @JsonIgnore annotáció segít elrejteni az azonosítót a JSON reprezentációból, amikor az objektumokat JSON formátumba alakítod (például REST API válaszként). A @JsonIgnore azt jelenti, hogy az azonosító nem lesz beleértve a JSON kimenetbe.
//
//A @Getter, @Setter, @Builder, @NoArgsConstructor és @AllArgsConstructor annotációk segítenek a kényelmes getterek, setterek és konstruktorok generálásában, ami hasznos a Spring Data JPA-val való munkához.