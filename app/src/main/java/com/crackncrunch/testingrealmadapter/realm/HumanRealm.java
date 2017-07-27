package com.crackncrunch.testingrealmadapter.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HumanRealm extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;

    public HumanRealm() {
    }

    public HumanRealm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
