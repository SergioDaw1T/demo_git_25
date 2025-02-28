package Proyecto;

import java.util.Scanner;

public class Proyecto {

	//Tamaño máximo de array
	static final int max = 100;
	static String[] nombres =  new String[max];
	static String[] usuarios =  new String[max];
	static String[] pass =  new String[max];
	static String[] eventos =  new String[max];
	static String[] fechas =  new String[max];
	static String[] duracionEvento =  new String[max];
	static int[] inscripcionesUsuarios = new int[max];
	static int[] inscripcionesEventos = new int[max];
	static int contadorUsuarios = 0;
	static int contadorEventos = 0;
	static int contadorInscripciones = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion;
		
		do {
			System.out.println("----------------------------------");
			System.out.println("Portal de Eventos");
			System.out.println("1. Registrar usuario");
			System.out.println("2. Crear evento");
			System.out.println("3. Inscribirse en evento");
			System.out.println("4. Mostrar usuarios");
			System.out.println("5. Mostrar eventos");
			System.out.println("6. Mostrar inscripciones");
			System.out.println("7. Salir");
			System.out.println("----------------------------------");
			System.out.println("Elige una opción: ");
			opcion = sc.nextInt();
			//esto limpia el buffer para que no de problemas
			sc.nextLine();
			
			switch (opcion) {
				case 1:
					registrarUsuario(sc, nombres,usuarios, pass);
					break;
				case 2:
					crearEvento(sc, eventos, fechas, duracionEvento);
					break;
				case 3:
					inscripcionesEventos(sc);
					break;
				case 4:
					mostrarUsuarios();
					break;
				case 5:
					mostrarEventos();
					break;					
				case 6:
					mostrarInscripciones();
					break;
				case 7:
					System.out.println("Saliendo del portal.");
					break;
				default:
					System.out.println("Opción invalida, introduce otra.");
					break;
			}
		} while (opcion != 7);
		
