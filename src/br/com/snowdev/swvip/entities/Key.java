package br.com.snowdev.swvip.entities;

public class Key
{
    public String code;
    public String group;
    public int days;

    public Key(String code, String group, int days)
    {
        this.code = code;
        this.group = group;
        this.days = days;
    }
}