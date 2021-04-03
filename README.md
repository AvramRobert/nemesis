# nemesis

Another JSON Library for Java.

This one however emphasises the safe composition and dynamic manipulation of JSON structure.  

Contrary to most other java JSON libraries, _nemesis_ makes one view and manipulate JSON as a proper data structure, as opposed to something
that should be coerced to some domain-object. (_think Jackson JsonNode, but better_)

It is, as such, somewhat of a nemesis to most other JSON libraries.  

For a slightly more detailed introduction and rationale, please take a look [here](docs/intro.md). 

## Disclaimer
Not necessarily battle tested.

## Dependency
**Supports Java 1.8 and upwards**

```xml
<dependency>
  <groupId>com.ravram</groupId>
  <artifactId>nemesis</artifactId>
  <version>0.1.1</version>
</dependency>
```

## Usage

```java
public static void main (String... args) {
    String json1 = "{ \"hello\" : \"world\" }";
    String json2 = "{ \"numbers\" : [{ \"first\" : 1 }, { \"second\" : 2 }] }";

    Either<String, String> modified = parse(json1)
         .insertValue("Mark", in("Oh-Hai"))
         .insertValue("value", in("Deep", "Nested", "Structure"))
         .remove("hello")
         .mergeJson(parse(json2))
         .updateValue(JSON_TO_LONG, a -> a + 1, in("numbers", 1, "second"))
         .affix()
         .map(json -> json.encode());

     System.out.println(modified);
 }
// Right: 
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
See [API](./docs/api.md) for more details and examples.\
See [Benchmarks](./docs/benchmarks.md) for some performance related information.
## License

Copyright © 2021 Robert M. Avram

Distributed under the MIT License.