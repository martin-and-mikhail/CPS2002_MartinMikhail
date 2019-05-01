public class SafeMapCreator extends MapCreator{
    private int mapSize;

    //Constructor for SafeMapCreator
    SafeMapCreator(int pmapSize){
        mapSize = pmapSize;
    }

    //Method to create and return a safe map
    @Override
    public Map create(){
        SafeMap map = new SafeMap(mapSize);
        map.generate();
        return map;
    }
}
