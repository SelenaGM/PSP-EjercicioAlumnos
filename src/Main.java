import models.EstudianteModel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        //DOS HIJOS, UNO LEE LO QUE HAY EN UN FICHERO SOBRE ALUMNOS Y EL OTRO ESCRIBE EL FICHERO, añadir alumno al lista, cargar datos y guardar datos.
        //El padre le pasa el nombre del fichero a leer o a escribir, todo con -jars
        Scanner sc = new Scanner(System.in);
        ArrayList<EstudianteModel> listaEstudiante = new ArrayList<>();
        File destino = new File("AlumnosFichero");
        ProcessBuilder proceso = null;
        int opcion;
        do {
            try {
                opcion = menu(sc);
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        anyadirAlumno(sc, listaEstudiante);
                    /*for (EstudianteModel e: listaEstudiante) {
                        System.out.println(e);
                    }*/
                        break;
                    case 2:
                        proceso = new ProcessBuilder("java", "-jar", "addAlumno.jar");
                        guardarDatosAlumno(destino, listaEstudiante, proceso);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }while(opcion!=4);

    }

    private static void guardarDatosAlumno(File destino, ArrayList<EstudianteModel> listaEstudiante, ProcessBuilder proceso) throws IOException {
        proceso.redirectErrorStream(true);
        Process hijo = proceso.start();

        OutputStream ops = hijo.getOutputStream();
        PrintStream ps = new PrintStream(ops);

        ps.println(destino);
        ps.flush();
        ps.println(listaEstudiante);
        ps.flush();


    }

    private static void anyadirAlumno(Scanner sc, ArrayList<EstudianteModel> listaEstudiante) {
        System.out.println("Introduce los datos del alumno");
        System.out.println("Nombre:");
        String nombre = sc.nextLine();
        System.out.println("Apellido:");
        String apellido = sc.nextLine();
        System.out.println("Dni:");
        String dni = sc.nextLine();
        EstudianteModel estudiante = new EstudianteModel(nombre,apellido,dni);
        listaEstudiante.add(estudiante);
    }

    private static int menu(Scanner sc) {
        System.out.println("Elige una opción");
        System.out.println("1-Añadir un alumno");
        System.out.println("2-Guardar datos en el fichero");
        System.out.println("3-Cargar datos en la lista");
        System.out.println("4-Salir");
        return sc.nextInt();
    }
}