		sc.close();
	}

	//Con esta función se registran usuarios
	static void registrarUsuario (Scanner sc, String[] nombres,	String[] usuarios, 
			String[] pass) {
		
		//Indico que el array está completo cuando llega a 100 registros
		if (contadorUsuarios >= max) {
			System.out.println("Límite de usuarios alcanzado.");
			return;
		}
		
		/*Pongo entre [] contadorUsuarios para que me lo almacene en la misma posición en
		los 3 arrays, esto hará que si por ejemplo es la primera vez que introducimos los
		datos, estos se almacenen en la posición 0, o si es la última se almacene en la
		posición 100, etc.*/

		System.out.println("Nombre: ");
		nombres[contadorUsuarios] = sc.nextLine();
		
		System.out.println("Usuario: ");
		usuarios[contadorUsuarios] = sc.nextLine();
		
		System.out.println("Contraseña: ");
		pass[contadorUsuarios] = sc.nextLine();
		
		System.out.println("Usuario registrado con éxito.");
		
		//Se incrementa el contador para que la siguiente vez que se quieran introducir
		//unos datos, se haga en la siguiente posición del array, si es posible.
		contadorUsuarios++;
	}
	
	//Con esta función se crean eventos
	static void crearEvento(Scanner sc, String[] eventos, String[] fechas,
			String[] duracionEvento) {
		if (contadorEventos >= max) {
			System.out.println("Límite de usuarios alcanzado.");
			return;
		}
		//Hago lo mismo que hice en la función registrarUsuario
		
		System.out.println("Nombre del evento: ");
		eventos[contadorEventos] = sc.nextLine();
		
		System.out.println("Fecha del evento (d-m-a)");
		fechas[contadorEventos] = sc.nextLine();
		
		System.out.println("Duración: ");
		duracionEvento[contadorEventos] = sc.nextLine();
		
		System.out.println("Evento creado con éxito");
		contadorEventos++;
	}
	
	
	//Función para inscribirse en un evento
	static void inscripcionesEventos(Scanner sc) {
		if(contadorUsuarios == 0 || contadorEventos == 0) {
			System.out.println("No hay usuarios o eventos registrados.");
			//Esto evita que el código siga ejecutándose
			return;
		}
		
		//Traigo a la función la función que muestra usuarios para saber los que tengo
		//registrados
		mostrarUsuarios();
		System.out.println("Elige el número de usuario: ");
		//Paso un string, ya que si pusiese un carácter no  numérico me saldría un error
		String idUsuario = sc.nextLine();
		//Lo paso por la función esNumeroValido() para saber si es un número o no, si no lo
		//es te devuelve el syso, si lo es te guardará la inscripción más adelante
		if (!esNumeroValido(idUsuario)) {
			System.out.println("Tienes que introducir un número.");
			return;
		}
		int idUsuarioCorregido = Integer.parseInt(idUsuario);
		
		mostrarEventos();
		System.out.println("Elige el número de evento: ");
		String idEvento = sc.nextLine();
		//Lo paso por la función esNumeroValido() para saber si es un número o no, si no lo
		//es te devuelve el syso, si lo es te guardará la inscripción más adelante
		if (!esNumeroValido(idEvento)) {
			System.out.println("Tienes que introducir un número.");
			return;
		}
		int idEventoCorregido = Integer.parseInt(idEvento);
		
		//Si los numeros introducidos son menores que 0 o mayores que el numero ya
		//introducido de usuarios o eventos, no se puede hacer, asi que lo marco como
		//selecion invalida
		if (idUsuarioCorregido < 0 || idUsuarioCorregido >= contadorUsuarios || 
				idEventoCorregido < 0 || idEventoCorregido >= contadorEventos) {
			System.out.println("Selecciones inválidas");
			return;
		}
		
		//Para la posición especificada dentro de [] dentro del array especificado se le
		//iguala el id corregido a la funcion, esto para que esto se pueda mostrar en la
		//funcion mostrarInscripciones()
		inscripcionesUsuarios[contadorInscripciones] = idUsuarioCorregido;
		inscripcionesEventos[contadorInscripciones] = idEventoCorregido;
		contadorInscripciones++;
		
		System.out.println("Inscripción exitosa.");
	}
	
	//Esto hace que las ids introducida en la función inscripcionesEventos no pueda ser un
	//carácter diferente de un número, ya que si no saltaba un error
	static boolean esNumeroValido(String str) {
		for (char c : str.toCharArray()) {
			//si el caracter no es un digito el boolean pasa a ser false
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		//si la cadena no esta vacia, te devuelve true
		return !str.isEmpty();
	}
	
	//Función para mostrar los usuarios introducidos con anterioridad por pantalla
	static void mostrarUsuarios() {
		if (contadorUsuarios == 0) {
			System.out.println("No hay usuarios registrados.");
			return;
		}
		
		System.out.println("\n Lista de usuarios: ");
		//for que pasa por los arrays mientras que haya usuarios
		for (int i = 0; i < contadorUsuarios; i++) {
			//No pongo la contraseña, porque los administradores no tienen porque tenerla
			System.out.println(i + ". " + nombres[i] + " (" + usuarios[i] + ")");
		}
	}
	
	//Función para mostrar eventos registrados
	static void mostrarEventos() {
		if (contadorEventos == 0) {
			System.out.println("No hay eventos registrados.");
			return;
		}
		
		System.out.println("\n Lista de eventos: ");
		//for que pasa por los arrays mientras que haya eventos
		for (int i = 0; i < contadorEventos; i++) {
			System.out.println(i + ". " + eventos[i] + ", se hará el: " + fechas[i] + 
					" (Dura: " + duracionEvento[i] + " horas)");
		}
	}
	
	//Función para mostrar inscripciones realizadas
	static void mostrarInscripciones() {
		if (contadorInscripciones == 0) {
			System.out.println("No hay inscripciones registradas.");
			return;
		}
		
		System.out.println("\n Lista de inscripciones: ");
		//for que pasa por los arrays mientras que haya inscripciones
		for (int i = 0; i < contadorInscripciones; i++) {
			System.out.println(nombres[inscripcionesUsuarios[i]] + " está inscrito en " +
		eventos[inscripcionesEventos[i]]);
		}
	}
}