package graphics.shapes;

import graphics.shapes.uiCalques.ModelDraftman;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

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

    public void accept(ModelDraftman draftman) {
        draftman.visitModel(this);
    }
}
