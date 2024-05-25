package pathfinder.controller;

import pathfinder.model.Grid;
import pathfinder.view.LevelSelectionGUI;
import pathfinder.view.PathfinderGUI;
import pathfinder.ControllerInterface;

public class GameController implements ControllerInterface
{
    private LevelSelectionGUI level;
    private Grid grid;
    private PathfinderGUI pathfinder;

    public GameController()
    {
        this.level = new LevelSelectionGUI(this);
    }

    public void userPressed(String level)
    {
        this.grid = new Grid(level);
        this.pathfinder = new PathfinderGUI(grid, this);
    }

    public void userPressed(int row, int col)
    {
        if (!grid.isAdjacent(row, col))
        {
            pathfinder.showError();
            return;
        }
        this.grid.isCorrect(row, col);
        if (grid.isFound())
        {
            level.redoScores();
        }
    }


}