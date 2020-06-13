package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Coordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LawnRepositoryTest {

  @Test
  public void should_get_upper_right_coordinate() {
    // Given
    LawnRepository lawnRepository = new LawnRepository("5 5");

    // When
    // Then
    assertThat(lawnRepository.getUpperRightCoordinate()).isEqualTo(Coordinate.from(5, 5));
  }
}
