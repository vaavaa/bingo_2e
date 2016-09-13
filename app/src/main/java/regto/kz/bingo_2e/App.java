package regto.kz.bingo_2e;

import android.app.Application;

public class App extends Application {
private Thread MainThreadLink;

    public Thread getMainThreadLink() {
        return MainThreadLink;
    }

    public void setMainThreadLink(Thread mainThreadLink) {
        MainThreadLink = mainThreadLink;
    }
}
