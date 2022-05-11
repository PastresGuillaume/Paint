package graphics.formes;

import graphics.ui.Visitor.ModelDraftman;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Définition du modèle.
 */
public class SModel extends Shape{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 2516608974470857280L;

    /**
     * Liste des calques.
     */
    ArrayList<Calque> calques;

    /**
     * Constructeur.
     */
    public SModel() {
        this.calques = new ArrayList<>();
        Calque c1 = new Calque();
        c1.use();
        c1.setPaint(true);
        addCalque(c1);
    }

    /**
     * Retourne null car le modèle tout entier n'a pas de position.
     *
     * @return null.
     */
    @Override
    public Point getLoc() {
        return null;
    }

    /**
     * Ne fais rien, car getLoc est null.
     *
     * @param point Type : Point
     */
    @Override
    public void setLoc(Point point) {
    }

    /**
     * Renvoie null, car il n'y a pas de limite au model.
     *
     * @return null.
     */
    @Override
    public Rectangle getBounds() {
        return null;
    }

    /**
     * Désélectionne toutes les formes de tous les calques.
     */
    @Override
    public void unselect(){
        for (Calque calque : this.calques) {
            calque.getContent().unselect();
        }
    }

    /**
     * Ne fais rien, car on ne redimensionne pas tous les calques à la fois.
     *
     * @param width Type : int
     * @param height Type : int
     */
    @Override
    public void resize(int width, int height) {
    }

    /**
     * Ajoute un calque en dernière position.
     *
     * @param calque Le calque à ajouter.
     */
    public void addCalque(Calque calque){
        for (Calque calque1 : this.calques) {
            if (calque1.getName().equals(calque.getName())){
                return;
            }
        }
        this.calques.add(calque);
    }

    /**
     * Ajoute un calque en dernière position.
     */
    public void addCalque(){
        this.addCalque(new Calque());
    }

    /**
     * Supprime un calque du model.
     *
     * @param calque Calque à supprimer.
     */
    public void delCalque(Calque calque){
        if (calque.isUsed()){
            this.calques.remove(calque);
            this.setUse(0);
        }
    }

    /**
     * Échange les calques.
     *
     * @param c1 Indice du premier calque.
     * @param c2 Indice du second calque.
     */
    public void exchangeCalques(int c1, int c2){
        if (c1!=c2 && c1<this.calques.size() && c2<this.calques.size() && c1>=0 && c2>=0){
            Calque move = this.calques.get(c1);
            this.calques.set(c1, this.calques.get(c2));
            this.calques.set(c2, move);
        }
    }

    /**
     * Échange les calques.
     *
     * @param c1 Indice du premier calque.
     * @param name2 Nom du second calque.
     */
    public void exchangeCalques(int c1, String name2){
        for (int c2=0; c2<this.calques.size(); c2++){
            if (this.calques.get(c2).getName().equals(name2)){
                exchangeCalques(c1, c2);
                return;
            }
        }
    }

    /**
     * Échange les calques.
     *
     * @param name1 Nom du premier calque.
     * @param c2 Indice du second calque.
     */
    public void exchangeCalques(String name1, int c2){
        for (int c1=0; c1<this.calques.size(); c1++){
            if (this.calques.get(c1).getName().equals(name1)){
                exchangeCalques(c1, c2);
                return;
            }
        }
    }

    /**
     * Échange les calques.
     *
     * @param name1 Nom du premier calque.
     * @param name2 Nom du second calque.
     */
    public void exchangeCalques(String name1, String name2){
        if (name1.equals(name2)){
            return;
        }
        int c1 = -1;
        for (int i=0; i<this.calques.size(); i++){
            if (this.calques.get(i).getName().equals(name1) || this.calques.get(i).getName().equals(name2)){
                if (c1==-1){
                    c1 = i;
                }
                else{
                    exchangeCalques(c1, i);
                    return;
                }
            }
        }
    }

    /**
     * Bouge le calque numéro nbCalque entre calques de place newPlace-1 et newPlace.
     *
     * @param nbCalque Numéro du calque à déplacer.
     * @param newPlace Nouvelle place du calque.
     */
    public void moveCalque(int nbCalque, int newPlace){
        if (nbCalque<0 || nbCalque>=this.calques.size() || newPlace<0 || newPlace>=this.calques.size() || nbCalque==newPlace || nbCalque+1==newPlace){
//            nbCalque==newPlace                -> cela ne sert à rien de bouger un calque jusqu'à soi
//            nbCalque+1==newPlace              -> cela ne sert à rien d'insérer un calque entre lui et son voisin de droite
            return;
        }
        Calque calque = this.calques.get(nbCalque);
        int increment;
        if (nbCalque<newPlace){
            increment = 1;
        }
        else {
            increment = -1;
        }
        for (int i=nbCalque; i<newPlace; i+=increment){
            this.calques.set(i, this.calques.get(i+increment));
        }
        this.calques.set(newPlace, calque);
    }

    /**
     * Bouge le calque numéro nbCalque entre calques de place newPlace-1 et newPlace.
     *
     * @param nameCalque Nom du calque à déplacer.
     * @param newPlace Nouvelle place du calque.
     */
    public void moveCalque(String nameCalque, int newPlace){
        for (int i=0; i<this.calques.size(); i++){
            if (this.calques.get(i).getName().equals(nameCalque)){
                moveCalque(i, newPlace);
                return;
            }
        }
    }

