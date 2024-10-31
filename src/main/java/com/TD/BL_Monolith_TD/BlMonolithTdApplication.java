package com.TD.BL_Monolith_TD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BlMonolithTdApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlMonolithTdApplication.class, args);
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("""
				████████╗██╗░░░██╗  ██████╗░███████╗░██████╗████████╗██╗███╗░░██╗░█████╗░
				╚══██╔══╝██║░░░██║  ██╔══██╗██╔════╝██╔════╝╚══██╔══╝██║████╗░██║██╔══██╗
				░░░██║░░░██║░░░██║  ██║░░██║█████╗░░╚█████╗░░░░██║░░░██║██╔██╗██║██║░░██║
				░░░██║░░░██║░░░██║  ██║░░██║██╔══╝░░░╚═══██╗░░░██║░░░██║██║╚████║██║░░██║
				░░░██║░░░╚██████╔╝  ██████╔╝███████╗██████╔╝░░░██║░░░██║██║░╚███║╚█████╔╝
				░░░╚═╝░░░░╚═════╝░  ╚═════╝░╚══════╝╚═════╝░░░░╚═╝░░░╚═╝╚═╝░░╚══╝░╚════╝░
				""");
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("🚀 **Compilación Exitosa del Proyecto 'Tu Destino'** 🚀\n\n" +
				"¡Hola a todos! 🎉\n\n" +
				"Me complace anunciar que hemos completado con éxito la compilación de nuestra aplicación 'Tu Destino'.\n\n" +
				"Gracias a todos por su esfuerzo y dedicación.\n\n" +
				"¡Sigamos adelante con esta energía positiva! 🌟\n\n" +
				"Atentamente,\n" +
				"Equipo de Desarrollo Tu Destino  ");
		System.out.println("|----------------------------------------------------------------------------|");

	}



}
