import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class MouseMoverApp implements ActionListener, AppConstants {
    private JLabel currentLocationLabel;
    private JButton startButton;
    private JButton stopButton;
    private JComboBox delaySelect;
    private MouseController mc;
    
    public MouseMoverApp() {
        mc = new MouseController(this);
    }
    
    public void createAndShowGUI() {
        // create the window
        JFrame jframe = new JFrame(APP_TITLE);
        
        // set the behavior for when the window is closed
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // set the jframe size and location, and make it visible
        jframe.setPreferredSize(APP_DIMENSION);

        Container pane = jframe.getContentPane();
        
//        BorderPane border = new BorderPane();
        
        LayoutManager layout = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(layout);

//        VBox vbox = new VBox();
//        vbox.setPadding(new Insets(10, 10, 10, 10));
//        vbox.setSpacing(10);
        
//        vbox.getChildren().addAll(label, startButton, stopButton, delaySelect);

        currentLocationLabel = new JLabel(LABEL_CURRENT_LOCATION);
        
        startButton = new JButton(LABEL_START_BUTTON);
        startButton.setMaximumSize(WIDGET_DIMENSION);
        stopButton = new JButton(LABEL_STOP_BUTTON);
        stopButton.setMaximumSize(WIDGET_DIMENSION);
        stopButton.setEnabled(false);
        
        String[] delayOptions = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        delaySelect = new JComboBox(delayOptions);
        delaySelect.setSelectedIndex(2);
        delaySelect.setMaximumSize(WIDGET_DIMENSION);
        
//        border.setCenter(vbox);
        pane.add(new JLabel(LABEL_HEADING));
        pane.add(new JLabel(LABEL_DELAY));
        pane.add(delaySelect);
        pane.add(startButton);
        pane.add(stopButton);
        pane.add(currentLocationLabel);        
        
        jframe.pack(); // arrange components inside the window
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true); // not visible by default
        
        Thread mcThread = new Thread(mc, "mouseController");
        mcThread.start();
        
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
    }
    
    public void updateMouseLocation(Point location) {
        currentLocationLabel.setText(LABEL_CURRENT_LOCATION + ": (X: " + location.x + ", Y: " + location.y + ")");
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startButton) {
            int delaySeconds = Integer.parseInt((String) delaySelect.getSelectedItem());
            mc.setDelay(delaySeconds);
            mc.startAutoMouse();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        } else if (source == stopButton) {
            mc.stopAutoMouse();
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MouseMoverApp().createAndShowGUI();
            }
        });
    }
}
