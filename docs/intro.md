# Nemesis Introduction

## Rationale
Even though the [JSON specification](https://tools.ietf.org/html/rfc7159) enumerates a plethora of types that a _valid_ JSON instance 
may or may not be, in practice, the form wild JSON instances most often take are a tad more homogeneous. 

If you've ever worked with JSON (and I assume you're not here because you haven't) you might've quickly noticed that almost all JSONs 
are most likely going to be dictionaries, whilst the remaining ones sequences.

Thus begs the question, why can't we work with these similarly to how we do with other structures of their kind?  
Other languages are by no means shy about it and employ this practice with great success.

Why can't Java as well?

## Concepts

_nemesis_ sees JSON as a trial (as in _three_), **immutable** data structure whose type is basically the union of what JSON can be,
i.e: either a _scalar_, a _sequence_ or a _dictionary_.

Primarily however, it biases the fact that, most likely, it's going to be the latter two.
It thus provides data structure-esque functions, which allow one to **safely**, **efficiently** and **immutably** alter and change them to their hearts content.

## Transformation
As with any JSON library, _nemesis_ complies to the JSON specification and can parse any form it may occur in.  
The parsed version is represented through the datatype `nemesis.data.Json`.

Every such `nemsis.data.Json` can either be marshalled back into a `String` or can be _transformed_. 

A transformation wraps the `nemesis.data.Json` into a `nemesis.data.JsonT`, which acts as the context in which
changes are allowed to happen. These emphasise two properties:

1. **Compositionality**
   
   Every transformation, irrespective of sanity and/or success, returns a `nemesis.data.JsonT`.
   This allows one to chain transformations together and actively "pretend" as if all were fine.

2. **Safety**
    
    Changes are only "materialised" after a particular function has been called which "terminates" the transformation. 
    
    That function then returns an `nemesis.util.Either<String, Json>`, which signals its outcome, either success or failure.\
    (Yes, the error is truly a `String`. Don't worry, it contains more info than you may expect.)
    