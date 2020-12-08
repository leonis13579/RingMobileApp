package ru.realityfamily.ringapp.Models;

import androidx.annotation.Nullable;

public class CloudObject {
    String _id;
    String name;
    State state;

    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public class State {
        boolean ring;

        public boolean isRing() {
            return ring;
        }
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CloudObject) {
            return this._id.equals(((CloudObject) obj).getId());
        }
        return super.equals(obj);
    }
}
