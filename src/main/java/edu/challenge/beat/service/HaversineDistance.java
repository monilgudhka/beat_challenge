package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;

public class HaversineDistance {

    private static final int R = 6371; // Radius of the earth

    public static double getDistance(Position source, Position destination) {
        double lat1 = source.getLatitude();
        double lon1 = source.getLongitude();
        double lat2 = destination.getLatitude();
        double lon2 = destination.getLongitude();

        double latDistance = toRad(lat2 - lat1);
        double lonDistance = toRad(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private static double toRad(Double value) {
        return value * Math.PI / 180;
    }
    
    private HaversineDistance() {
    }

}
