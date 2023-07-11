package com.fazpass.javatdv2;


public class Sample
{
    public static void main(String[] args) {
        TrustedDevice td = Fazpass.initialize("key.priv");
        td.extract("META");
    }
}
