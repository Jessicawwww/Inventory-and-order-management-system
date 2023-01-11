import java.io.*;
import java.util.Date;

public class Loan implements Serializable{
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;

    //default constructor
    public Loan(){
        annualInterestRate = 2.5;
        numberOfYears = 1;
        loanAmount = 1000;
    }
    //constructs a loan with specified interest rate, years, and loan amount
    public Loan(double annualInterestRate, int numberOfYears, double loanAmount){
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        this.loanDate = new Date();
    }
    //getter methods
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }
    //setter methods
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    //returns the monthly payment for this loan
    public double getMonthlyPayment(){
        double monthlyInterestRate = annualInterestRate/1200;
        double monthlyPayment = loanAmount*monthlyInterestRate*Math.pow(1 + monthlyInterestRate, numberOfYears * 12)/(Math.pow(1 + monthlyInterestRate, numberOfYears * 12)-1);
        return monthlyPayment;
    }

    //returns the total payment for this loan
    public double getTotalPayment(){
        double totalPayment = getMonthlyPayment()*numberOfYears*12;
        return totalPayment;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "annualInterestRate=" + annualInterestRate +
                ", numberOfYears=" + numberOfYears +
                ", loanAmount=" + loanAmount +
                ", loanDate=" + loanDate +
                '}';
    }
}
