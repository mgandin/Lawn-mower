package fr.mga.lawnmower;

import com.google.common.io.Files;
import fr.mga.lawnmower.parser.LawnParser;
import fr.mga.lawnmower.repository.ActionAndMowerRepository;
import fr.mga.lawnmower.repository.LawnRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Runner {

  public static void main(String[] args) throws IOException, InterruptedException {
    if(args.length != 0) {
      try {
        List<String> strings = Files.readLines(new File(args[0]), StandardCharsets.UTF_8);
        String header = strings.get(0);
        int chunk = 2;
        final AtomicInteger counter = new AtomicInteger();
        List<List<String>> chunked = strings.stream()
          .collect(Collectors.groupingBy(it -> counter.incrementAndGet() / chunk))
          .values().stream().skip(1).collect(Collectors.toList());
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        chunked.forEach(mowerAndCommand -> {
          executorService.execute(() -> {
            try {
              run(header, mowerAndCommand);
            } catch (InterruptedException e) {
              System.out.println("ERROR : " + e);
            }
          });
        });
      } catch (IllegalArgumentException e) {
        System.out.println("ERROR : " + e);
      }
    } else {
      System.out.println("You must provide a file to parse");
      System.out.println("example : java -jar target/lawnmower-1.0-SNAPSHOT-shaded.jar src/main/resources/lawn.txt");
    }
  }

  public static void run(String header, List<String> mowerAndCommand) throws InterruptedException {
    LawnParser parser = new LawnParser(new LawnRepository(), new ActionAndMowerRepository());
    parser.parseHeader(header);
    parser.parse(mowerAndCommand);
    parser.lawn().mow().forEach(mower -> System.out.println(Thread.currentThread().toString() + mower));
  }
}
