package com.codecool.hogwarts_potions.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Potion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
//több potionnek is lehet ugyanaz a studentje azaz potionnek lesz egy student_id mezője ahol studentek lesznek
    private Student student;//@ManyToOne(cascade = CascadeType.ALL)//több potionnek is lehet ugyanaz a studentje azaz potionnek lesz egy student_id mezője ahol studentek lesznek,@ManyToOne annotáció azt jelzi, hogy több bájital is ugyanahhoz a diákhoz tartozhat, de egyetlen bájitalhoz csak egy diák tartozik. Tehát a JSON kérésben csak egy diákot kell megadni a bájitalhoz, akit a bájital készítőjeként szeretnél rögzíteni.
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;
    private BrewingStatus brewingStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private Recipe recipe;

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}

//@ManyToOne: Egy bájitalhoz egyetlen diák tartozik, és az adatbázisban általában egy student_id mező jelenik meg a bájital táblájában, ami egy idegen kulcs a diák táblához. Ezzel a kapcsolattal egy bájitalhoz egy diák tartozik, de több bájital is hivatkozhat ugyanarra a diákra.
//
//@OneToMany: Ezzel a kapcsolattal egy diákhoz több bájital is tartozhat, és az adatbázisban gyakran a diákhoz tartozó bájitalok listája vagy halmaza jelenik meg a diák táblájában. Itt nincs külön student_id mező a bájital táblájában, mivel az adatbázisban nincs szükség az ilyen típusú visszafelé hivatkozásra. Ebben az esetben egy diákhoz több bájital tartozhat.
//
//Az @ManyToOne-val van egyetlen student mező a bájitalban, ami az adott bájital készítőjére hivatkozik. Az @OneToMany-nél a diákhoz tartozó bájitalokat általában egy kollekcióban (lista vagy halmaz) tároljuk a diák osztályban, hogy könnyen lekérdezhető legyen az összes bájital, amit adott diák készített.
//A @ManyToOne kapcsolat esetén a bájital (Potion) osztályban található egy mező, amely a diákra (Student) mutat, és ezt az idegen kulcsot (általában student_id néven) általában az adatbázisban hozza létre. Ez a mező az adatbázisban egy idegen kulcs, amely a bájitalt kapcsolja össze a hozzá tartozó diákkal.
//
//A @OneToMany kapcsolat esetén a diák (Student) osztályban található egy kollekció (pl. lista vagy halmaz), amely a bájitalokat tartalmazza. Ebben az esetben nincs külön mező az adatbázisban, amely a bájitalokat tárolná a diákok esetében. Ehelyett a kapcsolatot általában az @JoinColumn és az @OneToMany(mappedBy = "student") annotációk segítségével kezeljük, és az adatbázisban nincs külön mező a diák táblájában.
//
//Ez azért van így, mert a JPA az egyik irányú kapcsolatokat könnyebben kezeli, és így optimalizálhatóbb az adatbázis. A @ManyToOne kapcsolat esetén az adatbázis könnyen kezelheti az idegen kulcsot, míg a @OneToMany kapcsolat esetén nincs szükség külön mezőre az adatbázisban, mivel a kapcsolatot az @JoinColumn és @OneToMany(mappedBy) annotációk segítségével állítjuk be.