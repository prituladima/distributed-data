package com.prituladima.distributed_data.librusec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StreamReader {

    public long readStream(InputStream stream){

        long counter = 0;

        BufferedReader br = null;

        InputStreamReader reader = null;

        Scanner scanner = null;

        try {

            reader = new InputStreamReader(stream);
            br = new BufferedReader(reader);
            scanner = new Scanner(reader);
            String sCurrentLine;

            while (scanner.hasNext()) {
                scanner.next();
                counter++;
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (reader != null)
                    reader.close();

                if (scanner != null)
                    scanner.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
            return counter;

        }
    }

}
