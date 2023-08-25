package civitas.celestis;

import civitas.celestis.graphics.Colors;
import civitas.celestis.graphics.Face;
import civitas.celestis.graphics.Model;
import civitas.celestis.graphics.Viewport;
import civitas.celestis.math.Scalars;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vectors;
import civitas.celestis.task.Task;
import civitas.celestis.task.lifecycle.Scheduler;
import civitas.celestis.task.lifecycle.SyncScheduler;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Testing {

    static final Model model;
    static final JFrame frame = new JFrame();
    static final Viewport viewport = new Viewport();
    static final Scheduler scheduler = new SyncScheduler();

    static {
        frame.setSize(1920, 1080);
        frame.add(viewport);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                scheduler.terminate();

                System.exit(Application.EXIT_CODE_NORMAL);
            }
        });

        try {
            model = Model.loadModel("MoebiusEngine/src/main/resources/models/bc304/BC304Render.obj", 1);
        } catch (final IOException e) {
            throw new RuntimeException("Model loading failed.");
        }

        model.getFaces().forEach(f -> f.setColor(Colors.bezier(Colors.LIGHT_GRAY, Colors.BLACK, Scalars.random(0, 0.25))));


        scheduler.initialize();

        scheduler.register(new Task() {
            @Override
            public void execute(long delta) {
                final double seconds = delta / 1000d;
                final Quaternion rotation = Vectors.randomQuaternion().scale(seconds * 0.1);

                viewport.angle = rotation.multiply(viewport.angle);

                frame.repaint();
            }

            @Override
            public long interval() {
                return 100;
            }
        });

        viewport.origin = new Vector3(0, 0, -300);

    }

    public static void main(String[] args) {
        final Quaternion random = Vectors.randomQuaternion();
        viewport.faces.addAll(model.getFaces().safeArray().mapToCollection(f -> {
            final Face face = new Face(
                    f.getA().rotate(random),
                    f.getB().rotate(random),
                    f.getC().rotate(random));
            face.setColor(f.getColor());

            return face;
        }));
        frame.setVisible(true);

        scheduler.start();
    }
}
