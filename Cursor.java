/* Cursor.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 */

package terminal;

public class Cursor
{
    public static class CursorPosition
    {
        public int row;
        public int col;

        private CursorPosition()
        {}

        public CursorPosition(int newRow, int newCol)
        {
            row = newRow;
            col = newCol;
        }
    }

    private static int _row = 0;
    private static int _col = 0;
    private static CursorPosition _cursorPosition;

    private Cursor()
    {}

    public static CursorPosition getPosition()
    {
        _cursorPosition = new CursorPosition(getRow(), getCol());
        return _cursorPosition;
    }

    public static int getRow()
    {
        return _row;
    }

    public static int getCol()
    {
        return _col;
    }

    public static void set(int row, int col)
    {
        /* Stop cursor from going out of bounds (vertical). */
        if (row < 0)
        {
            row = 0;
        }
        else if (row >= Screen.MAX_ROWS)
        {
            row = Screen.MAX_ROWS - 1;
        }

        /* Stop cursor from going out of bounds (horizontal). */
        if (col < 0)
        {
            col = 0;
        }
        else if (col >= Screen.MAX_COLS)
        {
            col = Screen.MAX_COLS - 1;
        }

        _row = row;
        _col = col;
    }

    public static void left()
    {
        set(getRow(), getCol() - 1);
    }

    public static void right()
    {
        set(getRow(), getCol() + 1);
    }

    public static void down()
    {
        set(getRow() + 1, getCol());
    }

    public static void up()
    {
        set(getRow() - 1, getCol());
    }

    public static void home()
    {
        set(0, 0);
    }

    public static void begin()
    {
        set(getRow(), 0);
    }
}
