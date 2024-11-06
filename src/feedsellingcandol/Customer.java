package feedsellingcandol;

import java.util.Scanner;


public class Customer {
    public void cTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("CUSTOMER PANEL");    
        System.out.println("1. ADD CUSTOMER");
        System.out.println("2. VIEW CUSTOMER");
        System.out.println("3. UPDATE CUSTOMER");
        System.out.println("4. DELETE CUSTOMER");
        System.out.println("5. EXIT... ");
        
        System.out.println("Enter Action: ");
        int action = sc.nextInt();
        Customer cs = new Customer ();
        

        switch(action){
            case 1:
                cs.addCustomers();           
                break;
            case 2:       
                cs.viewCustomers();
                break;
            case 3:
                cs.viewCustomers();
                cs.updateCustomers();
                cs.viewCustomers();
                break;
            case 4:
                cs.viewCustomers();
                cs.deleteCustomers();
                cs.viewCustomers();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addCustomers(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Contact Number: ");
        String cn = sc.next();
        System.out.print("Address: ");
        String add = sc.next();

        String sql = "INSERT INTO tbl_customers (c_fname, c_lname, c_contact, c_address) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, cn, add);


    }

    public void viewCustomers() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_customers";
        String[] Headers = {"Customers_ID","FirstName", "LastName", "Contact Number", "Address"};
        String[] Columns = {"c_id", "c_fname", "c_lname", "c_contact", "c_address"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateCustomers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT c_id FROM tbl_customers WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.print("New First Name: ");
        String nfname = sc.next();
        System.out.print("New Last Name: ");
        String nlname = sc.next();
        System.out.print("New Contact Number: ");
        String ncn = sc.next();
        System.out.print("New Address: ");
        String nadd = sc.next();
        String qry = "UPDATE tbl_customers SET c_fname = ?, c_lname = ?, c_contact = ?, c_address = ? WHERE c_id = ?";
        
        
        conf.updateRecord(qry, nfname, nlname, ncn, nadd, id);         
        
        
    }
    
    private void deleteCustomers() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customers WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_customers WHERE c_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}

