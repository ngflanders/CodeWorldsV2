package CodeWorldsV2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CompositeBody implements Body, Displayable {

    ArrayList<Body> children;
    Vector loc;

    public CompositeBody(ArrayList<Body> bodies) {
        children = bodies;
    }

    // Copy constructor
    public CompositeBody(CompositeBody other) {

    }


    public CompositeBody(Vector loc) {
        this.loc = loc;
    }

    public CompositeBody(ArrayList<Body> children, Vector loc) {
        this.children = children;
        this.loc = loc;
    }

    @Override
    public Rectangle getBounds() {

        int leftMax = 0, rightMax = 0, topMax = 0, bottomMax = 0, temp;
        Rectangle r = children.get(0).getBounds();
        Rectangle s;
        for (int i = 0; i < children.size() - 1; i++) {
            s = children.get(i+1).getBounds();
            r.unionBy(s);
        }
        return r;
    }

    @Override
    public Body clone(Vector offset) {
        //return new CompositeBody(offset.plus(this.getLoc()));
        return new CompositeBody(children, offset);
    }

    @Override
    public Iterator<Brick> iterator() {
        int x = -1;
        if (children.get(++x) instanceof Brick) {
            return new Iterator<Brick>() {
                int i = -1;

                @Override
                public boolean hasNext() {
                    return i < children.size() - 1;
                }

                @Override
                public Brick next() {
                    return ((Brick) children.get(++i));
                }
            };
        } else {
            return new Iterator<Brick>() {
                int i = -1;

                @Override
                public boolean hasNext() {
                    return i < children.size() - 1;
                }

                @Override
                public Brick next() {
                    CompositeBody cmp = (CompositeBody) children.get(++i);
                    return (Brick)cmp.children.get(++i);
                }
            };
        }
    }

    @Override
    public Vector getLoc() {
        return loc;
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