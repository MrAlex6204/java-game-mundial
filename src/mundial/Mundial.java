package mundial;

import com.sun.org.apache.bcel.internal.Constants;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Mundial {
    
    public static int respuestasCorrectas = 0;
    public static int respuestasIncorrectas = 0;
    public static int puntosPorRespuesta =0;
    public static int totalContestadas = 0;
    
    public class Pregunta {

        private String _pregunta = "";
        private int _respuesta = 0;
        private String _opcion1 = "";
        private String _opcion2 = "";
        private String _opcion3 = "";
        private String _opcion4 = "";

        private boolean _fueContestada = false;
        private boolean _estaCorrecta = false;

        public Pregunta(String Pregunta, int Respuesta, String Opcion1, String Opcion2, String Opcion3, String Opcion4) {
            _pregunta = Pregunta;
            _respuesta = Respuesta;
            _opcion1 = Opcion1;
            _opcion2 = Opcion2;
            _opcion3 = Opcion3;
            _opcion4 = Opcion4;
        }

        public boolean fueContestada() {
            return _fueContestada;
        }
        
        public boolean estaCorrecta(){
            return _estaCorrecta;
        }

        public boolean preguntar() {            
            String opcionSeleccionada;
            
             _fueContestada = true;//Indicamos que esta pregunta ya fue contestada por el jugador.                     
            opcionSeleccionada = JOptionPane.showInputDialog(_pregunta
                                + "\nIngrese su Respuesta:\n1. "
                                + _opcion1 + "\n2. "
                                + _opcion2 + "\n3."
                                + _opcion3 + "\n4."
                                + _opcion4);
            
            //Verificamos si selecciono la respuesta correcta.
            _estaCorrecta = (Integer.parseInt(opcionSeleccionada) == _respuesta);            
           
            return _estaCorrecta;
        }

    }

    //Esta funcion nos crear un objeto de una pregunta.
    public static Mundial.Pregunta crearPregunta(String TuPregunta, int Respuesta, String Opcion1, String Opcion2, String Opcion3, String Opcion4) {
        Mundial miMundial = new Mundial();
        Mundial.Pregunta miPregunta = miMundial.new Pregunta(TuPregunta, Respuesta, Opcion1, Opcion2, Opcion3, Opcion4);
        return miPregunta;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        //Creamos un array de 10 preguntas
        Mundial.Pregunta[] misPreguntas = new Mundial.Pregunta[10];
        boolean salirDelJuego = false;
        
        //Llenamos el array con cada pregunta que queramos.
        misPreguntas[0] = crearPregunta("¿Quien ganó la copa mundial en 2010?", 1,
                            "España", "Francia","Alemania","Uruguay");
        misPreguntas[1] = crearPregunta("¿Quien obtuvo el balon de oro en el mundial de 2014?", 2,
                            "Cristiano Ronaldo", "Diego Forlan","Lionel Messi","David Beckham");                           
        misPreguntas[2] = crearPregunta("¿Cual es la longitud (en metros) de los arcos usados en el mundial 2014?",1
                           ,"7,32","10,23","8,49","4,57");        
        misPreguntas[3] = crearPregunta("¿En que año nacio Cristiano Ronaldo?",3,
                            "1987","1991","1985","1976");
        misPreguntas[4] = crearPregunta("¿En que estadio Uruguay le gano a brasil logrando ser campeones del mundo?",3,
                            "1946","1956","1950","1948");
        misPreguntas[5] = crearPregunta("¿Actualmente quien lleva el apodo de El pistolero uruguayo?",2,        
                            "Diego Forlan","Luis Suarez","Diego Lugano","Edinson Cavani");
        misPreguntas[6] = crearPregunta("¿Quien fue el director tecnico de la seleccion uruguaya en el año 2010?",1,
                            "Washintong Tabarez","Jorge Da Silva","Marcelo Gallardo","Manuel Keosseian");        
        misPreguntas[7] = crearPregunta("¿Como se llama el estadio en donde se jugo la final del Mundial 2014?",4,
                            "Arena de São Paulo","Estadio Nacional","Estadio das Dunas","Maracana");
        misPreguntas[8] = crearPregunta("¿En donde se jugo la primera Copa Mundial?",4,
                            "Brasil","Argentina","España","Uruguay");
        misPreguntas[9] = crearPregunta("¿Cual es el nombre completo de Messi?",3,
                            "Lionel Sebastian Messi Vazquez","Lionel Santiago Messi Ficolini","Lionel Andrés Messi Cuccittini","Lionel Messi Silvera");             
                
        
        while(!salirDelJuego){
            // usamos un arreglo de opciones para los botones
            Object[] options = {"1-Jugar", "2-Descripcion", "3-Puntajes", "4-Creditos", "5 - Salir"};
            //Mostrar Menu.
            int opcionDeMenu = JOptionPane.showOptionDialog(null, "Menu de opciones\n", "Trivia Futbol", JOptionPane.DEFAULT_OPTION,
                                 JOptionPane.INFORMATION_MESSAGE, null, options, options[0]); 
            
            switch(opcionDeMenu){//Opciones del Menu
                
                case 0://Iniciar juego;
                    int aleatorio = 0;                   
                    
                    Mundial.respuestasCorrectas = 0;
                    Mundial.respuestasIncorrectas = 0;
                    Mundial.totalContestadas = 0;
                    Mundial.puntosPorRespuesta = 10;
                    
                    do{
                     //Seleccionamos un numero del 1 al 10 al azar
                        do{
                            aleatorio = 1 + (int) (Math.random() * (misPreguntas.length - 1));
                        }while(!(!misPreguntas[aleatorio].fueContestada() & Mundial.totalContestadas != 10));
                        
                     //Verificar que no haya sido contestada anterior mente.
                     if(!misPreguntas[aleatorio].fueContestada()){
                         
                         misPreguntas[aleatorio].preguntar();//Mostrar pegunta;
                         
                         if(misPreguntas[aleatorio].estaCorrecta()){
                             //Seguir preguntado solo en caso que haya sido contestada correctamente.
                             Mundial.respuestasCorrectas++;
                         }else{
                             Mundial.respuestasIncorrectas++;
                             break;//Salir por que esta incorrecta la pregunta.
                         }
                         
                         Mundial.totalContestadas++;
                     }
                     
                     }while(Mundial.totalContestadas != 10);
                     
                    
                    
                    JOptionPane.showMessageDialog(null, "¡Usted ha anotado "
                    + (Mundial.respuestasCorrectas*Mundial.puntosPorRespuesta) + " puntos!");
                    
                break;
                    
                case 1:
                    JOptionPane.showMessageDialog(null, "Descripción aquí");
                    break;
                    
                case 2:
                    JOptionPane.showMessageDialog(null, "Puntaje mas alto:"+(Mundial.puntosPorRespuesta+Mundial.respuestasCorrectas));
                    break;
                    
                case 3:
                    JOptionPane.showMessageDialog(null, "Créditos aquí");
                    break;
                    
                case 4:
                    salirDelJuego = true;
                    break;
            }
           
        }
        
    }

}
