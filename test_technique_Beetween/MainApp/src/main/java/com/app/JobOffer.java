package com.app;

/** 
* Classe permettant de stocker les objets JobOffer
* Contient 2 string : l'url de l'offre d'emploi ainsi que sa référence.
**/
public class JobOffer {
    
    private String offerUrl;
    private String reference;

    //On retrouve le constructeur qui permettra d'instancier différentes offres d'emplois.
    public JobOffer(String offerUrl, String reference) {
        this.offerUrl = offerUrl;
        this.reference = reference;
    }
    //Getter du champ UrlOffer
        public String getOfferUrl() {
        return offerUrl;
    }
    //Getter du champ Reference
    public String getReference() {
        return reference;
    }
}