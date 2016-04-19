package CodeWorldsV2;

public interface Display {

    /* Add |dsp| to the list of Displayables to be shown in this Display */
    public abstract void addDisplayable(Displayable dsp);

    /* Update the display to reflect current state of Displayables.  Assume we
     * are at time |time|. */
    public abstract void redraw(int time);
}