package main.presentation;

import javax.validation.constraints.*;

public class ConversionForm {

    @NotNull(message = "Please specify the amount you want to convert")
    @DecimalMin(value="0.00001",message = "Please specify an amount larger then 0")
    private double amount;

    private String to;
    private String from;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
