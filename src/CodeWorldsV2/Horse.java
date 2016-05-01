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
        Color bodyClr = new Color(169, 138, 61), hornClr = Color.DARK_GRAY,
                hoofClr = new Color(60, 60, 40), spotClr = Color.BLACK;

        if (size != imgSize) {
            img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            grp = img.createGraphics();

            // Body, head, and ear
            grp.setColor(bodyClr);
            grp.fillRoundRect(/*X location*/3*size/10, /*Y location*/4*size/10, /*Width of Rect*/ 5*size/10,
                    /*Height of Rect*/ 2*size/10, size/10, size/10);
            grp.fillRoundRect(size/20,  2*size/10,  3*size/10,  2*size/10,
                    size/10, size/10);
            grp.fillPolygon(
                    new int[] {5*size/20, 8*size/20, 9*size/20},
                    new int[] {4*size/20, 4*size/20, 3*size/20}, 3);


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

            //Neck
            grp.setColor(bodyClr);
            grp.fillPolygon(
                    new int[] {size*6/20, size*4/10, size*9/20, size*3/10},
                    new int[] {size*4/20, size*4/20, size*9/20, size*9/20}, 4);

            //Tail
            grp.setColor(hornClr);
            grp.fillPolygon(
                    new int[] {16*size/20, 16*size/20, 19*size/20},
                    new int[] {4*size/10, 5*size/10, 6*size/10}, 3);

            // eye
            grp.setColor(spotClr);
            grp.fillOval(2*size/10,  2*size/10,  size/15,  size/15);


            // Mane
            grp.setColor(hornClr);
            grp.fillPolygon(
                    new int[] {/*top right*/size*6/20, /*top left*/size*4/10, /*bottom right*/size*10/20,
                            /*bottom left*/size*4/10},
                    new int[] {/*top right*/size*4/20, /*top left*/size*4/20, /*bottom right*/size*8/20,
                            /*bottom left*/size*8/20}, 4);
            grp.fillPolygon(
                    new int[] {size*6/20, size*4/10, size*9/20, size*4/10},
                    new int[] {size*4/20, size*4/20, size*9/20, size*9/20}, 4);

            Color greenish = new Color(230, 255, 230);

            grp.dispose();
        }

        return img;
    }

    ///////////////////////////////////////
//    @Override
//    public Body clone(Vector offset) {
//        return null;
//    }
    ////////////////////////////////////////
}
