package civitas.celestis;

import civitas.celestis.event.notification.NotificationEvent;
import civitas.celestis.math.DecimalMath;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.function.Graph;
import civitas.celestis.task.Task;
import civitas.celestis.util.tuple.ArrayTuple;

public class Test2 {
    public static void main(String[] args) {
        MoebiusApplication.TEST_APP.start();

        MoebiusApplication.TEST_APP.getEventManager().call(new NotificationEvent("Hello world!"));

        MoebiusApplication.TEST_APP.getScheduler().register(new Task() {
            @Override
            public void execute(long delta) {
                MoebiusApplication.TEST_APP.stop();
            }

            @Override
            public long interval() {
                return 5000;
            }
        });
    }
}
