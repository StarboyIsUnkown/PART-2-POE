/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.github.starboyisunkown.loginregistrationchat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRegistrationChatTest {

    private LoginRegistrationChat.QuickChatApp messageApp;

    @BeforeEach
    public void setUp() {
        messageApp = new LoginRegistrationChat.QuickChatApp();
    }

    @Test
    public void testMessageID_Success() {
        String validID = "1234567890";

        boolean result = messageApp.checkMessageID(validID);

        assertTrue(result);
    }

    @Test
    public void testMessageID_Failure() {
        String invalidID = "12345678901";

        boolean result = messageApp.checkMessageID(invalidID);

        assertFalse(result);
    }

    @Test
    public void testRecipientNumber_Success() {
        String validNumber = "+27718693002";

        String result = messageApp.checkRecipientCell(validNumber);

        assertEquals("Valid", result);
    }

    @Test
    public void testRecipientNumber_Failure() {
        String invalidNumber = "08575975889";

        String expectedError =
                "Invalid recipient number. Must start with +27 and contain 9 digits after.";

        String result = messageApp.checkRecipientCell(invalidNumber);

        assertEquals(expectedError, result);
    }

    @Test
    public void testMessageHash_TestCase1() {
        String messageID = "0012345678";
        int messageNum = 0;

        String message =
                "Hi Mike, can you join us for dinner tonight?";

        String expectedHash = "00:0:HITONIGHT?";

        String actualHash =
                messageApp.createMessageHash(messageID, messageNum, message);

        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testMessageLength_Success() {
        String message =
                "Hi Mike, can you join us for dinner tonight?";

        boolean result = message.length() <= 250;

        assertTrue(result);
    }

    @Test
    public void testMessageLength_Failure() {
        String longMessage = "a".repeat(255);

        boolean result = longMessage.length() <= 250;

        assertFalse(result);
    }

    @Test
    public void testTotalMessages_InitialValue() {
        int total = messageApp.returnTotalMessagess();

        assertEquals(0, total);
    }

    @Test
    public void testPrintMessages_Empty() {
        String result = messageApp.printMessages();

        assertEquals(
                "No messages sent while the program was running.",
                result
        );
    }
}