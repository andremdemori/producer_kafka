package br.mil.defesa.interc2.producer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProducerApplication {

	private static volatile ConfigurableApplicationContext context;

	private static ClassLoader mainThreadClassLoader;

	public static void main(String[] args) {
		mainThreadClassLoader = Thread.currentThread().getContextClassLoader();
		context = SpringApplication.run(ProducerApplication.class, args);
	}

	public static void restart() {

		var args = context.getBean(ApplicationArguments.class);

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(ProducerApplication.class, args.getSourceArgs());
		});

		thread.setContextClassLoader(mainThreadClassLoader);
		thread.setDaemon(false);
		thread.start();
	}

}
