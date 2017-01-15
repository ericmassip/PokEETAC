package business;

import org.apache.log4j.Logger;

/**
 * Created by ericmassip on 15/1/17.
 */
public class RouterBusiness {
    private Logger log = Logger.getLogger(RouterBusiness.class);
    public int getCorrespondingPointsForScannedRouter(int signalLevel) {
        Double points = 0.0;
        if (signalLevel > -80) {
            if(signalLevel > -75) {
                if (signalLevel > -70) {
                    if(signalLevel > -65) {
                        if (signalLevel > -60) {
                            if(signalLevel > -55) {
                                if (signalLevel > -50) {
                                    if(signalLevel > -45) {
                                        if (signalLevel > -40) {
                                            if(signalLevel > -35) {
                                                if (signalLevel > -30) {
                                                    points = 6.0;
                                                }
                                            } else {
                                                points = 5.5;
                                            }
                                        } else {
                                            points = 5.0;
                                        }
                                    } else {
                                        points = 4.5;
                                    }
                                } else {
                                    points = 4.0;
                                }
                            } else {
                                points = 3.5;
                            }
                        } else {
                            points = 3.0;
                        }
                    } else {
                        points = 2.5;
                    }
                } else {
                    points = 2.0;
                }
            } else {
                points = 1.5;
            }
        } else {
            points = 1.0;
        }
        return points.intValue();
    }

    public int getFloorWithMaximumPoints(int maximumPointsPerFloor, int[] pointsPerFloor) {
        if (maximumPointsPerFloor == pointsPerFloor[0]) {
            return 0;
        } else if (maximumPointsPerFloor == pointsPerFloor[1]) {
            return 1;
        } else if (maximumPointsPerFloor == pointsPerFloor[2]) {
            return 2;
        } else if (maximumPointsPerFloor == pointsPerFloor[3]) {
            return 3;
        }
        log.warn("Maximum points for one of the floors was not found");
        return 0;
    }
}
