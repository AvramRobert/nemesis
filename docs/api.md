# Nemesis API

## Table of contents

1. [Types and classes](api.md#types-and-classes-to-expect)
1. [Parsing](api.md#parsing)
1. [Encoding](api.md#encoding)   
1. [Transforming](api.md#transforming)
    * [Inserting](api.md#inserting)
    * [Removing](api.md#removing)
    * [Updating](api.md#updating)
    * [Retrieving](api.md#retrieving)
    * [Merging](api.md#merging)
    * [Creating](api.md#creating)
    * [Casting](api.md#casting)
    * [Reducing](api.md#reducing)
    * [Affixing](api.md#terminating--affixing-structure)
    * [Traversing](api.md#traversing) (not supported yet)
1. [Converting](api.md#converting)
    * [JSON to Object conversion](api.md#json-converters)
    * [Object to JSON conversion](api.md#object-converters)
    * [Automatic conversion](api.md#automatic-converter-derivation) (not supported)
1. [Error handling](api.md#error-handling)

## Types and classes to expect

### `Json`
   * Type representing a correct json value.

### `JsonT`
   * Type representing a json currently undergoing transformation.
   * Supports the primary manipulative functions.
   * Not a fixed JSON, transformation has to be terminated to get a `Json` out of it. (see [here](api.md#terminating--affixing-structure)).

### `Either<A, B>`
   * Left-biased disjunctive type used for representing successful and erroneous computations.
   * The left-hand side is seen as the error type, whilst the right-hand side as the success type.

### `Convert<A, B>`
   * An interface describing the safe conversion from a type `A` to a type `B`.
   * Equivalent to `Function<A, Either<String, B>>`
   
### `JsonConverter<A>`
   * A helper class to aid in writing `Convert<Json, A>` instances for json objects.
   
### `ObjectConverter<A>`
   * A helper class to aid in writing `Convert<A, Json>` instances for arbitrary objects.

## Parsing

### To `JsonT`

Generally, parsing reads directly into a `JsonT`.

```java


parse("{ \"hello\" : \"world\" }") // JsonT
```

### To an arbitrary type

You can however also parse directly to an arbitrary type `A`, given an instance of `Convert<Json, A>` for that type.

For more information on `Convert`, take a look [here](api.md#converting).

```java

final String myJson = ...;
final Convert<Json, MyType> myTypeConverter = ...;

parseAs(myTypeConverter, myJson);
```

## Encoding

Any `Json` can be encoded to a proper Json string by calling `encode`.

**Note:** Calling `toString` on these objects will **NOT** write to a valid json. 

```java
Json myJson = ...;

myJson.encode();
```

## Transforming

Assume we start with the following:
```java


JsonT jsonT = parse("{ \"hello\" : \"world\" }")
```

### Inserting

#### `Json` or `JsonT`

The simplest insertion is of either `Json` or `JsonT` values.

```java

JsonT jsonT2 = parse("{ \"a\" : 1 }");

jsonT.insertJson(jsonT2, in("node"));
```

```json
{
  "hello": "world",
  "node": {
    "a": 1
  }
}
```

#### Raw values

Any raw values can be directly inserted into a `JsonT`, **as long as they are part of the JSON specification**.

```java
jsonT
    .insertValue(1, in("field-1"))
    .insertValue(true, in("field-2"))
    .insertValue("bla", in("field-3"))
    .insertValue(null, in("field-4"))
    .insertValue(Arrays.asList(1, 2, 3, 4)), in("field-5"))
    .insertValue(new int[]{1, 2, 3, 4}, in("field-6"))
    .insertValue(new HashSet(Arrays.asList(1, 2, 3, 4)), in("field-7"))
    .insertValue(Map.of("a", "b"), in("field-8"));
```

```json
{
  "hello": "world",
  "field-1": 1,
  "field-2": true,
  "field-3": "bla",
  "field-4": null,
  "field-5": [
    1,
    2,
    3,
    4
  ],
  "field-6": [
    1,
    2,
    3,
    4
  ],
  "field-7": [
    1,
    2,
    3,
    4
  ],
  "field-8": {
    "a": "b"
  }
```
#### Arbitrary values

For arbitrary types, you'll have to provide a `Convert<A, Json>` instance. 

For more information on `Convert`, take a look [here](api.md#converting).

```java
import static com.ravram.nemesis.coerce.ObjectConverter.object;
import static com.ravram.nemesis.Converters.INT_TO_JSON;

static class Point {
    final int a;
    final int b;

    public Point(final int a, final int b) {
       this.a = a;
       this.b = b;
    }
}

Convert<Point, Json> pointConverter = point -> 
            convert(point)
                .with("a", p -> INT_TO_JSON.convert(p.a),
                      "b", p -> INT_TO_JSON.convert(p.b));

jsonT.insertValue(new Point(1, 1), pointConverter, in("point"))
```

```json
{
  "hello": "world",
  "point": {
    "a": 1,
    "b": 2
  }
}
```

#### Nested insertions

Values can be inserted with arbitrary nestedness. Structure will automatically be created wherever none exists.

**Note: This only works on JSON objects.**

```java
jsonT.insertValue(true, in("is", "this", "deep", "enough"))
```

```json
{
  "hello": "world",
  "is": {
    "this": {
      "deep": {
        "enough": true
      }
    }
  }
}
```

### Removing

Entries can only be deleted at the top level. (Support for removal at arbitrary nestedness is on the way)

**Note: This only works on JSON objects.**

```java
jsonT
    .insertValue(1, in("value"))
    .remove("hello", "value");
```

```json
{}
```

### Updating

Updates are supported for any value at any degree of nestedness.  
Can either be performed directly on a `JsonT` node or on a proper type `A`, provided a `Convert<Json, A>` instance.

**Note: Converters for JSON types can be found in `com.ravram.nemesis.Converters`**
```java


jsonT
    .insertValue(1, in("one", "level")
    .updateValue(JSON_TO_INT, n -> n + 1, in("one", "level"));
```

```json
{
  "hello": "world",
  "one": {
    "level": 2
  }
}
```

### Retrieving


#### `Json`
JSON can be retrieved from any level and any structure.

**Note: This returns a `JsonT` to leverage safety and further composition.**

```java
jsonT
  .insertValue(Arrays.asList(1, 2, 3), in("one", "level"))
  .getJson(in("one", "level", 2));
```

#### Values
Values of any type `A` can be retrieved from any level and any structure, provided a `Convert<Json, A>` for that type.

For more information on `Convert`, take a look [here](api.md#converting).

```java


jsonT
  .insertValue(true, in("one", "level"))
  .getValue(JSON_TO_BOOL, in("one", "level", 2));
  
```

###  Merging

Two `JsonT`s can be merged together if they're both either JSON objects or arrays respectively.

**Note:**
```java
JsonT jsonObj1 = parse("{ \"oh\" : \"my\" }");
JsonT jsonObj2 = parse ("{ \"well\" : \"hi\" }")

JsonT jsonArr1 = parse("[1, 2, 3, 4]");
JsonT jsonArr2 = parse("[5, 6, 7, 8]");

jsonObj1.mergeJson(jsonObj2);
jsonArr1.mergeJson(jsonArr2);
```

```json
{
  "oh": "my",
  "well": "hi"
}

[1, 2, 3, 4, 5, 6, 7, 8]
```

### Casting

A `JsonT` can be materialised to a concrete type `A` provided a `Convert<Json, A>` instance for that type.

For more information on `Convert`, take a look [here](api.md#converting).

#### Casting to JSON types

Converters for basic JSON types can be found in `com.ravram.nemesis.Converters`

A list of all default converters can be found [here](api.md#default-json-converters).

```java


jsonT.getJson(in("hello")).as(JSON_TO_STRING); // Either<String, String>
```
or
```java
jsonT.getValue(JSON_TO_STRING, in("hello")); // Either<String, String>
```

#### Casting to arbitrary types

Like [previously](api.md#casting) mentioned, any `JsonT` can be coerced to an arbitrary type `A` given
that one constructs a `Convert<Json, A>` instance for that type.

For more information on `Convert`, take a look [here](api.md#converting).

```java
static class Greeting {
    public final String value;
    
    public Greeting(final String value) {
        this.value = value;
    } 
}

Convert<Json, Greeting> converter = json ->
    convert(json)
        .with(json  -> json.getValue(JSON_TO_STRING, in("hello")), 
              value -> new Greeting(value));
```

### Reducing

Any `JsonT` can be reduced shallowly for both JSON objects and JSON arrays.

#### Objects

Object reduction occurs at the upper-most level of entries and doesn't recursively traverse down the structure. 

For tree-wise traversal, please take a look [here](api.md#traversing).

Because the reduction may very well need to coerce the inner `JsonT` to some concrete type, the reduction function
is required to enforce a return of `Either<String, A>`.

Signature: 
```java
Function3<A, String, JsonT, Either<String, A>>
```
```java
import com.ravram.nemesis.Converters.JSON_TO_STRING;

jsonT.reduceObj("Greeting:", (inter, key, jsonValue) -> {
    return jsonValue.as(JSON_TO_STRING).map(value -> inter + " " + key + " " + value);
});
```

```java
"Greeting: hello world"
``` 

#### Arrays

Array reduction only occurs at the sequence level and doesn't recursively traverse down the structure.

For tree-wise traversal, please take a look [here](api.md#traversing).

The reducing function receives the intermediate result, the current element's index and the element's value as `JsonT`.

Because the reduction may very well need to coerce the inner `JsonT` to some concrete type, the reduction function
is required to enforce a return of `Either<String, A>`.

Signature: 
```java
Function3<A, Integer, JsonT, Either<String, A>>
```
    
```java
import com.ravram.nemesis.Converters.JSON_TO_INT;

JsonT json = parse("[{\"value\" : 1}, {\"value\" : 2}, {\"value\" : 3}, {\"value\" : 4}]")

json.reduceArr(0, (inter, index, jsonValue) -> {
    return jsonValue.getAs(JSON_TO_INT, in("value")).map(x -> x + inter);
});
```

### Creating

Json's can be manually created from an empty node.

```java
empty.transform().insertValue("hello", "world");
```

```json
{ "hello" : "world" }
```

### Affixing

To materialise a `JsonT` to a concrete `Json` type, it's transformation has to be terminated.

This yields an `Either<String, Json>` indicating either a failed (`String`) successful (`Json`) transformation. \
Yes, the error is a `String`. Don't worry, it contains more information that you may expect.

```java
jsonT.affix()
```
### Traversing

**NOT YET SUPPORTED**

## Converting

Conversion from JSON to Java types and vice-versa is abstractly modeled by the interface `Convert<A, B>`

### Json Converters

Converting `Json` to an arbitrary type `A` is done by defining an instance of `Convert<Json, A>` for it.

#### Default Json Converters

For the most basic of JSON types, _nemesis_ already provides converters in `com.ravram.nemesis.Converters`.

Here's a list:

* `JSON_TO_INT` -> `Convert<Json, Int>`
* `JSON_TO_LONG` -> `Convert<Json, Long>`
* `JSON_TO_DOUBLE` -> `Convert<Json, Double>`
* `JSON_TO_FLOAT` -> `Convert<Json, Float>`
* `JSON_TO_STRING` -> `Convert<Json, String>`
* `JSON_TO_BOOLEAN` -> `Convert<Json, Boolean>`
* `JSON_TO_NULL`    -> `Convert<Json, Void>`
* `JSON_TO_LIST`    -> `Convert<Json, List<Json>>`
* `JSON_TO_SET`    -> `Convert<Json, Set<Json>>`
* `listOf(Convert<Json, A>)` -> `Convert<Json, List<A>>`
* `setOf(Convert<Json, A>)` -> `Convert<Json, Set<A>>`
* `mapOf(Convert<Json, A>)`  -> `Convert<Json, Map<String, A>>`
* `optionalOf(Convert<Json, A>)` -> `Convert<Json, Optional<A>>`

#### Custom Json Converters

When it comes to creating your own, _nemesis_ provides some help.

There's a `convert` function which works like this:

```java

Convert<Json, MyType> converter = json ->
    convert(json)
        .with (
            // the first n - 1 arguments are individual `Convert` instances
            // with which you extract and convert individual attributes of your type
              .., // Convert<JsonT, A> 
              .., // Convert<JsonT, B>
              .., // Convert<JsonT, C>
              ..,
            // the last argument is a function containing all converted attribues
            // which you use to intantiate your type
              (a, b, c) -> new MyType(a, b, c))
```
**Example:**
```java
import static com.ravram.nemesis.Converters.*;

static class Coord {
    public final int s;
    public final int e;

    public Coord(final int s, final int e) {
        this.s = s;
        this.e = e;
    }
}

static class Line {
    public final Coord x;
    public final Coord y;
    
    public Line(final Coord x, final Coord y) {
        this.x = x;
        this.y = y;
    }
}

static class Figure {
    public final List<Line> lines;
    
    public Figure(final List<Line> lines) {
        this.lines = lines;
    }
}

Convert<Json, Coord> coord = json ->
    convert(json)
        .with(js -> js.getValue(JSON_TO_INT, in("s")),
              js -> js.getValue(JSON_TO_INT, in("e")),
              (s, e) -> new Coord(s, e));

Convert<Json, Line> line = json ->
    convert(json)
        .with(js -> js.getValue(coord, in("x")),
              js -> js.getValue(coord, in("y")),
              (x, y) -> new Line(x, y));

Convert<Json, Figure> figure = json ->
    convert(json))
        .with(js    -> js.getValue(listOf(line), in("lines")),
              lines -> new Figure(lines));
```

### Object Converters

#### Default Object Converters

For the most basic of JSON types, _nemesis_ already provides converters in `com.ravram.nemesis.Converters`.

Here's a list:

* `INT_TO_JSON` -> `Convert<Int, Json>`
* `LONG_TO_JSON` -> `Convert<Long, Json>`
* `DOUBLE_TO_JSON` -> `Convert<Double, Json>`
* `FLOAT_TO_JSON` -> `Convert<Float, Json>`
* `STRING_TO_JSON` -> `Convert<String, Json>`
* `BOOLEAN_TO_JSON` -> `Convert<Boolean, Json>`
* `NULL_TO_JSON`    -> `Convert<Void, Json>`
* `LIST_TO_JSON`    -> `Convert<List<Json>, Json>`
* `SET_TO_JSON` -> `Convert<Set<Json>, Json>`
* `listFrom(Convert<A, Json>)` -> `Convert<List<A>, Json>`
* `setFrom(Convert<A, Json>`) -> `Convert<Set<A>, Json>`
* `mapFrom(Convert<A, Json>)` -> `Convert<Map<String, A>, Json>`
* `optionalFrom(Convert<A, Json>)` -> `Convert<Optional<A>, Json>`

#### Custom Object Converters

When it comes to creating your own, _nemesis_ provides some help.

There's a `convert` function which works like this:

```java

Convert<MyType, Json> converter = json ->
    convert(json)
        .with (
            // arguments are pairs of `String` and `Convert<MyType, Json>`
            // `String` indicates the key an attribute of `MyType` should have in the json
            // `Convert<MyType, Json>` extracts that attribute and transforms it to a json
              .., .. // String, Convert<MyType, Json> 
              .., .. // String, Convert<MyType, Json>
              .., .. // String, Convert<MyType, Json>)
```
**Example:**
```java
import static com.ravram.nemesis.Converters.*;

static class Coord {
    public final int s;
    public final int e;

    public Coord(final int s, final int e) {
        this.s = s;
        this.e = e;
    }
}

static class Line {
    public final Coord x;
    public final Coord y;
    
    public Line(final Coord x, final Coord y) {
        this.x = x;
        this.y = y;
    }
}

static class Figure {
    public final List<Line> lines;
    
    public Figure(final List<Line> lines) {
        this.lines = lines;
    }
}

Convert<Coord, Json> jcoord = coord ->
    convert(coord)
        .with("s", c -> INT_TO_JSON.convert(c.s),
              "e", c -> INT_TO_JSON.convert(c.e));

Convert<Line, Json> jline = line ->
    convert(line)
        .with("x", l -> jcoord.convert(l.x),
              "y", l -> jcoord.convert(l.y));

Convert<Figure, Json> jfigure = figure ->
    convert(figure)
        .with("lines", f -> listFrom(jline).convert(f.lines));
```

### Automatic converter derivation

I just can't be bothered.

## Error Handling

_nemesis_ luckily doesn't throw exceptions, but rather follows a more data-oriented approach to error handling.

### Either

Materialisations of a `JsonT` always yield an `Either`, where the error type is set to `String`. (see [here](api.md#eithera-b))

The `Either` data structure is a left-biased version of the typical one you may find in the wild.

### What is this?
   
Either is a simple data type or interface, that has two implementations (often called `Left` and `Right`).   
The idea behind it is that, whilst the interface itself represents two things at the same time, 
a concrete instance of it can _either_ only be one or the other (`Left` or `Right`), but not both at the same time.

It thus represents a _disjuction_ and is fairly handy for abstractly describing two things at the same time,
that at runtime break down to just one. For example, explicitly denoting that a function may fail or succeed
upon execution.

```java
interface Either<E, V> {}

public class Right<E, V> implements Either<E, V> {
    public final V value;

        public Right(final V value) {
            this.value = value;        
        }           
}

public class Left<E, V> implements Either<E, V> {
    public final E error;

    public final Left(final E error) {
        this.error = error;
    }   
}
```
