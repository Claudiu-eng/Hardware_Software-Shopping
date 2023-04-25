package com.example.hardware_softwareshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HardwareSoftwareShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HardwareSoftwareShoppingApplication.class, args);


        /*
        * CUM inserez adresa??cand am many to one
        * cand inserez un client,fac eu new shopcart si new notifList,este ok asa?
        *
        *de ce trebuie la customer sa pun EAGER???
        *
        *
        * cum s-ar face logarea fara enum ,adica sa detectez automat rolul fiecarui user
        * cum fac asta in react?
        *
        * cum fac ca atunci cand ma loghez,sa nu vad parola in consola de la web
        *
        * sau cum salvez un user?cu care repo
        * are ceva ca in customerService folosesc userRepo?
        *
        *
        * cand vreau sa apas pe insert product ca admin
        * trebe pus review list de la product model ca EAGER
        *
        *
        * inserez bine reviurile?
        * sau alte obiecte din relatie many-one
        *
        * cand sterg review,il sterg bine??
        * */
    }

}
