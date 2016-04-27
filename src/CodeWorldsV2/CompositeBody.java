package CodeWorldsV2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CompositeBody implements Body, Displayable {

    ArrayList<Body> children;

    @SuppressWarnings("unchecked")
    public CompositeBody(ArrayList bodies) {
//        for (Object o : bodies) {
//            if (!(o instanceof Body))
//                try {
//                    throw new CWSException("Bad Array");
//                } catch (CWSException e) {
//                    e.printStackTrace();
//                }
//        }

        children = bodies;
    }

    // Copy constructor
    public CompositeBody(CompositeBody other) {

    }

    @Override
    public Rectangle getBounds() {
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *  Implement UnionBy
         *  HERE
         *
         */
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        int leftMax=0, rightMax=0, topMax=0, bottomMax=0, temp;
        Rectangle r;
        for (Body b : children) {
            r = b.getBounds();
            if ((temp = r.getLeft()) < leftMax)
                leftMax = temp;
            if ((temp = r.getRight()) > rightMax)
                rightMax = temp;
            if ((temp = r.getTop()) < topMax)
                topMax = temp;
            if ((temp = r.getBottom()) > bottomMax)
                bottomMax = temp;
        }
        return new Rectangle(leftMax, topMax, rightMax-leftMax, bottomMax-topMax);

        /**
         * Refer to Tom's email on 4/24/2016
         */

    }

    @Override
    public Body clone(Vector offset) {
        return new CompositeBody(this); // needs offset still!
    }

    @Override
    public Iterator<Brick> iterator() {
        return new Iterator<Brick>() {
            int i = -1;
            @Override
            public boolean hasNext() {
                return i<children.size()-1;
            }

            @Override
            public Brick next() {
                i++;
                return ((Brick) children.get(i));
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