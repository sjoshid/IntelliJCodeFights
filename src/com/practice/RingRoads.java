package com.practice;

import java.util.Arrays;

public class RingRoads {

    public static void main(String[] args) {
        double doubleArr[] = {209.253983, 38.969013, 178.467116, 137.531902, 181.993079, 129.885195, 178.516437, 283.597115, 147.611906, 70.972958, 30.68365, 287.241272, 327.252872, 320.581891, 207.981467, 230.251322, 57.999991, 175.158868, 218.254257, 276.207182, 74.387357, 42.690989, 311.780595, 219.341251, 82.990768, 121.382704, 341.861433, 223.908523, 89.413574, 91.510786, 63.453949, 281.596767, 7.812061, 89.234636, 155.427805, 146.770936, 334.669652, 359.928379, 84.990235, 205.403351, 353.127536, 288.703872, 292.191005, 48.789789, 143.825368, 82.991824, 7.082733, 220.705587, 135.904538, 229.574117, 102.247097, 116.28474, 232.236324, 28.565627, 309.324372, 247.603646, 309.936331, 38.6685, 245.371316, 15.799253, 278.360391, 123.745333, 1.93573, 146.333184, 148.875957, 43.006822, 173.749581, 96.635514, 272.695444, 56.108854, 349.687975, 48.880578, 315.660873, 187.702005, 307.663622, 9.174811, 281.815963, 356.598521, 157.795476, 274.792717, 98.406199, 0.904473, 188.436128, 306.709474, 249.287469, 273.368373, 77.922331, 289.508906, 195.037041, 96.526764, 288.353308, 265.453368, 306.190427, 27.456484, 324.509789, 77.683106, 224.602919, 145.821893, 223.176635, 279.00666};
        Arrays.sort(doubleArr);

        for (double i: doubleArr
             ) {
            System.out.println(i);
        }

        System.out.println(" found at index = "
                + Arrays.binarySearch(doubleArr, 3.727166));

        RingRoads r = new RingRoads();
        double [] t1 = new double[]{0,0};
        double [] t2 = new double[]{125.255576,333.606302};
        double [] t3 = new double[]{125.255576,333.606302};
        //r.ringRoads(10, 20, new double[]{180}, new double[][]{ {0,0}, {60,300}});
        //r.ringRoads(1, 42, new double[]{338.864038, 311.235725}, new double[][]{ {37.959303,20.206089}});
        r.ringRoads(999, 1000, new double[]{209.253983, 38.969013, 178.467116, 137.531902, 181.993079, 129.885195, 178.516437, 283.597115, 147.611906, 70.972958, 30.68365, 287.241272, 327.252872, 320.581891, 207.981467, 230.251322, 57.999991, 175.158868, 218.254257, 276.207182, 74.387357, 42.690989, 311.780595, 219.341251, 82.990768, 121.382704, 341.861433, 223.908523, 89.413574, 91.510786, 63.453949, 281.596767, 7.812061, 89.234636, 155.427805, 146.770936, 334.669652, 359.928379, 84.990235, 205.403351, 353.127536, 288.703872, 292.191005, 48.789789, 143.825368, 82.991824, 7.082733, 220.705587, 135.904538, 229.574117, 102.247097, 116.28474, 232.236324, 28.565627, 309.324372, 247.603646, 309.936331, 38.6685, 245.371316, 15.799253, 278.360391, 123.745333, 1.93573, 146.333184, 148.875957, 43.006822, 173.749581, 96.635514, 272.695444, 56.108854, 349.687975, 48.880578, 315.660873, 187.702005, 307.663622, 9.174811, 281.815963, 356.598521, 157.795476, 274.792717, 98.406199, 0.904473, 188.436128, 306.709474, 249.287469, 273.368373, 77.922331, 289.508906, 195.037041, 96.526764, 288.353308, 265.453368, 306.190427, 27.456484, 324.509789, 77.683106, 224.602919, 145.821893, 223.176635, 279.00666}, new double[][]{ {192.463997,358.327345}});
    }

