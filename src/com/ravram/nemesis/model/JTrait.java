package com.ravram.nemesis.model;

import com.ravram.nemesis.JsonT;

public interface JTrait {
    JType type();
    JsonT transform();
    String encode();
}
