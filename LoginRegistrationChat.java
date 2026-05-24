/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.github.starboyisunkown.loginregistrationchat;

/**
 *
 * @author makhosini
 */
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;

public class LoginRegistrationChat {

    private String firstName;
    private String lastName;
    private String storedPhone;

    // USERNAME CHECK
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // PASSWORD CHECK
    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]).{8,}$";
        return password.matches(regex);
    }

    // PHONE CHECK
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return Pattern.matches("^\\+27\\d{9}$", phoneNumber);
    }

    // LOGIN CHECK
    public boolean loginUser(String enteredUser, String enteredPass, String storedUser, String storedPass) {
        return enteredUser.equals(storedUser) && enteredPass.equals(storedPass);
    }

    // LOGIN STATUS
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you.";
        } else {
            return "Username or password incorrect.";
        }
    }

    public static class QuickChatApp {
        private int totalMessagesSent = 0;
        private String messagesLog = ""; 

        //Text message id check
        public boolean checkMessageID(String messageID) {
            return messageID != null && messageID.length() <= 10;
        }

        //Cellphone number check
        public String checkRecipientCell(String recipient) {
            if (recipient.matches("^\\+27\\d{9}$")) {
                return "Valid";
            } else {
                return "Invalid recipient number. Must start with +27 and contain 9 digits after.";
            }
        }

        // # Message
        public String createMessageHash(String messageID, int messageNum, String messageBody) {
            String[] words = messageBody.trim().split("\\s+");
            String firstWord = words[0];
            String lastWord = words[words.length - 1];

            String hash = messageID.substring(0, 2) 
                        + ":" + messageNum 
                        + ":" + (firstWord + lastWord);
            
            return hash.toUpperCase();
        }

        // Option list to choose from
        public String SentMessage(Scanner input) {
            System.out.println("\nOnce the message is completed, choose one of the following options:");
            System.out.println("· Send Message (Type 'Send')");
            System.out.println("· Disregard Message (Type '0')");
            System.out.println("· Store Message to send later (Type 'Store')");
            System.out.print("Your choice: ");
            
            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("Send")) {
                totalMessagesSent++;
                System.out.println("Message successfully sent");
                return "Sent";
            } else if (choice.equals("0")) {
                System.out.println("Press 0 to delete the message");
                return "Disregarded";
            } else if (choice.equalsIgnoreCase("Store")) {
                totalMessagesSent++;
                System.out.println("Message successfully stored");
                return "Stored";
            } else {
                System.out.println("Invalid option. Message automatically disregarded.");
                return "Disregarded";
            }
        }

        // Total messages sent
        public String printMessages() {
            if (messagesLog.isEmpty()) {
                return "No messages sent while the program was running.";
            }
            return messagesLog;
        }

        public int returnTotalMessagess() {
            return totalMessagesSent;
        }

        //Message storage
        public void storeMessage() {
            System.out.println("[JSON Engine Notice: Storage implementation under research]");
        }

        public void compileAndDisplayMessage(String id, String hash, String recipient, String body, String status) {
            String receipt = "-------------\n"
                           + "Message ID: " + id + "\n"
                           + "Message Hash: " + hash + "\n"
                           + "Recipient: " + recipient + "\n"
                           + "Message Body: " + body + "\n"
                           + "Status: " + status + "\n"
                           + "----------\n";
            
            System.out.print("\n" + receipt);
            this.messagesLog += receipt;
        }
    }

    //First code main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LoginRegistrationChat auth = new LoginRegistrationChat();

        System.out.println("=== REGISTRATION ===");

        System.out.print("Enter First Name: ");
        auth.firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        auth.lastName = input.nextLine();

        // PHONE VALIDATION
        while (true) {
            System.out.print("Enter Cellphone Number (+27XXXXXXXXX): ");
            String phone = input.nextLine();
            if (auth.checkCellPhoneNumber(phone)) {
                auth.storedPhone = phone;
                System.out.println("Cellphone number captured.");
                break;
            } else {
                System.out.println("Invalid phone number format.");
            }
        }

        // USERNAME VALIDATION
        String username;
        while (true) {
            System.out.print("Enter Username: ");
            username = input.nextLine();
            if (auth.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username must contain '_' and be max 5 characters.");
            }
        }

        // PASSWORD VALIDATION
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = input.nextLine();
            if (auth.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password must contain uppercase, number, special character and be 8+ chars.");
            }
        }

        // ONE CLEAN LOGIN LOOP
        boolean loggedIn = false;
        System.out.println("\n=== LOGIN ===");
        while (!loggedIn) {
            System.out.print("Enter Username: ");
            String loginUser = input.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = input.nextLine();

            loggedIn = auth.loginUser(loginUser, loginPass, username, password);
            System.out.println(auth.returnLoginStatus(loggedIn));
        }

        //Chat app after logging in
        if (loggedIn) {
            // Instantiate the nested architecture class securely
            QuickChatApp chat = new QuickChatApp();

            System.out.println("\nWelcome to QuickChat.");
            System.out.print("How many messages do you wish to enter when the application starts? ");
            int maxMessages = Integer.parseInt(input.nextLine());

            boolean running = true;
            while (running) {
                System.out.println("\n--- Numeric Menu ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                String choice = input.nextLine();

                switch (choice) {
                    case "1":
                     //MESSAGE YOU WANNA SEND
                        if (chat.returnTotalMessagess() >= maxMessages) {
                            System.out.println("Application limit reached. You can only enter the set number of messages.");
                            break;
                        }

                        //UNIQUE ID
                        Random random = new Random();
                        String messageID = "";
                        for (int i = 0; i < 10; i++) {
                            messageID += random.nextInt(10);
                        }

                        //ID VALIDATION
                        if (!chat.checkMessageID(messageID)) {
                            System.out.println("Message ID generation failed constraint tracking.");
                            break;
                        }

                        //RECEIVER VALIDATION
                        String recipient;
                        while (true) {
                            System.out.print("Enter recipient cell number must start with(+27) and be 12 characters long: ");
                            recipient = input.nextLine();
                            String checkResult = chat.checkRecipientCell(recipient);
                            
                            if (checkResult.equals("Valid")) {
                                break;
                            } else {
                                System.out.println(checkResult);
                            }
                        }

                        //TEXT MESSAGE LENGTH
                        String messageBody;
                        while (true) {
                            System.out.print("Enter message that is less than 250 characters: ");
                            messageBody = input.nextLine();

                            if (messageBody.length() <= 250) {
                                System.out.println("Message sent");
                                break;
                            } else {
                                System.out.println("Please enter a message of less than 250 characters.");
                            }
                        }

                        
                        int currentMsgIndexNum = chat.returnTotalMessagess() + 1;

                        
                        String messageHash = chat.createMessageHash(messageID, currentMsgIndexNum, messageBody);

                       
                        String transactionStatus = chat.SentMessage(input);

                        
                        if (!transactionStatus.equals("Disregarded")) {
                            chat.compileAndDisplayMessage(messageID, messageHash, recipient, messageBody, transactionStatus);
                        }
                        break;

                    case "2":
                     
                        System.out.println("Coming Soon.");
                        System.out.println("\n--- Current Session Messages Printout ---");
                        System.out.println(chat.printMessages());
                        break;

                    case "3":
                        System.out.println("Exiting application loop...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid numeric choice. Try again.");
                }
            }
        }

        input.close();
    }
}