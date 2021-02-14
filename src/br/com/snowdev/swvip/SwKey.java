package br.com.snowdev.swvip;

public class SwKey {
	public String code;
	public String group;
	public int days;
	public int uses;
	
	public SwKey(String code, String group, int days, int uses) {
		this.code = code;
		this.group = group;
		this.days = days;
		this.uses = uses;
	}
}
