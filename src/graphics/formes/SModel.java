package graphics.formes;

import graphics.ui.Visitor.ModelDraftman;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class SModel extends Shape{
    ArrayList<Calque> calques;

    public SModel() {
        this.calques = new ArrayList<>();
        Calque c1 = new Calque();
        c1.use();
        c1.setPaint(true);
        addCalque(c1);
    }

    @Override
    public Point getLoc() {
        return null;
    }

    @Override
    public void setLoc(Point point) {
    }

    @Override
    public void translate(int x, int y) {
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void unselect(){
        for (Calque calque : this.calques) {
            calque.getContent().unselect();
        }
    }

    @Override
    public void accept(ShapeVisitor visitor) {
    }

    @Override
    public void resize(int width, int height) {
        //TODO a quoi sert SMODEL ?
        //TODO c'est ce qui remplace le model du prof. En gros c'est ce qui regroupe tous mes calques, et apres j'ai la vue et le model qui correspondent pour gerer comme il faut les calques
    }

    public void addCalque(Calque calque){
        for (Calque calque1 : this.calques) {
            if (calque1.getName().equals(calque.getName())){
                return;
            }
        }
        this.calques.add(calque);
    }

    public void addCalque(){
        this.addCalque(new Calque());
    }

    public void delCalque(Calque calque){
        if (calque.isUsed()){
            this.calques.remove(calque);
            this.setUse(0);
        }
    }

    public void exchangeCalques(int c1, int c2){
        if (c1!=c2 && c1<this.calques.size() && c2<this.calques.size() && c1>=0 && c2>=0){
            Calque move = this.calques.get(c1);
            this.calques.set(c1, this.calques.get(c2));
            this.calques.set(c2, move);
        }
    }

    public void exchangeCalques(int c1, String name2){
        for (int c2=0; c2<this.calques.size(); c2++){
            if (this.calques.get(c2).getName().equals(name2)){
                exchangeCalques(c1, c2);
                return;
            }
        }
    }

    public void exchangeCalques(String name1, int c2){
        for (int c1=0; c1<this.calques.size(); c1++){
            if (this.calques.get(c1).getName().equals(name1)){
                exchangeCalques(c1, c2);
                return;
            }
        }
    }

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

    public void moveCalque(int nbCalque, int newPlace){
        /**
        /* insert between SCalques newPlace-1 and newPlace
        */
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

    public void moveCalque(String nameCalque, int newPlace){
        /**
        /* insert between SCalques newPlace-1 and newPlace
        */
        for (int i=0; i<this.calques.size(); i++){
            if (this.calques.get(i).getName().equals(nameCalque)){
                moveCalque(i, newPlace);
                return;
            }
        }
    }

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

    public void setName(int n, String newName){
        this.setName(this.getCalques().get(n), newName);
    }

    public void setUse(Calque c){
        if (this.calques.contains(c)){
            for (Calque calque: this.calques){
                calque.notUse();
            }
            c.use();
        }
    }

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

    public void setUse(int n){
        if (this.calques.size()==0){
            this.calques.add(new Calque());
        }
        if (n>=0 && n<this.calques.size()){
            this.setUse(this.calques.get(n));
        }
    }

    public void setPaint(Calque c){
        c.setPaint(true);
    }

    public void setPaint(String name){
        for (Calque c:this.calques){
            if (Objects.equals(c.getName(), name)){
                c.setPaint(true);
            }
        }
    }

    public void setPaint(int n){
        if (n>=0 && n<this.calques.size()){
            this.calques.get(n).setPaint(true);
        }
    }

    public void setNotPaint(Calque c){
        c.setPaint(false);
    }

    public void setNotPaint(String name){
        for (Calque c:this.calques){
            if (Objects.equals(c.getName(), name)){
                c.setPaint(false);
            }
        }
    }

    public void setNotPaint(int n){
        if (n>=0 && n < this.calques.size()){
            this.calques.get(n).setPaint(false);
        }
    }

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

    public SCollection getCalqueUse(){
        for (Calque calque : this.calques) {
            if (calque.isUsed()){
                return calque.getContent();
            }
        }
        return null;
    }

    public void accept(ModelDraftman draftman) {
        draftman.visitModel(this);
    }

    public void force_translate(int dx, int dy){
        for (Calque calque : this.getCalques()) {
            calque.force_translate(dx, dy);
        }
    }

    @Override
    public void zoomIn() {
        for (Calque calque : this.getCalques()) {
            calque.zoomIn();
        }
    }

    @Override
    public void zoomOut() {
        for (Calque calque : this.getCalques()) {
            calque.zoomOut();
        }
    }
}
