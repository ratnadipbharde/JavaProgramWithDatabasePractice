package org.example;
import java.util.Scanner;

public class Main {
    static final int ADDCONTACTSINADDRESSBOOK =1;
    static final int EXIT =0;
    public static void main(String[] args) {
        System.out.println("Welcome to Addressbook");
        System.out.println("1.Add contacts in ContactList");
        Scanner sc = new Scanner(System.in);
        Addressbook addressbook=new Addressbook();
        while (true) {
            switch (sc.nextInt()) {
                case ADDCONTACTSINADDRESSBOOK:
                  addressbook.addContactInAddressbook();
                  break;
                case EXIT:
                  return;
            }
        }
    }
}