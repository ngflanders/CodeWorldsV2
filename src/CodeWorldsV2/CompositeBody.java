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

    public CompositeBody(ArrayList<Body> childre, Vector loc) {
        children = new ArrayList<>();
        for (Body b : childre) {
            if (b instanceof Brick)
                this.children.add(b.clone(loc.plus(((Brick) b).getLoc())));
            else
                this.children.add(b.clone(loc.plus(((CompositeBody) b).getLoc())));
        }

        // this.children = children;
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
        return new Iterator<Brick>() {
            int i = 0;
            int j = 0;

            @Override
            public boolean hasNext() {
                return i < children.size();
            }

            @Override
            public Brick next() {
                if(children.get(i) instanceof Brick) {
                    return ((Brick) children.get(i++));
                }
                if(children.get(i) instanceof CompositeBody) {
                    if (j < ((CompositeBody) children.get(i)).children.size())
                        return ((Brick) ((CompositeBody) children.get(i)).children.get(j++));
                    else {
                        j = 0;
                        if (children.size() > i+1) {
                            i++;
                            return next();
                        }
                    }
                }
                return null;
            }
        };

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