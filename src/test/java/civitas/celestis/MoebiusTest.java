package civitas.celestis;

public class MoebiusTest {
    public static void main(String[] args) {
////        final File file = new File("src/main/resources/models/mcx/MCX.obj");
//        final File file = new File("src/main/resources/models/bc304/BC304Render.obj");
//        final FileReader reader;
//        final Obj obj;
//
//        try {
//            reader = new FileReader(file);
//            obj = ObjReader.read(reader);
//        } catch (IOException e) {
//            return;
//        }
//
//        final Model model = new ColoredModel(obj, Color.GRAY, 3);
////        final Model model = new ColoredModel(obj, Color.GRAY, 150);
//
//        final BaseObject odyssey = new BaseObject(
//                UUID.randomUUID(),
//                new Vector3(0, 0, 1000),
//                Quaternion.IDENTITY,
//                new SphereProfile(300, MassUnit.IMPERIAL_TON.toKilograms(60000)),
//                model
//        );
//
//        odyssey.setRotationRate(Quaternion.builder().pitchDegrees(23).yawDegrees(45).build());
//
//        final World earth = new RealisticWorld(
//                UUID.randomUUID(),
//                "Earth",
//                new Vector3(0, -9.807, 0),
//                1.225
//        );
//
//        earth.addObject(odyssey);
//        Moebius.getWorldManager().addWorld(earth);
//
//        final JFrame frame = new JFrame("Moebius");
//        frame.setSize(1920, 1080);
//
//        final Viewport viewport = new Viewport(new Scene(earth));
//        frame.add(viewport);
//
//        Moebius.getScheduler().register(delta -> viewport.repaint());
//
//        Moebius.start();
//
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                frame.dispose();
//                Moebius.stop();
//            }
//        });
//
//        frame.setVisible(true);
    }
}
