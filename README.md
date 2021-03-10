# nemesis

Another JSON Library for Java.

This one however emphasises the safe composition and dynamic manipulation of JSON structure.  

Contrary to most other java JSON libraries, _nemesis_ makes one view and manipulate JSON as a proper data structure, as opposed to something
that should be coerced to some domain-object. (_think Jackson JsonNode, but better_)

It is, as such, somewhat of a nemesis to most other JSON libraries.  

For a slightly more detailed introduction and rationale, please take a look [here](docs/intro.md). 

## Disclaimer
**STILL IN ACTIVE DEVELOPMENT**  
For a list of the things that currently may or may not work, take a look at the [current feature set](./docs/featureset.md).


## Usage

```java
import static nemesis.json.JsonOps.*;
import static nemesis.json.Converters.JSON_TO_LONG;

public static void main (String... args) {
    var json1 = "{ \"hello\" : \"world\" }";
    var json2 = "{ \"numbers\" : [{ \"first\" : 1 }, { \"second\" : 2 }] }";

    var modified = parse(json1)
         .insertValue("Mark", in("Oh-Hai"))
         .insertValue("value", in("Deep", "Nested", "Structure"))
         .remove("hello")
         .mergeJson(parse(json2))
         .updateValue(JSON_TO_LONG, a -> a + 1, in("numbers", 1, "second"))
         .affix()
         .map(json -> json.stringify());

     System.out.println(modified);
 }
//    {
//        "Deep":{
//          "Nested":{
//            "Structure":"value"
//              }
//          },
//        "numbers":[
//          { "first":1 },
//          { "second":3 }
//          ],
//        "Oh-Hai":"Mark"
//    }
```
See [API](./docs/api.md) for more details and examples.
## License

Copyright © 2021 Robert M. Avram

Distributed under the MIT License.