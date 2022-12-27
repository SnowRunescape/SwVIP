package br.com.snowdev.swvip.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandPermissions
{
    public abstract String[] value();
}