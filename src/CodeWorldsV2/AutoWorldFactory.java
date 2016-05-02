package CodeWorldsV2;

import java.util.ArrayList;
import java.util.Random;

public class AutoWorldFactory implements WorldFactory {
    ArrayList<Body> bodies;
    ArrayList<Vector> usedSpace = new ArrayList<>();
    public AutoWorldFactory() { bodies = new ArrayList<>(); }

    /*
    Desc: creates the list of Bodies in unique locations for the AutoWorldFactory
    Pre:  none
    Post: the list of bodies has been created, and the AutoWorldFactory instance is returned
    */
    @Override
    public WorldFactory build() throws CWSException {
        Random random = new Random();
        Vector v;
        // Random Number of Cows
        for (int i = 0; i < random.nextInt(100); i++) {
            v = generatorLoc(30, 10);
            bodies.add(new Cow(v));
        }
        // Random Number of Trees
        for (int i = 0; i < random.nextInt(200); i++) {
            v = generatorLoc(30, 20);
            bodies.add(new Tree(v));
        }
        // Random Number of Horse
        for (int i = 0; i < random.nextInt(100); i++) {
            v = generatorLoc(30, 20);
            bodies.add(new Horse(v));
        }
        // Random Number of Water
        for (int i = 0; i < random.nextInt(150); i++) {
            v = generatorLoc(30, 20);
            bodies.add(new Water(v));
        }
        // Random Number of Ore
        for (int i = 0; i < random.nextInt(150); i++) {
            v = generatorLoc(30, 20);
            bodies.add(new Ore(v));
        }
        // Random Number of Sloth
        for (int i = 0; i < random.nextInt(150); i++) {
            v = generatorLoc(30, 20);
            bodies.add(new Sloth(v));
        }
        return this;
    }

    /*
    Desc: creates a random and unused Vector location below the max values provided
    Pre:  a max X and Y has been provided
    Post: the Vector location has been returned
    */
    private Vector generatorLoc(int xMax, int yMax) {
        int x, y;
        Random random = new Random();
        Vector v;
        x = random.nextInt(xMax);
        y = random.nextInt(yMax);
        while (usedSpace.contains(v = (new Vector(x, y)))) {
            x = random.nextInt(xMax);
            y = random.nextInt(yMax);
        }
        usedSpace.add(v);
        return v;
    }

    /*
    Desc: returns a CompositeBody which contains all of the other Bodies of the world
    Pre:  the bodies arrayList has already been filled with Bodies to be displayed
    Post: the CompositeBody is created and returned
    */
    @Override
    public Body getWorld() {
        return new CompositeBody(bodies);
    }
}
