package com.example.projekt_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ID extends Taotlus {
    private String id;

    public ID(String taotlejaNimi, String tooteNimi) throws Exception{
        super(taotlejaNimi, tooteNimi);
        this.id = generateID();
    }
    private static final String idList = "idlist.ser"; //tegin faili ainult ID-de hoidmiseks
    // Meetod failist ID listi tegemiseks
    private static List<String> laenIDListi() {
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(idList);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<String>) ois.readObject();
            ois.close();
            fis.close();
            //System.out.println("Id list võetud failist " + idList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Teen uue listi");
        }
        return list;
    }
    //meetod id listi salvestamiseks faili
    private static void salvestanIDListi(List<String> list) {
        try {
            FileOutputStream fos = new FileOutputStream(idList);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
            System.out.println("Salvestasin ID faili " + idList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // ID genereerimine
    private String generateID() throws Exception {
        this.id = super.getTaotluseKuupäev().toString().replaceAll("-", "").substring(2) + "-" +
                super.getTaotlejaNimi().substring(0, 2).toUpperCase() + super.getTooteNimi().substring(0, 4).toUpperCase();

        if (!idSobivuseKontroll(this.id)) {
            this.id = super.getTaotluseKuupäev().toString().replaceAll("-", "").substring(2) + "-" +
                    super.getTaotlejaNimi().substring(0, 2).toUpperCase() + super.getTooteNimi().substring(0, 4).toUpperCase() +
                    +(int) Math.round(Math.random() * 5); //ID genereerimisel ka juhuslikkuse kasutamine
            if (!idSobivuseKontroll(this.id))
                return veaTeade();
        }
        super.taotluseKirjutamineAndmebaasi(this.id);
        return this.id;
    }

    //Kontrollin, kas list on programmiga kaasas olevas failis olemas, kui ei, siis salvestan id faili
    private boolean idSobivuseKontroll(String id) {
        List<String> idList = laenIDListi();
        if (idList.contains(id)) {
            return false;
        } else {
            idList.add(id);
            salvestanIDListi(idList);
            return true;
        }
    }

    public String  veaTeade () {
        String teade = "Taotlust ei registreeritud!" + "\n" +"ID on juba andmebaasis! Palun proovi uuesti!";
        return teade;
    }


    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        if (this.id == null)
            return veaTeade();
        else
            return "Taotluse ID: " + id;
    }

}