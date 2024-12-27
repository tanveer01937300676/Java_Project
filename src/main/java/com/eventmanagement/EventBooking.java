package com.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EventBooking extends JFrame {
    private JComboBox<String> eventCategories;
    private JComboBox<String> locationComboBox;
    private JTextField capacityField, organizerField;
    private JButton bookButton, viewBookingListButton;

    private static final Map<String, String[]> LOCATIONS = new HashMap<>();

    static {
        LOCATIONS.put("Dhaka", new String[]{"500", "Dhaka Organizer"});
        LOCATIONS.put("Chittagong", new String[]{"300", "Chittagong Organizer"});
        LOCATIONS.put("Sylhet", new String[]{"200", "Sylhet Organizer"});
    }

    public EventBooking() {
        setTitle("Event Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Event Category Selection
        add(new JLabel("Select Event Category:"));
        String[] categories = {"Wedding", "Birthday", "Seminar", "Concert"};
        eventCategories = new JComboBox<>(categories);
        add(eventCategories);

        // Location Selection
        add(new JLabel("Location:"));
        locationComboBox = new JComboBox<>(LOCATIONS.keySet().toArray(new String[0]));
        locationComboBox.addActionListener(e -> updateLocationDetails());
        add(locationComboBox);

        // Capacity
        add(new JLabel("Capacity:"));
        capacityField = new JTextField(15);
        capacityField.setEditable(false);
        add(capacityField);

        // Organizer
        add(new JLabel("Organizer:"));
        organizerField = new JTextField(15);
        organizerField.setEditable(false);
        add(organizerField);

        // Buttons
        bookButton = new JButton("Book Now");
        viewBookingListButton = new JButton("View Booking List");
        add(bookButton);
        add(viewBookingListButton);

        // Event Handlers
        bookButton.addActionListener(new BookNowHandler());
        viewBookingListButton.addActionListener(e -> new BookingList().setVisible(true));

        updateLocationDetails(); // Set initial location details
        setVisible(true);
    }

    private void updateLocationDetails() {
        String selectedLocation = (String) locationComboBox.getSelectedItem();
        if (selectedLocation != null) {
            String[] details = LOCATIONS.get(selectedLocation);
            capacityField.setText(details[0]);
            organizerField.setText(details[1]);
        }
    }

    private class BookNowHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String category = (String) eventCategories.getSelectedItem();
            String location = (String) locationComboBox.getSelectedItem();
            String capacity = capacityField.getText();
            String organizer = organizerField.getText();

            // Collect additional booking details
            String name = JOptionPane.showInputDialog("Enter Your Name:");
            String date = JOptionPane.showInputDialog("Enter Event Date (YYYY-MM-DD):");
            String contact = JOptionPane.showInputDialog("Enter Contact Number:");

            if (name != null && date != null && contact != null) {
                String amount = String.valueOf(Integer.parseInt(capacity) * 100); // Example calculation
                Booking booking = new Booking(name, date, contact, category, location, capacity, organizer, amount);
                BookingList.addBooking(booking);
                JOptionPane.showMessageDialog(EventBooking.this, "Booking Successful!\nPayment Amount: " + amount + " BDT");
            }
        }
    }
}
