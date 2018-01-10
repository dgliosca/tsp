package com.project.TSP;


public class City {

    private double coordX;
    private double coordY;
    private String name;
    private int ID;

    /**
     *  Class constructor
     *  @param name the name of the city
     *  @param id the id of the city
     *  @param coordX it is the x-axis of the position of the city
     *  @param coordY it is the y-axis of the position of the city
    */
    public City(String name, int id, double coordX, double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.name = name;
        this.ID = id;
    }

    /**
     *   @return the number of the city
     */
    public int getID() {
        return ID;
    }

    /**
     *   @param id the number of the city
     */
    public void setID(int id) {
        this.ID = id;
    }

    /**
     *   @return the name of the city
     */
    public String getName() {
        return name;
    }

    /**
     *   @param name the name of the city
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *   @return the y-axis position of the city
     */
    public double getCoordY() {
        return coordY;
    }

    /**
     *   @param coordY it is the y-axis value of the city
     */
    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    /**
     *   @return the x-axis position of the city
     */
    public double getCoordX() {
        return coordX;
    }

    /**
     *  @param coordX it is the x-axis value of the city
    */
    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    /**
     *   This method calculates the euclidean distance between two points in
     *   two dimensions
     *   @param c1 the first city that we want to use to calculate the distance
     *   @param c2 the second city that we want to use to calculate the distance
     *   @return the distance between the cities
     */
    public static double getDistance(City c1, City c2) {
        return distInMiles(c1, c2);
    }

    /**
     *  This method calculates the distance between two coordinates
     *  @param c1 the first city that we want to use to calculate the distance
     *  @param c2 the second city that we want to use to calculate the distance
     *  @return the distance between the cities in miles
     */
    private static double distInMiles(City c1, City c2) {
        double earthRadius = 3958.75;
        double lat1 = c1.getCoordY();
        double lng1 = c1.getCoordX();
        double lat2 = c2.getCoordY();
        double lng2 = c2.getCoordX();
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))*
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }
    /**
     * This method builds a string with all the parameters of the city
     * @return a string with all the parameters of the city
     */
    @Override
    public String toString() {
        return "City{" +
                "coordX=" + coordX +
                ", coordY=" + coordY +
                ", name='" + name + '\'' +
                ", number=" + ID +
                '}';
    }

}
