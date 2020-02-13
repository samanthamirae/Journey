// This program constructs three GeoLocation objects and prints out the latitude 
// and longitude of three locations, Walter's Stash, ABQ Studios, and FBI Building, 
// then calculates the distance in miles between Walter's Stash and ABQ Studios and 
// Walter's Stash and FBI Building


public class GeoLocationClient {
   public static void main(String[] args) {
      //Declaration and initialization of the objects
      GeoLocation waltersStash = new GeoLocation(34.988889, -106.614444);
      GeoLocation abqStudios = new GeoLocation(34.989978, -106.614357);
      GeoLocation fbiBuilding = new GeoLocation(35.131281, -106.61263);
      
      locationCoordinates(waltersStash, abqStudios, fbiBuilding);
      distanceCalculations(waltersStash, abqStudios, fbiBuilding);
   }
   
   // Prints the three location's coordinates
   //
   // GeoLocation waltersStash - geolocation of Walter's Stash to print
   // GeoLocation abqStudios - geolocation of ABQ Studios to print
   // GeoLocation fbiBuilding - geolocation of FBI Building to print
   public static void locationCoordinates(GeoLocation waltersStash, GeoLocation abqStudios, 
                                          GeoLocation fbiBuilding) {
      System.out.println("the stash is at " + waltersStash.toString());
      System.out.println("ABQ studio is at " + abqStudios.toString());
      System.out.println("FBI building is at " + fbiBuilding.toString());
   }
   
   // Calculates and prints the distances in miles between Walter's Stash to the ABQ Studios
   // and Walter's Stash to the FBI Building
   //
   // GeoLocation waltersStash - geolocation of Walter's Stash to calculate the distance from 
   // ABQ Studios and FBI Building
   // GeoLocation abqStudios - geolocation of ABQ Studios to calculate the distance from the stash
   // GeoLocation fbiBuilding - geolocation of FBI Building to calculate the 
   // distance from the stash
   public static void distanceCalculations(GeoLocation waltersStash, GeoLocation abqStudios, 
                                           GeoLocation fbiBuilding) {
      System.out.println("distance in miles between:");
      System.out.println("    stash/studio = " + waltersStash.distanceFrom(abqStudios));
      System.out.println("    stash/fbi    = " + waltersStash.distanceFrom(fbiBuilding));
   }
}
