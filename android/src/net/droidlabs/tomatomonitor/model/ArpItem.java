package model;

/**
 * Created with IntelliJ IDEA.
 * User: Radek Piekarz
 * Date: 09.07.12
 * Time: 17:48
 */
public class ArpItem
{
    private String ip;
    private String mac;
    private String routerInterface;
    private String dhcpLease;
    public ArpItem(String ip, String mac, String routerInterface)
    {
        this.ip = ip;
        this.mac = mac;
        this.routerInterface = routerInterface;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getMac()
    {
        return mac;
    }

    public void setMac(String mac)
    {
        this.mac = mac;
    }

    public String getInterface()
    {
        return routerInterface;
    }

    public void setInterface(String routerInterface)
    {
        this.routerInterface = routerInterface;
    }

    public String getDhcpLease()
    {
        return dhcpLease;
    }

    public void setDhcpLease(String dhcpLease)
    {
        this.dhcpLease = dhcpLease;
    }
}
