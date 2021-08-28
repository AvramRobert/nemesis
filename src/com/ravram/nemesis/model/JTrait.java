package com.ravram.nemesis.model;

import com.ravram.nemesis.Attempt;
import com.ravram.nemesis.JsonT;
import com.ravram.nemesis.Read;

public interface JTrait {
    default <A> Attempt<A> as (final Read<A> f) {
        return transform().as(f);
    }
    JType type();
    JsonT transform();
    String encode();
}
