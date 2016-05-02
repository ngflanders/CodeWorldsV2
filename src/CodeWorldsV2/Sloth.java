package CodeWorldsV2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;


public class Sloth extends Animal {
    private static int imgSize;
    private static BufferedImage img;

    public Sloth(Vector loc) {super(loc, new Vector(0,0));}

    @Override
    public String getLabel() {return "Sloth";}

    @Override
    public void step() {
        super.step();   // changed from just "step()"

        vlc.scaleBy(.5);  // Sloths just move more and more slowly
    }

    @Override
    public Image getImage(int size) {
        Graphics2D grp;

        if (size != imgSize) {
            img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            grp = img.createGraphics();

            Color bodyClr = new Color(100, 79, 43), hornClr = Color.DARK_GRAY,
                    WhiteSpot = new Color(255, 255, 255), spotClr = Color.BLACK;

            //Body and Head
            grp.setColor(bodyClr);
            grp.fillOval(5*size/10,  7*size/10,  size/2,  size/3);      //Body
            grp.fillOval(7*size/20,  8*size/10,  size/4,  size/4);      //Head

            //Claws
            grp.setColor(spotClr);
            grp.fillPolygon(
                    new int[] {14*size/20, 14*size/20, 10*size/20},
                    new int[] {18*size/20, size, size}, 3);
            grp.fillPolygon(
                    new int[] {19*size/20, 19*size/20, 15*size/20},
                    new int[] {18*size/20, size, size}, 3);

            //White Spot
            grp.setColor(WhiteSpot);
            grp.fillPolygon(
                    new int[] {11*size/30, 11*size/30, 11*size/20},
                    new int[] {17*size/20, 28*size/30, 18*size/20}, 3);

            // eye
            grp.setColor(spotClr);
            grp.fillOval(7*size/20,  25*size/30,  size/12,  size/12);

            grp.dispose();
        }

        return img;
    }

    /////////////////////////////////////////////
    @Override
    public Body clone(Vector offset) {
        return new Sloth(offset);
    }
    ///////////////////////////////////////////
}