package CodeWorldsV2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class InputStreamWorldFactory implements WorldFactory {

    HashMap<String, Body> modelTable;
    ArrayList<Body> bodies;

    public InputStreamWorldFactory(InputStream inputStream) {
        bodies = new ArrayList<>();
        modelTable = new HashMap<>();
        readInputStream(inputStream);

    }

    @Override
    public WorldFactory build() throws CWSException {

        return this;
    }

    @Override
    public Body getWorld() {
        return new CompositeBody(bodies);
    }

    private void readInputStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String[] lineItems;
        Body b;
        try {
            while ((line = reader.readLine()) != null) {
                lineItems = line.split(" ");
                switch (lineItems[0]) {
                    case "Cow":
                        if (!modelTable.containsKey("Cow")) {
                            bodies.add(b = (new Cow(new Vector(Integer.parseInt(lineItems[1]), Integer.parseInt(lineItems[2])))));
                            modelTable.put("Cow", b);
                        } else {
                            bodies.add(modelTable.get("Cow").
                                    clone(new Vector(Integer.parseInt(lineItems[1]), Integer.parseInt(lineItems[2]))));
                        }
                        break;
                    case "Tree":
                        if (!modelTable.containsKey("Tree")) {
                            bodies.add(b = (new Tree(new Vector(Integer.parseInt(lineItems[1]), Integer.parseInt(lineItems[2])))));
                            modelTable.put("Tree", b);
                        } else {
                            bodies.add(modelTable.get("Tree").
                                    clone(new Vector(Integer.parseInt(lineItems[1]), Integer.parseInt(lineItems[2]))));
                        }
                        break;
                    case "Horse":
                        if (!modelTable.containsKey("Horse")) {
                            bodies.add(b = (new Horse(new Vector(Integer.parseInt(lineItems[1]), Integer.parseInt(lineItems[2])))));
                            modelTable.put("Horse", b);
                        } else {
                            bodies.add(modelTable.get("Horse").
                                    clone(new Vector(Integer.parseInt(lineItems[1]), Integer.parseInt(lineItems[2]))));
                        }
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}