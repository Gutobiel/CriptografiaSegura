import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VisaoSHA256 extends JFrame{
	//propriedades da classe
	private static final long serialVersionUID = 1L;

	private JLabel lblTexto = new JLabel("texto Original");
	private JTextArea txtTexto = new JTextArea();
	private JScrollPane jspTexto = new JScrollPane(txtTexto);
	
	private JLabel lblHash = new JLabel("Hash SHA-256");
	private JTextField txtHash = new JTextField();
	
	private JButton btnCalcular = new JButton("Calcular");
	
	//Função principal de execuçao do programa
	public static void main(String[] args) {
		new VisaoSHA256().setVisible(true);
	}
	
	//metodo construtor da classe
	public VisaoSHA256() {
		//configução da janela
		setTitle("Cálculo do Resumo Criptoográfico SHA-256");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		//caixa de texto
		lblTexto.setBounds(10, 10, 200, 20);
		add(lblTexto);
		jspTexto.setBounds(10, 30, 465, 330);
		add(jspTexto);
		txtTexto.setLineWrap(true);
		
		//Caixa do HASH
		lblHash.setBounds(10, 370, 200, 20);
		add(lblHash);
		txtHash.setBounds(10, 390, 465, 20);
		add(txtHash);
		
		//botão
		btnCalcular.setBounds(200, 420, 100, 30);
		add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtHash.setText(calcularHash(txtTexto.getText()));
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, erro);
				}
			}
		});
	}
	
	private static String calcularHash(String texto) throws Exception {
		String retorno = "";
		
		MessageDigest objResumidor = MessageDigest.getInstance("SHA-256");
		
		byte[] vetorDeBytesDoResumo = objResumidor.digest(texto.getBytes("UTF-8"));
		
		for (int i = 0; i < vetorDeBytesDoResumo.length; i++) {
			String hexadecimal = Integer.toHexString(0xff & vetorDeBytesDoResumo[i]);
			if(hexadecimal.length() == 1) {
				hexadecimal = "0" + hexadecimal;
			}
			
			retorno = retorno + hexadecimal;
		}
		
		return retorno;
 }
}
