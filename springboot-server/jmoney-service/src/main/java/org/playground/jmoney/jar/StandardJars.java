package org.playground.jmoney.jar;

public enum StandardJars {

  PLAY("Play"),
  FINANCIAL_FREEDOM("Financial Freedom"),
  NECESSITIES("Necessities"),
  LONG_TERM_SPENDING("Long Term Spending"),
  EDUCATION("Education"),
  GIVE("Give");

  private String name;

  private StandardJars(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
