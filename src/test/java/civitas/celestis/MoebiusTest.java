package civitas.celestis;

import civitas.celestis.graphics.face.ColoredFace;
import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.model.ColoredModel;
import civitas.celestis.graphics.model.Model;
import civitas.celestis.graphics.scene.Scene;
import civitas.celestis.graphics.util.Colors;
import civitas.celestis.graphics.viewport.Viewport;
import civitas.celestis.listener.object.ObjectsCollidedListener;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.util.Numbers;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.physics.profile.SphereProfile;
import civitas.celestis.physics.unit.MassUnit;
import civitas.celestis.task.Task;
import civitas.celestis.util.counter.Repeater;
import civitas.celestis.world.RealisticWorld;
import civitas.celestis.world.World;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class MoebiusTest {
    public static void main(String[] args) {
//        final File file = new File("src/main/resources/models/mcx/MCX.obj");
        final File file = new File("src/main/resources/models/bc304/BC304Render.obj");
        final FileReader reader;
        final Obj obj;

        try {
            reader = new FileReader(file);
            obj = ObjReader.read(reader);
        } catch (IOException e) {
            return;
        }

        final ColoredModel model = new ColoredModel(obj, Color.LIGHT_GRAY, 3);
//        final ColoredModel model = new ColoredModel(obj, Color.GRAY, 150);

        for (final ColoredFace face : model.getFaces()) {
            face.setColor(Colors.average(face.getColor(), Colors.BLACK, 3, Numbers.random(Double.MIN_VALUE, 1)));
        }

        final BaseObject odyssey = new BaseObject(
                UUID.randomUUID(),
                new Vector3(0, 0, 1000),
                Quaternion.IDENTITY,
                new SphereProfile(300, MassUnit.IMPERIAL_TON.toKilograms(5000000)),
                model
        );

        odyssey.setRotationRate(Quaternion.builder().pitchDegrees(23).yawDegrees(45).build());

        final ColoredModel model2 = new ColoredModel(obj, Color.LIGHT_GRAY, 3);

        for (final ColoredFace face : model2.getFaces()) {
            face.setColor(Colors.average(face.getColor(), Colors.BLACK, 1, Numbers.random(Double.MIN_VALUE, 1)));
        }

        final BaseObject daedalus = new BaseObject(
                UUID.randomUUID(),
                new Vector3(0, 1000, 1000),
                Quaternion.IDENTITY,
                new SphereProfile(300, MassUnit.IMPERIAL_TON.toKilograms(5000000)),
                model2
        );

        daedalus.rotate(Quaternion.builder().pitchDegrees(90).build());

        daedalus.setAcceleration(new Vector3(0, -1e+1, 0));

        final RealisticWorld earth = new RealisticWorld(
                UUID.randomUUID(),
                "Earth",
                new Vector3(0, -9.807, 0),
                1.225
        );

        earth.addObject(odyssey);
        earth.addObject(daedalus);
        Moebius.getWorldManager().addWorld(earth);

        final JFrame frame = new JFrame("Moebius");
        frame.setSize(1920, 1080);

        final Viewport viewport = new Viewport(new Scene(earth));
        frame.add(viewport);

        Moebius.getScheduler().register(delta -> viewport.repaint());

        Moebius.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                Moebius.stop();
            }
        });

        frame.setVisible(true);

        Moebius.getEventManager().register(new TestListener());

        Moebius.getScheduler().register(delta -> {
            final double s = delta / 1000d;
            final Vector3 a = earth.getGravity().negate().multiply(s);
            odyssey.accelerate(a);
        });

        Moebius.getScheduler().register(delta -> {
            odyssey.accelerate(Vector3.random().multiply(10));
            odyssey.rotateRate(Quaternion.random());

            daedalus.accelerate(Vector3.random());
            daedalus.rotateRate(Quaternion.random());
        });

        Moebius.getScheduler().register(new Task() {
            private final Color[] colors = {
                    Color.RED,
                    Color.GREEN,
                    Color.BLUE
            };
            private final Repeater repeater = new Repeater(colors.length);
            private Color next() {
                return colors[repeater.next()];
            }

            @Override
            public void execute(long delta) {
                if (new AtomicBoolean(true).get()) return;

                Color c1 = next();
                for (final ColoredFace face : model.getFaces()) {
//                    face.setColor(c1);
                    face.setColor(Colors.random());
                }

                c1 = next();
                for (final ColoredFace face : model2.getFaces()) {
//                    face.setColor(c1);
                    face.setColor(Colors.random());
                }
            }

            @Override
            public long interval() {
                return 100;
            }
        });

        Moebius.getScheduler().register(delta -> {
            final Vector3 offset = new Vector3(0, 100, -1000);
            final Vector3 cameraLocation = odyssey.getLocation().add(offset.rotate(odyssey.getRotation()));

            final Vector3 axis = cameraLocation.cross(odyssey.getLocation());

            final Vector3 cameraNorm = cameraLocation.normalize();
            final Vector3 objectNorm = odyssey.getLocation().normalize();

            final double dot = cameraNorm.dot(objectNorm);
            final double angle = Math.acos(dot);

            final double halfAngle = angle / 2;
            final double sinHalfAngle = Math.sin(halfAngle);
            final double cosHalfAngle = Math.cos(halfAngle);

            final Quaternion rotation = new Quaternion(
                    cosHalfAngle,
                    axis.multiply(sinHalfAngle)
            );

            viewport.setContext(viewport.getContext()
                    .location(cameraLocation)
                    .rotation(rotation));
        });
    }
}
