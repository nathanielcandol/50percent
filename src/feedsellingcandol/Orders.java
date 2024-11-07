package feedsellingcandol;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Orders {
    
    public void oTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("ORDERS PANEL");    
        System.out.println("1. ADD ORDER");
        System.out.println("2. VIEW ORDER");
        System.out.println("3. UPDATE ORDER");
        System.out.println("4. DELETE ORDER");
        System.out.println("5. EXIT... ");
        
        System.out.println("Enter Action: ");
        int action = sc.nextInt();
        Orders or = new Orders ();
        switch(action){
            case 1:
                or.addOrders(); 
                or.viewOrders();
                break;
            case 2:                
                or.viewOrders();
                break;
            case 3:
                or.viewOrders();
                or.updateOrders();
                or.viewOrders();
                break;
            case 4:
                  
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addOrders(){
        Scanner sc = new Scanner (System.in);   
        config conf = new config(); 
        Customer cs = new Customer ();
        cs.viewCustomers();
        
        System.out.print("Enter the ID of the Customer: ");
        int cid = sc.nextInt();
        
        String csql = "SELECT c_id FROM tbl_customers WHERE c_id = ?";
        while(conf.getSingleValue(csql, cid) == 0){
            System.out.print("Customer does not exist, Select Again: ");
            cid = sc.nextInt();
            
        }
        Feeds fd = new Feeds ();
        fd.viewFeeds();
        
        System.out.print("Enter the ID of the Feeds: ");
        int fid = sc.nextInt();
        
        String fsql = "SELECT f_id FROM tbl_feeds WHERE f_id = ?";
        while(conf.getSingleValue(fsql, fid) == 0){
            System.out.print("Feeds does not exist, Select Again: ");
            fid = sc.nextInt();
            
            
        }
        System.out.println("Enter Quantity: ");
        double quantity = sc.nextDouble();
        
        
        String priceqry = "SELECT f_price FROM tbl_feeds WHERE f_id = ?";
        double price = conf.getSingleValue(priceqry, fid);
        double due = price * quantity;
        
        System.out.println("---------------------------");
        System.out.println("Total Due: "+due);
        System.out.println("---------------------------");

        System.out.println("");
        
        System.out.println("Enter the received cash: ");
        double rcash = sc.nextDouble();
        
        while(rcash < due){
            System.out.println("Invalid Amount, Try Again: ");
            rcash = sc.nextDouble();
        }
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate. format(format);

        String status = "Pending";
        
            String qry = "INSERT INTO tbl_orders (c_id, f_id, o_quantity, o_due, o_rcash, o_date, o_status)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(qry, cid, fid, quantity, due, rcash, date, status);
        
    }   
    
    public void viewOrders() {
    String qry = "SELECT o_id, c_fname, f_name, f_type, f_price, f_stockquantity, o_quantity, o_due, o_date, o_status "
               + "FROM tbl_orders "
               + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_orders.c_id "
               + "LEFT JOIN tbl_feeds ON tbl_feeds.f_id = tbl_orders.f_id";

    String[] hrds = {"O_ID", "Customer", "FeedName", "Type", "Price", "Stock", "Order Quantity", "Due", "Date", "Status"};
    String[] clms = {"o_id", "c_fname", "f_name", "f_type", "f_price", "f_stockquantity", "o_quantity", "o_due", "o_date", "o_status"};
    config conf = new config();
    conf.viewRecords(qry, hrds, clms);
}

    private void updateOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   }

