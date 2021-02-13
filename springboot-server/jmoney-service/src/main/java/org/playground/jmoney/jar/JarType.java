package org.playground.jmoney.jar;

public enum JarType {

  PRIMARY("primary"),
  SECONDARY("secondary"),
  SUCCESS("success"),
  WARNING("warning"),
  DANGER("danger"),
  LIGHT("light"),
  LINK("link");

  private String name;

  JarType(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public static JarType fromName(String name) {
    for (JarType t : values()) {
      if (t.name == name) {
        return t;
      }
    }
    return null;
  }
}
