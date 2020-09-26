package edu.challenge.beat.util;

/**
 * Class calculating https://en.wikipedia.org/wiki/Haversine_formula
 */
public final class HaversineDistance {

    private static final double EARTH_RADIUS = 6371.0088; // Radius of the earth

    public static double getDistance(double latitudeSource, double longitudeSource, double latitudeDestination, double longitudeDestination) {
        double latDistance = toRad(latitudeDestination - latitudeSource);
        double lonDistance = toRad(longitudeDestination - longitudeSource);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(latitudeSource)) * Math.cos(toRad(latitudeDestination)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private static double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
