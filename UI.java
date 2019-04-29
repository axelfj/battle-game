/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package proyecto0;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author JosePablo
 */
public class UI {

    /**
     * @param args the command line arguments
     */
    private static boolean cargar = false;
    private static boolean guardar = false;
    private static boolean terminoJuego = false;

    //Recibe como parametro todo lo que se desea guardar
    public static void guardarPartida(int numeroPartida, Player p1, Player p2){        
        if (guardar == true){
            if (numeroPartida < 1 || numeroPartida > 3)
                    System.out.println("Maximum amount of saves reached!");            
            else{
                FileWriter partidaGuardada;
                String nombre = "";
                if (numeroPartida == 1){
                    nombre = "savedGame1.txt";
                }
                if (numeroPartida == 2){
                    nombre = "savedGame2.txt";
                }
                if (numeroPartida == 3){
                    nombre = "savedGame3.txt";
                }
                try{
                    partidaGuardada = new FileWriter(nombre);
                    partidaGuardada.write(p1.toString());
                    partidaGuardada.write(p2.toString());
                    
                    partidaGuardada.close();
                } catch (IOException e){
                    System.out.println(e);
                }   
            }
        }
    }
    public static void cargarPartida(int numeroPartida, int tamanoTablero){
        if (cargar == true){
            if (numeroPartida < 1 || numeroPartida > 3)
                    System.out.println("Invalid saved game!");            
            else{
                FileReader partidaGuardada;
                String nombre = "";
                if (numeroPartida == 1){
                    nombre = "savedGame1.txt";
                }
                if (numeroPartida == 2){
                    nombre = "savedGame2.txt";
                }
                if (numeroPartida == 3){
                    nombre = "savedGame3.txt";
                }
                try{
                    partidaGuardada = new FileReader(nombre);
                    char[] contenido = new char[500+tamanoTablero*2*tamanoTablero];
                    partidaGuardada.read(contenido);
                    
                    System.out.println(contenido);
                
                } catch(IOException e){
                    System.out.println(e);
                }
            }    
        }
    }
            
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.print("Desea cargar una partida anterior? (1 = y; 0 = n): ");
        int Cargar = reader.nextInt();

        if(Cargar == 1) {
            cargar = true;
            reader = new Scanner(System.in);
            System.out.print("Ingrese el tamano del tablero (es cuadrado): ");
            int partida = reader.nextInt();

            reader = new Scanner(System.in);
            System.out.print("Ingrese el tamano del tablero (es cuadrado): ");
            int tablero = reader.nextInt();

            cargarPartida(partida, tablero);
        }else {
            System.out.println("New game!");

            reader = new Scanner(System.in);
            System.out.print("Ingrese el tamano del tablero (es cuadrado): ");
            int Columnas = reader.nextInt();

            Field tablero = new Field(Columnas);

            int OroRestante = Columnas*3;
            System.out.println("El maximo oro que puede gastar cada jugador es de: "+ OroRestante);

            int Arqueros1 = 1024;
            int Lanceros1 = 1024;
            int Caballerias1 = 1024;

            reader = new Scanner(System.in);
            System.out.print("Jugador 1" + '\n' + "Ingrese su nombre: ");
            String nombre1 = reader.next();
            Player p1 = new Player(nombre1, tablero);

            while (!p1.createArmy(Arqueros1, Caballerias1, Lanceros1))
            {
                System.out.println();
                reader = new Scanner(System.in);
                System.out.print("Arqueros(su costo es " + Arquero.getCost() + ") : ");
                Arqueros1 = reader.nextInt();

                OroRestante-= Arqueros1*Arquero.getCost();
                System.out.println("El oro restante es: " + OroRestante);


                reader = new Scanner(System.in);
                System.out.print("Lanceros (su costo es " + Lancero.getCost() + ") : ");
                Lanceros1 = reader.nextInt();

                OroRestante-= Lanceros1*Lancero.getCost();
                System.out.println("El oro restante es: " + OroRestante);


                reader = new Scanner(System.in);
                System.out.print("Caballerias (su costo es " + Caballeria.getCost() + ") : ");
                Caballerias1 = reader.nextInt();

                OroRestante-= Lanceros1*Lancero.getCost();
                System.out.println("El oro restante es: " + OroRestante);
                OroRestante = p1.getMaxCost();
            }

            p1.setPlayfield(tablero);

            reader = new Scanner(System.in);
            System.out.print("Jugador 2" + '\n' + "Ingrese su nombre: ");
            String nombre2 = reader.next();
            Player p2 = new Player(nombre2, tablero);

            OroRestante = Columnas*3;


            int Arqueros2 =1024;
            int Lanceros2 = 1024;
            int Caballerias2 = 1024;

            while (!p2.createArmy(Arqueros2, Lanceros2, Caballerias2)) {
                reader = new Scanner(System.in);
                System.out.print("Arqueros(su costo es " + Arquero.getCost() + ") : ");
                Arqueros2 = reader.nextInt();

                OroRestante-= Arqueros1*Arquero.getCost(); // disminuye la cantidad de oro ya que compro arq
                System.out.println("El oro restante es: " + OroRestante); // despliega la cantidad de oro que le queda


                reader = new Scanner(System.in);
                System.out.print("Lanceros (su costo es " + Lancero.getCost() + ") : ");
                Lanceros2 = reader.nextInt();

                OroRestante-= Lanceros1*Lancero.getCost(); // disminuye la cantidad de oro ya que compro arq
                System.out.println("El oro restante es: " + OroRestante); // despliega la cantidad de oro que le queda


                reader = new Scanner(System.in);
                System.out.print("Caballerias (su costo es " + Caballeria.getCost() + ") : ");
                Caballerias2 = reader.nextInt();

                OroRestante-= Lanceros1*Lancero.getCost(); // disminuye la cantidad de oro ya que compro arq
                System.out.println("El oro restante es: " + OroRestante); // despliega la cantidad de oro que le queda
            }

            p2.setPlayfield(tablero);



            int turn = 0;
            while (!p1.lost() && !p2.lost()){
                if (turn % 2 ==0){
                    p1.play();
                }else {
                    p2.play();
                }
                turn++;
                reader = new Scanner(System.in);
                System.out.print("Desea continuar (1) o guardar(0): ");
                int Guardar = reader.nextInt();
                if (Guardar == 0){
                    guardar = true;
                    reader = new Scanner(System.in);
                    System.out.print("Seleccione el numero de partida: ");
                    int partida = reader.nextInt();

                    guardarPartida(partida, p1, p2);
                    break;
                }
            }
            if(p1.lost()){
                System.out.println("Gano el jugador 2");
            } else if(p2.lost()){
                System.out.println("Gano el jugador 1");
            }


        }
    }
    
}
