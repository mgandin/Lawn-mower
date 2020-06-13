package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Coordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LawnRepositoryTest {

  @Test
  public void should_get_upper_right_coordinate() {
    // Given
    LawnRepository lawnRepository = new LawnRepository("5 5");

    // When
    // Then
    assertThat(lawnRepository.getUpperRightCoordinate()).isEqualTo(Coordinate.from(5, 5));
  }

  @Test
  public void should_not_parse() {
    // Given
    LawnRepository lawnRepository = new LawnRepository("55");

    // When
    // Then
    assertThatThrownBy(lawnRepository::getUpperRightCoordinate).hasMessage("error parsing coordinate for lawn 55");
  }
}
