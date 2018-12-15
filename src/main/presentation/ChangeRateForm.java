package main.presentation;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class ChangeRateForm {

    @NotNull(message = "Please specify the new rate ")
    @DecimalMin(value="0.00001",message = "Please specify an rate larger then 0")
    private double newRate;

    private String to;
    private String from;

    public double getNewRate() {
        return newRate;
    }

    public void setNewRate(double newRate) {
        this.newRate = newRate;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
