package org.util;

import java.util.HashMap;

public class DataHolder {

  private static final DataHolder instance = new DataHolder();

  private final HashMap<String, Object> holder;

  private DataHolder() {
    holder = new HashMap<>();
  }

  public static DataHolder getInstance() {
    return instance;
  }

  public void putValue(String alias, Object value) {
    holder.put(alias, value);
  }

  public Object getValue(String alias) {
    return holder.get(alias);
  }
}