    double[] ringRoads(int innerRing, int outerRing, double[] roads, double[][] travels) {
        int roadLength = outerRing - innerRing;
        Arrays.sort(roads);
        double [] distances = new double[travels.length];

        for (int i = 0; i < travels.length; i++) {
            double [] points = travels[i];
            double startPoint = points[0];
            double endPoint = points[1];

            int endPosition = Arrays.binarySearch(roads, endPoint);
            int startPosition = Arrays.binarySearch(roads, startPoint);

            double endPointDist = minDistanceFromEndPointBridges(innerRing, outerRing, roads, roadLength, startPoint, endPoint, endPosition);
            double startPointDist = minDistanceFromEndPointBridges(innerRing, outerRing, roads, roadLength, startPoint, endPoint, startPosition);

            distances[i] = Math.min(endPointDist, startPointDist);
        }

        return distances;
    }

    private double minDistanceFromEndPointBridges(int innerRing, int outerRing, double[] roads, int roadLength, double startPoint, double endPoint, int position) {
        int finalPosition;
        double distanceToTravelOnInnerRing;
        if(position < 0) {
            finalPosition = (position * -1) - 1;
            int [] locationOfClosestRoadOnOuterRing = null;

            if(finalPosition > 0 && finalPosition < roads.length - 1) {
                int left = finalPosition - 1;
                int right = finalPosition;

                locationOfClosestRoadOnOuterRing = findClosestRoad(roads, endPoint, left, right);
            } else if (finalPosition == roads.length - 1) {
                // is 0
                int right = 0;
                int left = roads.length - 1;

                locationOfClosestRoadOnOuterRing = findClosestRoad(roads, endPoint, left, right);
            } else if (finalPosition == 0) {
                // is 0
                int right =  roads.length - 1;
                int left = 0;

                locationOfClosestRoadOnOuterRing = findClosestRoad(roads, endPoint, left, right);
            } else {
                locationOfClosestRoadOnOuterRing = findClosestRoad(roads, endPoint, 0, 0);
            }

            double leftAngle = roads[locationOfClosestRoadOnOuterRing[0]];
            double rightAngle = roads[locationOfClosestRoadOnOuterRing[1]];
            double d1 = distance(leftAngle, startPoint, endPoint, innerRing, outerRing, roadLength);
            double d2 = d1;

            if(leftAngle != rightAngle)
                d2 = distance(rightAngle, startPoint, endPoint, innerRing, outerRing, roadLength);

            double minDistance = Math.min(d1, d2);
            return minDistance;


        } else {
            // road found

            double roadAngle = roads[position];

            distanceToTravelOnInnerRing = Math.abs(roadAngle - startPoint);

            if (distanceToTravelOnInnerRing > 180.0) {
                distanceToTravelOnInnerRing = 360.0 - distanceToTravelOnInnerRing;
            }
            double distance = calculateDistance(distanceToTravelOnInnerRing, innerRing, 0.0, outerRing, roadLength);
            return distance;
        }
    }

    private double calculateDistance(double distanceToTravelOnInnerRing, int innerRad, double distanceToTravelOnOuterRing, int outerRad, int roadLength){
        double innerCircleTravelDistance = (2 * Math.PI * innerRad * distanceToTravelOnInnerRing) / 360.0;
        double outerCircleTravelDistance = (2 * Math.PI * outerRad * distanceToTravelOnOuterRing) / 360.0;

        return innerCircleTravelDistance + outerCircleTravelDistance + roadLength;
    }

    private int [] findClosestRoad(double[] roads, double outerAngle, int left, int right) {
        return new int[]{left, right};
    }

    private double distance(double leftAngle, double startPoint, double endPoint, int innerRing, int outerRing, int roadLength) {

        double distanceToTravelOnInnerRing = Math.abs(leftAngle - startPoint);
        if (distanceToTravelOnInnerRing > 180.0) {
            distanceToTravelOnInnerRing = 360.0 - distanceToTravelOnInnerRing;
        }

        double distanceToTravelOnOuterRing = Math.abs(leftAngle - endPoint);
        if (distanceToTravelOnOuterRing > 180.0) {
            distanceToTravelOnOuterRing = 360.0 - distanceToTravelOnOuterRing;
        }

        return calculateDistance(distanceToTravelOnInnerRing, innerRing, distanceToTravelOnOuterRing, outerRing, roadLength);
    }
}
