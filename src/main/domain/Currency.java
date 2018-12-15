package main.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id private String currencyName;

    public Currency(String currencyName) {
        this.currencyName = currencyName;
    }

}
