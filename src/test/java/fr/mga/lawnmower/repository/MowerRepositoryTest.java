package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Coordinate;
import fr.mga.lawnmower.domain.Mower;
import fr.mga.lawnmower.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MowerRepositoryTest {

  @Test
  public void should_find_all_mowers() {
    // Given
    List<String> allMowersAsString = Arrays.asList("1 2 N", "3 3 E");
    MowerRepository mowerRepository = new MowerRepository(allMowersAsString);
    Map<Integer, Mower> expectedMowers = new HashMap<>();
    expectedMowers.put(1, new Mower(Position.NORTH, Coordinate.from(1, 2)));
    expectedMowers.put(2, new Mower(Position.EAST, Coordinate.from(3, 3)));

    // When
    Map<Integer, Mower> allMowers = mowerRepository.findAll();

    // Then
    assertThat(allMowers).isEqualTo(expectedMowers);
  }

  @Test
  public void should_not_parse() {
    // Given
    MowerRepository mowerRepository = new MowerRepository(Arrays.asList("12N"));

    // When
    // Then
    assertThatThrownBy(mowerRepository::findAll).hasMessage("error parsing mower 12N");
  }
}
