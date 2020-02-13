// This class stores information about a certain place (restaurant, tourist site,
// library, etc.) on Earth. Each place is specified by its name, address, tags, 
// latitude, and longitude. This class contains a method for computing the 
// distance between two places


public class PlaceInformation {
   private String name;
   private String address;
   private String tag;
   private GeoLocation thisLocation; 
   
   // Constructs a place information object with given name, address, tag, latitude, and longitude
   public PlaceInformation(String name, String address, String tag,
                           double latitude, double longitude) {
      this.name = name;
      this.address = address;
      this.tag = tag;
      thisLocation = new GeoLocation(latitude, longitude);                
   }
   
   // Returns the name of this place
   public String getName() {
      return this.name;
   }
   
   // Returns the address of this place
   public String getAddress() {
      return this.address;
   }
   
   // Returns the tags of this place
   public String getTag() {
      return this.tag;
   }
   
   // Returns a string representation of the name of the place and it's 
   // geolocation (latitude and longitude)
   public String toString() {
      return getName() + " (" + getLocation().toString() + ")";
   }
   
   // Returns the geolocation of the place
   public GeoLocation getLocation() {
      return thisLocation;
   }
   
   // Returns the distance in miles between this place's geolocation and the given
   // other geolocation's spot
   public double distanceFrom(GeoLocation spot) {
      return getLocation().distanceFrom(spot);
   }

}
