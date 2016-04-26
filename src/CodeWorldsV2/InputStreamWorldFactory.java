package CodeWorldsV2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class InputStreamWorldFactory implements WorldFactory {

    ArrayList<Body> bodies;

    public InputStreamWorldFactory(InputStream inputStream) {
        readInputStream(inputStream);

    }

    @Override
    public WorldFactory build() throws CWSException {
        bodies = new ArrayList<>();
        return this;
    }

    @Override
    public Body getWorld() {
        return null;
    }

    private void readInputStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        String[] lineitems;


        try {

            while ((line = reader.readLine()) != null) {

                lineitems = line.split(" ");
                switch (lineitems[0]) {
                    case "Cow":
                        bodies.add(new Cow(new Vector(Integer.parseInt(lineitems[1]), Integer.parseInt(lineitems[2]))));
                        break;
                    case "Tree":
                        bodies.add(new Tree(new Vector(Integer.parseInt(lineitems[1]), Integer.parseInt(lineitems[2]))));
                        break;
                    case "Horse":
                        bodies.add(new Horse(new Vector(Integer.parseInt(lineitems[1]), Integer.parseInt(lineitems[2]))));
                        break;
                }

                out.append(line).append("\n");
            }

            System.out.println(out.toString());   //Prints the string content read from input stream
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}