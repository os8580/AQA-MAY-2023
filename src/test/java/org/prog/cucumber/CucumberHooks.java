package org.prog.cucumber;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;

public class CucumberHooks implements EventListener {
  @Override
  public void setEventPublisher(EventPublisher eventPublisher) {
    eventPublisher.registerHandlerFor(TestRunStarted.class, this::setUp);
    eventPublisher.registerHandlerFor(TestRunFinished.class, this::tearDown);
  }

  public void setUp(TestRunStarted event) {
    System.out.println("Set up code");
  }

  public void tearDown(TestRunFinished event) {
    System.out.println("Tear down code");
  }
}
