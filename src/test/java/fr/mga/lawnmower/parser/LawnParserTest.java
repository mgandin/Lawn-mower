package fr.mga.lawnmower.parser;

import fr.mga.lawnmower.domain.Coordinate;
import fr.mga.lawnmower.domain.Lawn;
import fr.mga.lawnmower.domain.Mower;
import fr.mga.lawnmower.domain.Position;
import fr.mga.lawnmower.repository.ActionAndMowerRepository;
import fr.mga.lawnmower.repository.LawnRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LawnParserTest {
  @Test
  public void should_parse_lines_to_create_lawn() throws InterruptedException {
    // Given
    List<String> lines = Arrays.asList("1 2 N", "LFLFLFLFF", "3 3 E", "FFRFFRFRRF");
    LawnParser parser = new LawnParser(new LawnRepository(), new ActionAndMowerRepository());
    parser.parseHeader("5 5");
    parser.parse(lines);
    // When
    Lawn lawn = parser.lawn();

    // And When
    List<Mower> mowers = lawn.mow();

    // Then
    assertThat(mowers).containsExactlyInAnyOrder(
      new Mower(Position.NORTH, new Coordinate(1, 3)),
      new Mower(Position.EAST, new Coordinate(5, 1)));
  }
}
