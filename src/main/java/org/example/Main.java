package org.example;

import java.util.Scanner;

public class Main {
    static final int ADDCONTACTSINADDRESSBOOK = 1;
    static final int ADDALLCONTACTSTODATABASE = 2;
    static final int ADDALLCONTACTSFROMDATABASE = 3;
    static final int EXIT = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Addressbook addressbook = new Addressbook();
        while (true) {
            System.out.println("\n\t\t--------- Welcome to Addressbook ---------\n");
            System.out.print("0. Exit.\n1. Add contacts in ContactList.\n2. Save all contacts to database.\n3. Show all contacts from database.\n");
            System.out.print("\nInput your Choice : ");
            switch (sc.nextInt()) {
                case ADDCONTACTSINADDRESSBOOK:
                    addressbook.addContactInAddressbook();
                    break;
                case ADDALLCONTACTSTODATABASE:
                    addressbook.addAllContactToDatabase();
                    break;
                case ADDALLCONTACTSFROMDATABASE:
                    addressbook.showAllContactFromDatabase();
                    break;
                case EXIT:
                    return;
            }
        }
    }
}