package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Addressbook {
    List<Contact> addressbookList =new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    String classname="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/paddressbook?useSSL=False";
    String username="root";
    String password="root";

    public Connection getCon()
    {
        Connection con = null;
        try {
            Class.forName(classname);
            con= DriverManager.getConnection(url, username, password);
            if(con!=null)
            {
                System.out.println("connected..");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return con;
    }

    public void showAllContactFromDatabase() {
        Connection con = getCon();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from addressbook");
            System.out.println("---------Records are---------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            System.out.println("-----------------------------");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addAllContactToDatabase(){
        addressbookList.forEach(contact -> {
            Connection con=getCon();
            try {
                String sql="insert into addressbook(firstname,lastname,city) values (?,?,?)";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, contact.getFirstName());
                ps.setString(2, contact.getLastName());
                ps.setString(3, contact.getCity());
                int i=ps.executeUpdate();
                if(i>0)
                {
                    System.out.println(i+"row affected");
                }
                con.close();
                System.out.println("\nSave in Database successfully..... \n");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        });
    }

    public void addContactInAddressbook(){
        System.out.print("how many contact you want to save :");
        int number= sc.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("insert contact : "+(i+1));
            System.out.println("First Name : ");
            String firstName=sc.next();
            System.out.println("Last Name : ");
            String lastName=sc.next();
            System.out.println("City Name : ");
            String city=sc.next();
            Contact contact = new Contact(firstName,lastName,city);
            addressbookList.add(contact);
            System.out.println("---------------------\n");
        }
        System.out.println("\nadded successfully..... \n");
    }
}
