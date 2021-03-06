import java.sql.*;
import java.util.*;
import java.io.*;
import javax.sql.*;
import javax.xml.transform.Result;
import java.lang.*;
import java.text.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
//import org.apache.ibatis.jdbc.ScriptRunner;

public class App {

    static int storeID = 1;
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static Scanner sc = new Scanner(System.in);


    //-----------------------------------------------------------------------
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/username";

    public static void main(String[] args) {

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            String user = "username";
            String passwd = "password";
            conn = DriverManager.getConnection(jdbcURL, user, passwd);
    //-----------------------------------------------------------------------



    // static final String jdbcURL = "jdbc:mysql://localhost:3306/wolfwr";

    // public static void main(String[] args) {

    //     try{
    //         Class.forName("com.mysql.jdbc.Driver");
    //         String user = "username";
    //         String passwd = "password";
    //         conn = DriverManager.getConnection(jdbcURL, user, passwd);

            boolean flag = true;

            while(flag){

                System.out.println("Menu");
                System.out.println("1 : Information processing.");
                System.out.println("2 : Maintaining inventory records");
                System.out.println("3 : Maintaining billing and transaction records");
                System.out.println("4 : Reports");
                System.out.println("5 : Display Data");

                System.out.print("Please enter the input from the menu : ");
                System.out.println();


                int option = sc.nextInt();

                switch(option){
                    case 1:
                        informationProcessing();
                        System.out.Prinltn(":wIP");
                        break;
                    case 2:
                        maintainingInventoryRecords();
                        System.out.Prinltn(":wIP");
                        break;
                    case 3:
                        maintainingBillsAndTransactionRecords();
                        break;
                    case 4:
                        reports();
                        break;
                    case 5:
                        display();
                        break;
                    default:
                        System.out.println("Please enter a valid input!!!!");
                        break;

                }

                System.out.print("Main Menu....Do you wish to update anything else ? [y/n] : ");
                char c = sc.next().charAt(0);
                if(c == 'y' || c == 'Y'){
                    continue;
                }else if(c == 'n' || c == 'N'){
                    flag = false;
                }
            }

            stmt.close();
            conn.close();

        }catch(Throwable oops){
            oops.printStackTrace();
        }finally{

        }


    }

