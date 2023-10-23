package com.app;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
* Fonction Main permettant de d'instancialiser et tester l'application
**/
public class Main {
    public static void main(String[] args) throws IOException {

        //Booléen permettant de réitérer la recherche de page web en fonction de la référence
        boolean isFinish = false;
        //Scanner permettant à l'utilisteur de rentrer des données.
        Scanner scanInputIn = new Scanner(System.in);
        //Instanciation d'une liste d'offre vide qui sera définie plus tard 
        List<JobOffer> offers = new ArrayList<JobOffer>();
        //Récupération de l'entreprise au choix de l'utilisateur
        try {
            System.out.print("Please enter the full url of the 'Job Offer' tab of the desired company :");

            String companyUrl = scanInputIn.nextLine();
        //On scrap les données de la page demandée par l'utilisateur    
            offers = ScrapOffers.scrapeOffers(companyUrl);

        //On inspecte le scanner
        } catch (Exception e) {
            System.out.println("Error on the scanner " + e.getMessage());
        }
        // Permet à l'utilisateur de faire autant de recherches qu'il le souhaite
        while(!isFinish){
            try {
                //Récupération de la réference de l'utilisateur
                System.out.print("Please enter the reference of the desired offer (type 'no' if you want to quit) :");

                String findReference = scanInputIn.nextLine();
                //Vérification sur la référence donnée par l'utilisateur (On vérifie la taille, le type et la valeur de l'entrée)
                if (findReference.equals("no") || (findReference.length() != 8 && findReference.matches("\\d{8}"))) {
                    System.out.println("Valeur entrée incorrecte");
                    isFinish = true;
                //Parcours du jeux de données à la recherche de la référence correspondante
                } else {
                    for (JobOffer ref : offers) {
                        if (ref.getReference().equals(findReference)) {
                            System.out.println("Here is the url to the desired job offer : " + ref.getOfferUrl());
                        }
                    }
                }
            //Inspection du scanner    
            } catch (Exception e) {
                System.out.println("Error on the scanner " + e.getMessage());
            } 
        }  
        //Fin du scanner
        scanInputIn.close();
    }
}
