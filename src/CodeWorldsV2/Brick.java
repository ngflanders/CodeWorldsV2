package CodeWorldsV2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;

// Fundamental unit of display.  A Brick is one cell in the display.
public abstract class Brick implements Body, Displayable {

    Brick a = this;

    // Create an image of color |bg|, with |fg| dots at random location, with
    // one |fg| dot out of every |skip| dots.
    static public Image makeStippleImage(int size, Color bg, Color fg,
                                         int skip, int subSkip) {
        Graphics2D grp;
        int dotSize = Math.max(1, size/16);
        int cols = size/dotSize, numDots = cols * size/dotSize;
        BufferedImage img;

        img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        grp = img.createGraphics();

        grp.setColor(bg);
        grp.fillRect(0, 0, size, size);
        grp.setColor(fg);
        for (int dot = 0; dot < numDots; dot ++)
            if (dot % skip == 0 || dot % subSkip == 0)
                grp.fillRect(dot % cols * dotSize, dot/cols * dotSize, dotSize,
                        dotSize);

        grp.dispose();

        return img;
    }

    @Override
    abstract public Vector getLoc();
    @Override
    public Vector getVlc() {return new Vector();}

    @Override
    public Iterator<Brick> iterator() {
        return new Iterator<Brick>() {
            boolean b = true;
            @Override
            public boolean hasNext() {
                return b;
            }

            @Override
            public Brick next() {
                Brick temp;
                temp =  b ? a : null;
                b = false;
                return temp;
            }
        };
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getLoc().getX(),getLoc().getY(),1,1);
    }

//    @Override
//    public Body clone(Vector offset) {
//        // not sure if clone should be implemented in Brick or in the child classes such as Cow
//        if (this instanceof Cow) {
//            return new Cow(new Vector(offset.getX() + getLoc().getX(), offset.getY() + getLoc().getY()));
//        } else if (this instanceof Tree) {
//            return new Tree(offset);
//        } else if (this instanceof Horse) {
//            return new Horse(offset);
//        } else if (this instanceof Stone) {
//            return new Stone(offset);
//        }
//        return null;
//    }
}
