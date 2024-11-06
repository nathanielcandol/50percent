package feedsellingcandol;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.Scanner;


public class FEEDSELLINGCANDOL {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);    
        boolean exit = true;
        do{
        System.out.println("WELCOME TO FEEDS SELLING!");
        System.out.println("");
        System.out.println("1. CUSTOMERS");
        System.out.println("2. FEEDS");
        System.out.println("3. ORDERS");
        System.out.println("4. EXIT");
        
        System.out.print("Enter Action: ");
        int act = sc.nextInt();
        
        switch(act){
            case 1:
                Customer cs = new Customer ();
                cs.cTransaction();
            break;
            
            case 2:
                Feeds fd = new Feeds ();
                fd.fTransaction();
            break;
            
            case 3:
                Orders or = new Orders ();
                or.oTransaction();
            break;
            case 4:
                System.out.println("are you sure?? (yes/no): ");
                String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                           exit = false;
                }
            break;   
            
        }
        }while (exit);
        System.out.print("See youu soon!");               
    
    }} 