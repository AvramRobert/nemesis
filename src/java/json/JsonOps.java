package json;

import json.data.*;

public class JsonOps {

    public static Json empty() {
        return JEmpty.instance;
    }

    public static void main(String... args) {
        empty().transform().assocIn(empty(), "1", 2, 3, "0");
    }
}