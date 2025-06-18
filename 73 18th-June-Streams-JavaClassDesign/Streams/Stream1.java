// Given a list of CustomerPurchase objects, each representing a purchase 
// with customerId, customerName, purchaseAmount, and category. 
// Compute the tier of customers based on total purchases 
// (only counting purchases ≥ 500.0).

// Tiers:
// - Platinum ≥ 5000
// - Gold ≥ 2000 and < 5000
// - Silver < 2000

// Note: Display the customers in descending order of their purchases of same tier.

// Example 1
// ---------

// Input:
// 4
// C101 Alice 1200 Electronics
// C102 Bob 499 Books
// C101 Alice 4500 Travel
// C103 Charlie 800 Furniture

// Output:
// C101 Alice : Platinum
// C103 Charlie : Silver


// Example 2
// ----------
// Input:
// 8
// C801 Mia 1000 Electronics
// C801 Mia 1200 Furniture
// C801 MIA 3000 Lighting
// C802 Olivia 1001 Apparel
// C803 Emma 1999 Jewelry
// C803 Emma 1 Books
// C804 Ava 2000 Appliances
// C805 Mia 1000 Garden

// Output:
// C801 Mia : Platinum
// C804 Ava : Gold
// C803 Emma : Silver
// C802 Olivia : Silver
// C805 Mia : Silver


import java.util.*;
import java.util.stream.*;
class CustomerPurchase{
    String id;
    String name;
    int amount;
    String category;
    CustomerPurchase(String id,String name,int amount,String category){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
}

public class Stream1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<CustomerPurchase> cplist = new ArrayList<>();
        for(int i=0;i<n;i++){
            String id = sc.next();
            String name = sc.next();
            int amount = sc.nextInt();
            String category = sc.next();
            cplist.add(new CustomerPurchase(id,name,amount,category));
        }
        List<String> res = cplist.stream().filter(c->c.amount>=500)
        .collect(
            Collectors.groupingBy(
                c -> c.id,
                Collectors.summingInt(c->c.amount)
            )    
        ).entrySet().stream().sorted((a,b)->b.getValue()-a.getValue())
        .map(e -> {
            String name = cplist.stream().filter(c->c.id.equals(e.getKey())).findFirst().get().name;
            String status = e.getValue()>=5000 ? "Platinum" : e.getValue()<2000 ? "Silver" : "Gold";
            return e.getKey() + " " + name + " : " + status;
        })
        .collect(Collectors.toList());
        for(String i : res){
            System.out.println(i);
        }
    }
}