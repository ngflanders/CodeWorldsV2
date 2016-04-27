package CodeWorldsV2;

import java.util.ArrayList;
import java.util.Random;

public class AutoWorldFactory implements WorldFactory {

    ArrayList<Body> bodies;

    public AutoWorldFactory() {
        bodies = new ArrayList<>();
    }

    @Override
    public WorldFactory build() throws CWSException {
        Random random = new Random();
        Body b;
        // Random Number of Cows
        for (int i = 0; i < random.nextInt(4); i++) {
            bodies.add(b = new Cow(new Vector(random.nextInt(10), random.nextInt(6))));
            System.out.println("Cow   " + b.getBounds());
        }

        // Random Number of Trees
        for (int i = 0; i < random.nextInt(4); i++) {
            bodies.add(b = new Tree(new Vector(random.nextInt(10), random.nextInt(6))));
            System.out.println("Tree  " + b.getBounds());
        }

        // Random Number of Horse
        for (int i = 0; i < random.nextInt(4); i++) {
            bodies.add(b = new Horse(new Vector(random.nextInt(10), random.nextInt(6))));
            System.out.println("Horse " + b.getBounds());
        }

        return this;
    }

    @Override
    public Body getWorld() {
        return new CompositeBody(bodies);
    }
}
