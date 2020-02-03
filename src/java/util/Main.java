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

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                final Point that = (Point) obj;
                return this.x == that.x && this.y == that.y;
            }
            else return false;
        }
    }

    public static void main (String... args) {
        final Convert<Point, Json> POINT_TO_JSON = Derivator.writer(Point.class);
        final Convert<Json, Point> JSON_TO_POINT = Derivator.reader(Point.class);
        final Point point = new Point(1L, 2L);

        Debug.println(POINT_TO_JSON.compose(JSON_TO_POINT).convert(point).map(x -> x.equals(point)));
    }
}
