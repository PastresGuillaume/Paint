package graphics.shapes;

import graphics.shapes.uiCalques.ModelDraftman;

import java.util.ArrayList;
import java.util.Objects;

public class SModel {
    ArrayList<SCalque> calques;

    public SModel(){
        this.calques = new ArrayList<>();
        SCalque c1 = new SCalque();
        c1.use();
        c1.setPaint(true);
        addCalque(c1);
    }

    public void addCalque(SCalque calque){
        this.calques.add(calque);
    }

    public void delCalque(SCalque calque){
        this.calques.remove(calque);
    }

    public void exchangeCalques(int c1, int c2){
        if (c1!=c2 && c1<this.calques.size() && c2<this.calques.size() && c1>=0 && c2>=0){
            SCalque move = this.calques.get(c1);
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
        SCalque calque = this.calques.get(nbCalque);
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

    public ArrayList<SCalque> getCalques(){
        return this.calques;
    }

    public void setName(SCalque calque, String newName){
        for (SCalque c:this.calques){
            if (Objects.equals(c.getName(), newName)){
                return;
            }
        }
        calque.setName(newName);
    }

    public void setName(String oldName, String newName){
        SCalque calque = null;
        for (SCalque c:this.calques){
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
        if (n<this.calques.size()) {
            for (SCalque c : this.calques) {
                if (Objects.equals(c.getName(), newName)) {
                    return;
                }
            }
            this.calques.get(n).setName(newName);
        }
    }

    public void setUse(SCalque c){
        if (this.calques.contains(c)){
            for (SCalque calque: this.calques){
                calque.notUse();
            }
            c.use();
        }
    }

    public void setUse(String s){
        SCalque calque=null;
        for (SCalque c: this.calques){
            if (Objects.equals(c.getName(), s)){
                calque = c;
            }
        }
        if (calque!=null){
            this.setUse(calque);
        }
    }

    public void setUse(int n){
        if (n<this.calques.size()){
            this.setUse(this.calques.get(n));
        }
    }

    public void setPaint(SCalque c){
        if (this.calques.contains(c)){
            c.setPaint(true);
        }
    }

    public void setPaint(String name){
        for (SCalque c:this.calques){
            if (Objects.equals(c.getName(), name)){
                c.setPaint(true);
            }
        }
    }

    public void setPaint(int n){
        if (n < this.calques.size()){
            this.calques.get(n).setPaint(true);
        }
    }

    public void setNotPaint(SCalque c){
        if (this.calques.contains(c)){
            c.setPaint(false);
        }
    }

    public void setNotPaint(String name){
        for (SCalque c:this.calques){
            if (Objects.equals(c.getName(), name)){
                c.setPaint(false);
            }
        }
    }

    public void setNotPaint(int n){
        if (n < this.calques.size()){
            this.calques.get(n).setPaint(false);
        }
    }

    public void add(Shape s){
        if (this.calques.size()==0){
            this.calques.add(new SCalque());
            this.calques.get(0).use();
        }
        for (SCalque c: this.calques){
            if (c.isUsed()){
                c.add(s);
            }
        }
    }

    public SCollection getModel(){
        for (SCalque calque : this.calques) {
            if (calque.isUsed()){
                return calque.getContent();
            }
        }
        return null;
    }

    public void accept(ModelDraftman draftman) {
        draftman.visitModel(this);
    }
}
