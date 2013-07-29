import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Random;

public class MouseController implements Runnable, AppConstants {
    private MouseMoverApp mmApp;
    private Robot robot;
    private int sleepDelay;
    private boolean running;
    private Dimension screenDimension;
    private int lastX, lastY;
    
    public MouseController(MouseMoverApp app) {
        mmApp = app;
        running = false;
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setDelay(AppConstants.DEFAULT_SLEEP_DELAY);
        try {
            robot = new Robot();
        } catch (AWTException awte) {
            robot = null;
        }
    }

    public Point getMousePointerLocation() {
        Point location = MouseInfo.getPointerInfo().getLocation();
        return location;
    }
    
    public void rightClick() {
        if (robot != null) {
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
        }
    }

    public void leftClick() {
        if (robot != null) {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }
    }

    public void middleWheelClick() {
        if (robot != null) {
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        }
    }

    public void scrollWheel() {
        if (robot != null) {
            robot.mouseWheel(-100);
        }
    }
    
    public void randomMoveCursor() {
        if (robot != null) {
            Random r = new Random();
            lastX = r.nextInt(screenDimension.width);
            lastY = r.nextInt(screenDimension.height);
            robot.mouseMove(lastX, lastY);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public synchronized void setDelay(int delaySeconds) {
        sleepDelay = delaySeconds * 1000;
    }
    
    public synchronized void startAutoMouse() {
        running = true;
    }

    public synchronized void stopAutoMouse() {
        running = false;
    }

    public void run() {
        while(true) {
            mmApp.updateMouseLocation(getMousePointerLocation());
            try {
                if (running) {
                    Thread.sleep(sleepDelay);
                    randomMoveCursor();
                    //rightClick();
                    //leftClick();
                }
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }
    }
}
