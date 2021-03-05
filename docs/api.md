# Nemesis API

## Types and classes to expect

### `Json`
   * Type representing a correct json value.

### `JsonT`
   * Type representing a json currently undergoing transformation.

### `Convert<A, B>`
   * An interface describing the safe conversion from a type `A` to a type `B`.
   
### `JsonConverter<A>`
   * A helper class to aid in writing `Convert<Json, A>` instances for json objects.
   
### `ObjectConverter<A>`
   * A helper class to aid in writing `Convert<A, Json>` instances for arbitrary objects.

### `Either<A, B>`
   * Left-biased disjunctive type used for representing successful and erroneous computations.
   * The left-hand side is seen as the error type, whilst the right-hand side as the success type.

## Parsing

Parsing reads directly into a `JsonT`.

```java
import static nemesis.json.JsonOps.*;

parse("{ \"hello\" : \"world\" }") // JsonT
```

## Transforming

Assume we start with the following:
```java
import static nemesis.json.JsonOps.*;

JsonT jsonT = parse("{ \"hello\" : \"world\" }")
```

### Inserting

#### `JsonT` nodes

The simplest insertion is of raw `JsonT` nodes.

```java

JsonT jsonT2 = parse("{ \"a\" : 1 }");

jsonT.insert(jsonT2, in("node"));
```

```json
{
  "hello": "world",
  "node": {
    "a": 1
  }
}
```

#### Json-compliant values

Any raw json-compliant value can be directly inserted into a `JsonT`.

```java
jsonT
    .insert(1, in("field-1"))
    .insert(true, in("field-2"))
    .insert("bla", in("field-3"))
    .insert(null, in("field-4"))
    .insert(Arrays.asList(1, 2, 3, 4)), in("field-5"))
    .insert(Map.of("a", "b"), in("field-6"))
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
  "field-6": {
    "a": "b"
  }

```
#### Arbitrary values

For arbitrary types, you'll have to provide a `Convert<A, Json>` instance. 

Luckily, there's help for that. More in [custom converters](api.md#object-converters).

```java
import static nemesis.json.coerce.ObjectConverter.object;
import static nemesis.json.Converters.INT_TO_JSON;

static class Point {
    final int a;
    final int b;

    public Point(final int a, final int b) {
       this.a = a;
       this.b = b;
    }
}

Convert<Point, Json> pointConverter = 
    point -> convert(point)
                .with("a", p -> INT_TO_JSON.convert(p.a),
                      "b", p -> INT_TO_JSON.convert(p.b))

jsonT.insert(new Point(1, 1), pointConverter, in("point"))
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
jsonT.insert(true, in("is", "this", "deep", "enough"))
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

Entries can only be deleted at the top level. (Support for removal at arbitrary nestedness)

**Note: This only works on JSON objects.**

```java
jsonT
    .insert(1, in("value"))
    .remove("hello", "value")
```

```json
{}
```

### Updating

Updates are supported for any value at any degree of nestedness.  
Can either be performed directly on a `JsonT` node or on a proper type `A`, provided a `Convert<Json, A>` instance.

**Note: Converters for JSON types can be found in `nemesis.json.Converters`**
```java
import static nemesis.json.Converters.JSON_TO_INT;

jsonT
    .insert(1, in("one", "level")
    .update(JSON_TO_INT, n -> n + 1, in("one", "level"))
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

Values can be retrieved from any level and any structure.

**Note: This returns a `JsonT` to leverage safety and further composition.**

```json
jsonT
  .insert(Arrays.asList(1, 2, 3), in("one", "level"))
  .get(in("one", "level", 2))
```

###  Merging

Two `JsonT`s can be merged together.

**Note: This only works on JSON objects.**

```java
JsonT jsonT2 = parse("{ \"you\" : \"good\" }")

jsonT.merge(jsonT2)
```

```json
{
  "hello": "world",
  "you": "good"
}
```

### Casting

#### Casting to JSON types

A `JsonT` can be materialised to a concrete type `A` provided a `Convert<Json, A>` instance.

**Note: Converters for JSON types can be found in `nemesis.json.Converters`**
```java
import static nemesis.json.Converters.JSON_TO_STRING;

jsonT.get(in("hello")).as(JSON_TO_STRING); // Either<String, String>
```

#### Casting to arbitrary types

Like [previously](api.md#casting-to-json-types) mentioned, any `JsonT` can be coerced to an arbitrary type `A` given
that one constructs an `Convert<Json, A>` instance for that type.

Luckily, there's help for that. More in [custom converters](api.md#json-converters).

```java
static class Greeting {
    public final String value;
    
    public Greeting(final String value) {
        this.value = value;
    } 
}

Convert<Json, Greeting> converter = json ->
    convert(json)
        .with(json -> json.get("hello").as(JSON_TO_STRING), 
              value -> new Greeting(value));
```

### Reducing

// TODO:


### Creating

Json's can be manually created from an empty node.

```java
empty.transform().insert("hello", "world");
```

```json
{ "hello" : "world" }
```

### Terminating

To materialise a `JsonT` to a concrete `Json` type, it's transformation has to be terminated.

This yields an `Either<String, Json>` indicating either a failed (`String`) successful (`Json`) transformation. \
Yes, the error is a `String`. Don't worry, it contains more information that you may expect.

```java
jsonT.affix()
```

## Converting

Conversion from JSON to Java types and vice-versa is abstractly modeled by the interface `Convert<A, B>`

### Json Converters

Converting `Json` to an arbitrary type `A` is done by defining an instance of `Convert<Json, A>` for it.

#### Default Json Converters

For the most basic of JSON types, _nemesis_ already provides converters in `nemesis.json.Converters`.

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
import static.nemesis.json.Converters.*;
import static nemesis.json.JsonOps.*;

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
        .with(js -> js.get("s").as(JSON_TO_INT),
              js -> js.get("e").as(JSON_TO_INT),
              (s, e) -> new Coord(s, e));

Convert<Json, Line> line = json ->
    convert(json)
        .with(js -> js.get("x").as(coord),
              js -> js.get("y").as(coord),
              (x, y) -> new Line(x, y));

Convert<Json, Figure> figure = json ->
    convert(json))
        .with(js -> js.get("lines").as(listOf(line)),
              lines -> new Figure(lines));
```

### Object Converters

#### Default Object Converters

For the most basic of JSON types, _nemesis_ already provides converters in `nemesis.json.Converters`.

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
import static.nemesis.json.Converters.*;
import static nemesis.json.JsonOps.*;

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
        .with("lines", f -> listFrom(line).convert(f.lines));
```

### Automatic converter derivation

I just can't be bothered right now.

## Error Handling

_nemesis_ luckily doesn't throw exceptions, but rather follows a more data-oriented approach to error handling.

### Either

Materialisations of a `JsonT` always yield an `Either`, where the error type is set to `String`. (see [here](api.md#eithera-b))

The `Either` data structure is a left-biased version of the typical one you may find in the wild.

### What is this?
   
Either is a simple data type or interface, that has two implementations (often called `Left` and `Right`).   
The idea behind it is that, whilst the interface itself represents two things at the same time, 
a concrete instance of it can _either_ only be one instantiation or the other (`Left` or `Right`), but not both at the same time.

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
