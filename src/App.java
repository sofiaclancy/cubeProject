import java.util.Scanner;


public class App {

    // initial cube array -- cube[face][row][column]
    // i made a 3d array so indexes would be simpler to call and visualize
    public static void main(String[] args) {
        
        String[] history = new String[100]; // array called "history" which stores all the moves as a string array, max 100 moves
        int moveCount = 0; 

        String[][][] cube = {
    
    {   // front face (white)
        {"r","r","r"},
        {"r","r","r"},
        {"r","r","r"}
    },
        // up face (blue)
    {   
        {"b","b","b"},
        {"b","b","b"},
        {"b","b","b"}
    },
    {   // down face (yellow)
        {"o","o","o"},
        {"o","o","o"},
        {"o","o","o"}
    },
    {   // back face (green)
        {"g","g","g"},
        {"g","g","g"},
        {"g","g","g"}
    },
    {   // Left face (red)
        {"y","y","y"},
        {"y","y","y"},
        {"y","y","y"}
    },
    {   // Right face (orange)
        {"w","w","w"},
        {"w","w","w"},
        {"w","w","w"}
    }
};
    
    // COMMAND LINE ARG FOR TESTING WITH PREDETERMINED INPUTS
    if (args.length > 0) {

        for (int i = 0; i < args.length; i++) {
            executeMove(cube, args[i]);
            history[moveCount] = args[i];
            moveCount++;
        }

        printCube(cube);
        return; // stops program automatically
    }

    // USER TYPES MOVES
    Scanner scanner = new Scanner(System.in); 

    while (true) {

        System.out.print("Enter move or type 'exit': ");
        String move = scanner.nextLine();

        if (move.equals("exit"))
            break;

        executeMove(cube, move);
        history[moveCount] = move;
        moveCount++;
    }

        // printing final cube
        printCube(cube);
        printReverseSolution(history, moveCount);
    }

    // printing the cube
    public static void printCube(String[][][] cube) {
        RubiksCube Cube = new RubiksCube();
        Cube.show(cube);
        for (int f = 0; f < cube.length; f++) {
            System.out.println();
            for (int r = 0; r < cube[f].length; r++) {
                for (int c = 0; c < cube[f][r].length; c++) {
                    System.out.print(cube[f][r][c]);
                    if (c < cube[f][r].length - 1) System.out.print("|");
                }
                System.out.println();
            }
        }
    }

   // executing move with u, d, l, r, f, b and their primes -- correlating them with each of the faces

    public static void executeMove(String[][][] cube, String move) {
       
        if (move.equals("u"))
        rotateUpFace(cube, 1);

        else if (move.equals("u'"))
            rotateUpFacePrime(cube, 1);

        else if (move.equals("d"))
            rotateDownFace(cube, 2);

        else if (move.equals("d'"))
            rotateDownFacePrime(cube, 2);

        else if (move.equals("l"))
            rotateLeftFace(cube, 4);

        else if (move.equals("l'"))
            rotateLeftFacePrime(cube, 4);

        else if (move.equals("r"))
            rotateRightFace(cube, 5);

        else if (move.equals("r'"))
            rotateRightFacePrime(cube, 5);

        else if (move.equals("f"))
            rotateFrontFace(cube, 0);

        else if (move.equals("f'"))
            rotateFrontFacePrime(cube, 0);

        else if (move.equals("b"))
            rotateBackFace(cube, 3);

        else if (move.equals("b'"))
            rotateBackFacePrime(cube, 3);

        else
            System.out.println("Invalid move");
    }


    public static void rotateDownFace(String[][][] cube, int face) {

        // these steps change the down faces index to their new indexes once rotated 
        // while also changing the adjacent rows 

        // corners
        String tempCorner = cube[face][0][0]; // so no data is lost 
        cube[face][0][0] = cube[face][2][0];
        cube[face][2][0] = cube[face][2][2];
        cube[face][2][2] = cube[face][0][2];
        cube[face][0][2] = tempCorner; 

        // edges
        String tempEdge = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = tempEdge;

        // adjacent rows
        String[] tempRow = cube[0][2].clone();
        cube[0][2] = cube[4][2].clone();
        cube[4][2] = cube[3][2].clone();
        cube[3][2] = cube[5][2].clone();
        cube[5][2] = tempRow;
    }

    // rotating counterclockwise -- clockwise three times
    public static void rotateDownFacePrime(String[][][] cube, int face) {
        for (int i = 0; i < 3; i++)
            rotateDownFace(cube, face);
    }

    public static void rotateUpFace(String[][][] cube, int face) {

        // corners
        String tempCorner = cube[face][0][0];
        cube[face][0][0] = cube[face][2][0];
        cube[face][2][0] = cube[face][2][2];
        cube[face][2][2] = cube[face][0][2];
        cube[face][0][2] = tempCorner;

        // edges
        String tempEdge = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = tempEdge;

        // adjacent rows
        String[] tempRow = cube[0][0].clone();
        cube[0][0] = cube[5][0].clone();
        cube[5][0] = cube[3][0].clone();
        cube[3][0] = cube[4][0].clone();
        cube[4][0] = tempRow;
    }

