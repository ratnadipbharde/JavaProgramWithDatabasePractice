package org.example;

import java.util.Scanner;

public class Main {
    static final int ADDCONTACTSINADDRESSBOOK = 1;
    static final int SHOWCONTACTSFROMDATABASE = 2;
    static final int EDITCONTACT = 3;
    static final int DELETECONTACT = 4;
    static final int SEARCHBYCITYORSTATE = 5;
    static final int COUNTBYCITYORSTATE = 6;
    static final int SORTBYPERSONNAME = 7;
    static final int EXIT = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Addressbook addressbook = new Addressbook();
        while (true) {
            System.out.println("\n\t\t--------- Welcome to Addressbook ---------\n");
            System.out.print("0. Exit.\n1. Add contacts in ContactList.\n2. Show all contacts from database.\n3. Edit Contact." +
                    "\n4. Delete Contact.\n5. Search by City or State.\n6. Count by City or State.\n7. Search by name given city\n");
            System.out.print("\nInput your Choice : ");
            switch (sc.nextInt()) {
                case ADDCONTACTSINADDRESSBOOK:
                    addressbook.addContactInAddressBookDatabase();
                    break;
                case SHOWCONTACTSFROMDATABASE:
                    addressbook.showAllContactFromDatabase();
                    break;
                case EDITCONTACT:
                    addressbook.editContact();
                    break;
                case DELETECONTACT:
                    addressbook.deleteData();
                    break;
                case SEARCHBYCITYORSTATE:
                    addressbook.retriveContactByCityOrState();
                    break;
                    case COUNTBYCITYORSTATE:
                    addressbook.getCountByCityOrState();
                    break;
                    case SORTBYPERSONNAME:
                    addressbook.getSortedAlphabeticallybyPersonForCity();
                    break;
                case EXIT:
                    return;
            }
        }
    }
}