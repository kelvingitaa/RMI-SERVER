package rmi;

import java.io.Serializable;

public class Tenant implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String houseNumber;
    private double rentAmount;
    private double paidAmount;
    private String paymentDate;

    public Tenant() {}

    public Tenant(int id, String name, String houseNumber, double rentAmount, double paidAmount, String paymentDate) {
        this.id = id;
        this.name = name;
        this.houseNumber = houseNumber;
        this.rentAmount = rentAmount;
        this.paidAmount = paidAmount;
        this.paymentDate = paymentDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public double getRentAmount() { return rentAmount; }
    public void setRentAmount(double rentAmount) { this.rentAmount = rentAmount; }

    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public double getBalance() { return rentAmount - paidAmount; }
}
