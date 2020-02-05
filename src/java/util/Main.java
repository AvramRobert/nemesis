package util;

import json.Ops;

import java.util.List;

import static json.coerce.DefaultConverters.*;

public class Main {

    public static class Point {
        public final long x;
        public final long y;

        public Point(final long x, final long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                final Point that = (Point) obj;
                return this.x == that.x && this.y == that.y;
            }
            else return false;
        }

        @Override
        public String toString() {
            return String.format("Point(%d, %d)", x, y);
        }
    }

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

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Line) {
                final Line that = (Line) obj;
                return this.a.equals(that.a) && this.b.equals(that.b) && this.slope == that.slope && this.name.equals(that.name);
            }
            return false;
        }
    }

    public static class Spline {
        public final String name;
        public final List<Point> points;

        public Spline (final String name, final List<Point> points) {
            this.name = name;
            this.points = points;
        }

        @Override
        public String toString() {
            return String.format("Spline(%s, %s)", name, points);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Spline) {
                final Spline that = (Spline) obj;
                return this.points.equals(that.points) && this.name.equals(that.name);
            }
            else return false;
        }
    }

    public static void main (String... args) {
//        final Convert<Point, Json> POINT_TO_JSON = Derivator.writer(Point.class);
//        final Convert<Json, Point> JSON_TO_POINT = Derivator.reader(Point.class);
//        final Point point = new Point(1L, 2L);
//
//        final Convert<Line, Json> LINE_TO_JSON = Derivator.writer(Line.class);
//        final Convert<Json, Line> JSON_TO_LINE = Derivator.reader(Line.class);
//        final Line line = new Line (new Point (2L, 1L), new Point(4L, 5L), 1, "Bobbeh");
//
//        final Convert<Spline, Json> SPLINE_TO_JSON = Derivator.writer(Spline.class);
//        final Convert<Json, Spline> JSON_TO_SPLINE = Derivator.reader(Spline.class);
//        final Spline spline = new Spline (
//          "Archibald",
//          Arrays.asList(new Point(1L, 2L), new Point (3L, 4L), new Point(4L, 6L), new Point (9L, 8L)));
//
//        var x = SPLINE_TO_JSON.convert(spline);
//        Debug.println(x);
//        Debug.println(x.flatMap(JSON_TO_SPLINE::convert));


        Ops.parse("{ \"a\" : 1, \"b\" : {\"c\" : 3}}")
          .traverse((k, v) -> {
              if (k.equals("a")) return v.as(JSON_TO_LONG).map(l -> v);
              else if (k.equals("c")) return v.as(JSON_TO_LONG).map(l -> v);
              else return Either.right(v)   ;
          })
        .consume(Debug::println, Debug::println);
    }
}
