package com.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingList extends JFrame {
    private static final String FILE_PATH = "bookings.txt"; // File to store booking data
    private static List<Booking> bookings = new ArrayList<>();
    private JTextArea bookingDetails;

    public BookingList() {
        setTitle("Booking List");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        bookingDetails = new JTextArea();
        loadBookings(); // Load existing bookings from file
        updateBookingList();
        add(new JScrollPane(bookingDetails), BorderLayout.CENTER);
    }

    public static void addBooking(Booking booking) {
        bookings.add(booking);
        saveBookings(); // Save bookings to file after adding
    }

    private void updateBookingList() {
        StringBuilder details = new StringBuilder("Name | Date | Contact | Event | Amount\n");
        details.append("------------------------------------------------\n");
        for (Booking booking : bookings) {
            details.append(booking.getDetails()).append("\n");
        }
        bookingDetails.setText(details.toString());
    }

    private static void saveBookings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(bookings); // Save the list of bookings to the file
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving bookings: " + e.getMessage());
        }
    }

    private static void loadBookings() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                bookings = (List<Booking>) ois.readObject(); // Load bookings from the file
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error loading bookings: " + e.getMessage());
            }
        }
    }
}
