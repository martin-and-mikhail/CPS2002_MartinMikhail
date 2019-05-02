public class HazardousMapCreator extends MapCreator{

    private int mapSize;

    //Constructor for HazardousMapCreator
    HazardousMapCreator(int pmapSize){
        mapSize = pmapSize;
    }

    //Method to create and return a hazardous map
    @Override
    public Map create(){

        //getInstance method used here to obtain a static instance of the hazardous map
        HazardousMap map = HazardousMap.getInstance();
        map.setMapSize(mapSize);
        map.generate();
        return map;
    }
}
