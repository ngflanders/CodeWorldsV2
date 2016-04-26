package CodeWorldsV2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamWorldFactory implements WorldFactory {

    public InputStreamWorldFactory(InputStream inputStream) {
        readInputStream(inputStream);




    }

    @Override
    public Body getWorld() {
        return null;
    }

    @Override
    public WorldFactory build() throws CWSException {
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
