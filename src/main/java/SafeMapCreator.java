public class SafeMapCreator extends MapCreator{
    private int mapSize;

    //A static method of the map creator is made

    //Constructor for SafeMapCreator
    SafeMapCreator(int pmapSize){
        mapSize = pmapSize;
    }

    //Method to create and return a safe map
    @Override
    public Map create(){
        //getInstance method used here to obtain a static instance of the hazardous map
        SafeMap map =  SafeMap.getInstance();
        map.setMapSize(mapSize);
        map.generate();
        return map;
    }
}
