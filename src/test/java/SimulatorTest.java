import com.au.iress.toy.robot.Simulator;
import com.au.iress.toy.robot.exception.DirectionException;
import com.au.iress.toy.robot.exception.PositionException;
import com.au.iress.toy.robot.exception.ToyException;
import com.au.iress.toy.robot.service.Direction;
import com.au.iress.toy.robot.service.Position;
import com.au.iress.toy.robot.service.SquareBoard;
import com.au.iress.toy.robot.service.Robot;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SimulatorTest {

    final int BOARD_ROWS = 5;

    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPlacing() throws ToyException,DirectionException,PositionException {

        SquareBoard board = new SquareBoard( BOARD_ROWS);
        Robot robot = new Robot();
        Simulator simulator = new Simulator(board, robot);

        Assert.assertFalse(simulator.placeToyRobot(new Position(-1, 5, Direction.EAST)));
        Assert.assertTrue(simulator.placeToyRobot(new Position(5, 5, Direction.NORTH)));
        Assert.assertFalse(simulator.placeToyRobot(new Position(0, -5, Direction.NORTH)));
        Assert.assertTrue(simulator.placeToyRobot(new Position(0, 0, Direction.NORTH)));
        Assert.assertTrue(simulator.placeToyRobot(new Position(2, 2, Direction.SOUTH)));

    }

    @Test
    public void testPlacingExceptions() throws ToyException,DirectionException,PositionException {

        SquareBoard board = new SquareBoard(BOARD_ROWS);
        Robot robot = new Robot();
        Simulator simulator = new Simulator(board, robot);

        thrown.expect(PositionException.class);
        simulator.placeToyRobot(null);
        thrown.expect(PositionException.class);
        simulator.placeToyRobot(new Position(0, 1, null));
    }

    @Test
    public void testEval() throws ToyException,DirectionException,PositionException {

        SquareBoard board = new SquareBoard(BOARD_ROWS);
        Robot robot = new Robot();
        Simulator simulator = new Simulator(board, robot);

        simulator.eval("PLACE 0,0,NORTH");
        Assert.assertEquals("0,0,NORTH", simulator.eval("REPORT"));

        // ignores the command if robot falls out of board
        for (int i = 0; i < 10; i++)
            simulator.eval("MOVE");
        Assert.assertEquals("0,5,NORTH", simulator.eval("REPORT"));

        simulator.eval("RIGHT");
        //rotate on itself
        for (int i = 0; i < 4; i++)
            simulator.eval("LEFT");
        Assert.assertEquals("0,5,EAST", simulator.eval("REPORT"));

        // invalid commands
        thrown.expect(ToyException.class);
        Assert.assertEquals("Invalid command", simulator.eval("PLACE00NORTH"));
        thrown.expect(ToyException.class);
        Assert.assertEquals("Invalid command", simulator.eval("SAHIL"));
        thrown.expect(ToyException.class);
        Assert.assertEquals("Invalid command", simulator.eval("SAMPLE"));
    }
}
