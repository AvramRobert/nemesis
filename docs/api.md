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
   * Type representing a json currently undergoing transformation
   * Supports the primary manipulative functions
   * Not a fixed JSON, transformation has to be terminated to get a `Json` out of it (see [here](api.md#terminating--affixing-structure))

### `Either<A, B>`
   * Left-biased disjunctive type used for representing successful and erroneous computations
   * The left-hand side is seen as the error type, whilst the right-hand side as the success type

### `Convert<E, I, O>`
   * An interface describing the safe conversion from an input type `I` to an output type `O` that may fail with an error of type `E`
   * Equivalent to `Function<I, Either<E, O>>`

### `Read<A>`
   * Concrete extension of `Convert` for reading `Json`
   * Equivalent to `Convert<String, Json, A>`

### `Write<A>`
   * Concrete extension of `Convert` from writing `Json`
   * Equivalent to `Convert<String, A, Json>`

### `JsonReader`
   * A helper class to aid in writing `Read<A>` instances
   
### `JsonWriter<A>`
   * A helper class to aid in writing `Write<A>` instances

## Parsing

### To `JsonT`

Generally, parsing reads directly into a `JsonT`.

```java
import static com.ravram.nemesis.Json.*;

parse("{ \"hello\" : \"world\" }") // JsonT
```

### To an arbitrary type

You can however also parse directly to an arbitrary type `A`, given an instance of `Read<A>` for that type.

For more information on `Read`, take a look [here](api.md#converting).

```java

final String myJson = ...;
final Read<MyType> myTypeReader = ...;

parseAs(myTypeReader, myJson);
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
import static com.ravram.nemesis.Json.*;
import static com.ravram.nemesis.JsonT;

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

For arbitrary types, you'll have to provide a `Write<A>` instance.

For more information on `Write`, take a look [here](api.md#converting).

```java
import static com.ravram.nemesis.Json.*;
import static com.ravram.nemesis.Write;
import static com.ravram.nemesis.Writers.WRITE_INT;

static class Point {
   final int a;
   final int b;

   public Point(final int a, final int b) {
      this.a = a;
      this.b = b;
   }
}

   Write<Point> writePoint = point -> 
        write(point).using(
                "a", p -> WRITE_INT.apply(p.a),
                "b", p -> WRITE_INT.apply(p.b));

jsonT.insertValue(new Point(1,1), writePoint, in("point"))
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
Can either be performed directly on a `JsonT` node or on a proper type `A`, provided a `Read<A>` instance.

For more information on `Read`, take a look [here](api.md#converting).
```java
jsonT
    .insertValue(1, in("one", "level")
    .updateValue(READ_INT, n -> n + 1, in("one", "level"));
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
Values of any type `A` can be retrieved from any level and any structure, provided a `Read<A>` for that type.

For more information on `Read`, take a look [here](api.md#converting).
```java


jsonT
  .insertValue(true, in("one", "level"))
  .getValue(READ_BOOLEAN, in("one", "level", 2));
  
```

###  Merging

Two `JsonT`s can be merged together if they're both either JSON objects or arrays respectively.

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

A `JsonT` can be materialised to a concrete type `A` provided a `Read<A>` instance for that type.

For more information on `Read`, take a look [here](api.md#converting).

#### Casting to JSON types

Writers and Readers for a bunch of types can be found in `com.ravram.nemesis.Writers` and
`com.ravram.nemsis.Readers` respectively.

A list of all the defaults can be found [here](api.md#default-readwrite-instances).

```java
jsonT.getJson(in("hello")).as(READ_STRING); // Either<String, String>
```
or
```java
jsonT.getValue(WRITE_STRING, in("hello")); // Either<String, String>
```

#### Casting to arbitrary types

Like [previously](api.md#casting) mentioned, any `JsonT` can be coerced to an arbitrary type `A` given
that one constructs a `Read<A>` instance for that type.

For more information on `Read`, take a look [here](api.md#converting).

```java
static class Greeting {
    public final String value;
    
    public Greeting(final String value) {
        this.value = value;
    } 
}

Convert<Json, Greeting> converter = json ->
    convert(json).using(
            json  -> json.transform().getValue(READ_STRING, in("hello")), 
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
import com.ravram.nemesis.Writers.JSON_TO_STRING;

jsonT.reduceObj("Greeting:",(inter,key,jsonValue)->{
        return jsonValue.as(WRITE_STRING).map(value-> inter + " " + key + " " + value);
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
import com.ravram.nemesis.Writers.JSON_TO_INT;

JsonT json=parse("[{\"value\" : 1}, {\"value\" : 2}, {\"value\" : 3}, {\"value\" : 4}]")
        
json.reduceArr(0, (inter, index, jsonValue)->{
    return jsonValue.getAs(READ_INT, in("value")). map(x -> x + inter);
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

Conversion from JSON to Java types and vice-versa is modeled abstractly via the functional interface `Convert<E, I, O>`.
It represents the conversion of some type `A` into a type `B` which may fail with an error `E`.

Two interfaces are derived from it that specifically model reading and writing respectively:

* `Read<A>` (`Convert<String, Json, A>`)
  - Reads types `A` from `Json`

* `Write<A>` (`Convert<String, A, Json>`)
  - Writes types `A` to `Json`
   

Converting `Json` to an arbitrary type `A` and vice versa is thusdone by defining `Read` and `Write`
instances for that type and using them wherever needed.

#### Default Read/Write Instances

_nemesis_ provides `Read` and `Write` instances for a number of basic types.
These can be found in `com.ravram.nemesis.Readers` and `com.ravram.nemesis.Writers` respectively.

Here's a list:

* `Integer`
* `Long`
* `Float`
* `Double`
* `String`
* `Boolean`
* `Null`
* `UUID`
* `ZonedDateTime`
* `List` (both `Json` and arbitrary types)
* `Set` (both `Json` and arbitrary types)
* `Map` (both `Json` and arbitrary types)

#### Custom Read/Write instances

When it comes to creating your `Read` and `Write` instances, _nemesis_ offers some help.

#### **Read** instances

There's a `read` function which works like this:

```java
Read<MyType> reader = json ->
    read(json)
        .using (
            // the first n - 1 arguments are individual `Read` instances
            // with which you extract and convert individual attributes of your type
              .., // Read<A> 
              .., // Read<B>
              .., // Read<C>
              ..,
            // the last argument is a function containing all converted attribues
            // which you use to intantiate your type
              (a, b, c) -> new MyType(a, b, c))
```
**Example:**

```java
import static com.ravram.nemesis.Readers.*;

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

   Read<Coord> coord = json ->
           read(json).using(
                   js -> js.transform().getValue(READ_INT, in("s")),
                   js -> js.transform().getValue(READ_INT, in("e")),
                   (s, e) -> new Coord(s, e));

   Read<Line> line = json ->
           read(json).using(
                   js -> js.transform().getValue(coord, in("x")),
                   js -> js.transform().getValue(coord, in("y")),
                   (x, y) -> new Line(x, y));

   Read<Figure> figure = json ->
           read(json).using(
                   js -> js.transform().getValue(readListOf(line), in("lines")),
                   lines -> new Figure(lines));
```

#### **Write** instances

There's a `write` function which works like this:

```java

Write<MyType> writer = json ->
    write(json)
        .using (
            // arguments are pairs of `String` and `Convert<MyType, Json>`
            // `String` indicates the key an attribute of `MyType` should have in the json
            // `Convert<MyType, Json>` extracts that attribute and transforms it to a json
              .., .. // String, Write<MyType> 
              .., .. // String, Write<MyType>
              .., .. // String, Write<MyType>)
```
**Example:**

```java
import static com.ravram.nemesis.Writers.*;

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

    Write<Coord> jcoord = coord ->
            write(coord).using(
                    "s", c -> WRITE_INT.convert(c.s),
                    "e", c -> WRITE_INT.convert(c.e));

    Write<Line> jline = line ->
            write(line).using(
                    "x", l -> jcoord.convert(l.x),
                    "y", l -> jcoord.convert(l.y));

    Write<Figure> jfigure = figure ->
            write(figure).using(
                    "lines", f -> writeListOf(jline).convert(f.lines));
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