    /**
     * Renvoie la liste des calques.
     *
     * @return La liste des calques du modèle.
     */
    public ArrayList<Calque> getCalques(){
        return this.calques;
    }

    public void setName(Calque calque, String newName){
        for (Calque c:this.calques){
            if (Objects.equals(c.getName(), newName)){
                return;
            }
        }
        calque.setName(newName);
    }

    /**
     * Change le nom d'un calque.
     *
     * @param oldName Ancien nom du calque.
     * @param newName Nouveau nom du calque.
     */
    public void setName(String oldName, String newName){
        Calque calque = null;
        for (Calque c:this.calques){
            if (Objects.equals(c.getName(), newName)){
                return;
            }
            if (Objects.equals(c.getName(), oldName)){
                calque = c;
            }
        }
        if (calque==null){
            return;
        }
        calque.setName(newName);
    }

    /**
     * Change le nom d'un calque.
     *
     * @param n Numéro du calque.
     * @param newName Nouveau nom du calque.
     */
    public void setName(int n, String newName){
        this.setName(this.getCalques().get(n), newName);
    }

    /**
     * Change le calque en cours d'utilisation.
     *
     * @param c Nouveau calque à utiliser.
     */
    public void setUse(Calque c){
        if (this.calques.contains(c)){
            for (Calque calque: this.calques){
                calque.notUse();
            }
            c.use();
        }
    }

    /**
     * Change le calque en cours d'utilisation.
     *
     * @param s Nom du nouveau calque à utiliser.
     */
    public void setUse(String s){
        Calque calque=null;
        for (Calque c: this.calques){
            if (Objects.equals(c.getName(), s)){
                calque = c;
            }
        }
        if (calque!=null){
            calque.use();
        }
    }

    /**
     * Change le calque en cours d'utilisation.
     *
     * @param n Place du nouveau calque à utiliser.
     */
    public void setUse(int n){
        if (this.calques.size()!=0) {
            if (n >= 0 && n < this.calques.size()) {
                this.setUse(this.calques.get(n));
            }
        }
    }

    /**
     * Dit au calque qu'il doit être affiché.
     *
     * @param c Calque à afficher.
     */
    public void setPaint(Calque c){
        c.setPaint(true);
    }

    /**
     * Dit au calque qu'il doit être affiché.
     *
     * @param name Nom du calque à afficher.
     */
    public void setPaint(String name){
        for (Calque c:this.calques){
            if (Objects.equals(c.getName(), name)){
                c.setPaint(true);
            }
        }
    }

    /**
     * Dit au calque qu'il doit être affiché.
     *
     * @param n Numéro du calque à afficher.
     */
    public void setPaint(int n){
        if (n>=0 && n<this.calques.size()){
            this.calques.get(n).setPaint(true);
        }
    }

    /**
     * Dit au calque qu'il ne doit pas être affiché.
     *
     * @param c Calque à ne pas afficher.
     */
    public void setNotPaint(Calque c){
        c.setPaint(false);
    }

    /**
     * Dit au calque qu'il ne doit pas être affiché.
     *
     * @param name Nom du calque à ne pas afficher.
     */
    public void setNotPaint(String name){
        for (Calque c:this.calques){
            if (Objects.equals(c.getName(), name)){
                c.setPaint(false);
            }
        }
    }

    /**
     * Dit au calque qu'il ne doit pas être affiché.
     *
     * @param n Numéro du calque à ne pas afficher.
     */
    public void setNotPaint(int n){
        if (n>=0 && n < this.calques.size()){
            this.calques.get(n).setPaint(false);
        }
    }

    /**
     * Ajoute une forme au calque en cours d'utilisation.
     *
     * @param s Forme à ajouter.
     */
    public void add(Shape s){
        if (this.calques.size()==0){
            this.calques.add(new Calque());
            this.calques.get(0).use();
        }
        for (Calque c: this.calques){
            if (c.isUsed()){
                c.add(s);
            }
        }
    }

    /**
     * Renvoie le contenu du calque en cours d'utilisation.
     *
     * @return Le contenu du calque en cours d'utilisation.
     */
    public SCollection getCalqueUse(){
        for (Calque calque : this.calques) {
            if (calque.isUsed()){
                return calque.getContent();
            }
        }
        return null;
    }

    /**
     * Ne fais rien, car un model ne doit pas être dessiné par un ShapeVisitor.
     *
     * @param visitor Un dessinateur quelconque, ou null.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
    }

    /**
     * Opération d'affichage.
     *
     * @param draftman Le dessinateur qui sait dessiner un modèle.
     */
    public void accept(ModelDraftman draftman) {
        draftman.visitModel(this);
    }

    /**
     * Ne fais rien, car on ne doit pas déplacer toutes les formes de tous les calques sans raison.
     *
     * @param dx Type : int
     * @param dy Type : int
     */
    @Override
    public void translate(int dx, int dy) {
    }

    /**
     * Force la translation de toutes les formes de tout le modèle.
     *
     * @param dx Delta translation selon x.
     * @param dy Delta translation selon y.
     */
    public void force_translate(int dx, int dy){
        for (Calque calque : this.getCalques()) {
            calque.force_translate(dx, dy);
        }
    }

    /**
     * Fais un zoom *2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomIn() {
        for (Calque calque : this.getCalques()) {
            calque.zoomIn();
        }
    }

    /**
     * Fais un zoom /2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomOut() {
        for (Calque calque : this.getCalques()) {
            calque.zoomOut();
        }
    }
}
