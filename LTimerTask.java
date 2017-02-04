import java.io.IOException;
import java.util.TimerTask;
class LTimerTask extends TimerTask {
    private Controller filectrl;
    LTimerTask(Controller fcntr) {
        this.filectrl=fcntr;
    }
    public void run() {
        try {
            filectrl.syncro();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}