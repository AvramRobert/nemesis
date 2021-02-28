# nemesis

Another JSON Library for Java.

This one however emphasises the safe composition and dynamic manipulation of JSON structure.  

Contrary to most other java JSON libraries, _nemesis_ makes one view and manipulate JSON as a proper data structure, as opposed to something
that should be coerced to some domain-object. (_think Jackson JsonNode, but better_)

It is, as such, somewhat of a nemesis to most other JSON libraries.  

## Disclaimer
**STILL IN ACTIVE DEVELOPMENT**  
For a list of the things that currently may or may not work, take a look at the [current feature set](./docs/featureset.md).


## Usage

```java
import nemesis.json.JsonOps;

public static void main (String... args) {
    var json1 = "{ \"hello\" : \"world\" }";
    var json2 = "{ \"numbers\" : [{ \"first\" : 1 }, { \"second\" : 2 }] }";
    
    var modified = JsonOps
    .parse(json1)
    .insert("Mark", "Oh-Hai")
    .insertIn("value", "Deep", "Nested", "Structure")
    .remove("hello")
    .merge(JsonOps.parse(json2))
    .updateIn((n: Integer) -> n + 1, "numbers", 1, "second")
    .affix()
    .stringify()

    System.out.println(modified);
}

// "{ \"Oh-Hai\" : \"Mark\",
//    \"Deep\" : { \"Nested\" : { \"Structure\" : \"value\" }}
//    \"numbers\" : [{ \"first\" : 1 }, { \"second\" : 3 }] }
```

See [API](./docs/api.md) for more details and examples.
## License

Copyright © 2021 Robert M. Avram

Distributed under the MIT License.