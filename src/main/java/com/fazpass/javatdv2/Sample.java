package com.fazpass.javatdv2;

import com.fazpass.javatdv2.exception.FazpassException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class Sample
{
   /* public static void main( String[] args )
    {
        String url = "http://localhost:8080";
        try {
            String privateKey = readKeyFromFile("./key.priv");
//            System.out.println(privateKey);
            TrustedDevice td = Fazpass.initialize(privateKey, url);
            Device d = td.enrollDevice("085811751000","meta_data", "app-id");
            System.out.println(d.getFazpassId());
            System.out.println(d.getScoring());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FazpassException e) {
            System.out.println(e.getMessage());
        }

    }

    public static String readKeyFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }*/
}