/*
------------------Methods for Tasks and Operations--------------------
*/


    public static void display()
    {
        System.out.println("Enter the table name which you want to see: ");
        String table = sc.next();
        try
        {
            stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * from "+table);
            ResultSetMetaData rsmd = resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();

            for (int i = 1; i <= columnsNumber; i++)
            {
                if (i>1) System.out.print(" | ");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(" | ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue );
                }
                System.out.println("");
            }
        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }




    public static void informationProcessing(){
        System.out.println("Menu");
        System.out.println("------Store APIs------");
        System.out.println("1: Add New Store");
        System.out.println("2: Update Existing Store Information ");
        System.out.println("3: Delete a Store");
        System.out.println("------Club Member APIs------");
        System.out.println("4: Add New Customer");
        System.out.println("5: Update Existing Customer Information");
        System.out.println("6: Delete Customer Information");
        System.out.println("------Staff APIs------");
        System.out.println("7: Add New Staff Member");
        System.out.println("8: Update Staff Information");
        System.out.println("9: Delete Staff Member");
        System.out.println("------Supplier APIs------");
        System.out.println("10: Add new Supplier");
        System.out.println("11: Update Supplier Information");
        System.out.println("12: Delete Supplier Information");
        System.out.println("------Discount APIs------");
        System.out.println("13: Add Discount on a Product");
        System.out.println("14:Update Discount on a Product");
        System.out.println("15: Delete Discount on a Product");

        System.out.print("Please enter the input from the menu : ");
        System.out.println();
        // Scanner sc = new Scanner(System.in);

        int option = sc.nextInt();

        switch(option){
            case 1:
                addStore();
                break;
            case 2:
                updateStore();
                break;
            case 3:
                deleteStore();
                break;
            case 4:
                addClubMember();
                break;
            case 5:
                updateClubMember();
                break;
            case 6:
                deleteClubMember();
                break;
            case 7:
                addStaff();
                break;
            case 8:
                updateStaff();
                break;
            case 9:
                deleteStaff();
                break;
            case 10:
                addNewSupplier();
                break;
            case 11:
                updateSupplierInfo();
                break;
            case 12:
                deleteSupplier();
                break;
            case 13:
                addDiscount();
                break;
            case 14:
                updateDiscount();
                break;
            case 15:
                deleteDiscount();
                break;
            default:
                System.out.println("Please enter a valid input!!!!");
                break;

        }
    }

    public static void maintainingInventoryRecords(){
        System.out.println("Menu");
        System.out.println("1: Add product to Inventory and store");
        System.out.println("2: Update ProductInformation in Inventory");
        System.out.println("3: Update Product Information in Store");
        System.out.println("4: Delete Product from Store");
        System.out.println("5: Update store with returns");
        System.out.println("6: Transfer product between two stores");

        int option=sc.nextInt();

        switch(option){
            case 1:
                addNewInventory();
                break;
            case 2:
                updateProductInInventory();
                break;
            case 3:
                updateProductInStore();
                break;
            case 4:
                deleteProductInStore();
                break;
            case 5:
                returnItems();
                break;
            case 6:
                transferProductBetweenStores();
                break;
            default:
                System.out.println("Wrong input!!! Try again!!!");
                maintainingInventoryRecords();
        }

    }

    public static void maintainingBillsAndTransactionRecords(){
        System.out.println("Menu : ");
        System.out.println("1 : Generate Bills");
        System.out.println("2 : Make Transactions");
        System.out.println("3 : Generate Rewards");

        int option = sc.nextInt();

        switch(option){
            case 1:
                generatebills();
                break;
            case 2:    
                makeTransaction();
                break;
            case 3:
                generateRewardChecks();
                break;
            default:
                System.out.println("Choose correct option....");
                maintainingBillsAndTransactionRecords();
                break;

        }


    }


    public static void reports(){
        System.out.println("Menu");
        System.out.println("1: Get Total Sales on a specific date:");
        System.out.println("2: Get Total Sales for a specific month:");
        System.out.println("3: Get Total Sales for a specific year:");
        System.out.println("4: Get Sales Growth between two dates");
        System.out.println("5: Get Stock Quantity for a particular merchandise");
        System.out.println("6: Get Customer Growth between two dates");
        System.out.println("7: Get Customer Activity between two dates");

        System.out.print("Please enter the input from the menu : ");
        System.out.println();
        Scanner sc = new Scanner(System.in);

        int option = sc.nextInt();

        switch(option){
            case 1:
                System.out.print("Enter the date for which you want to get sales for (YYYY_MM_DD) : ");
                String date = sc.next();
                System.out.println();

                getSalesbyDay(date);
                break;
            case 2:
                System.out.print("Enter the month: ");
                String month = sc.next();
                System.out.println();

                System.out.print("Enter the year: ");
                String year = sc.next();
                System.out.println();

                getSalesbyMonth(month, year);
                break;
            case 3:
                System.out.print("Enter the year: ");
                year = sc.next();
                System.out.println();

                getSalesbyYear(year);
                break;
            case 4:

                getSalesGrowth();
                break;
            case 5:
                System.out.println("What kind of merchandise report do you want ?");
                System.out.println("1 : By productID and storeID");
                System.out.println("2 : By productID");
                System.out.println("3 : By storeID");

                option = sc.nextInt();

                switch(option){
                    case 1:
                        System.out.print("Enter the storeID for getting merchandise stock: ");
                        int storeID = sc.nextInt();
                        System.out.println();

                        System.out.print("Enter the productID for getting merchandise stock: ");
                        int productID = sc.nextInt();
                        System.out.println();

                        getMerchandiseStock(storeID, productID);
                        break;
                    case 2:

                        System.out.print("Enter the productID for getting merchandise stock: ");
                        productID = sc.nextInt();
                        System.out.println();

                        getMerchandiseStock_product(productID);
                        break;

                    case 3:
                        System.out.print("Enter the storeID for getting merchandise stock: ");
                        storeID = sc.nextInt();
                        System.out.println();

                        getMerchandiseStock_store(storeID);
                        break;
                }

                break;
            case 6:
                System.out.println("1. Get customer growth by year");
                System.out.println("2 : Get customer growth by month");

                option = sc.nextInt();

                switch(option){
                    case 1:
                        getCustomerGrowthbyYear();
                        break;
                    case 2:
                        getCustomerGrowthbyMonth();
                        break;
                    default:
                        System.out.println("Wrong input!!! Try again!!!");
                        reports();
                        break;
                }

                break;
            case 7:
                System.out.print("Enter the storeID for customer activity: ");
                storeID = sc.nextInt();
                System.out.println();

                System.out.print("Enter the customerID for customer activity: ");
                int customerID = sc.nextInt();
                System.out.println();

                System.out.print("Enter the start date: ");
                String startDate = sc.next();
                System.out.println();

                System.out.print("Enter the end date: ");
                String endDate = sc.next();
                System.out.println();

                getCustomerActivity(customerID, storeID, startDate, endDate);
                break;
            default:
                System.out.println("Please enter a valid input!!!!");
                break;

        }
    }

/*
------------------------------------------------------------------------
*/


    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    static boolean isValidDate(String input) {
        try {
            format.parse(input);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }



    public static void addStore(){
        try{
            System.out.println("Enter Store Information");

            System.out.print("Store Address: ");
            sc.nextLine();
            String storeAddr = sc.nextLine();
            System.out.println();

            System.out.print("Store Phone: ");
            String storePhone = sc.next();
            System.out.println();

            try{
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO Store(storeAddress, Phone_No) VALUES (\'" + storeAddr + "\',\'" + storePhone + "\')");


            }catch(Throwable oops){
                oops.printStackTrace();
            }
        }catch(Throwable oops){
            oops.printStackTrace();
        }finally{
            // stmt.close();
            // conn.close();
        }


    }

    public static void updateStore(){
        boolean flag = true;

        while(flag){
            System.out.println("Enter the store ID for which you want to change the information : ");
            int storeID = sc.nextInt();
            System.out.println();

            System.out.println("Update : ");
            System.out.println("1 : Store Address");
            System.out.println("2 : Staff Phone");


            int input = sc.nextInt();


            switch(input){
                case 1:
                    try{

                        System.out.print("Enter the new Address : ");
                        sc.nextLine();
                        String addr = sc.nextLine();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Store SET storeAddress = \'" + addr + "\' WHERE storeID = " + storeID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 2:
                    try{
                        System.out.print("Enter the new phone number : ");
                        String phone = sc.next();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Store SET Phone_No = \'" + phone + "\' WHERE storeID = " + storeID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Please enter a valid number!!! Try again!!!");
                    updateStore();
                    break;
            }


            System.out.print("Do you wish to update anything else ? [y/n] : ");
            char c = sc.next().charAt(0);
            if(c == 'y' || c == 'Y'){
                continue;
            }else if(c == 'n' || c == 'N'){
                flag = false;
            }else{
                System.out.println("Wrong input!!");
                updateStore();
            }
        }
    }

    public static void deleteStore(){
        System.out.print("Give the store id of the store to be removed : ");
        int storeID = sc.nextInt();
        System.out.println();

        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Store WHERE storeID = " + storeID);

            // stmt.close();
            // conn.close();
        }catch(Throwable oops){
            oops.printStackTrace();
        }

    }


    public static void addStaff(){
        System.out.println("Enter Store Information");

        System.out.print("Store ID: "); // check if this store ID exists or not....
        int store_id = sc.nextInt();
        System.out.println();

        System.out.print("Staff Name : ");
        sc.nextLine();
        String staffName = sc.nextLine();
        System.out.println();

        System.out.print("Age : ");
        int age = sc.nextInt();
        System.out.println();

        System.out.print("Staff job title : ");
        sc.nextLine();
        String job_title = sc.nextLine();
        System.out.println();

        System.out.print("Staff email : ");
        String email = sc.next();
        System.out.println();

        System.out.print("Staff Address : ");
        sc.nextLine();
        String address = sc.nextLine();
        System.out.println();


        System.out.print("Staff phone number : ");
        String phoneNumber = sc.next();
        System.out.println();

        // TimeOfEmployment...
        System.out.print("Time of employment : ");
        String date = sc.next();
        while(!isValidDate(date)){
            System.out.print("Enter a valid date (YYYY-MM-DD) : ");
            date = sc.next();
            System.out.println();
        }
        System.out.println();

        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Staff (storeID, name, Age, Job_Title, Email, Phone_No, Address, TimeOfEmployment) VALUES (\'" + storeID + "\',\'" + staffName + "\',\'" + age + "\',\'" + job_title + "\',\'" + email + "\',\'" + phoneNumber + "\',\'" + address + "\',\'" + date + "\')");

            // stmt.close();
            // conn.close();
        }catch(Throwable oops){
            oops.printStackTrace();
        }

    }


    public static void deleteStaff(){
        System.out.print("Give the staff id of the staff to be removed : ");
        int staffID = sc.nextInt();
        System.out.println();

        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Staff WHERE staffID = " + staffID);

            // stmt.close();
            // conn.close();
        }catch(Throwable oops){
            oops.printStackTrace();
        }

    }

    public static void updateStaff(){
        boolean flag = true;

        while(flag){
            System.out.println("Enter the staff ID for which you want to change the information : ");
            int staffID = sc.nextInt();
            System.out.println();

            System.out.println("Update : ");
            System.out.println("1 : Store ID");
            System.out.println("2 : Staff Name");
            System.out.println("3 : Age");
            System.out.println("4 : Job_Title");
            System.out.println("5 : Email");
            System.out.println("6 : Phone Number");
            System.out.println("7 : Address");
            System.out.println("8 : Time of Employment");

            int input = sc.nextInt();


            switch(input){
                case 1:
                    try{

                        System.out.print("Enter the new value of storeID : ");
                        int store_id = sc.nextInt();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET storeID = " + store_id + " WHERE staffID = " + staffID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 2:
                    try{
                        System.out.print("Enter the new name : ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET name = \'" + name + "\' WHERE staffID = " + staffID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 3:
                    try{
                        System.out.print("Enter the new value of age : ");
                        int age = sc.nextInt();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET Age = " + age + " WHERE staffID = " + staffID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 4:
                    try{
                        System.out.print("Enter the new value of job title : ");
                        sc.nextLine();
                        String job_title = sc.nextLine();

                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET job_title = \'" + job_title + "\' WHERE staffID = " + staffID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 5:
                    try{
                        System.out.print("Enter the new email : ");
                        String email = sc.next();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET Email = \'" + email + "\' WHERE staffID = " + staffID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 6:
                    try{
                        System.out.print("Enter the new phone number : ");
                        String phone = sc.next();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET Phone_No = \'" + phone + "\' WHERE staffID = " + staffID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 7:
                    try{
                        System.out.print("Enter the new address : ");
                        sc.nextLine();
                        String address = sc.nextLine();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET Address = \'" + address + "\' WHERE staffID = " + staffID);

                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 8:
                    try{
                        System.out.print("Enter the employment date (YYYY-MM-DD) : ");
                        String time = sc.next();
                        while(!isValidDate(time)){
                            System.out.print("Enter a valid date (YYYY-MM-DD) : ");
                            time = sc.next();
                            System.out.println();
                        }
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Staff SET TimeOfEmployment = \'" + time + "\' WHERE staffID = " + staffID);


                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Please enter a valid number!!! Try again!!!");
                    updateStaff();
                    break;
            }


            System.out.print("Do you wish to update anything else ? [y/n] : ");
            char c = sc.next().charAt(0);
            if(c == 'y' || c == 'Y'){
                continue;
            }else if(c == 'n' || c == 'N'){
                flag = false;
            }else{
                System.out.println("Wrong input!!");
                updateStaff();
            }
        }

    }


    public static void addClubMember(){
        System.out.println("Enter ClubMemberInformation");

        System.out.print("ClubMember firstName:");
        sc.nextLine();
        String firstName= sc.nextLine();
        System.out.println();

        System.out.print("ClubMember lastName: ");
        //sc.nextLine();
        String lastName= sc.nextLine();
        System.out.println();

        System.out.print("ClubMember PhoneNumber: ");
        String phoneNumber = sc.nextLine();
        System.out.println();

        System.out.print("ClubMember Address:");
        String address= sc.nextLine();
        System.out.println();

        System.out.print("ClubMember Email");
        String email= sc.next();
        System.out.println();

        System.out.print("ClubMember membershipstatus(Gold/Platinum):");
        String membershipLevel=sc.next();
        System.out.println();

        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO ClubMember(firstName, lastName,PhoneNumber, Address, Email,membershipLevel) VALUES (\'" + firstName+ "\',\'" + lastName+ "\',\'" + phoneNumber+ "\',\'" + address+ "\',\'"+ email + "\',\'"+ membershipLevel + "\')");
        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

    public static void deleteClubMember(){
        System.out.print("Enter ClubMemberID to be deleted");
        String clubMemberID = sc.next();
        System.out.println();

        try{
            stmt=conn.createStatement();
            //we setActive status to false instead of deleting clubmember
            stmt.executeUpdate("UPDATE ClubMember SET ActiveStatus = 0 WHERE customerID = " + clubMemberID);

        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

    public static void updateClubMember(){
        System.out.println("enter ClubMemberID");
        String clubMemberID = sc.next();

        boolean flag = true;

        while(flag){
            System.out.println("choose field to be update : ");
            System.out.println("1: firstName");
            System.out.println("2: lastName");
            System.out.println("3: phoneNumber");
            System.out.println("4: Address");
            System.out.println("5: Email");
            System.out.println("6: MembershipLevel");
            int option= sc.nextInt();
            switch(option){
                case 1:
                    updateClubMemberFirstName(clubMemberID);
                    break;
                case 2:
                    updateClubMemberLastName(clubMemberID);
                    break;
                case 3:
                    updateClubMemberPhoneNumber(clubMemberID);
                    break;
                case 4:
                    updateClubMemberAddress(clubMemberID);
                    break;
                case 5:
                    updateClubMemberEmail(clubMemberID);
                    break;
                case 6:
                    updateClubMemberMembershipLevel(clubMemberID);
                    break;
            }

            System.out.print("Do you wish to update anything else ? [y/n] : ");
            char c = sc.next().charAt(0);
            if(c == 'y' || c == 'Y'){
                continue;
            }else if(c == 'n' || c == 'N'){
                flag = false;
            }else{
                System.out.println("Wrong input!!");
                updateClubMember();
            }


        }

    }

    public static void updateClubMemberFirstName(String clubMemberID){
        System.out.println("enter firstname of clubMember");
        sc.nextLine();
        String firstName=sc.nextLine();
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE ClubMember SET firstName =\'" + firstName + "\' WHERE customerID= " + clubMemberID);

        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

    public static void updateClubMemberLastName(String clubMemberID){
        System.out.println("enter lastName of clubMember");
        sc.nextLine();
        String lastName=sc.nextLine();
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE ClubMember SET lastName =\'" + lastName + "\' WHERE customerID= " + clubMemberID);
        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

    public static void updateClubMemberPhoneNumber(String clubMemberID){
        System.out.println("enter PhoneNumber of clubMember");
        String phoneNumber=sc.next();
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE ClubMember SET PhoneNumber =\'" + phoneNumber + "\' WHERE customerID= " + clubMemberID);
        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

    public static void updateClubMemberAddress(String clubMemberID){
        System.out.println("enter address of clubMember");
        sc.nextLine();
        String address=sc.nextLine();
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE ClubMember SET Address =\'" + address + "\' WHERE customerID= " + clubMemberID);
        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

    public static void updateClubMemberEmail(String clubMemberID){
        System.out.println("enter email of clubMember");
        String email=sc.next();
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE ClubMember SET Email =\'"+ email +"\' WHERE customerID= " + clubMemberID);
        }


        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

    public static void updateClubMemberMembershipLevel(String clubMemberID){
        System.out.println("enter membershiplevel of clubMember");
        String membershipLevel=sc.next();
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE ClubMember SET membershipLevel =\'" + membershipLevel + "\' WHERE customerID= " + clubMemberID);
        }
        catch(Throwable oops){
            oops.printStackTrace();
        }

    }




    public static void addNewSupplier(){
        System.out.println("Enter Supplier Information");

        System.out.print("Supplier Name : ");
        sc.nextLine();
        String suppName = sc.nextLine();
        System.out.println(suppName);

        System.out.print("Supplier Phone: ");
        String suppPhone = sc.nextLine();
        //System.out.println();
        sc.nextLine();


        System.out.print("Supplier Email: ");
        String suppEmail = sc.nextLine();
        //System.out.println();
        sc.nextLine();

        System.out.print("Supplier Location: ");
        String suppLocation = sc.nextLine();
        //System.out.println();
        sc.nextLine();


        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Supplier (supplierName, phoneNumber, Email, Location) VALUES (\'" + suppName + "\',\'" + suppPhone + "\',\'" + suppEmail + "\',\'" + suppLocation + "\')");

        }catch(Throwable oops){
            oops.printStackTrace();
        }


    }

    public static void updateSupplierInfo(){

        boolean flag = true;

        while(flag){

            System.out.print("Enter the supplier ID for which you want to update the fields : ");
            int suppID = sc.nextInt();
            System.out.println();

            System.out.println("Choose field to Update Supplier Information");
            System.out.println("1: Change Supplier Name");
            System.out.println("2: Change Supplier Phone");
            System.out.println("3: Change Supplier Email");
            System.out.println("4: Change Supplier Location");

            int option = sc.nextInt();

            switch(option){
                case 1:
                    try{

                        System.out.print("Enter the Supplier Name : ");
                        sc.nextLine();
                        String suppName = sc.nextLine();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Supplier SET supplierName = \'" + suppName + "\' WHERE supplierID = " + suppID);

                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }

                    break;
                case 2:
                    try{
                        System.out.print("Enter the Supplier Phone : ");
                        String suppPhone = sc.next();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Supplier SET phoneNumber = \'" + suppPhone + "\' WHERE supplierID = " + suppID);

                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;

                case 3:
                    try{

                        System.out.print("Enter the Supplier Email : ");
                        String suppEmail = sc.next();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Supplier SET Email = \'" + suppEmail + "\' WHERE supplierID = " + suppID);

                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                case 4:
                    try{

                        System.out.print("Enter the Supplier Location : ");
                        sc.nextLine();
                        String suppLocation = sc.nextLine();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Supplier SET Location = \'" + suppLocation + "\' WHERE supplierID = " + suppID);

                        stmt.close();
                        conn.close();
                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Wrong Input!!!");
                    updateSupplierInfo();

            }

            System.out.print("Do you wish to update anything else ? [y/n] : ");
            char c = sc.next().charAt(0);

            if(c == 'y' || c == 'Y'){
                continue;
            }else if(c == 'n' || c == 'N'){
                flag = false;
            }else{
                System.out.println("Wrong input!! Please run the code again...");
                updateSupplierInfo();
            }

        }

    }


    public static void deleteSupplier(){
        System.out.println("Delete Supplier Information");

        System.out.print("Supplier ID : ");
        String suppID = sc.next();
        System.out.println();

        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Supplier WHERE supplierID = " + suppID);

        }catch(Throwable oops){
            oops.printStackTrace();
        }

    }

    public static void addDiscount(){

        System.out.println("Enter Discount Information");
        System.out.println("------------------------------");

        System.out.print("Enter the Product ID : ");
        int productID = sc.nextInt();
        System.out.println();

        System.out.print("Enter the Store ID: ");
        int storeID= sc.nextInt();
        System.out.println();

        System.out.print("Enter the Discount Percentage: ");
        int discountPercentage = sc.nextInt();
        System.out.println();

        System.out.print("Enter the Start Date (in YYYY-MM-DD format): ");
        String startDate = sc.next();
        System.out.println();

        System.out.print("Enter the End Date (in YYYY-MM-DD format): ");
        String endDate = sc.next();
        System.out.println();


        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Discount WHERE storeID = " + storeID + " AND productID = " + productID);

            while(rs.next()){
                Integer integer = rs.getInt("discountID");

                if(integer != null){
                    stmt.executeUpdate("DELETE from Discount WHERE storeID = " + storeID + " AND productID = " + productID);
                }
                stmt.executeUpdate("INSERT INTO Discount (productID, storeID, DiscountPercentage, startDate, endDate) VALUES (" + productID + ", " + storeID + ", " + discountPercentage + " , \'" + startDate + "\',  \'" + endDate + "\')");
            }


        }catch(Throwable oops){
            oops.printStackTrace();
        }

    }

    public static void deleteDiscount(){
        System.out.println("Delete Discount Process");
        System.out.println("------------------------------");

        System.out.print("Enter the Product ID of the discount to be deleted : ");
        int productID = sc.nextInt();
        System.out.println();

        System.out.print("Enter the Store ID of the discount to be deleted: ");
        int storeID= sc.nextInt();
        System.out.println();

        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE from Discount WHERE storeID = "+storeID+" AND productID = "+productID+" ");
        }catch(Throwable oops){
            oops.printStackTrace();
        }

    }


//    public static void generateRewardChecks(){
//
//        System.out.println("Generate Reward Checks");
//
//        int rewardPercentage = 2;
//        System.out.println("Reward Percentage is set to : " + rewardPercentage + "% (default for all Platinum Customers)");
//        System.out.println("Generating Reward Checks...");
//
//        ArrayList<Integer> platinum_customerIDs = new ArrayList<>();
//        HashMap <Integer,Integer> hp = new HashMap <>();
//
//        try{
//
//            stmt = conn.createStatement();
//
//            ResultSet ct = stmt.executeQuery("SELECT customerID FROM ClubMember WHERE membershipLevel = 'Platinum' AND activeStatus = true");
//
//            while(ct.next()){
//                platinum_customerIDs.add(ct.getInt("customerID"));
//            }
//
//            for (Integer i : platinum_customerIDs){
//                ct = stmt.executeQuery("SELECT customerID, SUM(TotalPrice * " + rewardPercentage +") AS REWARDS FROM Transactions WHERE (customerID="+i+" AND purchaseDate BETWEEN '2005-1-2' AND '2015-10-05');");
//
//                while(ct.next()){
//                    hp.put(ct.getInt("customerID"),ct.getInt("REWARDS"));
//                }
//            }
//
//            System.out.println("customerID -> Reward Amount");
//            System.out.println("----------------------------------");
//
//            for(Map.Entry m : hp.entrySet()){
//                System.out.println(m.getKey() + " -> " + m.getValue());
//            }
//
//        }catch(Throwable oops){
//            oops.printStackTrace();
//        }
//
//    }

    public static void generateRewardChecks(){

        System.out.println("Generate Reward Checks");

        double rewardPercentage = 0.02;
        System.out.println("Reward Percentage is set to : " + rewardPercentage + "% (default for all Platinum Customers)");
        System.out.println("Generating Reward Checks...");

        System.out.println("Enter the year for which you want to generate reward checks: ");
        int year = sc.nextInt();

        System.out.println("Reward Checks Option Menu: ");
        System.out.println("1 - Generate reward check for a particular customer: ");
        System.out.println("2- Generate reward check for all platinum customers: ");
        System.out.println("Enter your choice: ");
        int option = sc.nextInt();

        HashMap<Integer,Integer> hp = new HashMap<>();

        switch(option)
        {
            case 1:
                System.out.println("Enter customerID: ");
                int customerID = sc.nextInt();

                //HashMap <Integer,Integer> hp = new HashMap <>();
                hp = new HashMap<>();
                try
                {
                    stmt = conn.createStatement();

                    ResultSet ct = stmt.executeQuery("SELECT membershipLevel, activeStatus FROM ClubMember WHERE customerID = "+customerID);

                    if(ct.next())
                    {
                        String mem = ct.getString("membershipLevel");
                        String active = (String) ct.getString("activeStatus");

                        if(mem.equals("Platinum") && active.equals("1"))
                        {

                            ct = stmt.executeQuery("SELECT customerID, SUM(TotalPrice * " + rewardPercentage +") AS REWARDS FROM Transactions WHERE customerID="+customerID+" AND YEAR(purchaseDate) = "+year+" ");

                            while(ct.next())
                            {
                                if(ct.getInt("customerID") == 0){
                                    continue;
                                }
                                hp.put(ct.getInt("customerID"),ct.getInt("REWARDS"));
                            }

                            System.out.println("customerID -> Reward Amount");
                            System.out.println("----------------------------------");

                            for(Map.Entry m : hp.entrySet())
                            {
                                if((int)m.getKey() == 0){
                                    continue;
                                }
                                System.out.println(m.getKey() + " -> " + m.getValue()+"$");
                            }

                        }
                        else{
                            System.out.println("Cannot generate reward checks for non-platinum members or inactive members.");
                        }

                    }

                }
                catch(Throwable oops)
                {
                    oops.printStackTrace();
                }

                break;
            case 2:

                ArrayList<Integer> platinum_customerIDs = new ArrayList<>();
                hp = new HashMap<>();

                try{

                    stmt = conn.createStatement();

                    ResultSet ct = stmt.executeQuery("SELECT customerID FROM ClubMember WHERE membershipLevel = 'Platinum' AND activeStatus = 1");

                    while(ct.next()){
                        platinum_customerIDs.add(ct.getInt("customerID"));
                    }

                    for (Integer i : platinum_customerIDs){
                        ct = stmt.executeQuery("SELECT customerID, SUM(TotalPrice * " + rewardPercentage +") AS REWARDS FROM Transactions WHERE customerID="+i+" AND YEAR(purchaseDate) = "+year+" ");

                        while(ct.next()){
                            hp.put(ct.getInt("customerID"),ct.getInt("REWARDS"));
                        }
                    }

                    System.out.println("customerID -> Reward Amount");
                    System.out.println("----------------------------------");

                    for(Map.Entry m : hp.entrySet()){
                        System.out.println(m.getKey() + " -> " + m.getValue()+"$");
                    }

                }catch(Throwable oops){
                    oops.printStackTrace();
                }

                break;
            default:
                System.out.println("Invalid choice");
                break;
        }


    }



    public static void updateDiscount(){


        Boolean flag = true;

        while(flag){

            System.out.println("Choose field to Update Discount");
            System.out.println("1: Change Discount Percentage");
            System.out.println("2: Change Start Date for a discount");
            System.out.println("3: Change End Date for a discount");

            System.out.println("Enter your choice: ");
            int option = sc.nextInt();


            switch(option){
                case 1:
                    try{
                        System.out.println("Enter the storeID : ");
                        int storeID = sc.nextInt();

                        System.out.println("Enter the productID : ");
                        int productID = sc.nextInt();

                        System.out.println("Enter the new Discount Percentage : ");
                        int disc_percentage = sc.nextInt();

                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Discount SET discountPercentage = " + disc_percentage + " WHERE storeID = " +storeID+" AND productID = "+productID);

                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }

                    break;

                case 2:
                    try{
                        System.out.println("Enter the storeID : ");
                        int storeID = sc.nextInt();

                        System.out.println("Enter the productID : ");
                        int productID = sc.nextInt();

                        System.out.println("Enter the Start Date : ");
                        String start_date = sc.next();

                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Discount SET startDate = \'" + start_date + "\' WHERE storeID = " +storeID+" AND productID = "+productID);

                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }

                    break;

                case 3:
                    try{
                        System.out.println("Enter the storeID : ");
                        int storeID = sc.nextInt();

                        System.out.println("Enter the productID : ");
                        int productID = sc.nextInt();

                        System.out.println("Enter the End Date : ");
                        String end_date = sc.next();

                        stmt = conn.createStatement();
                        stmt.executeUpdate("UPDATE Discount SET endDate = \'" + end_date + "\' WHERE storeID = " +storeID+ " AND productID = "+productID);

                    }catch(Throwable oops){
                        oops.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("Please try again ... enter a valid choice");
                    updateDiscount();

            }

            System.out.println("Do you wish to update anything else ? [y/n] : ");
            char c = sc.next().charAt(0);

            if(c == 'y' || c == 'Y'){
                continue;
            }else if(c == 'n' || c == 'N'){
                flag = false;
                break;
            }else{
                System.out.println("Wrong input!!");
                updateDiscount();
            }

        }

    }

//----------------------------------


    public static void getCustomerActivity(int customerID, int storeID, String startDate, String endDate){

        System.out.println("Getting total sales for the entered store in the required time period...");
        System.out.println();

        try
        {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Activity FROM Transactions WHERE customerID = "+customerID+" AND PurchaseDate BETWEEN \'"+startDate+"\' AND \'"+endDate+"\'");

            int total_activity = 0;

            while(rs.next())
            {
                Integer integer = rs.getInt("Total_Activity");

                total_activity = integer != null ? integer.intValue() : 0;

            }

            System.out.println("The total stock for the entered customerID: "+customerID+" and storeID: "+storeID+" between dates \'"+startDate+"\' and \'"+endDate+"\' is: "+total_activity+"$");

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }


    public static void getSalesbyYear(String year){
        System.out.println("Getting total sales for the entered year...");
        System.out.println();

        try{
            stmt = conn.createStatement();

            // percentSymbol = '%';
            // date = year+percentSymbol;

            ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE YEAR(purchaseDate) = "+year+" ");

            int total_sales = 0;

            while(rs.next())
            {
                Integer integer = rs.getInt("Total_Sales");

                total_sales = integer != null ? integer.intValue() : 0;

            }

            System.out.println("The total sales for the entered year is: "+total_sales+"$");

        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }
    public static void getSalesbyDay(String date)
    {
        System.out.println("Getting total sales for the entered date...");
        System.out.println();

        try
        {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE PurchaseDate = \'"+date + "\'");

            int total_sales = 0;

            while(rs.next())
            {
                Integer integer = rs.getInt("Total_Sales");

                total_sales = integer != null ? integer.intValue() : 0;

            }



            System.out.println("The total sales for the entered date is: $"+total_sales);

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }




    public static void getSalesbyMonth(String month, String year)
    {
        System.out.println("Getting total sales for the entered month...");
        System.out.println();

        try
        {
            stmt = conn.createStatement();

            // percentSymbol = '%';
            // date = month+percentSymbol;

            ResultSet rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE  MONTH(purchaseDate)="+month+" AND YEAR(purchaseDate) = "+year+" ");
            //"SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE PurchaseDate LIKE "+date+" "

            int total_sales = 0;
            while(rs.next())
            {
                Integer integer = rs.getInt("Total_Sales");

                total_sales = integer != null ? integer.intValue() : 0;

            }

            System.out.println("The total sales for the entered month is: $" + total_sales);

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }



    public static void getMerchandiseStock(int storeID, int productID)
    {

        System.out.println("Getting total merchandise stock for the entered storeID and productID...");
        System.out.println();

        try
        {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SUM(Quantity) AS Total_Stock FROM InventoryInfo WHERE productID = "+productID+" AND storeID = "+storeID+" ");

            while(rs.next())
            {
                Integer integer = rs.getInt("Total_Stock");

                if(integer != null)
                {
                    int total_stock = integer.intValue();
                    System.out.println("The total stock for the entered storeID: "+storeID+" and productID: "+productID+"  is: "+total_stock);

                }
                else
                {
                    System.out.println("The entered storeID or productID doesnt exist..please try again");
                }
            }

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }

    public static void getMerchandiseStock_product(int productID)
    {

        System.out.println("Getting total merchandise stock for the entered productID...");
        System.out.println();

//        try
//        {
//            stmt = conn.createStatement();
//
//            ResultSet rs = stmt.executeQuery("SELECT SUM(Quantity) AS Total_Stock FROM InventoryInfo WHERE productID = "+ productID);
//
//            while(rs.next())
//            {
//                Integer integer = rs.getInt("Total_Stock");
//
//                int total_stock = 0;
//
//                if(integer != null)
//                {
//                    total_stock = integer.intValue();
//                    System.out.println("The total stock for the entered productID: "+productID+"  is: "+ total_stock + " items.");
//
//                }
//                else
//                {
//                    System.out.println("The entered productID doesnt exist..please try again");
//                }
//            }
//
//        }
//        catch(Throwable oops)
//        {
//            oops.printStackTrace();
//        }

        try
        {
            stmt = conn.createStatement();
            HashMap<Integer, Integer> pc = new HashMap<>();

            ResultSet rs = stmt.executeQuery("SELECT storeID, quantity FROM InventoryInfo WHERE productID = "+ productID);

            while(rs.next())
            {
                Integer integer = rs.getInt("storeID");

                if(integer != null)
                {
                    rs = stmt.executeQuery("SELECT storeID, quantity FROM InventoryInfo WHERE productID = "+ productID);
                    while(rs.next())
                    {
                        pc.put(rs.getInt("storeID"), rs.getInt("quantity"));
                    }

                    System.out.println("storeID -> Quantity (for productID:"+productID+")");
                    System.out.println("----------------------------------");

                    for(Map.Entry m : pc.entrySet()){
                        System.out.println(m.getKey() + " -> " + m.getValue());
                    }


                }
                else
                {
                    System.out.println("The entered productID doesnt exist..please try again");
                }
            }

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }




    }


//    public static void getMerchandiseStock_store(int storeID)
//    {
//
//        System.out.println("Getting total merchandise stock for the entered storeID...");
//        System.out.println();
//
//        try
//        {
//            stmt = conn.createStatement();
//
//            ResultSet rs = stmt.executeQuery("SELECT SUM(Quantity) AS Total_Stock FROM InventoryInfo WHERE storeID = "+storeID);
//
//            while(rs.next())
//            {
//                Integer integer = rs.getInt("Total_Stock");
//                int total_stock;
//                if(integer != null)
//                {
//                    total_stock = integer.intValue();
//                    System.out.println("The total stock for the entered storeID: "+storeID+"  is: " + total_stock + " items");
//
//                }
//                else
//                {
//                    System.out.println("The entered storeID doesnt exist..please try again");
//                }
//            }
//            stmt.close();
//            conn.close();
//        }
//        catch(Throwable oops)
//        {
//            oops.printStackTrace();
//        }
//    }



    public static void getMerchandiseStock_store(int storeID)
    {

        HashMap<Integer, Integer> sk = new HashMap<>();

        System.out.println("Getting total merchandise stock for the entered storeID...");
        System.out.println();

        try
        {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT productID, quantity FROM InventoryInfo WHERE storeID = "+storeID);


                //Integer integer = rs.getInt("productID");

                if(rs.next())
                {
                    ResultSet rs1 = stmt.executeQuery("SELECT productID, quantity FROM InventoryInfo WHERE storeID = "+storeID);
                    while(rs1.next())
                    {
                        sk.put(rs1.getInt("productID"), rs1.getInt("quantity"));
                    }

                    System.out.println("productID -> Quantity (for storeID:"+storeID+")");
                    System.out.println("----------------------------------");

                    for(Map.Entry m : sk.entrySet()){
                        System.out.println(m.getKey() + " -> " + m.getValue()+" items");
                    }
//                    rs1.close();

                }
                else
                {
                    System.out.println("The entered storeID doesnt exist..please try again");
                }



        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }





    public static void getCustomerGrowthbyMonth(){

        System.out.println("Enter the year, whose monthly customer growth report you want (in format YYYY): ");
        String year = sc.next();
        System.out.println();

        System.out.println("Enter the month (in format MM): ");
        String month = sc.next();
        System.out.println();

        System.out.println("Getting total customer growth (across all stores) for the entered month...");
        System.out.println();

        try
        {
            stmt = conn.createStatement();
            //SELECT COUNT(customerID) FROM SignsUp WHERE signUpDate BETWEEN '2009-3-1' AND '2012-12-25';

            ResultSet rs = stmt.executeQuery("SELECT COUNT(customerID) AS total_enrolls FROM SignsUp WHERE MONTH(signUpDate) = \'"+month+"\' AND YEAR(signUpDate) = \'"+year+"\'");

            int total_enrolls = 0;

            while(rs.next())
            {
                Integer integer = rs.getInt("total_enrolls");

                total_enrolls = integer != null ? integer.intValue() : 0;

            }

            System.out.println("The total customer growth (across all stores) for the entered month is: "+ total_enrolls + " customers");

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }


    public static void getCustomerGrowthbyYear()
    {

        System.out.println("Enter the year, whose customer growth report you want (in format YYYY): ");
        String year = sc.next();
        System.out.println();

        System.out.println("Getting total customer growth (across all stores) for the entered year...");
        System.out.println();



        try
        {
            stmt = conn.createStatement();
            //SELECT COUNT(customerID) FROM SignsUp WHERE signUpDate BETWEEN '2009-3-1' AND '2012-12-25';

            ResultSet rs = stmt.executeQuery("SELECT COUNT(customerID) AS total_enrolls FROM SignsUp WHERE YEAR(signUpDate) = \'"+year+"\'");

            int total_enrolls = 0;

            while(rs.next())
            {
                Integer integer = rs.getInt("total_enrolls");

                total_enrolls = integer != null ? integer.intValue() : 0;

            }

            System.out.println("The total customer growth (across all stores) for the entered year is: " + total_enrolls + " customers");

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }

    public static void getSalesGrowth(){

        System.out.println("Enter the storeID: ");
        int storeID = sc.nextInt();
        System.out.println();

        System.out.println("Enter the start date (in format YYYY-MM-DD): ");
        String startDate = sc.next();
        System.out.println();

        System.out.println("Enter the end date (in format YYYY-MM-DD): ");
        String endDate = sc.next();
        System.out.println();


        System.out.println("Getting total sales for the entered store in the given time period...");
        System.out.println();



        try
        {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Store WHERE storeID = "+storeID);

            while(rs.next())
            {
                Integer integer = rs.getInt("storeID");

                if(integer != null)
                {
                    storeID = integer.intValue();

                    rs = stmt.executeQuery("SELECT SUM(TotalPrice) AS Total_Sales FROM Transactions WHERE storeID = "+storeID+" AND (PurchaseDate BETWEEN \'"+startDate+"\' AND \'"+endDate+"\')");

                    int total_sales = 0;

                    while(rs.next())
                    {
                        integer = rs.getInt("Total_Sales");

                        total_sales = integer != null ? integer.intValue() : 0;

                    }

                    System.out.println("The total sales for the entered store in the given time period is: $"+total_sales);
                }
                else
                {
                    System.out.println("Invalid storeID entered...");
                }
            }
        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }



    public static void addNewInventory(){
        System.out.print("Enter the storeID: ");
        int storeID = sc.nextInt();
        System.out.println();

        System.out.print("Enter the productName: ");
        sc.nextLine();
        String productName = sc.nextLine();
        System.out.println();

        System.out.print("Enter the productionDate (in YYYY-MM-DD format): ");
        String productionDate = sc.next();
        System.out.println();

        System.out.print("Enter the expirationDate (in YYYY-MM-DD format): ");
        String expirationDate = sc.next();
        System.out.println();

        System.out.print("Enter the newly arrived items quantity: ");
        int quantity = sc.nextInt();
        System.out.println();

        System.out.print("Enter the buyPrice: ");
        int buyPrice = sc.nextInt();
        System.out.println();

        System.out.print("Enter the marketPrice: ");
        int marketPrice = sc.nextInt();
        System.out.println();

        System.out.print("Adding new arrivals to the inventory...");
        System.out.println();

        int productID = 0;
        Integer integer = 0;
        try
        {
            stmt = conn.createStatement();

            //rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = \'" + productName + "\' AND buyPrice = " + buyPrice + " AND marketPrice = " + marketPrice);
            rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = \'" + productName + "\'");

            if(!rs.next()){
                stmt.executeUpdate("INSERT INTO productTable(productName, buyPrice, marketPrice) VALUES (\'"+productName+"\',"+ buyPrice+","+ marketPrice+")");
                rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = \'"+productName+"\' AND buyPrice = "+buyPrice+" AND marketPrice = "+marketPrice);

                while(rs.next())
                {
                    integer = rs.getInt("productID");
                    productID = integer.intValue();

                }
            }else{
                integer = rs.getInt("productID");
                productID = integer.intValue();

                stmt.executeUpdate("UPDATE productTable SET buyPrice = " + buyPrice + ", marketPrice = " + marketPrice + " WHERE productID = "+productID);
            }


            // if(rs.next() && integer != null)
            // {
            //     productID = integer.intValue();
            //
            // }
            // else
            // {
            //     stmt.executeUpdate("INSERT INTO productTable(productName, buyPrice, marketPrice) VALUES (\'"+productName+"\',"+ buyPrice+","+ marketPrice+")");
            //     rs = stmt.executeQuery("SELECT productID FROM productTable WHERE productName = \'"+productName+"\' AND buyPrice = "+buyPrice+" AND marketPrice = "+marketPrice);

            //     while(rs.next())
            //     {
            //         integer = rs.getInt("productID");
            //         productID = integer.intValue();
            //
            //     }
            // }


            rs = stmt.executeQuery("SELECT * FROM InventoryInfo WHERE productID = " + productID + " AND storeID = "+storeID);



            if(rs.next()){

                int old_quantity = rs.getInt("quantity");
                int updated_quantity = old_quantity + quantity;
                stmt = conn.createStatement();
                stmt.executeUpdate("UPDATE InventoryInfo SET quantity = "+updated_quantity+" WHERE productID = "+productID+" AND storeID = "+storeID);
                stmt.executeUpdate("UPDATE InventoryInfo SET productionDate = \'"+productionDate+"\' WHERE productID = "+productID+" AND storeID = "+storeID);
                stmt.executeUpdate("UPDATE InventoryInfo SET expirationDate = \'"+expirationDate+"\' WHERE productID = "+productID+" AND storeID = "+storeID);

            }else{
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES(" + productID + "," + storeID + ",\'" + productionDate + "\',\'" + expirationDate + "\', " + quantity + ")");

            }



            // while(rs.next())
            // {
            //     integer = rs.getInt("quantity");

            //     if(rs.next() && integer != null)
            //     {
            //         int old_quantity = integer.intValue();
            //         int updated_quantity = old_quantity + quantity;
            //         stmt = conn.createStatement();
            //         stmt.executeUpdate("UPDATE InventoryInfo SET quantity = "+updated_quantity+" WHERE productID = "+productID+" AND storeID = "+storeID);
            //         stmt.executeUpdate("UPDATE InventoryInfo SET productionDate = \'"+productionDate+"\' WHERE productID = "+productID+" AND storeID = "+storeID);
            //         stmt.executeUpdate("UPDATE InventoryInfo SET expirationDate = \'"+expirationDate+"\' WHERE productID = "+productID+" AND storeID = "+storeID);
            //
            //     }else{
            //         stmt = conn.createStatement();
            //         stmt.executeUpdate("INSERT INTO InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES(" + productID + "," + storeID + ",\'" + productionDate + "\',\'" + expirationDate + "\', " + quantity + ")");
            //
            //     }
            // }

            System.out.println("Successfully added newly arrived inventory....");

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();
        }
    }



    public static void updateProductInInventory(){
        System.out.print("enter productID to be updated");
        int productID=sc.nextInt();
        System.out.println();

        System.out.println("Choose the field to be updated");
        System.out.println("1: Product Name");
        System.out.println("2: Buy price");
        System.out.println("3: market price");

        int option= sc.nextInt();

        switch(option){
            case 1:
                System.out.println("enter new product name:");
                sc.nextLine();
                String productName=sc.next();
                try{
                    stmt=conn.createStatement();
                    stmt.executeUpdate("UPDATE productTable SET productName=\'"+ productName + "\'WHERE productID =" + productID);
                }
                catch(Throwable oops){
                    oops.printStackTrace();
                }
                break;
            case 2:
                System.out.println("enter Buy price to update:");
                String buyPrice=sc.next();
                try{
                    stmt=conn.createStatement();
                    stmt.executeUpdate("UPDATE productTable SET buyPrice=\'"+ buyPrice + "\'WHERE productID =" + productID);
                }
                catch(Throwable oops){
                    oops.printStackTrace();
                }
                break;
            case 3:
                System.out.println("enter marketprice to update:");
                String marketPrice=sc.next();
                try{
                    stmt=conn.createStatement();
                    stmt.executeUpdate("UPDATE productTable SET marketPrice=\'"+ marketPrice + "\'WHERE productID =" + productID);
                }
                catch(Throwable oops){
                    oops.printStackTrace();
                }
                break;
        }
    }



    public static void updateProductInStore(){
        System.out.print("enter productID to be to be updated");
        String productID = sc.next();
        System.out.println();

        System.out.println("enter storeID to be updated");
        String storeID= sc.next();

        System.out.println("1: productionDate");
        System.out.println("2: expiration Date");
        System.out.println("3: quantity");

        int option= sc.nextInt();

        switch(option){
            case 1:
                System.out.println("enter  production date in YYYY-MM-DD format to update:");
                String productionDate=sc.next();
                try{
                    stmt=conn.createStatement();
                    stmt.executeUpdate("UPDATE productTable SET productionDate = \'"+ productionDate + "\' WHERE productID =" + productID +" AND storeID = " +storeID);
                }
                catch(Throwable oops){
                    oops.printStackTrace();
                }
                break;
            case 2:
                System.out.println("enter expirationdate in YYYY-MM-DD to update :");
                String expirationDate=sc.next();
                try{
                    stmt=conn.createStatement();
                    stmt.executeUpdate("UPDATE productTable SET expirationDate= \'"+ expirationDate + "\' WHERE productID =" + productID+" AND storeID = " +storeID);
                }
                catch(Throwable oops){
                    oops.printStackTrace();
                }
                break;
            case 3:
                System.out.println("enter quantity  to update :");
                String quantity=sc.next();
                try{

                    stmt=conn.createStatement();
                    stmt.executeUpdate("UPDATE productTable SET quantity = "+ quantity + " WHERE productID =" + productID +" AND storeID = " + storeID);

                }catch(Throwable oops){
                    oops.printStackTrace();
                }
        }
    }


    public static void deleteProductInStore(){

        System.out.println("enter productID to be deleted");
        String productID = sc.next();
        System.out.println();

        System.out.print("enter storeID in which product to be deleted");
        String storeID = sc.next();
        System.out.println();

        try{
            stmt=conn.createStatement();
            stmt.executeUpdate("DELETE FROM InventoryInfo WHERE productID = " + productID + " AND storeID = " + storeID);
        }
        catch(Throwable oops){
            oops.printStackTrace();
        }

    }


    public static void returnItems(){
        System.out.print("Enter the trasactionID: ");
        int transactionID = sc.nextInt();
        System.out.println();

        System.out.print("Enter the productID: ");
        String productID = sc.next();
        System.out.println();

        System.out.print("Enter the returns quantity: ");
        int return_quantity = sc.nextInt();
        System.out.println();

        System.out.print("Returning Items....");
        System.out.println();

        int storeID = 0;
        double initial_total_price = 0;
        double new_total_price = 0;
        double price_diff = 0;
        int old_quantity = 0;
        int updated_quantity = 0;
        double prod_initial_final_price = 0;
        double prod_new_final_price = 0;
        int old_quantity_inventory = 0;
        int new_quantity_inventory = 0;

        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT TotalPrice, storeID FROM Transactions WHERE transactionID = " + transactionID);


            if(rs.next()){
                storeID = rs.getInt("storeID");
                initial_total_price = rs.getInt("TotalPrice");
            }else{
                System.out.println("Invalid TransactionID entered.");
                returnItems();
                return;
            }


            rs = stmt.executeQuery("SELECT * FROM ProductList WHERE productID = "+productID+" AND transactionID = " + transactionID);

            if(rs.next()){

                Integer integer = rs.getInt("quantity");
                old_quantity = integer.intValue();
                prod_initial_final_price = rs.getInt("finalPrice");

                double per_item_price = prod_initial_final_price/old_quantity;

                updated_quantity = old_quantity - return_quantity;
                prod_new_final_price = per_item_price*updated_quantity;

                price_diff = prod_initial_final_price - prod_new_final_price;
                new_total_price = initial_total_price - price_diff;

                stmt.executeUpdate("UPDATE ProductList SET quantity = "+updated_quantity+" WHERE productID = "+productID+" AND transactionID = "+transactionID);
                stmt.executeUpdate("UPDATE ProductList SET finalPrice = "+prod_new_final_price+" WHERE productID = "+productID+" AND transactionID = "+transactionID);

                stmt.executeUpdate("UPDATE Transactions SET TotalPrice = "+new_total_price+"WHERE storeID = "+storeID+" AND transactionID = "+transactionID);

            }else{
                System.out.println("Product List not found.");
            }


            rs = stmt.executeQuery("SELECT * FROM InventoryInfo WHERE productID = "+productID+" AND storeID = " + storeID);
            stmt.executeUpdate("INSERT INTO Store(15, Ahmedabad, 987655432)");

            if(rs.next())
            {
                Integer integer = rs.getInt("quantity");

                old_quantity_inventory = integer.intValue();

                new_quantity_inventory = old_quantity_inventory + return_quantity;

                stmt.executeUpdate("UPDATE InventoryInfo SET quantity = "+new_quantity_inventory+" WHERE productID = "+productID+" AND storeID = "+storeID);

            }
            else
            {
                System.out.println("Inventory Info not found...adding a new row.");
                String productionDate = "0000-00-00";
                String expirationDate = "0000-00-00";
                stmt.executeUpdate("INSERT into InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES("+productID+","+storeID+",\'"+productionDate+"\',\'"+expirationDate+"\',"+return_quantity+")");
            }

            System.out.println("Successfully returned the mentioned items.");

            conn.commit();

        }
        catch(Throwable oops)
        {
            oops.printStackTrace();

            try
            {
                System.out.println("Item return failed...rollback initiated.");
                conn.rollback();
                System.out.println();
            }
            catch (SQLException c)
            {
                c.printStackTrace();
            }
        }
        finally
        {
            try
            {
                conn.setAutoCommit(true);
            }
            catch (SQLException oops)
            {
                oops.printStackTrace();
            }
        }
    }

    public static void transferProductBetweenStores(){
        int availableInSender=0;
        int availableInReceiver=0;

        String productionDate = "";
        String expirationDate = "";

        System.out.print("Enter productID to be transferred:");
        String productID=sc.next();
        System.out.println();

        System.out.print("enter  storeID of product source");
        String senderStoreID=sc.next();
        System.out.println();

        System.out.print("enter  storeID of product destination");
        String receiverStoreID=sc.next();
        System.out.println();

        System.out.print(" enter quantity to be transferred");
        int quantity=sc.nextInt();
        System.out.println();


        try{

            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            rs=stmt.executeQuery("SELECT productionDate, expirationDate, quantity FROM InventoryInfo WHERE productID = " + productID+" AND storeID="+senderStoreID);

            while(rs.next()){
                productionDate = rs.getString("productionDate");


                expirationDate = rs.getString("expirationDate");


                availableInSender=rs.getInt("quantity");
            }

            int newQuantityInSender = availableInSender-quantity;


            if(availableInSender >= quantity){
                rs=stmt.executeQuery("SELECT quantity FROM InventoryInfo WHERE productID = " + productID + " AND storeID = " + receiverStoreID);

                if(rs.next()){
                    availableInReceiver=rs.getInt("quantity");

                    int newQuantityInReceiver=availableInReceiver+quantity;
                    stmt=conn.createStatement();
                    stmt.executeUpdate("UPDATE InventoryInfo SET quantity= "+ newQuantityInSender + " WHERE storeID =" + senderStoreID);
                    stmt.executeUpdate("UPDATE InventoryInfo SET quantity= "+ newQuantityInReceiver + " WHERE storeID =" + receiverStoreID);

                }else{

                    stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE InventoryInfo SET quantity = "+ newQuantityInSender + " WHERE storeID =" + senderStoreID);
                    stmt.executeUpdate("INSERT INTO InventoryInfo(productID, storeID, productionDate, expirationDate, quantity) VALUES (" + productID+ "," + receiverStoreID + ",\'" + productionDate+ "\',\'"+ expirationDate + "\',"+quantity + ")");

                }

            }else{
                System.out.println("Insufficient quantity in source store");
            }

            conn.commit();


        }
        catch(Throwable oops)
        {
            oops.printStackTrace();

            try
            {
                System.out.println("Transfer of product failed...rollback initiated.");
                conn.rollback();
                System.out.println();
            }
            catch (SQLException c)
            {
                c.printStackTrace();
            }
        }
        finally
        {
            try
            {
                conn.setAutoCommit(true);
            }
            catch (SQLException oops)
            {
                oops.printStackTrace();
            }
        }
    }


    public static void makeTransaction(){

        System.out.println("enter cashierID");
        String cashierID=sc.next();

        double TotalPrice=0;
        int transactionID = 0;
        int discountID=0;
        int storeID=0;
        boolean flag=true;
        double finalPrice=0;
        int marketPrice=0;
        String discountApplied= "false";

        System.out.print("enter customerID : ");
        String customerID= sc.next();
        System.out.println();

        System.out.print("enter date of purchase(YYYY-MM-DD) : ");
        String purchaseDate=sc.next();
        System.out.println();

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT storeID FROM Staff WHERE staffID = " + cashierID);

            if(rs.next()){

                storeID=rs.getInt("storeID");


                try{
                    stmt = conn.createStatement();
                    stmt.executeUpdate("INSERT INTO Transactions(storeID, customerID, cashierID, PurchaseDate, TotalPrice) VALUES (" + storeID+ "," + customerID+ "," + cashierID+ ",\'" + purchaseDate+ "\',"+ TotalPrice + ")");
                    ResultSet rs1 = stmt.executeQuery("SELECT transactionID FROM Transactions WHERE storeID = "+storeID+" AND customerID = "+customerID+" AND cashierID = "+cashierID+" AND PurchaseDate = \'"+purchaseDate+"\' AND TotalPrice = "+TotalPrice);

                     while(rs1.next()){
                        transactionID = rs1.getInt("transactionID");
                     }

//                    if(rs1.next()){
//                        transactionID = rs1.getInt("transactionID");
//                    }


                    while(flag){
                        System.out.print("enter productID : ");
                        String productID=sc.next();
                        System.out.println();

                        System.out.print("enter quantity : ");
                        int reqquantity=sc.nextInt();
                        System.out.println();

                        stmt = conn.createStatement();
                        ResultSet rs2 = stmt.executeQuery("SELECT marketPrice FROM productTable WHERE productID="+productID);

                        String startDate = "";
                        String endDate = "";


                        if(rs2.next()){
                            marketPrice= rs2.getInt("marketPrice");
                        }

                        ResultSet rs3 = stmt.executeQuery("SELECT startDate, endDate FROM Discount WHERE productID = " + productID + " AND storeID = "+ storeID);

                        if(rs3.next()){
                            startDate = rs3.getString("startDate");
                            endDate = rs3.getString("endDate");
                        }

                        ResultSet rs4 =stmt.executeQuery("SELECT discountID FROM Discount WHERE productID = " + productID + " AND storeID = "+ storeID + " AND \'" + purchaseDate + "\' BETWEEN \'" + startDate + "\' AND \'" + endDate + "\'");
                        //System.out.println(rs4.getInt("discountID"));

                        if(rs4.next()){

                            discountID=rs4.getInt("discountID");

                            ResultSet res = stmt.executeQuery("SELECT DiscountPercentage FROM Discount WHERE discountID = "+ discountID);

                            int discountPercentage = 0;

                            while(res.next()){
                                discountPercentage = res.getInt("DiscountPercentage");
                                int total = reqquantity*marketPrice;

                                finalPrice= total - (total*discountPercentage)/100;
                                TotalPrice=TotalPrice+finalPrice;
                                discountApplied="true";
                            }

                        }else{

                            discountApplied="false";
                            finalPrice=reqquantity *marketPrice;
                            TotalPrice=TotalPrice+finalPrice;

                        }

                        ResultSet res1 = stmt.executeQuery("SELECT quantity FROM InventoryInfo WHERE productID = "+ productID + " AND storeID = " + storeID);

                        int currQuantity = 0;

                        if(res1.next()){
                            currQuantity = res1.getInt("quantity");
                            if(currQuantity >= reqquantity){
                                currQuantity -= reqquantity;
                                stmt.executeUpdate("UPDATE InventoryInfo SET quantity = " + currQuantity + " WHERE productID= " + productID +" AND storeID ="+storeID);
                                stmt.executeUpdate("INSERT INTO ProductList(transactionID, productID, quantity, discountApplied, finalPrice) VALUES (" + transactionID+ "," + productID+ "," + reqquantity + "," + discountApplied+ ","+ finalPrice + ")");
                            }else{
                                System.out.println("Not enough quantity!!!");
                            }
                        }else{
                            System.out.println("Product not in store!!!");
                        }

                        // stmt.executeUpdate("UPDATE InventoryInfo SET quantity = quantity - " + reqquantity + " WHERE productID= " + productID +" AND storeID ="+storeID);
                        // stmt.executeUpdate("INSERT INTO ProductList(transactionID, productID, quantity, discountApplied, finalPrice) VALUES (\'" + transactionID+ "\',\'" + productID+ "\',\'" + quantity+ "\',\'" + discountApplied+ "\',\'"+ finalPrice + "\')");

                        System.out.print("do you want to add more products (y/n) : ");
                        char option = sc.next().charAt(0);
                        System.out.println();

                        if(option == 'y' || option == 'Y'){
                            continue;
                        }else{
                            flag=false;
                        }
                    }

                    try{
                        stmt = conn.createStatement();
                        System.out.println(TotalPrice);
                        stmt.executeUpdate("UPDATE Transactions SET TotalPrice = " + TotalPrice + " WHERE transactionID = " + transactionID );
                    }
                    catch(Throwable oops){
                        oops.printStackTrace();
                    }

                }catch(Throwable oops){
                    oops.printStackTrace();
                }

            }else{
                System.out.println("invalid cashier");
            }

        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }



    public static void generatebills(){
        System.out.print("enter staffID : ");
        String staffID= sc.next();
        System.out.println();

        System.out.print("enter supplierID");
        String supplierID=sc.next();
        System.out.println();

        System.out.print("enter Billing date in (YYYYY-MM-DD) format:");
        String billingDate=sc.next();
        System.out.println();

        int billingPrice=0;

        try{
            Statement stmt4;
            stmt4 = conn.createStatement();
            ResultSet rs = stmt4.executeQuery("SELECT orderID FROM ordersInfo WHERE supplierID = "+supplierID);

            while(rs.next()){

                billingPrice = 0;
                int orderID = rs.getInt("orderID");
                System.out.println("OrderID : " + orderID);
                Statement stmt1;
                stmt1 = conn.createStatement();


                ResultSet rs1 = stmt1.executeQuery("SELECT orderID  FROM Bills WHERE orderID = "+orderID);
                System.out.println("OrderID : " + orderID);

                if(rs1.next()){
                    System.out.println("Bill already generated!!!");
                    continue;
                }else{

                    Statement stmt2;
                    stmt2 = conn.createStatement();
                    ResultSet rs2=stmt2.executeQuery("SELECT productID, quantity FROM orderProductInfo WHERE orderID = "+orderID);

                    while(rs2.next()){
                        int productID = rs2.getInt("productID");
                        int quantity = rs2.getInt("quantity");
                        System.out.println("productID = " + productID + " quantity = " + quantity);

                        Statement stmt3;
                        stmt3 = conn.createStatement();
                        ResultSet rs3 = stmt3.executeQuery("SELECT buyPrice FROM productTable WHERE productID = "+productID);

                        while(rs3.next()){
                            int buyPrice = rs3.getInt("buyPrice");
                            billingPrice= billingPrice + (quantity * buyPrice);
                            System.out.println("billing total = " + billingPrice);
                        }
                        stmt3.close();
                    }

                    stmt2.close();
                }
                stmt1.close();

                Statement stmt3;
                stmt3 = conn.createStatement();
                stmt3.executeUpdate("INSERT into Bills(orderID, staffID, supplierID, billingDate, billingPrice) VALUES(" + orderID + ", " + staffID+","+ supplierID + ", \'" +billingDate+"\', " + billingPrice + ")");
                stmt3.close();
            }

            stmt4.close();
        }
        catch(Throwable oops){
            oops.printStackTrace();
        }
    }

}