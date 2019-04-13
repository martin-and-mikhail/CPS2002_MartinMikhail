import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Map {

    //This is an array which holds the coordinates of the given tile and also the tile type
    private char tileStatus[][][];

    //This variables holds the n value of an nxn tileGrid
    private int mapSize = 5;

    //These are the starting positions for the game
    private int xPos, yPos;

    //This is the window which displays the game
    final JFrame f = new JFrame("Game Map");

    //This panel holds all the tiles in the game
    JPanel tileGrid = new JPanel(new GridLayout(5, 5, 3, 3));

    //The is a header used to display the previous moves and also the current player
    JPanel header = new JPanel();


    //When generating map each tile is given a value, this is a character and is either g, b, y which correspond to grass, water or treasure
    //If the tile contains g then it is changed to green when user steps on it. Similar code for the b and y tiles

    //Constructor for Map class
    void Map() {

        //The size of the map is defined
        this.mapSize = 8;

        //The actual map is generated
        generate();
    }

    //Grid here generated kind of
    void generate() {

        //This is an array which holds the x y and tile type
        tileStatus = new char[mapSize][mapSize][1];

        //These labels will be added to header so as to be able to display the information
        JLabel playerInfo = new JLabel("Player 1");
        JLabel moveInfo = new JLabel("Moves: right right");

        header.add(playerInfo);
        header.add(moveInfo);

        //A 2d array of labels is made so as to hold each tile and so that it can be easily accessed with its unique coordinate
        JLabel[][] tiles = new JLabel[mapSize][mapSize];

        //Here each tile is being created and initialised
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                tiles[x][y] = new JLabel(" ");
                tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tiles[x][y].setOpaque(true);
                tiles[x][y].setBackground(Color.GRAY);

                tileGrid.add(tiles[x][y]);
            }
        }
//            }
//        }

        //Getting the start position of the character

        //setting the character of the tileStatus array to either 'g' 'y' 'b' or 'p'

        //Object used to obtain random numbers
        Random rand = new Random();

        int totalLabel = 0;
        int treasure = 0;
        int grass = 0;
        int water = 0;
        double waterLim;
        waterLim = Math.ceil((mapSize*mapSize)/5.0);

        //max values of water is ceil(mapSize/5)

        //Given n x n tileGrid we only need a value in this range
        int x = rand.nextInt(mapSize);
        int y = rand.nextInt(mapSize);

        xPos = x;
        yPos = y;

        tileStatus[x][y][0] = 'p';

        System.out.print(x);
        System.out.print(y);
        tiles[x][y].setBackground(Color.RED);

        //Go through each element in the matrix and assigning a random value from 0 - 2, 0 is grass, 1 is water, 2 is treasure
        //update counter when a value is set

            int ran;

            boolean tileFull = false;

            for (int x1 = 0; x1 < mapSize; x1++) {
                for (int y1 = 0; y1 < mapSize; y1++) {

                    if(x1 == x && y1 == y){
                        continue;
                    }

                    else {

                        //While loop to keep on iteration
                        do {
                            ran = rand.nextInt(3);

                            if (ran == 0) {
                                grass += 1;
                                tileStatus[x1][y1][0] = 'g';
                                tileFull = false;

                            } else if (ran == 1) {
                                if (water == waterLim) {

                                    tileFull = true;

                                } else {

                                    tileStatus[x1][y1][0] = 'b';
                                    tileFull = false;
                                    water += 1;
                                }
                            } else if (ran == 2) {
                                if (treasure == 1) {
                                    tileFull = true;
                                } else {

                                    tileStatus[x1][y1][0] = 'y';
                                    tileFull = false;
                                    treasure += 1;
                                }


                            }
                        } while (tileFull);
                    }


            }
        }


        for (int y2 = 0; y2 < mapSize; y2++) {
            for (int x2 = 0; x2 < mapSize; x2++) {
                System.out.print("(" + x2 + "," + y2 + ") ");
                System.out.println(tileStatus[x2][y2][0]);
            }
        }

        for (int y2 = 0; y2 < mapSize; y2++) {
            for (int x2 = 0; x2 < mapSize; x2++) {

                if(tileStatus[x2][y2][0] == 'g'){
                    tiles[x2][y2].setBackground(Color.GREEN);
                }

                else if(tileStatus[x2][y2][0] == 'b'){
                    tiles[x2][y2].setBackground(Color.BLUE);

                }

                else if(tileStatus[x2][y2][0] == 'y'){
                    tiles[x2][y2].setBackground(Color.YELLOW);

                }
            }
        }





        //Need to add logic for when x is at 0 and 5 and y is at 0 and 5.


        //starting position is tiles[x][y]


        //The input map holds which keyboard keys acts as input
        InputMap im = tileGrid.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

        //The action map holds which actions the specified keyz can do
        ActionMap am = tileGrid.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "Right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "Left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0), "Up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "Down");

        am.put("Right", new ArrowAction("Right", xPos, yPos));
        am.put("Left", new ArrowAction("Left", xPos, yPos));
        am.put("Up", new ArrowAction("Up", xPos, yPos));
        am.put("Down", new ArrowAction("Down", xPos, yPos));


        //finishin off adding functionality to the frame
        f.add(header, BorderLayout.PAGE_START);
        f.add(tileGrid, BorderLayout.CENTER);
        f.setSize(200, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);


    }

    //Do keybindings

    //Do change colours

    //instead of buttons change to labels
    JButton changeColourGreen(JButton b) {
        b.setBackground(Color.GREEN);

        return b;
    }

    JButton changeColourYellow(JButton b) {

        b.setBackground(Color.YELLOW);

        return b;

    }

    char getTileType(int x, int y) {

        return 'c';

    }

    boolean setMapSize(int x, int y) {

        //Check if x == y and other stuff I think

        return true;

    }

    //Method to place player on random element of the map
    JLabel getStartingPosition(JLabel[][] labels) {

        //Do something with labels to get a random starting position


        return labels[1][1];
    }

    public class ArrowAction extends AbstractAction {

        //get the starting coordinates

        private String cmd;

        private int xPos;
        private int yPos;

        public ArrowAction(String cmd, int x, int y ) {
            this.cmd = cmd;
            xPos = x;
            yPos = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cmd.equalsIgnoreCase("Left")) {
                //Get current position and do plus 1 in the x axis
                //also chnage the variable which controls the header

                xPos = xPos - 1;

                System.out.println("The left arrow was pressed!");
            } else if (cmd.equalsIgnoreCase("Right")) {
                System.out.println("kill me");
            } else if (cmd.equalsIgnoreCase("Up")) {
                System.out.println("The up arrow was pressed!");
            } else if (cmd.equalsIgnoreCase("Down")) {
                System.out.println("The down arrow was pressed!");
            }
        }

    }
}


