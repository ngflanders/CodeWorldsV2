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
        int x, y;
        try {
            while ((line = reader.readLine()) != null) {
                lineItems = line.split(" ");
                x = Integer.parseInt(lineItems[1]);
                y =  Integer.parseInt(lineItems[2]);
                switch (lineItems[0]) {
                    case "Cow":
                        if (!modelTable.containsKey("Cow")) {
                            bodies.add(b = (new Cow(new Vector(x, y))));
                            modelTable.put("Cow", b);
                        } else {
                            bodies.add(modelTable.get("Cow").clone(new Vector(x, y)));
                        }
                        break;
                    case "Tree":
                        if (!modelTable.containsKey("Tree")) {
                            bodies.add(b = (new Tree(new Vector(x, y))));
                            modelTable.put("Tree", b);
                        } else {
                            bodies.add(modelTable.get("Tree").clone(new Vector(x, y)));
                        }
                        break;
                    case "Horse":
                        if (!modelTable.containsKey("Horse")) {
                            bodies.add(b = (new Horse(new Vector(x, y))));
                            modelTable.put("Horse", b);
                        } else {
                            bodies.add(modelTable.get("Horse").clone(new Vector(x, y)));
                        }
                        break;
                    case "Stone":
                        if (!modelTable.containsKey("Stone")) {
                            bodies.add(b = (new Stone(new Vector(x, y))));
                            modelTable.put("Stone", b);
                        } else {
                            bodies.add(modelTable.get("Stone").clone(new Vector(x, y)));
                        }
                        break;

                    default:
                        if (modelTable.containsKey(lineItems[0])) {
                            bodies.add(modelTable.get(lineItems[0]).clone(new Vector(x, y)));
                        } else {
                            // TODO add new composite body to the model table ???
                        }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}