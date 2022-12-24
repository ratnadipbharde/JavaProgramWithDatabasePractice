package org.example;

import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Addressbook {
    List<Contact> addressbookList =new ArrayList<>();
    Scanner sc=new Scanner(System.in);

    public void addContactInAddressbook(){
        System.out.print("how many contact you want to save :");
        int number= sc.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("insert contact : "+i);
            System.out.println("First Name : ");
            String firstName=sc.next();
            System.out.println("Last Name : ");
            String lastName=sc.next();
            System.out.println("City Name : ");
            String city=sc.next();
            Contact contact = new Contact(firstName,lastName,city);
            addressbookList.add(contact);
        }

    }
}
