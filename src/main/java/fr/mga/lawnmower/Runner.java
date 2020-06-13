package fr.mga.lawnmower;

import com.google.common.io.Files;
import fr.mga.lawnmower.parser.LawnParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Runner {

  public static void main(String[] args) throws IOException {
    if(args.length != 0) {
      try {
        List<String> strings = Files.readLines(new File(args[0]), StandardCharsets.UTF_8);
        LawnParser parser = new LawnParser(strings);
        parser.lawn().mow().forEach(System.out::println);
      } catch (IllegalArgumentException e) {
        System.out.println("ERROR : " + e);
      }
    } else {
      System.out.println("You must provide a file to parse");
      System.out.println("example : java -jar target/lawnmower-1.0-SNAPSHOT-shaded.jar src/main/resources/lawn.txt");
    }
  }
}
