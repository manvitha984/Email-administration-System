import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Email {
    private String sender;
    private String subject;
    private String content;

    public Email(String sender, String subject, String content) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "From: " + sender + "\nSubject: " + subject + "\nContent: " + content;
    }
}

class EmailInbox {
    private List<Email> emails = new ArrayList<>();

    public void receiveEmail(Email email) {
        emails.add(email);
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void deleteEmail(int index) {
        if (index >= 0 && index < emails.size()) {
            emails.remove(index);
            System.out.println("Email deleted.");
        } else {
            System.out.println("Invalid email index.");
        }
    }
}

class Contact {
    private String name;
    private String email;
    private char gender;

    public Contact(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email + "\nGender: " + gender;
    }
}

class ContactManager {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added.");
    }

    public void viewContacts() {
        System.out.println("----- Contacts -----");
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println("--------------");
        }
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            System.out.println("Contact deleted.");
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    public Contact getContactByEmail(String email) {
        for (Contact contact : contacts) {
            if (contact.getEmail().equalsIgnoreCase(email)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> filterContactsByGender(char gender) {
        List<Contact> filteredContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            if (Character.toLowerCase(contact.getGender()) == Character.toLowerCase(gender)) {
                filteredContacts.add(contact);
            }
        }

        return filteredContacts;
    }
}

class EmailGenerator {
    private String firstName;
    private String lastName;
    private String department;
    private char gender;
    private String company = "company.com";
    private String email;
    private String password;
    private int mailboxCapacity;

    public EmailGenerator(String firstName, String lastName, String department, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.gender = gender;
        generateEmail();
        generateRandomPassword();
        generateRandomMailboxCapacity();
    }

    private void generateEmail() {
        if (department.isEmpty()) {
            email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + company;
        } else {
            email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department.toLowerCase() + "." + company;
        }
    }

    private void generateRandomPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "@#$%^&_.";

        String allCharacters = upperCaseLetters + lowerCaseLetters + numbers + specialCharacters;

        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(allCharacters.length());
            passwordBuilder.append(allCharacters.charAt(index));
        }

        password = passwordBuilder.toString();
    }

    private void generateRandomMailboxCapacity() {
        Random random = new Random();
        mailboxCapacity = random.nextInt(1000) + 1;
    }

    public void changePassword(String newPassword) {
        System.out.println("Email: " + getEmail());
        System.out.println("New password generated: " + newPassword);
        password = newPassword;
    }

    public void setMailboxCapacity(int capacity) {
        mailboxCapacity = capacity;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getPassword() {
        return password;
    }

    public List<String> generateAdditionalEmails(int count) {
        List<String> additionalEmails = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            String additionalEmail = generateAdditionalEmail(i);
            additionalEmails.add(additionalEmail);
        }

        return additionalEmails;
    }

    private String generateAdditionalEmail(int index) {
        String specialCharacters = "@#%^&";
        StringBuilder additionalEmailBuilder = new StringBuilder();

        if (department.isEmpty()) {
            additionalEmailBuilder.append(firstName.toLowerCase())
                    .append(".")
                    .append(lastName.toLowerCase())
                    .append(index)
                    .append("@")
                    .append(company);
        } else {
            additionalEmailBuilder.append(firstName.toLowerCase())
                    .append(".")
                    .append(lastName.toLowerCase())
                    .append(index)
                    .append("@")
                    .append(department.toLowerCase())
                    .append(".")
                    .append(company);
        }

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int charIndex = random.nextInt(specialCharacters.length());
            additionalEmailBuilder.insert(random.nextInt(additionalEmailBuilder.length()), specialCharacters.charAt(charIndex));
        }

        return additionalEmailBuilder.toString();
    }
}

