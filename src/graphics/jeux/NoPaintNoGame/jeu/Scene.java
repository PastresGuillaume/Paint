package graphics.jeux.NoPaintNoGame.jeu;

import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Affichage.CompteARebour;
import graphics.jeux.NoPaintNoGame.Affichage.Score;
import graphics.jeux.NoPaintNoGame.Objet.Bloc;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.Objet.Piece;
import graphics.jeux.NoPaintNoGame.Objet.TuyauRouge;
import graphics.jeux.NoPaintNoGame.Perso.Mario;
import graphics.jeux.NoPaintNoGame.ecoute.Audio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Scene extends JPanel {
    //Dimension des images
    public int imgFondX=630;

    public int getImgFondX() { return imgFondX;  }

    public int imgFondY;

    public int ligneChemin ;

    public int tuyauVertX;
    public int tuyauVertY;
    public  int posMAX = 6300;

    public int tuyauRougeY=420;

    public int tuyauRouge1X=700;
    public int tuyauRouge2X=1300;
    public int tuyauRouge3X=1900;
    public int tuyauRouge4X=2900;
    public int tuyauRouge5X=3700;
    public int tuyauRouge6X=4600;
    public int tuyauRouge7X=5400;
    public int tuyauRouge8X=6000;


    public int blocY=290;

    public int blocX1=400;
    public int blocX2=1000;
    public int blocX3=1600;
    public int blocX4=2300;
    public int blocX5=2600;
    public int blocX6=3400;
    public int blocX7=4000;
    public int blocX8=4300;
    public int blocX9=4900;
    public int blocX10=5700;


    public int pieceX1=blocX1+20;
    public int pieceX2=blocX2+20;
    public int pieceX3=blocX3+20;
    public int pieceX4=blocX4+20;
    public int pieceX5=blocX5+20;
    public int pieceX6=blocX6+20;
    public int pieceX7=blocX7+20;
    public int pieceX8=blocX8+20;
    public int pieceX9=blocX9+20;
    public int pieceX10=blocX10+20;



    public int pieceY=blocY-45;


    public int marioX=300;

    public void setMarioY(int marioY) {
        this.marioY = marioY;
    }

    public int marioY=155+160+70;//285

    public int getMarioY0() {
        return marioY0;
    }

    public int marioY0=385;

    int imageMarioLarge =108;
    int imageMarioHauteur = 170;

    int imageTuyauRLarge =106;
    int imageTuyauRHauteur = 200;

    int imageBlocLarge =95;
    int imageBlocHauteur = 94;

    int imagePieceX =40;
    int imagePieceY = 40;

    //hauteur de mario : 252


    public int chateauX;
    public int chateauY=275;

    public int departX;
    public int departY=365;

    public int imgDrapeauFinY=300;

    public int imgSortieY=370;
    //private ArrayList<String> tabNomDecor;

    public TuyauRouge tuyauRouge1;
    public TuyauRouge tuyauRouge2;
    public TuyauRouge tuyauRouge3;
    public TuyauRouge tuyauRouge4;
    public TuyauRouge tuyauRouge5;
    public TuyauRouge tuyauRouge6;
    public TuyauRouge tuyauRouge7;
    public TuyauRouge tuyauRouge8;

    public Bloc bloc1;
    public Bloc bloc2;
    public Bloc bloc3;
    public Bloc bloc4;
    public Bloc bloc5;
    public Bloc bloc6;
    public Bloc bloc7;
    public Bloc bloc8;
    public Bloc bloc9;
    public Bloc bloc10;


    public Piece piece1;
    public Piece piece2;
    public Piece piece3;
    public Piece piece4;
    public Piece piece5;
    public Piece piece6;
    public Piece piece7;
    public Piece piece8;
    public Piece piece9;
    public Piece piece10;

    private ImageIcon icoDrapeuFin;
    private Image imgDrapeauFin;
    private ImageIcon icoSortie;
    private Image imgSortie;

    //Stock image du fond
    private final ImageIcon icoFond;
    private final Image imgFond1;
    private final Image imgFond2;

    private final ImageIcon icoChateau1;
    private final Image imgChateau1;
    private final ImageIcon icoDepart;
    private final Image imgDepart;

    //private ArrayList<Decor> tabDecor2;

    private final ArrayList<Decor> tabDecorb=new ArrayList<Decor>();// tableau des objets du jeu
    private final ArrayList<Decor> tabDecor=new ArrayList<Decor>();

    private final ArrayList<Piece> tabPiece=new ArrayList<Piece>();// tableau des objets du jeu

    private CompteARebour compteARebours;

    private Score score;
    private Font police= new Font("Arial",Font.PLAIN ,10);

    // à remplacer par SText quand il sera incorposé au projet de groupe.

    private int xFond2;
    private int xPos;

    private int dx;
    private int xFond1;//abscice du coin sup gauche par rapport à lecran
    /*mario*/

    private int ysol;//hauteur du sol
    private int hauteurPlafond;
    public Mario mario;
    //private ImageIcon icoMario;//code provisoire
    //private Image imgMario;//code provisoire

//constructeur

    public Scene(){
        super();// hérite du constructeur de JPanel

        this.xFond1 = -50; // ( image déborde de chaque coté)
        this.xFond2 = 1250;
        this.dx=0;
        this.xPos=-1;
        this.ysol=293;
        this.hauteurPlafond=0;

        //association image
        icoFond = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"Fond.jpg");// quand on créer le fichier jar , le chemin change)

        this.imgFond1=this.icoFond.getImage();
        this.imgFond2=this.icoFond.getImage();



        this.icoChateau1 = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"chateau.jpg");// quand on créer le fichier jar , le chemin change)
        this.imgChateau1=this.icoChateau1.getImage();

        this.icoDepart = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"depart.jpg");// quand on créer le fichier jar , le chemin change)
        this.imgDepart=this.icoDepart.getImage();

        //for(int i=0; i<8 ;i++){
           // tuyauRouge$i = new TuyauRouge(600,230, 800,300);
        //}

        this.tuyauRouge1 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge1X,tuyauRougeY);
        this.tuyauRouge2 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge2X,tuyauRougeY);
        this.tuyauRouge3 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge3X,tuyauRougeY);
        this.tuyauRouge4 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge4X,tuyauRougeY);
        this.tuyauRouge5 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge5X,tuyauRougeY);
        this.tuyauRouge6 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge6X,tuyauRougeY);
        this.tuyauRouge7 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge7X,tuyauRougeY);
        this.tuyauRouge8 = new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge8X,tuyauRougeY);




        this.bloc1 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX1,blocY );
        this.bloc2 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX2,blocY);
        this.bloc3 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX3,blocY );
        this.bloc4 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX4,blocY );
        this.bloc5 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX5,blocY);
        this.bloc6 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX6,blocY );
        this.bloc7 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX7,blocY );
        this.bloc8 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX8,blocY);
        this.bloc9 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX9,blocY);
        this.bloc10 = new Bloc(imageBlocLarge,imageBlocHauteur, blocX10,blocY );



        this.piece1 = new Piece(imagePieceX,imagePieceY,pieceX1,pieceY);
        this.piece2 = new Piece(imagePieceX,imagePieceY,pieceX2,pieceY);
        this.piece3 = new Piece(imagePieceX,imagePieceY,pieceX3,pieceY);
        this.piece4 = new Piece(imagePieceX,imagePieceY,pieceX4,pieceY);
        this.piece5 = new Piece(imagePieceX,imagePieceY,pieceX5,pieceY);
        this.piece6 = new Piece(imagePieceX,imagePieceY,pieceX6,pieceY);
        this.piece7 = new Piece(imagePieceX,imagePieceY,pieceX7,pieceY);
        this.piece8 = new Piece(imagePieceX,imagePieceY,pieceX8,pieceY);
        this.piece9 = new Piece(imagePieceX,imagePieceY,pieceX9,pieceY);
        this.piece10 = new Piece(imagePieceX,imagePieceY,pieceX10,pieceY);




        this.tabDecor.add(this.tuyauRouge1);
        this.tabDecor.add(this.tuyauRouge2);
        this.tabDecor.add(this.tuyauRouge3);
        this.tabDecor.add(this.tuyauRouge4);
        this.tabDecor.add(this.tuyauRouge5);
        this.tabDecor.add(this.tuyauRouge6);
        this.tabDecor.add(this.tuyauRouge7);
        this.tabDecor.add(this.tuyauRouge8);



        this.tabDecor.add(this.bloc1);
        this.tabDecor.add(this.bloc2);
        this.tabDecor.add(this.bloc3);
        this.tabDecor.add(this.bloc4);
        this.tabDecor.add(this.bloc5);
        this.tabDecor.add(this.bloc6);
        this.tabDecor.add(this.bloc7);
        this.tabDecor.add(this.bloc8);
        this.tabDecor.add(this.bloc9);
        this.tabDecor.add(this.bloc10);


        this.tabPiece.add(this.piece1);
        this.tabPiece.add(this.piece2);
        this.tabPiece.add(this.piece3);
        this.tabPiece.add(this.piece4);
        this.tabPiece.add(this.piece5);
        this.tabPiece.add(this.piece6);
        this.tabPiece.add(this.piece7);
        this.tabPiece.add(this.piece8);
        this.tabPiece.add(this.piece9);
        this.tabPiece.add(this.piece10);










        this.icoSortie = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"sortie.jpg");
        this.imgSortie = this.icoSortie.getImage();

        this.icoDrapeuFin = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"drpeauFin.jpg");
        this.imgDrapeauFin = this.icoDrapeuFin.getImage();












        /*mario*/
        mario = new Mario(imageMarioLarge,imageMarioHauteur,marioX,marioY);
        //icoMario = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"personnage.jpg");// quand on créer le fichier jar , le chemin change)
        //this.imgMario=this.icoMario.getImage();
        /*fin mario*/


        //fin association

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addKeyListener(new Clavier());
        score = new Score();
        compteARebours = new CompteARebour();



        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();

    }






    // GETTERS//

    public int getDx() {return dx; }

    public int getxPos() {return xPos;}

    public int getYSol() { return ysol;  }

    public int getHauteurPlafond() {
        return hauteurPlafond;
    }

    //SETTERS//


    public void setYsol(int ysol) { this.ysol = ysol; }

    public void setHauteurPlafond(int hauteurPlafond) {this.hauteurPlafond = hauteurPlafond;}

    public void setDx(int dx) {this.dx = dx;}

    public void setxPos(int xPos) {this.xPos = xPos;}

    public void setxFond1(int xFond1) {this.xFond1 = xFond1; }

    public void setxFond2(int xFond2) {this.xFond2 = xFond2; }


    /////////////////////////////////////////////////////////
                                    //Méthodes//
    /////////////////////////////////////////////////////////

    public void deplacementFond(){
        //this.xFond1=this.xFond1 - this.dx;
        //this.xFond2=this.xFond2 - this.dx;
      if(this.xPos>=0 && this.xPos <= Main.scene.posMAX) {
          this.xPos = this.xPos + this.dx;
          this.xFond1 = this.xFond1 - this.dx;
          this.xFond2 = this.xFond2 - this.dx;
      }



        if(this.xFond1==-1300){this.xFond1=1300;}
        else if(this.xFond2==-1300){this.xFond2=1300;}
        else if(this.xFond1==1300){this.xFond1=-1300;}
        else if(this.xFond2==1300){this.xFond2=-1300;}



    }
        //&& (this.score.getNbrPiece() ==10)
    private boolean partieGagnee(){
        if((this.compteARebours.getCompteurTemps() > 0) && (this.mario.isVivant() ) && (this.xPos >=posMAX)){
                return (true);
        }
        else {return (false);}
    }


    private boolean partiePerdue(){
        if(this.compteARebours.getCompteurTemps()<= 0 || !this.mario.isVivant() ){
            return (true);
        }
        else {return (false);}
    }

    public boolean finPartie(){
        if(this.partieGagnee()|| this.partiePerdue()){return true;}
        else{return false;}
    }









    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;

        //detection contact

        for(int i=0; i< this.tabDecor.size();i++) {
            if(this.mario.proche(this.tabDecor.get(i))){
                this.mario.contact(this.tabDecor.get(i));
                this.mario.fly(this.tabDecor.get(i));

            }


        }
 /*
        for(int i=0; i< this.tabPiece.size();i++) {
            if(this.mario.proche(this.tabPiece.get(i))){
                if(this.mario.contactPiece(this.tabPiece.get(i))){
                    this.tabPiece.remove(this.tabPiece.get(i));
                    this.score.compteurPiece();


                }
            }
        }*/

        this.deplacementFond();

        //Mise à jour des scores
        g2.setFont(police);
        g2.drawString("Pièces trouvées : " + this.score.getNbrPiece() + "/" + this.score.getNombPieceTotal(),440,25 );



        //détection des contacts de mario avec des pieces
        /*for(int i=0; i< this.tabPiece.size() ; i++){
            if(this.mario.proche(this.tabPiece.get(i))){
                if(this.mario.contactPiece(this.tabPiece.get(i))){
                    this.tabPiece.remove(i);
                    this.score.setNbrPiece(this.score.getNbrPiece()+1);
                }
            }
        }



        //detection contact

        for(int i=0; i< this.tabDecor.size();i++) {
            //mario
            if (this.mario.contactAvant(this.tabDecor.get(i))) {
                this.mario.contactAvant(this.tabDecor.get(i));
            }
        }
            //proche et pas contact
        //fin détection
        // on met la détection avant sinon il y a des problèmes de syncronisations
*/



        if(this.xPos >=0 && this.xPos <= 5000){
            for(int i=0; i<this.tabDecor.size();i++){
                this.tabDecor.get(i).deplacement();
            }
            for(int i=0; i<this.tabPiece.size();i++){
                this.tabPiece.get(i).deplacement();
            }
        }





        g2.drawImage(this.imgFond1,this.xFond1,0,null);
        g2.drawImage(this.imgFond2,this.xFond2,0,null);


        g2.drawImage(this.imgChateau1,5-this.xPos,chateauY,null);
        g2.drawImage(this.imgDepart,220-this.xPos,departY,null);



           //image mario
        if(this.mario.isVivant){


            if(this.mario.isSaut()){g2.drawImage(this.mario.saute(),this.mario.getX(),this.mario.getY(),null);}
            else {g2.drawImage(this.mario.marche("personnage",25),this.mario.getX(),this.mario.getY(),null);}

        }
        else{g2.drawImage(this.mario.meurt(),this.mario.getX(),this.mario.getY(),null); }



        // Images du décor

        for(int i=0; i< this.tabDecor.size();i++){
            g2.drawImage(this.tabDecor.get(i).getImgDecor(),this.tabDecor.get(i).getX(),this.tabDecor.get(i).getY(),null);

        }

        /*//image piece
        for(int i=0; i< this.tabPiece.size();i++){
            g2.drawImage(this.tabPiece.get(i).getImgDecor(),this.tabPiece.get(i).getX(),this.tabPiece.get(i).getY(),null);

        }*/



        //image du drapeau d'arrivée
        g2.drawImage(imgDrapeauFin,posMAX-this.xPos,imgDrapeauFinY,null);
        g2.drawImage(imgSortie,posMAX-this.xPos,imgSortieY,null);


        //Compte  à rebours
        g2.drawString(this.compteARebours.getStr(), 5 , 25);

        //Nombre
        g2.drawString(this.score.getNbrPiece() + " pièces trouvées sur 10 ", 650 , 25);

        //fin de la partie

        if(this.finPartie()){
            Font policeFin = new Font("Arial",Font.BOLD ,50);
            g2.setFont(policeFin);
            if(this.partieGagnee()){
                g2.drawString("Vous avez gagné",120,180);
                Audio.playSound("/son/gagne.wav");

            }

            else if(this.partiePerdue())

            {g2.drawString("vous avez perdu...",120,180);
                Audio.playSound("/son/perdu.wav");

            }
        }


        }




}
