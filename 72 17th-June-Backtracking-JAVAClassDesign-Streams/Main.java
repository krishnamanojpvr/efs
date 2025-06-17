// You are developing a Credit Risk Assessment System for a financial institution. 
// The system evaluates customer creditworthiness based on income, debt, and missed 
// payments. Your task is to design and implement the program using Object-Oriented 
// Programming (OOP) principles in Java.

// The system should:
// ------------------
//  - Accept multiple customer profiles.
//  - Calculate a credit score for each customer using a weighted formula.
//  - Classify each customer as Low Risk, Medium Risk, or High Risk.
//  - Output a summary report for each customer.

// Specifications:
// ---------------
//  - Scoring Formula (Fixed weights):
//  - Income Score = min(1, income / 100000)
//  - Debt Score = 1 - min(1, debt / 50000)
//  - Missed Payments Score = 1 - min(1, missedPayments / 10)
//  - Final Score = (0.4 × Income Score) + (0.4 × Debt Score) + (0.2 × Missed Payment Score) × 100

// Risk Level Classification:
// --------------------------
//  - Score ≥ 80 → Low Risk
//  - 50 ≤ Score < 80 → Medium Risk
//  - Score < 50 → High Risk

// Tasks to Complete
// -----------------
// - Create a POJO class Customer
//     - Fields: name, income, debt, missedPayments
//     - Include constructors, getters, and setters.

// - Create a POJO class CreditReport
//     - Fields: Customer object, calculatedScore, riskLevel
//     - Include constructors and toString() method.

//  - Define an interface RiskAnalyzer
//     - Method: CreditReport analyzeRisk(Customer customer);

//  - Implement a class CreditRiskAnalyzerImpl that implements the interface.
//     - Calculate the score using the provided formula.
//     - Determine the risk level.
//     - Return a CreditReport object.

// - Write the Main class to:
//     - Create at least 3 sample Customer objects.
//     - Use the CreditRiskAnalyzerImpl to generate reports.
//     - Print the final report for each customer.


// Sample Input:
// -------------
// 3
// David
// 100000
// 0
// 0
// Eva
// 10000
// 10000
// 10
// Frank
// 80000
// 10000
// 2

// Sample Output:
// --------------
// Customer: David, Score: 100.0, Risk Level: Low                                                                                                        
// Customer: Eva, Score: 36.0, Risk Level: High                                                                                                          
// Customer: Frank, Score: 80.0, Risk Level: Low 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Customer {
    // TODO: Define private fields for name, income, debt, missedPayments
    // TODO: Add constructor, getters, setters
    
    private String name;
    private double income;
    private double debt;
    private int missedPayments;
    
    Customer(String name,double income,double debt,int missedPayments){
        this.name = name;
        this.income = income;
        this.debt = debt;
        this.missedPayments = missedPayments;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getIncome(){
        return this.income;
    }
    
    public double getDebt(){
        return this.debt;
    }
    
    public int getMissedPayments(){
        return this.missedPayments;
    }
    
    
    
}

class CreditReport {
    // TODO: Define Customer customer, double score, String riskLevel
    // TODO: Constructor and toString() method
    Customer customer;
    double score;
    String riskLevel;
    
    CreditReport(Customer customer,double score,String riskLevel){
        this.customer = customer;
        this.score = score;
        this.riskLevel = riskLevel;
    }
    
    @Override
    public String toString(){
        return "Customer: " + this.customer.getName() + ", Score: " + String.format("%.1f",this.score) + ", Risk Level: " + this.riskLevel;
    }
    
}

interface RiskAnalyzer {
    CreditReport analyzeRisk(Customer customer);
}

class CreditRiskAnalyzerImpl implements RiskAnalyzer {

    @Override
    public CreditReport analyzeRisk(Customer customer) {
        // TODO: Implement score calculation and risk classification
        double incomeScore = Math.min(1,customer.getIncome()/100000);
        double debtScore = 1 - Math.min(1,customer.getDebt()/50000);
        double mpScore = 1 - Math.min(1,customer.getMissedPayments()/10.0);
        double finalScore = ((0.4*incomeScore)+(0.4*debtScore)+(0.2*mpScore))*100;
        String riskLevel;
        if(finalScore>=80){
            riskLevel = "Low";
        }
        else if(finalScore >=50 && finalScore<80){
            riskLevel = "Medium";
        }
        else{
            riskLevel = "High";
        }
        CreditReport crt = new CreditReport(customer,finalScore,riskLevel);
        return crt;
    }
}


public class CreditReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            double income = Double.parseDouble(sc.nextLine());
            double debt = Double.parseDouble(sc.nextLine());
            int missedPayments = Integer.parseInt(sc.nextLine());

            customers.add(new Customer(name, income, debt, missedPayments));
        }

        RiskAnalyzer analyzer = new CreditRiskAnalyzerImpl();

        for (Customer customer : customers) {
            CreditReport report = analyzer.analyzeRisk(customer);
            System.out.println(report);
        }

        sc.close();
    }
}