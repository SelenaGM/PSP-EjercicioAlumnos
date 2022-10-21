import models.EstudianteModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        //DOS HIJOS, UNO LEE LO QUE HAY EN UN FICHERO SOBRE ALUMNOS Y EL OTRO ESCRIBE EL FICHERO, añadir alumno al lista, cargar datos y guardar datos.
        //El padre le pasa el nombre del fichero a leer o a escribir, todo con -jars
        Scanner sc = new Scanner(System.in);
        ArrayList<EstudianteModel> listaEstudiante = new ArrayList<>();
        File destino = new File("AlumnosFichero.txt");
        ProcessBuilder proceso = null;
        int opcion;
        do {
            try {
                opcion = menu(sc);
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        anyadirAlumno(sc, listaEstudiante);
                    for (EstudianteModel e: listaEstudiante) {
                        System.out.println(e);
                    }
                        break;
                    case 2:
                        //System.out.println("Dime el nombre del fichero");
                        //try{
                        //guardarDatosAlumno(listaEstudiante,sc.nextLine());
                        //}catch(){}
                        proceso = new ProcessBuilder("java", "-jar", "addAlumno.jar");
                        guardarDatosAlumno( destino, listaEstudiante, proceso);
                        break;
                    case 3:
                        proceso = new ProcessBuilder("java", "-jar", "cargarAlumno.jar");
                        //System.out.println("Dime el nombre del fichero");
                        //try{
                        //cargarDatosAlumno(listaEstudiante,sc.nextLine());
                        //}catch(){}
                        cargarDatosAlumno(destino ,proceso, listaEstudiante);
                        break;
                    case 4:
                        muestraAlumnos(listaEstudiante);
                        break;
                    default:
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }while(opcion!=4);

    }

    private static void muestraAlumnos(ArrayList<EstudianteModel> listaEstudiante) {

        for(EstudianteModel a: listaEstudiante){
           System.out.println(a.toString());
        }

    }

    private static void cargarDatosAlumno(File destino, ProcessBuilder proceso, ArrayList<EstudianteModel> listaEstudiante) throws IOException {
        /*listaEstudiante.clear();

        proceso.redirectErrorStream(true);
        Process hijo = proceso.start();

        OutputStream ops = hijo.getOutputStream();
        PrintStream ps = new PrintStream(ops);

        ps.println(destino);
        ps.flush();

        //AHORA A LEER
        InputStream is = hijo.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader br = new BufferedReader(isr);
        while(br.readLine()!= null){
            String nombre = br.readLine();
            String apellido = br.readLine();
            String dni = br.readLine();
            EstudianteModel estudiante = new EstudianteModel(nombre, apellido, dni);
            listaEstudiante.add(estudiante);
        }

        for (EstudianteModel e: listaEstudiante) {
            System.out.println(e);
        }*/

        //SOLUCION EDU

        String fileName = null;
        listaEstudiante.clear();
        ProcessBuilder processBuilder = new ProcessBuilder("java","-jar","librerias/cargarAlumno");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        OutputStream os = process.getOutputStream(); //ESTO ES PARA ESCRIBIR
        PrintStream ps = new PrintStream(os);

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8); //el utf es para temas de codificacion
        BufferedReader br = new BufferedReader(isr);

        ps.println(destino);
        ps.flush();


        if((new File(fileName)).exists()){
            listaEstudiante.clear();

        }

        String linea;

        while(!((linea = br.readLine()).isEmpty())){
            listaEstudiante.add(new EstudianteModel(linea));
        }



    }

    private static void guardarDatosAlumno(File destino, ArrayList<EstudianteModel> listaEstudiante, ProcessBuilder proceso) throws IOException {



        for (int i = 0; i < listaEstudiante.size(); i++) {

            proceso.redirectErrorStream(true);
            Process hijo = proceso.start();

            OutputStream ops = hijo.getOutputStream();
            PrintStream ps = new PrintStream(ops);

            ps.println(destino);
            ps.flush();
            ps.println(listaEstudiante.get(i).toFile());
            ps.flush();

        }




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