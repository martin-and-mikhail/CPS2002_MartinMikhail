class MapCreator {

    //Factory Method
    Map createMap(String type, int mapSize){

        MapCreator creator;

        if(type.equals("safe")){
            creator = new SafeMapCreator(mapSize);
        }
        else if(type.equals("hazardous")){
            creator = new HazardousMapCreator(mapSize);
        }
        else{
            creator = null;
            System.err.println("Invalid map type");
        }

        if (creator != null) {
            return creator.create();
        }
        else{
            return null;
        }
    }

    //Overridden in subclasses
    Map create() {
        return null;
    }
}
