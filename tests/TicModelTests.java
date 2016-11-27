import Model.Mark;
import Model.TicModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Morten on 26/11/2016.
 */
public class TicModelTests {


    private TicModel ticModel;

    @Before
    public void setUp() {
        ticModel = new TicModel();
    }

    @Test
    public void testTurns() {
        assertThat("First mark should be cross!", ticModel.getCurrentMark(), is(Mark.CROSS));
        ticModel.placeMark(0, 0);
        assertThat("Second mark should be circle!", ticModel.getCurrentMark(), is(Mark.CIRCLE));
        ticModel.placeMark(1, 1);
        assertThat("Third mark should be cross again!", ticModel.getCurrentMark(), is(Mark.CROSS));
        ticModel.placeMark(2, 2);
        assertThat(ticModel.getCurrentMark(), is(Mark.CIRCLE));
    }

    @Test
    public void testNoMoreTurnsIfWon() {
        ticModel.placeMark(0,1);
        ticModel.placeMark(1,2);
        ticModel.placeMark(1,1);
        ticModel.placeMark(2,2);
        ticModel.placeMark(2,1);
        ticModel.placeMark(0,0);

        assertThat("There should not be a mark at (0,0)", ticModel.getBoard()[0][0], is(nullValue()));

        assertThat("Game should be won!", ticModel.isWon(), is(true));

        assertThat("No more moves should be allowed!", ticModel.placeMark(2,2), is(false));
        assertThat("No more moves should be allowed!", ticModel.placeMark(1,2), is(false));
    }

    @Test
    public void testCorrectMarks() {
        testTurns();

        assertThat("(0,0) should be a cross!", ticModel.getBoard()[0][0], is(Mark.CROSS));
        assertThat("(1,1) should be a circle!", ticModel.getBoard()[1][1], is(Mark.CIRCLE));
        assertThat("(2,2) should be a cross!", ticModel.getBoard()[2][2], is(Mark.CROSS));

    }

    @Test
    public void testMultipleMarksSameLocation() {
        assertThat("Model.Mark should have been placed!", ticModel.placeMark(0,0), is(true));
        Mark currentMark = ticModel.getCurrentMark();
        assertThat("A mark was overwritten!", ticModel.placeMark(0,0), is(false));
        assertThat("A mark was overwritten!", ticModel.placeMark(0, 0), is(false));
        assertThat("Turn shouldn't switch when placement is invalid!",
                ticModel.getCurrentMark(), is(currentMark));
        assertThat("Model.Mark should have been placed!", ticModel.placeMark(1, 1), is(true));

    }

    @Test
    public void testWinCrossesDiagonal() {

        ticModel.placeMark(0,0);
        ticModel.placeMark(0,2);
        ticModel.placeMark(1,1);
        ticModel.placeMark(0,1);
        ticModel.placeMark(2,2);

        assertThat("Game should be won by crosses!", ticModel.getWinner(), is(Mark.CROSS));
    }

    @Test
    public void testWinCrossesRow1() {
        ticModel.placeMark(0,0);
        ticModel.placeMark(0,2);
        ticModel.placeMark(1,0);
        ticModel.placeMark(2,2);
        ticModel.placeMark(2,0);

        assertThat("Game should be won by crosses!", ticModel.getWinner(), is(Mark.CROSS));
    }

    @Test
    public void testWinCrosses2Row() {
        ticModel.placeMark(0,1);
        ticModel.placeMark(0,2);
        ticModel.placeMark(1,1);
        ticModel.placeMark(2,2);
        ticModel.placeMark(2,1);

        assertThat("Game should be won by crosses!", ticModel.getWinner(), is(Mark.CROSS));
    }


    @Test
    public void testWinCrossesColumn() {
        ticModel.placeMark(0,1);
        ticModel.placeMark(1,1);
        ticModel.placeMark(0,0);
        ticModel.placeMark(2,2);
        ticModel.placeMark(0,2);

        assertThat("Game should be won by crosses!", ticModel.getWinner(), is(Mark.CROSS));
    }

    @Test
    public void testWinCirclesDiagonal() {
        ticModel.placeMark(1,2);
        ticModel.placeMark(0,0);
        ticModel.placeMark(0,2);
        ticModel.placeMark(1,1);
        ticModel.placeMark(0,1);
        ticModel.placeMark(2,2);

        assertThat("Game should be won by circles!", ticModel.getWinner(), is(Mark.CIRCLE));
    }

}