package edu.challenge.beat.util;

/**
 * Class calculating https://en.wikipedia.org/wiki/Haversine_formula
 */
public final class HaversineDistanceUtil {

    private static final double EARTH_RADIUS = 6371.0088; // Radius of the earth

    private HaversineDistanceUtil (){}

    public static double getDistance(final double latitudeSource, final double longitudeSource, final double latitudeDestination, final double longitudeDestination) {
        final double latDistance = toRad(latitudeDestination - latitudeSource);
        final double lonDistance = toRad(longitudeDestination - longitudeSource);
        final double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(latitudeSource)) * Math.cos(toRad(latitudeDestination)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private static double toRad(final Double value) {
        return value * Math.PI / 180;
    }
}
