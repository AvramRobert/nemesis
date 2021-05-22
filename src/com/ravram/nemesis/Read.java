package com.ravram.nemesis;

import com.ravram.nemesis.coerce.Convert;
import com.ravram.nemesis.model.Json;

public interface Read<A> extends Convert<String, Json, A> {}
