package main.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class ConversionRate {

    @EmbeddedId private ConversionPair pair;
    private double rate;

    protected ConversionRate(){}
    public ConversionRate(ConversionPair pair) {
        this.pair = pair;
    }

    public void setRate(double rate){
        this.rate = rate;
    }

    public double convert(double valueToBeConverted){
        return valueToBeConverted*rate;
    }
}
