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

    /*
    Desc: a CompositeBody constructor which is used for cloning CompositeBodies
    Pre:  an ArrayList of the children to be copied has been provided, as well as a Vector offset to move them from
    Post: a new instance of CompositeBody has been created which has the children member data shifted by the offset
    */
    public CompositeBody(ArrayList<Body> childre, Vector loc) {
        children = new ArrayList<>();
        for (Body b : childre) {
            if (b instanceof Brick)
                this.children.add(b.clone(loc.plus(((Brick) b).getLoc())));
            else
                this.children.add(b.clone(loc.plus(((CompositeBody) b).getLoc())));
        }
        this.loc = loc;
    }

    /*
    Desc: returns a Rectangle of the furthest bounds of all of the children objects
    Pre:  the children data member has already been filled
    Post: a Rectangle has been returned whose bounds enclose all of the children Bodies
    */
    @Override
    public Rectangle getBounds() {
        Rectangle r = children.get(0).getBounds(), s;
        for (int i = 0; i < children.size() - 1; i++) {
            s = children.get(i+1).getBounds();
            r.unionBy(s);
        }
        return r;
    }

    /*
    Desc: returns a new instance of a CompositeBody of the same class type as the calling object
    Pre:  the calling object, another CompositeBody, already has children member data, as well as an offset is provided
    Post: a new instance of CompositeBody is returned with children adjusted for the offset
    */
    @Override
    public Body clone(Vector offset) {
        //return new CompositeBody(offset.plus(this.getLoc()));
        return new CompositeBody(children, offset);
    }

    /*
    Desc: returns an anonymous inner class Iterator object which can return Bodies from the world's CompositeBody
            children list
    Pre:  the children arrayList has already been filled with Bodies to be displayed
    Post: an Iterator object is returned which can cycle through Brick objects nested up to 2 layers
            deep within CompositeBodies
    */
    @Override
    public Iterator<Brick> iterator() {
        return new Iterator<Brick>() {
            int i = 0;  // outer loop counter
            int j = 0;  // inner loop counter

            @Override
            public boolean hasNext() {
                return i < children.size();
            }

            @Override
            public Brick next() {
                // if it's a Brick, return the Brick and increment the outer counter
                if(children.get(i) instanceof Brick) {
                    return ((Brick) children.get(i++));
                }
                // if it's a CompositeBody, return the Brick within the CompositeBody and increment the inner counter
                if(children.get(i) instanceof CompositeBody) {
                    if (j < ((CompositeBody) children.get(i)).children.size())
                        return ((Brick) ((CompositeBody) children.get(i)).children.get(j++));
                    else {
                        // once all of the inner Bricks of the Composite have been retrieved, reset j, and increment i
                        j = 0;
                        if (children.size() > i+1) {
                            i++;
                            return next();
                        } j = 0;
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