import java.io.IOException;
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
        }
    }
}