public class App4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EmailGenerator emailGenerator = createEmailGenerator(scanner);
        EmailInbox emailInbox = new EmailInbox();
        ContactManager contactManager = new ContactManager();

        Contact sampleContact = new Contact("John Doe", "john.doe@company.com", 'M');
        contactManager.addContact(sampleContact);

        displayUserInformation(emailGenerator);

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    contactManager.viewContacts();
                    break;
                case 2:
                    addContact(scanner, contactManager);
                    break;
                case 3:
                    deleteContact(scanner, contactManager);
                    break;
                case 4:
                    changePassword(emailGenerator, scanner);
                    break;
                case 5:
                    displayPassword(emailGenerator);
                    break;
                case 6:
                    filterContactsByGender(scanner, contactManager);
                    break;
                case 7:
                    changeMailboxCapacity(emailGenerator, scanner);
                    break;
                case 8:
                    generateAdditionalEmails(emailGenerator, scanner);
                    break;
                case 9:
                    System.out.println("Exiting Email Administration. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 9);
    }

    private static EmailGenerator createEmailGenerator(Scanner scanner) {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your gender (M/F): ");
        char gender = scanner.next().charAt(0);
        scanner.nextLine();

        System.out.print("Enter your department (leave blank if none): ");
        String department = scanner.nextLine();

        return new EmailGenerator(firstName, lastName, department, gender);
    }

    private static void displayUserInformation(EmailGenerator emailGenerator) {
        System.out.println("User Information:");
        System.out.println("Name: " + emailGenerator.getName());
        System.out.println("Email: " + emailGenerator.getEmail());
        System.out.println("Mailbox Capacity: " + emailGenerator.getMailboxCapacity() + " MB");
        System.out.println("Password: " + emailGenerator.getPassword());
    }

    private static void displayMenu() {
        System.out.println("----- Menu -----");
        System.out.println("1. View Contacts");
        System.out.println("2. Add Contact");
        System.out.println("3. Delete Contact");
        System.out.println("4. Change Password");
        System.out.println("5. Display Current Password");
        System.out.println("6. Filter Contacts by Gender");
        System.out.println("7. Change Mailbox Capacity");
        System.out.println("8. Generate Additional Emails");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addContact(Scanner scanner, ContactManager contactManager) {
        System.out.print("Enter contact name: ");
        String contactName = scanner.nextLine();

        System.out.print("Enter contact email address: ");
        String contactEmail = scanner.nextLine();

        System.out.print("Enter contact gender (M/F): ");
        char contactGender = scanner.next().charAt(0);
        scanner.nextLine();

        Contact newContact = new Contact(contactName, contactEmail, contactGender);
        contactManager.addContact(newContact);
    }

    private static void deleteContact(Scanner scanner, ContactManager contactManager) {
        System.out.print("Enter the index of the contact you want to delete: ");
        int contactIndex = scanner.nextInt();
        contactManager.deleteContact(contactIndex);
    }

    private static void changePassword(EmailGenerator emailGenerator, Scanner scanner) {
        System.out.print("Do you want to change the password? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();

        if ("yes".equals(response)) {
            System.out.print("Enter the new password: ");
            String newPassword = scanner.nextLine();
            emailGenerator.changePassword(newPassword);
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Password not changed.");
        }
    }

    private static void displayPassword(EmailGenerator emailGenerator) {
        System.out.println("Current Password: " + emailGenerator.getPassword());
    }

    private static void filterContactsByGender(Scanner scanner, ContactManager contactManager) {
        System.out.print("Enter gender to filter (M/F): ");
        char gender = scanner.next().charAt(0);
        scanner.nextLine();

        List<Contact> filteredContacts = contactManager.filterContactsByGender(gender);

        if (!filteredContacts.isEmpty()) {
            System.out.println("----- Filtered Contacts -----");
            for (Contact contact : filteredContacts) {
                System.out.println(contact);
                System.out.println("--------------");
            }
        } else {
            System.out.println("No contacts found for the specified gender.");
        }
    }

    private static void changeMailboxCapacity(EmailGenerator emailGenerator, Scanner scanner) {
        System.out.print("Do you want to change the mailbox capacity? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();

        if ("yes".equals(response)) {
            System.out.print("Enter the new mailbox capacity (in MB): ");
            int newCapacity = scanner.nextInt();
            emailGenerator.setMailboxCapacity(newCapacity);
            System.out.println("Mailbox capacity changed successfully!");
            System.out.println("Email: " + emailGenerator.getEmail());
            System.out.println("Password: " + emailGenerator.getPassword());
            System.out.println("New Mailbox Capacity: " + emailGenerator.getMailboxCapacity() + " MB");
        } else {
            System.out.println("Mailbox capacity not changed.");
        }
    }

    private static void generateAdditionalEmails(EmailGenerator emailGenerator, Scanner scanner) {
        System.out.print("Enter the number of additional emails to generate: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<String> additionalEmails = emailGenerator.generateAdditionalEmails(count);

        System.out.println("The generated mails are:");
        for (String additionalEmail : additionalEmails) {
            System.out.println(additionalEmail);
        }
    }
}