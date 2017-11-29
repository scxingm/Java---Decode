package decode;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Decode extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Decode frame = new Decode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Decode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textEncode = new JTextArea();
		textEncode.setBounds(72, 10, 352, 48);
		textEncode.setLineWrap(true);
		contentPane.add(textEncode);
		
		JTextArea textDecode = new JTextArea();
		textDecode.setText("\u52A0\u5BC6\u4EE3\u7801\u7684\u89E3\u5BC6\u7ED3\u679C\u5C06\u5728\u8FD9\u91CC\u8FDB\u884C\u5C55\u793A...");
		textDecode.setBounds(10, 126, 414, 125);
		textDecode.setLineWrap(true);
		contentPane.add(textDecode);
		
		JLabel label = new JLabel("\u795E\u79D8\u4EE3\u7801\uFF1A");
		label.setBounds(10, 10, 74, 48);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u89E3\u5BC6\u7ED3\u679C\uFF1A");
		label_1.setBounds(10, 101, 414, 15);
		contentPane.add(label_1);
		
		JButton btnDecode = new JButton("\u5F00\u59CB\u89E3\u5BC6");
		btnDecode.setBounds(10, 68, 138, 23);
		btnDecode.setContentAreaFilled(false);
		contentPane.add(btnDecode);
		btnDecode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codeStr = textEncode.getText().toString();
				textDecode.setText(decode(codeStr));
			}
		});
		
		JButton btnEncode = new JButton("\u5F00\u59CB\u52A0\u5BC6");
		btnEncode.setBounds(148, 68, 138, 23);
		btnEncode.setContentAreaFilled(false);
		contentPane.add(btnEncode);
		btnEncode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codeStr = textEncode.getText().toString();
				textDecode.setText(encode(codeStr));
			}
		});
		
		JButton btnClear = new JButton("\u6E05\u7406\u5185\u5BB9");
		btnClear.setBounds(286, 68, 138, 23);
		btnClear.setContentAreaFilled(false);
		contentPane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textEncode.setText("");
				textDecode.setText("");
			}
		});
		
	}
	
    /**
     * ½âÂë
     * @param string
     * @return
     */
    public static String decode(String string){
        String result = null;
        try {
            String urlString = URLDecoder.decode(string, "UTF-8");//url±àÂë
            char[] chars = urlString.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
//                char cs = (char)chars[i];
                int ics = chars[i]^7;
                sb.append((char)ics+"");
            }
            result = URLDecoder.decode(sb.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ¼ÓÃÜ
     * @param string
     * @return
     */
    public static String encode(String string){
        StringBuilder sb = new StringBuilder();
        try {
            String urlString = URLEncoder.encode(string, "UTF-8");//url±àÂë
            char[] chars = urlString.toCharArray();
            for (int i = 0; i < chars.length; i++) {
//                char cs = (char)chars[i];
                int ics = chars[i]^7;
                sb.append(URLEncoder.encode((char)ics+"", "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }
}
