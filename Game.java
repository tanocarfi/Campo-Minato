import java.util.ArrayList;

public class Game {
    private int [][] grid;
    private int [][] visibility;
    private int [][] bomb;

    public Game() {
        this.grid = new int[9][9];
        this.visibility = new int[9][9];
        this.bomb = new int[2][5];
        //Setting bombs
        for (int i = 0; i < 5; i++) {
            this.bomb[0][i] = -1;
            this.bomb[1][i] = -1;
        }
    }

    public boolean setBombAt(int x, int y) throws Exception {
        if (x < 0 || x > 8) throw new Exception("Invalid x coordinated");
        if (y < 0 || y > 8) throw new Exception("Invalid y coordinated");
        for (int i = 0; i < 5; i++) {
            if (this.bomb[0][i] != -1 && this.bomb[1][i] != -1) continue;
            this.bomb[0][i] = x;
            this.bomb[1][i] = y;
            return true;
        }
        return false;
    }

    public void setBombOnGrid() {
        for (int i = 0; i < 5; i++) {
            this.grid[this.bomb[0][i]][this.bomb[1][i]] = -1;
        }
    }

    public void setAround() {
        for (int i = 0; i < 5; i++) {
            int x = this.bomb[0][i];
            int y = this.bomb[1][i];
            ArrayList<int []> near = this.getNear(x, y);
            System.out.println("x: " + x + "y: " + y + "Near: " + near.size());
            for (int counter = 0; counter < near.size(); counter++) {
                int [] nearCoords = near.get(counter);
                this.grid[nearCoords[0]][nearCoords[1]]++;
            }
            /*for (int j = y-1; j <= y+1; j++) {
                for (int k = x-1; k <= x+1; k++) {
                    if (j < 0 || j > 8) continue;
                    if (k < 0 || k > 8) continue;
                    if (j == y && k == x) continue;
                    if (this.grid[k][j] == -1) continue;
                    this.grid[k][j]++;
                }
            }*/
        }
    }

    public ArrayList<int[]> getNear(int x, int y) {
        ArrayList a = new ArrayList<int []>();
        for (int j = y-1; j <= y+1; j++) {
            if (j < 0 || j > 8) continue;
            for (int k = x - 1; k <= x + 1; k++) {
                if (k < 0 || k > 8 || (j == x && k == y)) continue;
                a.add(new int []{j, k}); //contenitore variabile può contenere al suo interno qualunque cosa.
            }
        }
        return null;
    }

    public boolean fireAt(int x, int y) {
        /*if (x < 0 || x > 8) throw new Exception("Invalid x coordinated");
        if (y < 0 || y > 8) throw new Exception("Invalid y coordinated");
        return this.grid[x][y];*/

        this.visibility[x][y] = 1;
        for (int [] bomb : this.bomb) {
            if (bomb[0] == x && bomb[1] == y) return true;
        }
        if (this.grid[x][y] == 0) {
            this.showCell(x, y);
        }
        return false;
    }

    public void showCell(int x, int y) {
        ArrayList<int[]> near = this.getNear(x, y);
        for (int[] coords : near) {
            if (this.visibility[coords[0]][coords[1]] == 1) continue;
            this.visibility[coords[0]][coords[1]] = 1;
            if (this.grid[coords[0]][coords[1]] == 0) {
                this.showCell(coords[0], coords[1]);
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                s += "| " + this.grid[i][j] + " |";
            }
            s += "\n";
        }
        return s;
    }
}