/* Terminal.java */

/**
 *
 * @author __MadHatter (alias used on https://www.reddit.com/r/dailyprogrammer)
 *
 * [2016-02-13] Challenge #253 [Hard] Working like a terminal
 * https://www.reddit.com/r/dailyprogrammer/comments/45k70o/20160213_challenge_253_hard_working_like_a/
 *
 */

package terminal;

public class Terminal
{
    public static enum WriteMode
    {
        INSERT,
        OVERWRITE
    }

    private CommandManager _commandManager;
    private WriteMode _writeMode;
    private Cursor _cursor;
    private Screen _screen;

    public Terminal()
    {
        _commandManager = new CommandManager();
        _screen = new Screen();
        _writeMode = WriteMode.OVERWRITE;
        _cursor = new Cursor();

        execute();
    }

    private void execute()
    {}

    public WriteMode getWriteMode()
    {
        return _writeMode;
    }

    public void setText(String s)
    {
        Main.textArea.setText(s);
    }
}
