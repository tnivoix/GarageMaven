package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        // Et si la voiture est déjà dans un garage ?
        if (estDansUnGarage()) {
            throw new Exception("La voiture est déjà dans un garage");
        }
        if (null == g) {
            throw new IllegalArgumentException("Un garage doit être fourni");
        }
        Stationnement s = new Stationnement(this, g);
        myStationnements.add(s);
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        if (!estDansUnGarage()) {
            throw new Exception("La voiture n'est pas dans un garage");
        }
        myStationnements.get(myStationnements.size() - 1).terminer();
    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        // TODO: Implémenter cette méthode
        Set<Garage> garages = new HashSet<>();
        for(Stationnement s : myStationnements){
            if(!garages.contains(s.getGarage())){
                garages.add(s.getGarage());
            }
        }
        return garages;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        if(myStationnements.isEmpty()){
            return false;
        }else{
        return myStationnements.get(myStationnements.size() - 1).estEnCours();
        }
    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    public void imprimeStationnements(PrintStream out) {
        for(Garage g : garagesVisites()){
            out.println(g.toString());
            for(Stationnement s : stationnementDansGarage(g)){
                out.println(s.toString());
            }
        }
        
    }
    
    /**
     * @param g le garage à tester
     * @return la liste des stationnements dans un garage
     */
    public List<Stationnement> stationnementDansGarage(Garage g){
        List<Stationnement> stationnements = new LinkedList<>();
        for(Stationnement s : myStationnements){
            if(s.getGarage() == g){
                stationnements.add(s);
            }
        }
        return stationnements;
    }

}
