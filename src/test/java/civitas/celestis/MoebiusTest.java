package civitas.celestis;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.model.ColoredModel;
import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.physics.profile.SphereProfile;
import civitas.celestis.physics.unit.MassUnit;
import civitas.celestis.task.Task;
import civitas.celestis.world.RealisticWorld;
import civitas.celestis.world.World;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class MoebiusTest {
    public static void main(String[] args) {
        final File file = new File("src/main/resources/models/bc304/BC304Render.obj");
        final FileReader reader;
        final Obj obj;

        try {
            reader = new FileReader(file);
            obj = ObjReader.read(reader);
        } catch (IOException e) {
            return;
        }

        final Model model = new ColoredModel(obj, Color.GRAY, 3);

        final BaseObject odyssey = new BaseObject(
                UUID.randomUUID(),
                Vector3.ZERO,
                Quaternion.IDENTITY,
                new SphereProfile(300, MassUnit.IMPERIAL_TON.toKilograms(60000)),
                model
        );

        final World earth = new RealisticWorld(
                UUID.randomUUID(),
                "Earth",
                new Vector3(0, -9.807, 0),
                1.225
        );

        earth.addObject(odyssey);
        Moebius.getWorldManager().addWorld(earth);

        Moebius.getScheduler().register(new Task() {
            @Override
            public void execute(long delta) {
                System.out.println(odyssey.getLocation());
            }

            @Override
            public long interval() {
                return 1000;
            }
        });

        Moebius.start();
    }
}