    // rotating counterclockwise -- clockwise three times
    public static void rotateUpFacePrime(String[][][] cube, int face) {
        for (int i = 0; i < 3; i++)
            rotateUpFace(cube, face);
    }

    public static void rotateLeftFace(String[][][] cube, int face) {
        
        // corners
        String tempCorner = cube[face][0][0];
        cube[face][0][0] = cube[face][2][0];
        cube[face][2][0] = cube[face][2][2];
        cube[face][2][2] = cube[face][0][2];
        cube[face][0][2] = tempCorner;

        // edges
        String tempEdge = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = tempEdge;

        // adjacent columns
        String[] tempCol = {cube[1][0][0], cube[1][1][0], cube[1][2][0]};

        for (int i = 0; i < 3; i++) cube[1][i][0] = cube[0][i][0];
        for (int i = 0; i < 3; i++) cube[0][i][0] = cube[3][2 - i][2];
        for (int i = 0; i < 3; i++) cube[3][i][2] = cube[2][i][0];
        for (int i = 0; i < 3; i++) cube[2][i][0] = tempCol[i];
    }
    
    // rotating counterclockwise -- clockwise three times
    public static void rotateLeftFacePrime(String[][][] cube, int face) {
        for (int i = 0; i < 3; i++)
            rotateLeftFace(cube, face);
    }

    public static void rotateRightFace(String[][][] cube, int face) {

        // corners
        String tempCorner = cube[face][0][0];
        cube[face][0][0] = cube[face][2][0];
        cube[face][2][0] = cube[face][2][2];
        cube[face][2][2] = cube[face][0][2];
        cube[face][0][2] = tempCorner;

        //edges
        String tempEdge = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = tempEdge;

        // adjacent columns
        String[] tempCol = {cube[1][0][0], cube[1][1][0], cube[1][2][0]};

        for (int i = 0; i < 3; i++) cube[1][i][0] = cube[0][i][0];
        for (int i = 0; i < 3; i++) cube[0][i][0] = cube[3][2 - i][2];
        for (int i = 0; i < 3; i++) cube[3][i][2] = cube[2][i][0];
        for (int i = 0; i < 3; i++) cube[2][i][0] = tempCol[i];
    }

    // rotating counterclockwise -- clockwise three times
    public static void rotateRightFacePrime(String[][][] cube, int face) {
        for (int i = 0; i < 3; i++)
            rotateRightFace(cube, face);
    }

    public static void rotateBackFace(String[][][] cube, int face) {

        // corners
        String tempCorner = cube[face][0][0];
        cube[face][0][0] = cube[face][2][0];
        cube[face][2][0] = cube[face][2][2];
        cube[face][2][2] = cube[face][0][2];
        cube[face][0][2] = tempCorner;

        // edges
        String tempEdge = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = tempEdge;

        // adjacent rows
        String[] tempRow = cube[1][0].clone();

        cube[1][0] = cube[5][2].clone();
        cube[5][2] = cube[3][2].clone();
        cube[3][2] = cube[4][0].clone();
        cube[4][0] = tempRow;
    }

    // rotating counterclockwise -- clockwise three times
    public static void rotateBackFacePrime(String[][][] cube, int face) {
        for (int i = 0; i < 3; i++)
            rotateBackFace(cube, face);
    }

    public static void rotateFrontFace(String[][][] cube, int face) {
        
         // corners
        String tempCorner = cube[face][0][0];
        cube[face][0][0] = cube[face][2][0];
        cube[face][2][0] = cube[face][2][2];
        cube[face][2][2] = cube[face][0][2];
        cube[face][0][2] = tempCorner;

        // edges
        String tempEdge = cube[face][0][1];
        cube[face][0][1] = cube[face][1][0];
        cube[face][1][0] = cube[face][2][1];
        cube[face][2][1] = cube[face][1][2];
        cube[face][1][2] = tempEdge;

        // adjacent rows
        String[] tempRow = cube[1][2].clone();

        cube[1][2] = cube[4][2].clone();
        cube[4][2] = cube[2][0].clone();
        cube[2][0] = cube[5][0].clone();
        cube[5][0] = tempRow;
    }

    // rotating counterclockwise -- clockwise three times
    public static void rotateFrontFacePrime(String[][][] cube, int face) {
        for (int i = 0; i < 3; i++)
            rotateFrontFace(cube, face);
    }


    public static void printReverseSolution(String[] history, int moveCount) {
        System.out.println("Solution: ");

        // loop backwards through the moves to reveal answer
        for (int i = moveCount - 1; i >= 0; i--) {

            String move = history[i];

            // flip the move: if it has a prime, remove it, if it doesn't, add a prime
            if (move.endsWith("'")) {
                System.out.print(move.substring(0, 1) + " ");
            } else {
                System.out.print(move + "' ");
            }
        }

        System.out.println();
    }
}