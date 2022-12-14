package projetotela.classes;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThreads extends Thread {

	private static ConcurrentLinkedQueue<ObjetoFilaThreads> pilha_fila = new ConcurrentLinkedQueue<ObjetoFilaThreads>();

	public static void add(ObjetoFilaThreads objetoFilaThreads) {

		pilha_fila.add(objetoFilaThreads);

	}

	@Override
	public void run() {
		System.out.println("fila rodando");

		while (true) {

			
			synchronized (pilha_fila) {// bloqueia o acesso a essa lista de outros processos
				Iterator iteracao = pilha_fila.iterator();

				while (iteracao.hasNext()) {// enquanto estiver dados da lista vao processar

					ObjetoFilaThreads processar = (ObjetoFilaThreads) iteracao.next();// processa o objeto atual

					/*
					 * gera notas ficasis eletronicas gera milhares de PDF fazer um envio em massa
					 * de email
					 */
					System.out.println("---------------------------------------------");
					System.out.println(processar.getEmail());
					System.out.println(processar.getNome());

					iteracao.remove();

					try {
						Thread.sleep(1000); // dar um tempo na descarga da memoria
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			try {
				Thread.sleep(1000); // procesou toda a lista dar um tempo para limpeza de memoria
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
