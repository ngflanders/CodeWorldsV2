package CodeWorldsV2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CompositeBody implements Body, Displayable {

    public static ArrayList<Body> CollectBody;

    public CompositeBody() {
    }

    // Copy constructor
    public CompositeBody(CompositeBody other) {

    }

    @Override
    public Rectangle getBounds() {


        return null;
    }

    @Override
    public Body clone(Vector offset) {
        return new CompositeBody(this); // needs offset still!
    }

    @Override
    public Iterator<Brick> iterator() {
        return new Iterator<Brick>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Brick next() {
                return null;
            }
        };
    }

    @Override
    public Vector getLoc() {
        return null;
    }

    @Override
    public Vector getVlc() {
        return null;
    }

    @Override
    public Image getImage(int size) {
        return null;
    }
}
