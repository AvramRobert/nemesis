# Benchmarks

These are benchmarks that look at the performance of features, that have been uniquely implemented in the library.

Bechmarks metadata:
* Software: [criterium](https://github.com/hugoduncan/criterium)
* Hardware: 
    * CPU: Intel i7-4720HQ 2.60GHz x 8
    * RAM: 32 GB
    
## Parser

_nemesis_ comes with its own parser, which, in some circles, may or may not be frowned upon. 
Nevertheless, the parser is consistently benchmarked against a number of other ones stemming from other
popular JVM libraries:

Parsing [this JSON](/test/resources/sample.json) takes on average this much:

| Parser  | avg. ms |
| ------- | ------- |
| Nemesis | 5.07 ms |
| GSON    | 5.64 ms |
| Jackson | 4.00 ms |
| Play    | 6.22 ms |

## Operations

Given _nemesis_ esoteric approach to working with JSON, some may assume that its operations may be somewhat inefficient.

As such, each one of them is consistently benchmarked against randomly generated JSONs that explicitly vary in content density.
Every generated JSON is configured to satisfy certain criteria related to density.\
These are: 
1. **Depth**:
    * How many levels the json tree should have, starting with *0*
1. **Elements / Level**:
    * How many elements the json tree should have at one particular level
    
### Inserting Json
 
| Density | avg. ms |
| ------- | ------- |
| Small   |         |
| Large   |         |

##### Small
| Depth | Min Elements | Max Elements | 
| ----- | ------------ | ------------ |
|   0   |      2       |      20      |
|   1   |      5       |      10      |
##### Large
| Depth | Min Elements | Max Elements | 
| ----- | ------------ | ------------ |
|   0   |      30      |      40      |
|   1   |      5       |      10      |
|   2   |      3       |      5       |