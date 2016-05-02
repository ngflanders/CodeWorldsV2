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

    /*
    Desc: returns the current InputStreamWorldFactory instance
    Pre:  the current InputStreamWorldFactory has already been initialized
    Post: the instance is returned
    */
    @Override
    public WorldFactory build() throws CWSException {
        return this;
    }

    /*
    Desc: returns a CompositeBody which contains all of the other Bodies of the world
    Pre:  the bodies arrayList has already been filled with Bodies to be displayed
    Post: the CompositeBody is created and returned
    */
    @Override
    public Body getWorld() {
        return new CompositeBody(bodies);
    }

    /*
    Desc: handles the reading in of data through any InputStream type
            also does the exception handing for other methods in the class
    Pre:  an InputStream has been provided
    Post: the arrayList bodies stores all of the objects to be displayed
    */
    private void readInputStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                addItem(line, bodies, reader);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Desc: handles the creation of patterns, adding the pattern's subitems to an ArrayList called children
    Pre:  the current line has been provided as a String, as well as a BufferedReader for reading the rest of the
            pattern's contents
    Post: the arrayList children stores all of the subitems of the pattern and the pattern is added to the modelTable
    */
    public void addPattern(String line, BufferedReader reader) throws IOException {
        String name = line.substring(2);
        ArrayList<Body> children = new ArrayList<>();
        while ((line = reader.readLine()) != null && !line.equals(")")) {
            addItem(line, children, reader);
        }
        modelTable.put(name, new CompositeBody(children));
    }

    /*
    Desc: handles the adding of items to either the world or to patterns
    Pre:  the current line has been provided as a String, as well as an arrayList to add the items to,
            as well as a BufferedReader for passing to the addPattern function reading the rest of the
            pattern's contents
    Post: the arrayList that was provided stores the object from the line that was passed in
            a prototype is also added to the modelTable if it is not already in there
    */
    public void addItem(String line, ArrayList<Body> arrayList, BufferedReader reader) throws IOException {
        Body b;
        if (line.isEmpty())             // ignore empty Strings
            return;
        if (line.charAt(0) == ' ') {    // remove leading spaces commonly provided within the pattern declaration
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
                if (!modelTable.containsKey("Cow")) {               // check if model table contains the prototype
                    arrayList.add(b = (new Cow(new Vector(x, y)))); // if not, create new object and add to arrayList
                    modelTable.put("Cow", b);                       // then add the prototype to the model table
                } else {
                    arrayList.add(modelTable.get("Cow").clone(new Vector(x, y)));   // create copy of prototype
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
            case "Ore":
                if (!modelTable.containsKey("Ore")) {
                    arrayList.add(b = (new Ore(new Vector(x, y))));
                    modelTable.put("Ore", b);
                } else {
                    arrayList.add(modelTable.get("Ore").clone(new Vector(x, y)));
                }
                break;
            case "Water":
                if (!modelTable.containsKey("Water")) {
                    arrayList.add(b = (new Water(new Vector(x, y))));
                    modelTable.put("Water", b);
                } else {
                    arrayList.add(modelTable.get("Water").clone(new Vector(x, y)));
                }
                break;

            default:
                if (modelTable.containsKey(lineItems[0])) {
                    arrayList.add(modelTable.get(lineItems[0]).clone(new Vector(x,y)));
                } else {
                    addPattern(line, reader);
                }
        }
    }
}