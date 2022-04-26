package graphics.jeux.Dames;

public class Coup {
    private int position_x;
    private int position_y;
    private Coup suivant;
    private int length;

    public Coup(int position_x, int position_y) {
        assert position_x>0;
        assert position_x<8;
        assert position_y>0;
        assert position_y<8;
        assert (position_x+position_y)%2==1;
        this.position_x = position_x;
        this.position_y = position_y;
        suivant = null;
        length = 0;
    }

    public void addDeplacement(int position_x, int position_y){
        assert position_x>0;
        assert position_x<8;
        assert position_y>0;
        assert position_y<8;
        assert (position_x+position_y)%2==1;
        if (this.length==0){
            this.suivant = new Coup(position_x, position_y);
        }
        else{
            this.suivant.addDeplacement(position_x, position_y);
        }
        this.length++;
    }

    public void addDeplacement(Coup coup){
        if (this.length==0){
            this.suivant = coup;
        }
        else{
            this.suivant.addDeplacement(coup);
        }
        this.length++;
    }

    public int getLength() {
        return length;
    }

    public Coup getSuivant() {
        return suivant;
    }

    public int getPosition_x() {
        return position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public Coup getFirst(){
        Coup retour = new Coup(this.position_x, this.position_y);
        retour.addDeplacement(this.getSuivant().position_x ,this.getSuivant().position_y);
        return retour;
    }
}
