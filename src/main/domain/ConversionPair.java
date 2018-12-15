package main.domain;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConversionPair implements Serializable {
    private String to;
    private String fromCur;

    protected ConversionPair(){}
    public ConversionPair(String to, String from) {
        this.to = to;
        this.fromCur = from;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return fromCur;
    }
//Testa att kommentera bort dessa sedan.
    @Override
    public int hashCode() {
        return Objects.hash(getFrom(),getTo());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ConversionPair)) return false;
        ConversionPair that = (ConversionPair) obj;
        return Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo());
    }
}
