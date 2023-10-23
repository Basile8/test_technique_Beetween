package com.app;
import org.htmlunit.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlunit.html.DomElement;
import org.htmlunit.html.HtmlPage;

/**
* Classe ScrapOffers permettant de récupérer les différentes informations utiles sur le site voulu.
**/
public class ScrapOffers {

    //Fonction principale permettant le scrapping des infos d'un site web. Le site web est passé en argument, c'est l'utilisateur qui indique quel entreprise il veut scrapper les infos.
    public static List<JobOffer> scrapeOffers(String companyUrl) throws IOException {
        List<JobOffer> offers = new ArrayList<>();

        //Instanciation d'une simulation de site web
        try (WebClient webClient = new WebClient()) {
            //On ne garde du site que la partie html 
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            //On récupère la page html souhaitée.
            HtmlPage page = webClient.getPage(companyUrl);
            //On vise ici les éléments se trouvant dans une balise <div> elle même contenue dans une valise <li> elle même contenu dans une balise <ul> afin d'être sûr de récupérer les bons éléments
            List<DomElement> offerElements = page.getByXPath("//ul/li/div");

            //On parcourt notre liste d'élément, et on récupère l'attribut 'id' correspondant à notre reference. On concatène ensuite cette reference avec le reste de l'url afin d'obtenir
            //l'url correspondant à l'offre de travail. On finit par instancialiser un objet JobOffer avec les données trouvées. 
            for (DomElement element : offerElements) {
                String reference = element.getAttribute("id");
                String offerUrl = extractReferenceFromUrl(reference);
                offers.add(new JobOffer(offerUrl, reference));
            }
        } 
        //Exception dans le cas ou le client Web n'est pas initialisé correctement
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching the page.");
        }
        //On retourne le tableau d'offres
        return offers;
    }
    //Fonction permettant la concaténation de l'url
    public static String extractReferenceFromUrl(String url) {
        String fullUrl = "https://www.hellowork.com/fr-fr/emplois/" + url + ".html";
        return fullUrl;
    }
}
