package br.com.snowdev.swvip.entities;

public class Vip
{
    public int id;
    public String username;
    public String group;

    public Vip(int id, String username, String group)
    {
        this.id = id;
        this.username = username;
        this.group = group;
    }
}