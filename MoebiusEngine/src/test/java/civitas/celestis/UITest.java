package civitas.celestis;

import civitas.celestis.task.Task;
import civitas.celestis.ui.UIFrame;
import civitas.celestis.ui.image.RichImage;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UITest {
    public static void main(String[] args) {
        final UIFrame uiFrame = new UIFrame();
        uiFrame.getContainer().addComponent(new TestComponent());
        uiFrame.setVisible(true);
        uiFrame.setSize(1920, 1080);

        Moebius.start();
        Moebius.getScheduler().register(new Task() {
            @Override
            public void execute(long delta) {
                uiFrame.getContainer().render();
            }

            @Override
            public long interval() {
                return 500;
            }
        });

        uiFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                uiFrame.dispose();
                Moebius.stop();
            }
        });
    }
}
