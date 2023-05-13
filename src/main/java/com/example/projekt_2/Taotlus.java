package com.example.projekt_2;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Taotlus {
    private String taotlejaNimi;
    private String tooteNimi;
    private LocalDate taotluseKuupäev;

    public Taotlus(String taotlejaNimi, String tooteNimi) {
        this.taotlejaNimi = taotlejaNimi;
        this.tooteNimi = tooteNimi;
        taotluseKuupäev = LocalDate.now();
    }

    public String getTaotlejaNimi() {
        return taotlejaNimi;
    }

    public String getTooteNimi() {
        return tooteNimi;
    }

    public LocalDate getTaotluseKuupäev() {
        return taotluseKuupäev;
    }
    public void taotluseKirjutamineFaili(String id) throws Exception {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream("andmebaas.txt", true))) {
            pw.println(this.taotluseKuupäev + " " +  this.taotlejaNimi + " " + this.getTooteNimi() + " " + id);
        }
    }

}

