package util;

import json.Ops;
import json.coerce.Convert;
import json.experimental.Derivator;
import json.data.Json;

import java.util.Arrays;
import java.util.List;

import static json.coerce.DefaultConverters.*;

public class Main {

    public static class Line {
        public final Point a;
        public final Point b;
        public final float slope;
        public final String name;

        public Line(final Point a, final Point b, final float slope, final String name) {
            this.name = name;
            this.a = a;
            this.b = b;
            this.slope = slope;
        }
    }

    public static class Spline {
        public final String name;
        public final List<Point> points;

        public Spline (final String name, final List<Point> points) {
            this.name = name;
            this.points = points;
        }
    }

    public static class Point {
        public final long x;
        public final long y;

        public Point(final long x, final long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main (String... args) {
        final Convert<Point, Json> POINT_TO_JSON =
         Ops.<Point>objectConverter()
          .object(
           "x", p -> Ops.asJson(LONG_TO_JSON, p.x),
           "y", p -> Ops.asJson(LONG_TO_JSON, p.y));

        final Convert<Line, Json> LINE_TO_JSON =
         Ops.<Line> objectConverter()
         .object(
          "a", l -> Ops.asJson(POINT_TO_JSON, l.a),
          "b", l -> Ops.asJson(POINT_TO_JSON, l.b),
          "slope", l -> Ops.asJson(FLOAT_TO_JSON, l.slope),
          "name", l -> Ops.asJson(STRING_TO_JSON, l.name));

        final var line = new Line(new Point(0, 0), new Point(3, 1),2.0f, "Line 1");
        final var spline = new Spline("My Spline", Arrays.asList(new Point (1, 2), new Point(2, 3)));

        final Convert<Json, Point> JSON_TO_POINT =
         Derivator.reader(Point.class);

        final Convert<Json, Line> JSON_TO_LINE =
         Derivator.reader(Line.class);

        final Convert<Spline, Json> SPLINE_TO_JSON =
         Ops.<Spline>objectConverter()
         .object(
          "name", s -> Ops.asJson(STRING_TO_JSON, s.name),
          "points", s -> Ops.asJson(listFrom(POINT_TO_JSON), s.points));

        final Convert<Json, Spline> JSON_TO_SPLINE =
         Derivator.reader(Spline.class);

        System.out.println(SPLINE_TO_JSON.convert(spline).flatMap(JSON_TO_SPLINE::convert));
    }
}
