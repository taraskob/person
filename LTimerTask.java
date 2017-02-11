import java.io.IOException;
import java.text.ParseException;
import java.util.TimerTask;
class LTimerTask extends TimerTask {
    private Controller ctrl;
    LTimerTask(Controller fcntr) {
        this.ctrl=fcntr;
    }
    public void run() {
        try {
            ctrl.syncro();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}