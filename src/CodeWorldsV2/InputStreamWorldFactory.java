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
       // bodies = new ArrayList<>();
        return null;
    }

    @Override
    public Body getWorld() {
        return null;
    }



    private void readInputStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;

        try {

            while ((line = reader.readLine()) != null) {
                out.append(line).append("\n");
            }

            System.out.println(out.toString());   //Prints the string content read from input stream
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}