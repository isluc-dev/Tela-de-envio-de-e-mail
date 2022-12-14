package projetotela.classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

public class TelaTime extends JDialog {

	private JPanel jPanel = new JPanel(new GridBagLayout());// painel de componentes
	private JLabel descricaoHora = new JLabel("Nome");
	private JTextField mostraTempo = new JTextField();

	private JLabel descricaoHora2 = new JLabel("E-mail");
	private JTextField mostraTempo2 = new JTextField();

	private JButton jButton1 = new JButton("Gera");
	private JButton jButton2 = new JButton("Stop");
	
/*	private Runnable thread1 = new Runnable() {
		
		@Override
		public void run() {
		 while(true) {
			 mostraTempo.setText(new SimpleDateFormat("dd/MM/yyyy/hh:mm.ss").format(Calendar.getInstance().getTime()));
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
		 }
			
		}
	};
	
private Runnable thread2 = new Runnable() {
		
		@Override
		public void run() {
		 while(true) {
			 mostraTempo2.setText(new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss").format(Calendar.getInstance().getTime()));
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
		 }
			
		}
	};
	
	private Thread thread1Time;
	private Thread thread12ime;*/
	
	
	private ImplementacaoFilaThreads fila = new ImplementacaoFilaThreads();

	public TelaTime() {
		setTitle("minha tela time");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);
		// primeira parte concluida

		GridBagConstraints gridBagConstraints = new GridBagConstraints();// contoloar de componentes
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST; // alinhando a esquerda
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);

		descricaoHora.setPreferredSize(new DimensionUIResource(200, 25));
		jPanel.add(descricaoHora, gridBagConstraints);

		mostraTempo.setPreferredSize(new DimensionUIResource(200, 25));
		gridBagConstraints.gridy++;
		//mostraTempo.setEditable(false);
		jPanel.add(mostraTempo, gridBagConstraints);

		descricaoHora2.setPreferredSize(new DimensionUIResource(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(descricaoHora2, gridBagConstraints);

		mostraTempo2.setPreferredSize(new DimensionUIResource(200, 25));
		gridBagConstraints.gridy++;
		//mostraTempo2.setEditable(false);
		jPanel.add(mostraTempo2, gridBagConstraints);

		gridBagConstraints.gridwidth = 1;

		jButton1.setPreferredSize(new DimensionUIResource(92, 25));
		gridBagConstraints.gridy++;
		jPanel.add(jButton1, gridBagConstraints);

		jButton2.setPreferredSize(new DimensionUIResource(92, 25));
		gridBagConstraints.gridx++;
		jPanel.add(jButton2, gridBagConstraints);
		
		
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(fila == null ) {
					fila = new ImplementacaoFilaThreads();
					fila.start();
				}
				
				for(int qnt = 0; qnt < 100; qnt++) {
				
				ObjetoFilaThreads filathread = new ObjetoFilaThreads();
				filathread.setNome(mostraTempo.getText()); 
				filathread.setEmail(mostraTempo2.getText() + "-" + qnt);
				
				fila.add(filathread);
				}
				
			/*	thread1Time = new Thread(thread1);
				thread1Time.start();
				thread12ime = new Thread(thread2);
				thread12ime.start();
				jButton1.setEnabled(false);// clico no start e desebilita 
				jButton2.setEnabled(true);// clico e no stop e habilita*/
			}
		});
		
		
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			/*	thread1Time.stop();
				thread12ime.stop();
				jButton1.setEnabled(true);//habilita 
				jButton2.setEnabled(false);//desabilita*/
				
				fila.stop();
				fila = null;
				
			}
		});
		fila.start();
       // jButton2.setEnabled(false);
		add(jPanel, BorderLayout.WEST);
		setVisible(true);// sempre sera o ultimo comando

	}
}
