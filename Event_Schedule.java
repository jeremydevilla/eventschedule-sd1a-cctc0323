import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Event {
    String type;
    String name;
    String date;
    String startTime;
    String endTime;
    String location;

    public Event(String type, String name, String date, String startTime, String endTime, String location) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Type: " + type + "\nName: " + name + "\nDate: " + date + "\nStart Time: " + startTime + "\nEnd Time: " + endTime + "\nLocation: " + location;
    }
}

class EventManager {
    List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String type, String name, String date, String startTime, String endTime, String location) {
        Event event = new Event(type, name, date, startTime, endTime, location);
        events.add(event);
    }

    public Event getEvent(int index) {
        if (index >= 0 && index < events.size()) {
            return events.get(index);
        }
        return null;
    }

    public void removeEvent(int index) {
        if (index >= 0 && index < events.size()) {
            events.remove(index);
        }
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getSize() {
        return events.size();
    }

    public String displaySchedule() {
        if (events.isEmpty()) {
            return "No events scheduled.";
        } else {
            StringBuilder schedule = new StringBuilder("Event Schedule:\n");
            for (Event event : events) {
                schedule.append(event).append("\n\n");
            }
            return schedule.toString();
        }
    }

    public boolean isDateOccupied(String date, String time) {
        for (Event event : events) {
            if (event.date.equals(date) && (event.startTime.equals(time) || event.endTime.equals(time))) {
                return true;
            }
        }
        return false;
    }
}

public class Event_Schedule {
    private EventManager eventManager;
    private JFrame frame;
    private JTextField nameField;
    private JComboBox<String> eventTypeComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> startHourComboBox;
    private JComboBox<String> startMinuteComboBox;
    private JComboBox<String> startAmPmComboBox;
    private JComboBox<String> finishHourComboBox;
    private JComboBox<String> finishMinuteComboBox;
    private JComboBox<String> finishAmPmComboBox;
    private JComboBox<String> municipalityComboBox;
    private JComboBox<String> barangayComboBox;
    private JPanel displayArea;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JFrame addEventFrame;
    private JFrame dateTimeFrame;
    private JFrame locationFrame;

    private Map<String, String[]> barangaysMap;

