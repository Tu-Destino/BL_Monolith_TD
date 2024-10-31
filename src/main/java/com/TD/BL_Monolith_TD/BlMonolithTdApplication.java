package com.TD.BL_Monolith_TD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, WebMvcAutoConfiguration.class })
public class BlMonolithTdApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlMonolithTdApplication.class, args);
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("████████╗██╗░░░██╗  ██████╗░███████╗░██████╗████████╗██╗███╗░░██╗░█████╗░");
		System.out.println("╚══██╔══╝██║░░░██║  ██╔══██╗██╔════╝██╔════╝╚══██╔══╝██║████╗░██║██╔══██╗");
		System.out.println("░░░██║░░░██║░░░██║  ██║░░██║█████╗░░╚█████╗░░░░██║░░░██║██╔██╗██║██║░░██║");
		System.out.println("░░░██║░░░██║░░░██║  ██║░░██║██╔══╝░░░╚═══██╗░░░██║░░░██║██║╚████║██║░░██║");
		System.out.println("░░░██║░░░╚██████╔╝  ██████╔╝███████╗██████╔╝░░░██║░░░██║██║░╚███║╚█████╔╝");
		System.out.println("░░░╚═╝░░░░╚═════╝░  ╚═════╝░╚══════╝╚═════╝░░░░╚═╝░░░╚═╝╚═╝░░╚══╝░╚════╝░");

		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("🚀 **Successful Compilation of the 'Tu Destino' Project** 🚀\n\n" +
				"Hello everyone! 🎉\n\n" +
				"I am pleased to announce that we have successfully completed the compilation of our 'Tu Destino' application.\n\n" +
				"Thank you all for your effort and dedication.\n\n" +
				"Let's continue with this positive energy! 🌟\n\n" +
				"Sincerely,\n" +
				"Tu Destino Development Team");
		System.out.println("|----------------------------------------------------------------------------|");

	}



}
