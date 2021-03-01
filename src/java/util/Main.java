package util;

import static json.JsonOps.*;
import static json.coerce.DefaultConverters.JSON_TO_LONG;

public class Main {

    public static void main (String... args) {
        var json1 = "{ \"hello\" : \"world\" }";
        var json2 = "{ \"numbers\" : [{ \"first\" : 1 }, { \"second\" : 2 }] }";

        var modified = parse(json1)
          .insert("Mark", in("Oh-Hai"))
          .insert("value", in("Deep", "Nested", "Structure"))
          .remove("hello")
          .merge(parse(json2))
          .update(JSON_TO_LONG, a -> a + 1, in("numbers", 1, "second"))
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
}
