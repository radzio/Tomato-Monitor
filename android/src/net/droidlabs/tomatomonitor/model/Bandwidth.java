package model;

/**
 * Created with IntelliJ IDEA.
 * User: Radek Piekarz
 * Date: 09.07.12
 * Time: 17:59
 */
public class Bandwidth
{
    private String tomatoInterface;
    private long rx;
    private long tx;

    public long getRx()
    {
        return rx;
    }

    public void setRx(String rx)
    {
        this.rx = Long.parseLong(rx, 16) / 125;
    }

    public String getTomatoInterface()
    {
        return tomatoInterface;
    }

    public void setTomatoInterface(String tomatoInterface)
    {
        this.tomatoInterface = tomatoInterface;
    }

    public long getTx()
    {
        return tx;
    }

    public void setTx(String tx)
    {
        this.tx = Long.parseLong(tx, 16) / 125;
    }
}
