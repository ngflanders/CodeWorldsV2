package CodeWorldsV2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


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
                addItem(line, bodies, reader);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addPattern(String line, BufferedReader reader) throws IOException {
        String name = line.substring(2);
        ArrayList<Body> children = new ArrayList<>();
        while ((line = reader.readLine()) != null && !line.equals(")")) {
            addItem(line, children, reader);
        }
        modelTable.put(name, new CompositeBody(children));
    }


    public void addItem(String line, ArrayList<Body> arrayList, BufferedReader reader) throws IOException {
        Body b;
        if (line.isEmpty())
            return;
        if (line.charAt(0) == ' ') {
            line = line.trim();
        }
        String[] lineItems = line.split(" ");
        int x=0, y=0;
        if (lineItems.length == 3) {
            x = Integer.parseInt(lineItems[1]);
            y = Integer.parseInt(lineItems[2]);
        }
        switch (lineItems[0]) {
            case "Cow":
                if (!modelTable.containsKey("Cow")) {
                    arrayList.add(b = (new Cow(new Vector(x, y))));
                    modelTable.put("Cow", b);
                } else {
                    arrayList.add(modelTable.get("Cow").clone(new Vector(x, y)));
                }
                break;
            case "Tree":
                if (!modelTable.containsKey("Tree")) {
                    arrayList.add(b = (new Tree(new Vector(x, y))));
                    modelTable.put("Tree", b);
                } else {
                    arrayList.add(modelTable.get("Tree").clone(new Vector(x, y)));
                }
                break;
            case "Horse":
                if (!modelTable.containsKey("Horse")) {
                    arrayList.add(b = (new Horse(new Vector(x, y))));
                    modelTable.put("Horse", b);
                } else {
                    arrayList.add(modelTable.get("Horse").clone(new Vector(x, y)));
                }
                break;
            case "Stone":
                if (!modelTable.containsKey("Stone")) {
                    arrayList.add(b = (new Stone(new Vector(x, y))));
                    modelTable.put("Stone", b);
                } else {
                    arrayList.add(modelTable.get("Stone").clone(new Vector(x, y)));
                }
                break;
            case "Sloth":
                if (!modelTable.containsKey("Sloth")) {
                    arrayList.add(b = (new Sloth(new Vector(x, y))));
                    modelTable.put("Sloth", b);
                } else {
                    arrayList.add(modelTable.get("Sloth").clone(new Vector(x, y)));
                }
                break;

            default:
                if (modelTable.containsKey(lineItems[0])) {
                    arrayList.add(modelTable.get(lineItems[0]).clone(new Vector(x,y)));
                } else {
                    addPattern(line, reader );
                }
        }
    }


}