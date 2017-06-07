import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sean.stack.Maze;

/**
 * Created by Sean on 6/7/17.
 */
public class MazeTest {

    private Maze maze;

    @Before
    public void setup() {
        maze = new Maze();
    }

    @Test
    public void findPath() throws Exception {
        int data[][] = {
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 0, 0}
        };

        maze.update(data);

        Maze.PosType start = new Maze.PosType(0, 0);
        Maze.PosType end = new Maze.PosType(2, 3);

        Assert.assertTrue(maze.locatePath(start, end));
    }

    @Test
    public void findInvalidPath() {
        int data[][] = {
                {0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 0, 0}
        };
        maze.update(data);

        Assert.assertFalse(maze.locatePath(new Maze.PosType(0, 0),
                new Maze.PosType(3, 8)));
    }

}