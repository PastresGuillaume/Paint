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
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                    //Création des donnees membres//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///Initialisation des ArrayList
    /////////////////////////////
    public final ArrayList<Decor> tabDecor=new ArrayList<Decor>();
   private final ArrayList<Piece> tabPiece=new ArrayList<Piece>();// tableau des objets du jeu

    //Initialisation du Parcourt
    /////////////////////////////



    public int repetitionMotif=9;

    public int tuyauRougeY=420;


    public int blocX1=400;
    public int tuyauRouge1X=700;
    public  int posMAX = (repetitionMotif+1)*600+tuyauRouge1X+700;

    public int blocY=290;

    public int pieceY=blocY-45;

    public int chateauX=5;
    public int chateauY=275;

    public int departX=220;
    public int departY=365;

    public int imgDrapeauFinY=300;// Pas de X , car on utilise la posMAX ,
    public int imgSortieY=370;// de meme

    //Personnage
    /////////////////////////////
    public int marioX=300;
    public int marioY=395;//385
    public int marioY0=385;
    public Mario mario;


    //Dimension des images
    /////////////////////////////
    int imageMarioLarge =108;
    int imageMarioHauteur = 170;

    int imageTuyauRLarge =106;
    int imageTuyauRHauteur = 200;

    int imageBlocLarge =95;
    int imageBlocHauteur = 94;

    int imagePieceX =40;
    int imagePieceY = 40;



    //Variable Image
    /////////////////////////////
    private ImageIcon icoDrapeuFin;
    private Image imgDrapeauFin;
    private ImageIcon icoSortie;
    private Image imgSortie;

    //fond
    private final ImageIcon icoFond;
    private final Image imgFond1;
    private final Image imgFond2;

    //départ
    private final ImageIcon icoChateau1;
    private final Image imgChateau1;
    private final ImageIcon icoDepart;
    private final Image imgDepart;





    //Variable Bonus
    /////////////////////////////
    private CompteARebour compteARebours;
    private Score score;
    private Font police= new Font("Arial",Font.PLAIN ,10);



    // Variable du Paysage
    /////////////////////////////
    private int xFond2;
    private int xPos;
    private int dx;
    private int xFond1;//abscice du coin sup gauche par rapport à lecran
    private int ysol;//hauteur du sol
    private int hauteurPlafond;



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        this.icoSortie = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"sortie.jpg");
        this.imgSortie = this.icoSortie.getImage();

        this.icoDrapeuFin = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"drpeauFin.jpg");
        this.imgDrapeauFin = this.icoDrapeuFin.getImage();


        //Instanciation du decor
        /////////////////////////////
        this.AbscisseDecor();


        /*
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX1,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX2,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX3,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX4,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX5,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX6,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX7,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX8,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX9,pieceY));
        this.tabPiece.add(new Piece(imagePieceX,imagePieceY,pieceX10,pieceY));

        */




        //Instanciation Personnage + clavier et fenetre
        ////////////////////////////////////////////////////////////
        mario = new Mario(imageMarioLarge,imageMarioHauteur,marioX,marioY);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        //Bonus
        /////////////////////////////
        score = new Score();
        compteARebours = new CompteARebour();

        //Instanciation du thread (Attention ne pas déplacer sinon rien ne va plus)
        /////////////////////////////
        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                     //Fin du constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                            // GETTERS//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getDx() {return dx; }

    public int getxPos() {return xPos;}

    public int getHauteurPlafond() {return hauteurPlafond; }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        // SETTERS//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void setYsol(int ysol) { this.ysol = ysol; }

    public void setHauteurPlafond(int hauteurPlafond) {this.hauteurPlafond = hauteurPlafond;}

    public void setDx(int dx) {this.dx = dx;}

    public void setxPos(int xPos) {this.xPos = xPos;}

    public void setxFond1(int xFond1) {this.xFond1 = xFond1; }

    public void setxFond2(int xFond2) {this.xFond2 = xFond2; }

    public void setMarioY(int marioY) {this.marioY = marioY; }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                //Méthodes//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Initialisation du décor
    /////////////////////////////

    public void AbscisseDecor(){
        //this.tabNomTRValeurX.add(tuyauRouge1X);
        int A= 0;

        for(int i=0; i<repetitionMotif-1;i++) {

            A = +i*900;

            this.tabDecor.add(new Bloc(imageBlocLarge,imageBlocHauteur,blocX1+ A,blocY ));
            //this.tabDecor.add(new Piece(imagePieceX,imagePieceY,blocX1+A+20,pieceY));
            this.tabDecor.add( new TuyauRouge(imageTuyauRLarge,imageTuyauRHauteur, tuyauRouge1X + A,tuyauRougeY));
            this.tabDecor.add(new Bloc(imageBlocLarge,imageBlocHauteur, blocX1+600+ A,blocY ));
            // this.tabDecor.add(new Piece(imagePieceX,imagePieceY,blocX1+ A+620,pieceY));

        }

    }



    //déplacementFond(
    /////////////////////////////
    public void deplacementFond(){

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

    //Partie gagnée ou non
    /////////////////////////////
    private boolean partieGagnee(){
        if((this.compteARebours.getCompteurTemps() > 0) && (this.mario.isVivant() ) && (this.xPos >=posMAX)){ return (true); }
        else {return (false);}
    }

    private boolean partiePerdue(){
        if(this.compteARebours.getCompteurTemps()<= 0 || !this.mario.isVivant() ){ return (true); }
        else {return (false);}
    }

    public boolean finPartie(){
        if(this.partieGagnee()|| this.partiePerdue()){return true;}
        else{return false;}
    }







    //PaintComponent
    /////////////////////////////

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;

        //detection contact

        for(int i=0; i< this.tabDecor.size();i++) {
            if(this.mario.proche(this.tabDecor.get(i))){
                this.mario.contact(this.tabDecor.get(i),i);
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
        /////////////////////////////

        g2.setFont(police);
        g2.drawString("Pièces trouvées : " + this.score.getNbrPiece() + "/" + this.score.getNombPieceTotal(),440,25 );

        //Mise à jour des positions des objets
        //////////////////////////////////////////////////////////
        if(this.xPos >=0 && this.xPos <= posMAX){
            for(int i=0; i<this.tabDecor.size();i++){
                this.tabDecor.get(i).deplacement();
            }
            for(int i=0; i<this.tabPiece.size();i++){
                this.tabPiece.get(i).deplacement();
            }
        }




        //dessin du fond
        /////////////////////////////
        g2.drawImage(this.imgFond1,this.xFond1,0,null);
        g2.drawImage(this.imgFond2,this.xFond2,0,null);


        g2.drawImage(this.imgChateau1,chateauX-this.xPos,chateauY,null);
        g2.drawImage(this.imgDepart,departX-this.xPos,departY,null);



        //dessin du personnage
        /////////////////////////////

        if(this.mario.isVivant){
            if(this.mario.isSaut()){g2.drawImage(this.mario.saute(),this.mario.getX(),this.mario.getY(),null);}
            else {g2.drawImage(this.mario.marche("personnage",25),this.mario.getX(),this.mario.getY(),null);}
        }
        else{g2.drawImage(this.mario.meurt(),this.mario.getX(),this.mario.getY(),null); }



        // Images du décor
        /////////////////////////////

        for(int i=0; i< this.tabDecor.size();i++){
            g2.drawImage(this.tabDecor.get(i).getImgDecor(),this.tabDecor.get(i).getX(),this.tabDecor.get(i).getY(),null);

        }

        /*//image piece
        for(int i=0; i< this.tabPiece.size();i++){
            g2.drawImage(this.tabPiece.get(i).getImgDecor(),this.tabPiece.get(i).getX(),this.tabPiece.get(i).getY(),null);

        }*/



        //image du drapeau d'arrivée
        g2.drawImage(imgDrapeauFin,posMAX-this.xPos,imgDrapeauFinY,null);
        g2.drawImage(imgSortie,posMAX+300-this.xPos,imgSortieY,null);

        //Bonus
        /////////////////////////////
        g2.drawString(this.compteARebours.getStr(), 5 , 25);
        g2.drawString(this.score.getNbrPiece() + " pièces trouvées sur 10 ", 650 , 25);


        //fin de la partie
        /////////////////////////////
        if(this.finPartie()){
            Font policeFin = new Font("Arial",Font.BOLD ,50);
            g2.setFont(policeFin);
            if(this.partieGagnee()){
                g2.drawString("Vous avez gagné",120,180);
                Audio.playSound("/son/gagne.wav");

            }

            else if(this.partiePerdue()){
                g2.drawString("vous avez perdu...",120,180);
                Audio.playSound("/son/perdu.wav");
            }
        }


        }




}
