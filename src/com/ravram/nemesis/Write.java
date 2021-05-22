package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.model.Json;

public interface Write<A> extends Convert<String, A, Json> {}
