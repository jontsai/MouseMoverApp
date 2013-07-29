import java.awt.Dimension;

public interface AppConstants {
    public final static String APP_TITLE = "Auto Mouse Mover";
    // labels
    public final static String LABEL_HEADING = "Random Mouse Movements";
    public final static String LABEL_CURRENT_LOCATION = "Current Location";
    public final static String LABEL_START_BUTTON = "Start Mouse Movement";
    public final static String LABEL_STOP_BUTTON = "Stop Mouse Movement";
    public final static String LABEL_DELAY = "Delay (seconds)";
    // dimensions
    public final static Dimension APP_DIMENSION = new Dimension(250, 200);
    public final static Dimension WIDGET_DIMENSION = new Dimension(175, 30);
    public final static int DEFAULT_SLEEP_DELAY = 5;
}
