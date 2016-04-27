package CodeWorldsV2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Horse extends HerdAnimal {
    private static int imgSize;
    private static BufferedImage img;

    public Horse(Vector loc) {super(loc, new Vector());}

    @Override
    public String getLabel() {return "Horse";}

    @Override
    boolean isGoodLeader(HerdAnimal ldr) {
        return ldr instanceof Horse && ldr.loc.equals(loc)
                && ldr.vlc.length() > vlc.length();
    }

    @Override
    public Image getImage(int size) {
        Graphics2D grp;
        Color bodyClr = new Color(100, 100, 100), hornClr = Color.DARK_GRAY,
                hoofClr = new Color(60, 60, 40), spotClr = Color.BLACK;

        if (size != imgSize) {
            img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            grp = img.createGraphics();

            // Body and head
            grp.setColor(bodyClr);
            grp.fillRoundRect(/*X location*/3*size/10, /*Y location*/3*size/10, /*Width of Rect*/ 6*size/10,
                    /*Height of Rect*/ 4*size/10, size/10, size/10);
            grp.fillRoundRect(size/10,  2*size/10,  3*size/10,  2*size/10,
                    size/10, size/10);

            // Hair
            /**
            grp.fillPolygon(
                    new int[] {size*4/10, size*5/10, size*4/10, size*5/20},
                    new int[] {size/2, size/2, size*17/20, size*17/20}, 4);
            grp.fillPolygon(
                    new int[] {size*7/10, size*8/10, size*9/10, size*15/20},
                    new int[] {size/2, size/2, size*17/20, size*17/20}, 4);
             **/

            // Legs
            grp.fillPolygon(
                    new int[] {size*4/10, size*5/10, size*4/10, size*5/20},
                    new int[] {size/2, size/2, size*17/20, size*17/20}, 4);
            grp.fillPolygon(
                    new int[] {size*7/10, size*8/10, size*9/10, size*15/20},
                    new int[] {size/2, size/2, size*17/20, size*17/20}, 4);

            // Hooves
            grp.setColor(hoofClr);
            grp.fillPolygon(
                    new int[] {size*5/20, size*4/10, size*9/20, size*2/10},
                    new int[] {size*17/20, size*17/20, size*19/20, size*19/20}, 4);
            grp.fillPolygon(
                    new int[] {size*15/20, size*9/10, size*19/20, size*7/10},
                    new int[] {size*17/20, size*17/20, size*19/20, size*19/20}, 4);

            // eye
            grp.setColor(spotClr);
            grp.fillOval(2*size/10,  2*size/10,  size/15,  size/15);


            // Horn
            grp.setColor(hornClr);
            grp.fillPolygon(
                    new int[] {3*size/10, 7*size/20, 5*size/20},
                    new int[] {2*size/10, 2*size/10, size/10}, 3);

            Color greenish = new Color(230, 255, 230);
            grp.fillOval(10,  20, 5, 5);

            grp.dispose();
        }

        return img;
    }

    ///////////////////////////////////////
    @Override
    public Body clone(Vector offset) {
        return new Horse(offset);
    }
    ////////////////////////////////////////
}
