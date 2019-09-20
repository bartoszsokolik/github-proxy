package pl.solutions.software.sokolik.bartosz.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MockedGithubHealthIndicator implements HealthIndicator {

  @Override
  public Health health() {
    if (isUp()) {
      return Health.up().build();
    }
    return Health.down().build();
  }

  private boolean isUp() {
    return true;
  }
}
