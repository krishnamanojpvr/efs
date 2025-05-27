// In computer networking, subnetting is the process of dividing a larger IP network
// into multiple smaller subnetworks (subnets). Given a base network IP address, 
// a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
// write a Java program that calculates the starting addresses of all the subnets.

// Your program should take as input:
// 	- A base network address (e.g., 192.168.1.0).
// 	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
// 	- The number of subnets to generate.

// The program should then compute and return the starting addresses of each subnet.

// Input Format:
// -------------
// A string NIP: The base network IP address (e.g., "192.168.1.0").
// An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
// An integer numSubnets: The number of subnets to be generated.

// Output Format:
// --------------
// A list of subnet addresses, each representing the starting address of a subnet.


// Sample Input:
// -------------
// 192.168.1.0
// 26
// 4

// Sample Output:
// --------------
// [192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

// Explanation:
// ------------
// A /26 subnet has 64 IP addresses. The starting addresses of 
// the first four subnets are:
// 192.168.1.0/28, 
// 192.168.1.16/28, 
// 192.168.1.32/28, 
// 192.168.1.48/28

import java.util.*;

public class Subnet4{
    static int ipToInt(String ipStr){
        String parts[] = ipStr.split("\\.");
        int ip = 0;
        ip = ip | Integer.parseInt(parts[0]);
        ip = ip << 8 | Integer.parseInt(parts[1]);
        ip = ip << 8 | Integer.parseInt(parts[2]);
        ip = ip << 8 | Integer.parseInt(parts[3]);
        return ip;
    }
    
    static int getMask(int cidr){
        return 0xFFFFFFFF << (32 - cidr);
    }
    
    static String intToIp(int ip, int cidr){
        return String.format("%d.%d.%d.%d/%d",
            (ip >> 24) & 0xFF,
            (ip >> 16) & 0xFF,
            (ip >> 8) & 0xFF,
            ip & 0xFF,
            cidr
        );
    }
    
    static String[] getSubnets(int ip, int subnets, int newCidr){
        int newMask = getMask(newCidr);
        String[] netAddrs = new String[subnets];
        
        for(int i = 0; i < subnets; ++i){
            int networkAddress = ip & newMask | (i << (32 - newCidr));
            netAddrs[i] = intToIp(networkAddress, newCidr);
        }
        
        return netAddrs;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String ip = sc.nextLine();
        int cidr = sc.nextInt();
        int noSubnets = sc.nextInt();
        int intIp = ipToInt(ip);
        int newCidr = cidr + (int)Math.ceil(Math.log(noSubnets) / Math.log(2));
        String[] subnets = getSubnets(intIp, noSubnets, newCidr);
        System.out.println(Arrays.toString(subnets));
        sc.close();
        
    }
}
