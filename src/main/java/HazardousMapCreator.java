public class HazardousMapCreator extends MapCreator{

    private int mapSize;

    //Constructor for HazardousMapCreator
    HazardousMapCreator(int pmapSize){
        mapSize = pmapSize;
    }

    //Method to create and return a hazardous map
    @Override
    public Map create(){
        HazardousMap map = new HazardousMap(mapSize);
        map.generate();
        return map;
    }
}
