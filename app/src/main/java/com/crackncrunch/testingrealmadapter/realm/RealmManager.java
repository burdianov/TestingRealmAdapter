package com.crackncrunch.testingrealmadapter.realm;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmManager {
    private Realm mRealmInstance;

    private Realm getQueryRealmInstance() {
        if (mRealmInstance == null || mRealmInstance.isClosed()) {
            mRealmInstance = Realm.getDefaultInstance();
        }
        return mRealmInstance;
    }

    public RealmResults<HumanRealm> getHumansFromRealm() {
        return getQueryRealmInstance()
                .where(HumanRealm.class)
                .findAllAsync();
    }

    public void saveHumanToRealm(HumanRealm human) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(human));
        realm.close();
    }
}
