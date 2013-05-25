package model;

/**
 * Created with IntelliJ IDEA.
 * User: Radek Piekarz
 * Date: 09.07.12
 * Time: 17:55
 */
public class SysInfo
{
    private String uptime;
    private int totalRam;
    private int freeRam;
    private int procs;

    public int getFreeRam()
    {
        return freeRam;
    }

    public void setFreeRam(int freeRam)
    {
        this.freeRam = freeRam;
    }

    public int getProcs()
    {
        return procs;
    }

    public void setProcs(int procs)
    {
        this.procs = procs;
    }

    public int getTotalRam()
    {
        return totalRam;
    }

    public void setTotalRam(int totalRam)
    {
        this.totalRam = totalRam;
    }

    public String getUptime()
    {
        return uptime;
    }

    public void setUptime(String uptime)
    {
        this.uptime = uptime;
    }
}
