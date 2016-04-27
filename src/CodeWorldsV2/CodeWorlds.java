package CodeWorldsV2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CodeWorlds {
    public static void main(String[] args) {
        Display dsp;
        Scanner in = new Scanner(System.in);
        WorldFactory fact;
        Body world;
        Rectangle bounds;


        try {
            if (args.length < 1 || args.length > 2
                    || !args[0].equals("D") && !args[0].equals("G") && !args[0].equals("A"))
                throw new CWSException("Usage: CodeWorlds (A|D|G) [entityFile]");

            if (args[0].equals("A")) {
                fact = new AutoWorldFactory();
            } else {
                fact = new InputStreamWorldFactory(args.length == 2 ? new FileInputStream(args[1]) : System.in);
            }

            world = fact.build().getWorld();

            bounds = world.getBounds();
            System.out.printf("Bounds %s\n", bounds);

            dsp = (args[0].equals("G") || args[0].equals("A")) ?
                    new GraphicsFrame(bounds.getRight(), bounds.getBottom()).getPnl() : new DumpDisplay();

            for (Brick brk: world)
                dsp.addDisplayable(brk);

            dsp.redraw(0);
        }
        catch (IOException | CWSException err) {
            Logger.getLogger().log("Error: %s\n", err.getMessage());
        } finally {
            in.close();
        }
    }
}