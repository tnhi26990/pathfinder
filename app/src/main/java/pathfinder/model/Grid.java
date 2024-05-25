package pathfinder.model;

import pathfinder.Observer;
import java.util.ArrayList;
import java.util.Random;




public class Grid{

    public int size;
    public int attempts;
    public String level;
    public boolean valid;
    public int prevRow;
    public int prevCol;
    public boolean [][]squares;
    private boolean [][]correct;

    protected ArrayList<Observer> observers;
    private Random rand;


    public Grid(String level) {
        this.level = level;
        this.attempts = 0;
        this.initGrid();
        this.prevRow = size -1;
        this.prevCol = 0;
        this.observers = new ArrayList<Observer>();
        this.generatePath();
    }

    public int getSize() {
        return this.size;
    }

    public int getAttempts(){
        return this.attempts;
    }

    public void register(Observer observer) {
       observers.add(observer);
    }
 
    public void unregister(Observer observer) {
       observers.add(observer);
    }
 
    public void notifyObservers() {
       for (Observer o: observers)
       {
          o.update();
       }
    }

    private void initGrid() {

        if (level.equals("Medium")) {
            this.size = 7;
        } else if (level.equals("Hard")) {
            this.size = 10;
        } else {
            this.size = 5;
        }
        this.squares = new boolean[size][size];
        this.correct = new boolean[size][size];
    }


    public void generatePath()
    {
        this.rand = new Random();

        //Initializing the start square and an ArrayCoordinate that follows the generation
        ArrayCoordinate position = new ArrayCoordinate(size - 1, 0);
        squares[size - 1][0] = true;

        //While path has not reached top right i.e. end square, generation continues
        while (squares[0][size - 1] != true) 
        {
            boolean moveUp = rand.nextBoolean();
            if (moveUp && position.getOuter() > 0)
            {
                position.decrementOuter(); //goes up
                squares[position.getOuter()][position.getInner()] = true;
            }
            else if (position.getInner() < size - 1)
            {
                position.incrementInner(); //goes right
                squares[position.getOuter()][position.getInner()] = true;
            }            
        }
    }

    public void setPreviousButton(ArrayCoordinate btn)
    {
        this.prevRow = btn.getOuter();
        this.prevCol = btn.getInner();
    }

    public boolean isAdjacent(int row, int col)
    {
        //Gets the absolute value of the difference between the two coordinates
        int rowDifference = Math.abs(this.prevRow - row);
        int colDifference = Math.abs(this.prevCol- col);
    
        boolean horizontal = rowDifference == 1 && colDifference == 0;
        boolean vertical = rowDifference == 0 && colDifference == 1;
        boolean sameSquare = rowDifference == 0 && colDifference == 0;
        boolean adjacent = false;
        if (horizontal || vertical || sameSquare)
        {
            adjacent = true;
        }
        return adjacent;
    }

    public boolean isCorrect(int row, int col)
    {
        valid = false;
        if (squares[row][col] == true)
        {
            valid = true;
            correct[row][col] = true;
            prevRow = row;
            prevCol = col;
        }
        else
        {
            this.attempts++;
            valid = false;
        }
        notifyObservers();
        return valid;
    }

    
    public boolean getSquare()
    {
        return valid;
    }

    public int getPrevRow()
    {
        return this.prevRow;
    }

    public int getPrevCol()
    {
        return this.prevCol;
    }

    public void reset()
    {
        prevRow = size -1;
        prevCol = 0;
    }

    public boolean isFound()
    {
        if (this.correct[0][size-1])
        {
            String currentBestAttempts = ScoresLoader.getAttempts(level);
            if (currentBestAttempts.equals("N/A"))
            {
                ScoresLoader.saveAttempts(attempts, level);
            }
            else if (this.attempts < Integer.parseInt(currentBestAttempts))
            {
                ScoresLoader.saveAttempts(this.attempts, this.level);
            }
            return true;
        } 
        return false;
    }



}
