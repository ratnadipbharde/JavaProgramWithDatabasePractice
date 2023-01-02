package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Addressbook {
    static final int FIRSTNAME=1;
    static final int LASTNAME=2;
    static final int CITY=3;
    static final int STATE=4;
    static final int PHONENUMBER=5;
    static final int EMAIL=6;
    static final int SEARCHCITY=1;
    static final int SEARCHSTATE=2;
    static final int COUNTCITY=1;
    static final int COUNTSTATE=2;

    Scanner sc = new Scanner(System.in);
    String classname = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/paddressbook?useSSL=False";
    String username = "root";
    String password = "root";

    public Connection getCon() {
        Connection con = null;
        try {
            Class.forName(classname);
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("connected..");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }


    public void getSortedAlphabeticallybyPersonForCity(){
        System.out.println("Enter City to get Contact Sorted Order : ");
        String city=sc.next();
        Connection con = getCon();
        try {
            String query="select * from addressbook where City=? ORDER by FirstName ASC";
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1, city);
            ResultSet rs=st.executeQuery();
            System.out.println("---------Records are---------");
            while (rs.next()) {
                Contact contact = new Contact(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                System.out.println(contact);
            }
            System.out.println("-----------------------------");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getCountByCityOrState(){
        System.out.println("\n1. Count By City\n2. Count By State");
        System.out.println("Choose option for Count : ");
        switch (sc.nextInt()){
            case COUNTCITY:
                System.out.println("Enter City Name : ");
                String city=sc.next();
                System.out.println("count : "+countByCityOrState(COUNTCITY,city));
                break;
            case COUNTSTATE:
                System.out.println("Enter State Name : ");
                String state=sc.next();
                System.out.println("count : "+countByCityOrState(COUNTSTATE,state));
                break;
            default:
                System.out.println("invalid input");
        }
    }

    private int countByCityOrState(int choice,String cityOrState) {
        Connection con = getCon();
        int count = 0;
        try {
            String query;
            if (choice==1){
                query="select COUNT(*) FROM addressbook where city=?";
            }
            else {
                query="select COUNT(*) FROM addressbook where state=?";
            }
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1, cityOrState);
            ResultSet rs=st.executeQuery();
            rs.next();
            count = rs.getInt(1);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
       return count;
    }

    public void retriveContactByCityOrState(){
        System.out.println("\n1. Search By City\n2. Search By State");
        System.out.println("Choose option for search : ");
        switch (sc.nextInt()){
            case SEARCHCITY:
                System.out.println("Enter City Name : ");
                String city=sc.next();
                searchContactBy(SEARCHCITY,city);
                break;
            case SEARCHSTATE:
                System.out.println("Enter State Name : ");
                String state=sc.next();
                searchContactBy(SEARCHSTATE,state);
                break;
            default:
                System.out.println("invalid input");
        }
    }

    public void searchContactBy(int choice,String cityOrState){
        Connection con = getCon();
        try {
            String query;
            if (choice==1){
               query="select * from addressbook where City=?";
            }
            else {
               query="select * from addressbook where State=?";
            }
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1, cityOrState);
            ResultSet rs=st.executeQuery();
            System.out.println("---------Records are---------");
            while (rs.next()) {
                Contact contact = new Contact(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                System.out.println(contact);
            }
            System.out.println("-----------------------------");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteData() {
        System.out.println("Enter First Name");
        String firstName= sc.next();
        Connection con = getCon();
        try {
            String sql="delete from addressbook where FirstName=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, firstName);
            int i=ps.executeUpdate();
            if(i>0)
            {
                System.out.println(i+"row deleted.");
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void editContact() {
        System.out.println("--------------------------------------------------------" + "\nEdit Contact Details\n--------------------------------------------------------");
        Contact contact = new Contact();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nsearch detail for edit.........");
        System.out.print("Name : ");
        String fName = sc.next();
        Connection con=getCon();
        try {
            PreparedStatement st=con.prepareStatement("select * from addressbook where FirstName=?");
            st.setString(1, fName);
            ResultSet rs=st.executeQuery();
            System.out.println("---------Records are---------");
            while(rs.next())
            {
                contact.setFirstName(rs.getString(1));
                contact.setLastName(rs.getString(2));
                contact.setCity(rs.getString(3));
                contact.setState(rs.getString(4));
                contact.setPhoneNumber(rs.getString(5));
                contact.setEmail(rs.getString(6));
                System.out.println(contact);
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                System.out.print("\n1. First Name\n2. Last Name\n3. City\n4. State\n5. Phone Number\n6. Email\ninsert choice to edit Contact details : ");
                switch (sc.nextInt()) {
                    case FIRSTNAME:
                        System.out.print("Name : ");
                        contact.setFirstName(sc.next());
                        updateData(fName,contact);
                        break;
                    case LASTNAME:
                        System.out.print("Last Name : ");
                        contact.setLastName(sc.next());
                        updateData(fName,contact);
                        break;
                    case CITY:
                        System.out.print("City : ");
                        contact.setCity(sc.next());
                        updateData(fName,contact);
                        break;
                    case STATE:
                        System.out.print("State : ");
                        contact.setState(sc.next());
                        updateData(fName,contact);
                        break;
                    case PHONENUMBER:
                        System.out.print("Phone Number : ");
                        contact.setPhoneNumber(sc.next());
                        updateData(fName,contact);
                        break;
                    case EMAIL:
                        System.out.print("Email : ");
                        contact.setEmail(sc.next());
                        updateData(fName,contact);
                        break;
                    default:
                        System.out.println("invalid input");
                }
        }

   public void updateData(String fName,Contact contact) {
        Connection con = getCon();
        try {
            String sql = "update addressbook set Firstname=?, Lastname=?, City=?,State=?, PhoneNumber=?, Email=? where firstname=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getCity());
            ps.setString(4, contact.getState());
            ps.setString(5, contact.getPhoneNumber());
            ps.setString(6, contact.getEmail());
            ps.setString(7, fName);
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println(i + "row Updated..");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllContactFromDatabase() {
        Connection con = getCon();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from addressbook");
            System.out.println("---------Records are---------");
            while (rs.next()) {
                Contact contact = new Contact(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                System.out.println(contact);
            }
            System.out.println("-----------------------------");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addContactInAddressBookDatabase() {
        System.out.print("how many contact you want to save :");
        int number = sc.nextInt();
        for (int j = 0; j < number; j++) {
            System.out.println("insert contact : " + (j + 1));
            System.out.println("First Name : ");
            String firstName = sc.next();
            System.out.println("Last Name : ");
            String lastName = sc.next();
            System.out.println("City Name : ");
            String city = sc.next();
            System.out.println("State Name : ");
            String state = sc.next();
            System.out.println("Phone Number : ");
            String phoneNumber = sc.next();
            System.out.println("Email : ");
            String email = sc.next();
            Contact contact = new Contact(firstName, lastName, city, state, phoneNumber, email);
            Connection con = getCon();
            try {
                String sql = "insert into addressbook(FirstName, LastName, City, state, PhoneNumber, Email) values (?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, contact.getFirstName());
                ps.setString(2, contact.getLastName());
                ps.setString(3, contact.getCity());
                ps.setString(4, contact.getState());
                ps.setString(5, contact.getPhoneNumber());
                ps.setString(6, contact.getEmail());
                int i = ps.executeUpdate();
                if (i > 0) {
                    System.out.println(i + "row affected");
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("---------------------\n");
        }
        System.out.println("\nadded successfully..... \n");
    }
}
