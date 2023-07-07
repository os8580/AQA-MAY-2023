package org.prog.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DataHolder {

//  private static final DataHolder instance = new DataHolder();

    private final HashMap<String, Object> holder = new HashMap<>();

//  private DataHolder() {
//    holder = new HashMap<>();
//  }

//  public static DataHolder getInstance() {
//    return instance;
//  }

    public void putValue(String alias, Object value) {
        holder.put(alias, value);
    }

    public Object getValue(String alias) {
        return holder.get(alias);
    }
}