    public Event_Schedule() {
        eventManager = new EventManager();
        frame = new JFrame("Event Schedule Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(null);

        initializeBarangaysMap();

        Font defaultFont = new Font("Times New Roman", Font.BOLD, 20);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.setBounds(150, 50, 200, 50);
        JButton displayScheduleButton = new JButton("Display Schedule");
        displayScheduleButton.setBounds(150, 150, 200, 50);
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(150, 250, 200, 50);

        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEventFrame();
            }
        });

        displayScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDisplaySchedulePanel();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(addEventButton);
        frame.add(displayScheduleButton);
        frame.add(exitButton);
        frame.setVisible(true);
    }

    private void initializeBarangaysMap() {
        barangaysMap = new HashMap<>();
        barangaysMap.put("Mariveles", new String[]{"Alas-asin", "Alion", "Balon-Anito", "Baseco Country(Nassco)","Batangas II", "Biaan", "Cabcaben", "Camaya", "Ipag", "Lucanin", "Malaya", "Maligaya", "Mt. View", "Poblacion", "San Carlos", "San Isidro", "Sisiman", "Townsite"});
        barangaysMap.put("Limay", new String[]{"Alangan", "Duale", "Kitang I", "Kitang 2 & Luz", "Lamao", "Landing", "Poblacion", "Reformista", "San Francisco de Asis", "Saint Francis II", "Townsite", "Wawa"});
        barangaysMap.put("Orion", new String[]{"Arellano (Poblacion)", "Bagumbayan (Poblacion)", "Balagtas (Poblacion)", "Balut (Poblacion)", "Bantan", "Bilolo", "Calungusan", "Camachile", "Daang Bago", "Daang Bilolo", "Daang Pare", "General Lim", "Kapunitan", "Lati","Lusungan", "Puting Buhangin", "Sabatan", "San Vicente", "Santa Elene", "Santo Domingo", "Villa Angeles", "Wakas", "Wawa"});
        barangaysMap.put("Balanga", new String[]{"Bagumbayan", "Cabog-Cabog", "Munting Batangas (Cadre)", "Cataning", "Central", "Cupang Proper", "Cupang West", "Dangcol (Bernabe)", "Ibayo",  "Malabia", "Poblacion Barcenas", "Puerto Rivas Ibaba", "Puerto Rivas Itaas", "Puerto Rivas Lote", "San Jose", "Sibacan", "Camacho", "Talisay", "Tanato", "Tenejero", "Tortugas", "Tuyo", "Bagong Silang", "Cupang North", "Doña Francisca"});
        barangaysMap.put("Samal", new String[]{"East Calaguiman (Poblacion)", "East Daang Bago (Poblacion)", "Gugo", "Ibaba (Poblacion)", "Imelda", "Lalawigan", "Palili", "San Juan (Poblacion)", "San Roque (Poblacion)", "Santa Lucia", "Sapa", "Tabing Ilog", "West Calaguiman (Poblacion)", "West Daang Bago (Poblacion)"});
        barangaysMap.put("Abucay", new String[]{"Bangkal", "Calaylayan(Poblacion)", "Capitangan", "Gabon", "Laon (Poblacion)", "Mabatang", "Omboy", "Salian", "Wawa (Poblacion)"});
        barangaysMap.put("Orani", new String[]{"Apollo", "Bagong Paraiso (Poblacion)", "Balut (Poblacion)", "Bayan (Poblacion)", "Calero (Poblacion)", "Centro I (Poblacion)", "Centro II (Poblacion)", "Dona", "Kabalutan", "Kaparangan", "Maria Fe", "Masantol", "Mulawin", "Pag-asa", "Paking-Carbonero (Poblacion)", "Palihan (Poblacion)", "Pantalan Bago (Poblacion)", "Pantalan Luma (Poblacion)", "Parang Parang (Poblacion)", "Puksuan", "Sibul", "Silahis", "Tagumpay", "Tala", "Talimundoc", "Tapulao", "Tenejero (Poblacion)", "Tugatog", "Wawa (Poblacion)"});
        barangaysMap.put("Hermosa", new String[]{"A. Rivera (Poblacion)", "Almacen", "Bacong", "Balsic", "Bamban", "Burgos-Soliman (Poblacion)", "Cataning (Poblacion)", "Culis", "Daungan (Poblacion)", "Judge Roman Cruz Sr. (Mandama)", "Mabiga", "Mabuco", "Maite", "Mambog - Mandama", "Palihan", "Pandatung", "Pulo", "Saba", "Sacrifice Valley", "San Pedro (Poblacion)", "Santo Cristo (Poblacion)", "Sumalo", "Tipo"});
        barangaysMap.put("Dinalupihan", new String[]{"Aquino", "Bangal", "Bayan-bayanan", "Bonifacio (Poblacion)", "Burgos (Poblacion)", "Colo", "Daang Bago", "Dalao", "Del Pilar (Poblacion)", "Gen. Luna (Poblacion)", "Gomez (Poblacion)", "Happy Valley", "Jose C. Payumo, Jr.", "Kataasan", "Layac", "Luacan", "Mabini Ext. (Poblacion)", "Mabini Proper (Poblacion)", "Magsaysay", "Maligaya", "Naparing", "New San Jose", "Old San Jose", "Padre Dandan (Poblacion)", "Pag-asa", "Pagalanggang", "Payangan", "Pentor", "Pinulot", "Pita", "Rizal (Poblacion)", "Roosevelt", "Roxas (Poblacion)", "Saguing", "San Benito", "San Isidro (Poblacion)", "San Pablo", "San Ramon", "San Simon", "Santa Isabel (Tabacan)", "Santo Niño", "Sapang Balas", "Torres (Poblacion)", "Tubo-tubo", "Tucop", "Zamora (Poblacion)"});
        barangaysMap.put("Morong", new String[]{"Nagbalayong", "Poblacion", "Binaritan", "Sabang", "Mabayo"});
        barangaysMap.put("Pilar", new String[]{"Ala-uli", "Bagumbayan", "Balut I", "Balut II", "Bantan Munti", "Burgos", "Del Rosario (Pob.)", "Diwa"});
        barangaysMap.put("Bagac", new String[]{"Bagumbayan (Pob.)", "Banawang", "Binuangan", "Binukawan", "Ibaba (Poblacion)", "Ibis", "Pag-asa (Wawa-Sibacan)", "Parang", "Paysawan", "Quinawan", "San Antonio", "Saysain", "Tabing-Ilog (Pob.)", "Atilano L. Ricardo"});
    }

    private void showAddEventFrame() {
        addEventFrame = new JFrame("Add Event");
        addEventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEventFrame.setSize(400, 300);
        addEventFrame.setLayout(null);

        JLabel typeLabel = new JLabel("Event type:");
        typeLabel.setBounds(50, 50, 100, 30);
        eventTypeComboBox = new JComboBox<>(new String[]{"SOCIAL", "SPORT", "CULTURAL", "EDUCATIONAL", "CORPORATE", "COMMUNITY"});
        eventTypeComboBox.setBounds(150, 50, 200, 30);

        JLabel nameLabel = new JLabel("Event name:");
        nameLabel.setBounds(50, 100, 100, 30);
        nameField = new JTextField();
        nameField.setBounds(150, 100, 200, 30);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(150, 200, 100, 30);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDateTimeFrame();
                addEventFrame.dispose();
            }
        });

        addEventFrame.add(typeLabel);
        addEventFrame.add(eventTypeComboBox);
        addEventFrame.add(nameLabel);
        addEventFrame.add(nameField);
        addEventFrame.add(nextButton);

        addEventFrame.setVisible(true);
    }

    private void showDateTimeFrame() {
        dateTimeFrame = new JFrame("Select Date and Time");
        dateTimeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dateTimeFrame.setSize(500, 400);
        dateTimeFrame.setLayout(null);

        JLabel monthLabel = new JLabel("Select event month:");
        monthLabel.setBounds(50, 50, 150, 30);
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        monthComboBox.setBounds(200, 50, 200, 30);
        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDaysComboBox();
            }
        });

        JLabel dayLabel = new JLabel("Select event day:");
        dayLabel.setBounds(50, 100, 150, 30);
        dayComboBox = new JComboBox<>();
        dayComboBox.setBounds(200, 100, 200, 30);

        JLabel yearLabel = new JLabel("Select event year:");
        yearLabel.setBounds(50, 150, 150, 30);
        yearComboBox = new JComboBox<>(new String[]{"2024", "2025", "2026", "2027", "2028"});
        yearComboBox.setBounds(200, 150, 200, 30);

        JLabel startTimeLabel = new JLabel("Starting time:");
        startTimeLabel.setBounds(50, 200, 150, 30);
        startHourComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        startHourComboBox.setBounds(200, 200, 50, 30);
        startMinuteComboBox = new JComboBox<>(new String[]{"00", "15", "30", "45"});
        startMinuteComboBox.setBounds(260, 200, 50, 30);
        startAmPmComboBox = new JComboBox<>(new String[]{"AM", "PM"});
        startAmPmComboBox.setBounds(320, 200, 60, 30);

        JLabel endTimeLabel = new JLabel("Finishing time:");
        endTimeLabel.setBounds(50, 250, 150, 30);
        finishHourComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        finishHourComboBox.setBounds(200, 250, 50, 30);
        finishMinuteComboBox = new JComboBox<>(new String[]{"00", "15", "30", "45"});
        finishMinuteComboBox.setBounds(260, 250, 50, 30);
        finishAmPmComboBox = new JComboBox<>(new String[]{"AM", "PM"});
        finishAmPmComboBox.setBounds(320, 250, 60, 30);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(200, 300, 100, 30);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLocationFrame();
                dateTimeFrame.dispose();
            }
        });

        dateTimeFrame.add(monthLabel);
        dateTimeFrame.add(monthComboBox);
        dateTimeFrame.add(dayLabel);
        dateTimeFrame.add(dayComboBox);
        dateTimeFrame.add(yearLabel);
        dateTimeFrame.add(yearComboBox);
        dateTimeFrame.add(startTimeLabel);
        dateTimeFrame.add(startHourComboBox);
        dateTimeFrame.add(startMinuteComboBox);
        dateTimeFrame.add(startAmPmComboBox);
        dateTimeFrame.add(endTimeLabel);
        dateTimeFrame.add(finishHourComboBox);
        dateTimeFrame.add(finishMinuteComboBox);
        dateTimeFrame.add(finishAmPmComboBox);
        dateTimeFrame.add(nextButton);

        dateTimeFrame.setVisible(true);
        updateDaysComboBox();
    }

    private void showLocationFrame() {
        locationFrame = new JFrame("Select Location");
        locationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        locationFrame.setSize(400, 300);
        locationFrame.setLayout(null);

        JLabel municipalityLabel = new JLabel("Municipality:");
        municipalityLabel.setBounds(50, 50, 100, 30);
        municipalityComboBox = new JComboBox<>(new String[]{"Mariveles", "Limay", "Orion", "Balanga", "Samal", "Abucay", "Orani", "Hermosa", "Dinalupihan", "Bagac", "Morong", "Pilar"});
        municipalityComboBox.setBounds(150, 50, 200, 30);
        municipalityComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBarangaysComboBox();
            }
        });

        JLabel barangayLabel = new JLabel("Barangay:");
        barangayLabel.setBounds(50, 100, 100, 30);
        barangayComboBox = new JComboBox<>();
        barangayComboBox.setBounds(150, 100, 200, 30);

        JButton addButton = new JButton("Add Event");
        addButton.setBounds(150, 200, 100, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
                locationFrame.dispose();
            }
        });

        locationFrame.add(municipalityLabel);
        locationFrame.add(municipalityComboBox);
        locationFrame.add(barangayLabel);
        locationFrame.add(barangayComboBox);
        locationFrame.add(addButton);

        locationFrame.setVisible(true);
        updateBarangaysComboBox();
    }

    private void updateDaysComboBox() {
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        int daysInMonth;
        switch (selectedMonth) {
            case "February":
                daysInMonth = 28; // Simplified for non-leap years
                break;
            case "April":
            case "June":
            case "September":
            case "November":
                daysInMonth = 30;
                break;
            default:
                daysInMonth = 31;
                break;
        }

        dayComboBox.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(String.valueOf(i));
        }
    }

    private void updateBarangaysComboBox() {
        String selectedMunicipality = (String) municipalityComboBox.getSelectedItem();
        String[] barangays = barangaysMap.get(selectedMunicipality);

        barangayComboBox.removeAllItems();
        if (barangays != null) {
            for (String barangay : barangays) {
                barangayComboBox.addItem(barangay);
            }
        }
    }

    private void addEvent() {
        String eventType = (String) eventTypeComboBox.getSelectedItem();
        String eventName = nameField.getText();
        String eventDate = monthComboBox.getSelectedItem() + " " + dayComboBox.getSelectedItem() + ", " + yearComboBox.getSelectedItem();
        String startTime = startHourComboBox.getSelectedItem() + ":" + startMinuteComboBox.getSelectedItem() + " " + startAmPmComboBox.getSelectedItem();
        String endTime = finishHourComboBox.getSelectedItem() + ":" + finishMinuteComboBox.getSelectedItem() + " " + finishAmPmComboBox.getSelectedItem();
        String location = municipalityComboBox.getSelectedItem() + ", " + barangayComboBox.getSelectedItem();

        eventManager.addEvent(eventType, eventName, eventDate, startTime, endTime, location);
        JOptionPane.showMessageDialog(frame, "Event added successfully!");
    }

    private void showDisplaySchedulePanel() {
        JFrame displayFrame = new JFrame("Event Schedule");
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setSize(500, 400);
        displayFrame.setLayout(new BorderLayout());

        JTextArea scheduleTextArea = new JTextArea(eventManager.displaySchedule());
        scheduleTextArea.setEditable(false);
        displayFrame.add(new JScrollPane(scheduleTextArea), BorderLayout.CENTER);

        displayFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Event_Schedule();
    }
}
