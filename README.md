# nemesis

Another JSON Library for Java.

This one however emphasises the composition and dynamic manipulation of JSON structure.  

Contrary to most other java JSON libraries, _nemesis_ makes one view and manipulate JSON as a proper data structure, as opposed to something
that should be coerced to some domain-object.

It is, as such, somewhat of a nemesis to most other JSON libraries.  

## Disclaimer
**STILL IN ACTIVE DEVELOPMENT**  
For a list of the things that currently may or may not work, take a look at the [current feature set](./docs/featureset.md).


## Usage

```java
import nemesis.json.JsonOps;

public static void main (String... args) {
    var json = "{ \"hello\" : \"world\" }";
    
    var modified = JsonOps
    .parse(json)
    .insert("Mark", "Oh-Hai")
    .insertIn("value", "Deep", "Nested", "Structure")
    .remove("hello")
    .affix()
    .stringify()

    System.out.println(modified);
}

// "{ \"Oh-Hai\" : \"Mark\",
//    \"Deep\" : { \"Nested\" : { \"Structure\" : \"value\" }}}
```

See [API](./docs/api.md) for more details and examples.
## License

Copyright © 2021 Robert M. Avram

Distributed under the MIT License.