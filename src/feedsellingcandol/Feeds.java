package feedsellingcandol;

import java.util.Scanner;


public class Feeds {
    public void fTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("FEEDS PANEL");    
        System.out.println("1. ADD FEEDS");
        System.out.println("2. VIEW FEEDS");
        System.out.println("3. UPDATE FEEDS");
        System.out.println("4. DELETE FEEDS");
        System.out.println("5. EXIT... ");
        
        System.out.println("Enter Action: ");
        int action = sc.nextInt();
        Feeds fd = new Feeds ();
        

        switch(action){
            case 1:
                fd.addFeeds();           
                break;
            case 2:       
                fd.viewFeeds();
                break;
            case 3:
                fd.viewFeeds();
                fd.updateFeeds();
                fd.viewFeeds();
                break;
            case 4:
                fd.viewFeeds();
                fd.deleteFeeds();
                fd.viewFeeds();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addFeeds(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Feeds Name: ");
        String fname = sc.nextLine();
        System.out.print("Type: ");
        String type = sc.next();
        System.out.print("Price: ");
        double pr = sc.nextDouble();
        System.out.print("Stock Quantity: ");
        String stck = sc.next();

        String sql = "INSERT INTO tbl_feeds (f_name, f_type, f_price, f_stockquantity) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, fname, type, pr, stck);


    }

    public void viewFeeds() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_feeds";
        String[] Headers = {"Feeds_ID","Feeds Name", "Type", "Price", "Stock Quantity"};
        String[] Columns = {"f_id", "f_name", "f_type", "f_price", "f_stockquantity"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateFeeds() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT f_id FROM tbl_feeds WHERE f_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Feed ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.print("New Feeds Name: ");
        String nfname = sc.nextLine();
        System.out.print("New Type: ");
        String ntype = sc.next();
        System.out.print("New Price: ");
        String npr = sc.next();
        System.out.print("New Stock: ");
        String nstck = sc.next();
        String qry = "UPDATE tbl_feeds SET f_name = ?, f_type = ?, f_price = ?, f_stockquantity = ? WHERE g_id = ?";
        
        
        conf.updateRecord(qry, nfname, ntype, npr, nstck, id);         
        
        
    }
    
    private void deleteFeeds() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT f_id FROM tbl_feeds WHERE f_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Feed ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_feeds WHERE f_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}



