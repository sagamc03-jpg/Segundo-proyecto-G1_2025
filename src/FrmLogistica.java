
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Servicios.GestionEnvios;
import Servicios.UtilServicios;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.text.AbstractDocument;
import Modelos.TipoTransporte;
import Máscaras.NumeroReal;
import Máscaras.AlfaNumerico;
import Máscaras.Codigo8digitos;

public class FrmLogistica extends JFrame {
    private JPanel pnlEditarEnvio;
    private JTextField txtCodigo, txtCliente, txtPeso, txtDistancia;
    private JComboBox<TipoTransporte> cmbTipoTransporte;
    private JTable tblEnvios;

    public FrmLogistica() {
        setSize(600, 400);
        setTitle("Logística");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tbLogistica = new JToolBar();
        JButton btnAgregar = new JButton();
        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Icons/Agregar.png")));
        btnAgregar.setToolTipText("Agregar Envío");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarClick();
                //pnlEditarEnvio.setVisible(true);
            }
        });
        tbLogistica.add(btnAgregar);

        JButton btnQuitar = new JButton();
        btnQuitar.setIcon(new ImageIcon(getClass().getResource("/Icons/quitar.png")));
        btnQuitar.setToolTipText("Quitar Envío");
        btnQuitar.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evt) {
                btnQuitarClick();
            }
        });
        tbLogistica.add(btnQuitar);

        // boxLayout 
        JPanel pnlContenedorEnvios = new JPanel();
        pnlContenedorEnvios.setLayout(new BoxLayout(pnlContenedorEnvios, BoxLayout.Y_AXIS));

        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 100));
        pnlEditarEnvio.setLayout(null);
   
        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtCodigo);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblPeso = new JLabel();
        lblPeso = new JLabel("Peso (Kg)");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        JLabel lblDistancia = new JLabel();
        lblDistancia = new JLabel("Distancia (Km)");
        lblDistancia.setBounds(220, 40, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 40, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        JLabel lblTransporte = new JLabel();
        lblTransporte = new JLabel("Transporte");
        lblTransporte.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTransporte);
        
        // lista desplegable, elige el transporte. 
        cmbTipoTransporte = new JComboBox<>(TipoTransporte.values());
        cmbTipoTransporte.setBounds(320, 10, 100, 25);
        pnlEditarEnvio.add(cmbTipoTransporte);

        
       // botones de guardar y cancelar
        JButton btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        btnGuardarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGuardarEnvioClick();
            }
        });
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(320, 70, 100, 25);
        btnCancelarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelarEnvioClick();
            }
        });
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false); 

        // La tabla
        tblEnvios = new JTable();
        JScrollPane splistaEnvios = new JScrollPane(tblEnvios);
        
        GestionEnvios.mostrar(tblEnvios); 

        // agregar componentes?
        pnlContenedorEnvios.add(pnlEditarEnvio);
        pnlContenedorEnvios.add(splistaEnvios);

        JScrollPane spEnvios = new JScrollPane(pnlContenedorEnvios);
        spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        
        getContentPane().add(tbLogistica, BorderLayout.NORTH);  // sin esto no salen
        getContentPane().add(spEnvios, BorderLayout.CENTER);;

        ((AbstractDocument)(txtDistancia.getDocument())).setDocumentFilter(new NumeroReal());
        ((AbstractDocument)(txtPeso.getDocument())).setDocumentFilter(new NumeroReal());
        ((AbstractDocument)(txtCliente.getDocument())).setDocumentFilter(new AlfaNumerico());
        ((AbstractDocument)(txtCodigo.getDocument())).setDocumentFilter(new Codigo8digitos());




    }
    private void btnAgregarClick() {
        pnlEditarEnvio.setVisible(true);
        //tp.setSelectedIndex(0);
    }
    private void btnQuitarClick(){
        int posicion = tblEnvios.getSelectedRow();
        if (posicion >=0) {
            if (GestionEnvios.quitar(posicion)) {
                GestionEnvios.mostrar(tblEnvios);
            }else {
                JOptionPane.showMessageDialog(null, "no se pudo retirar el envío");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe selecionar un envío");
        }
    }
    
    private void btnGuardarEnvioClick(){
        pnlEditarEnvio.setVisible(false);
        try{

            String cliente = txtCliente.getText();
            String codigo = txtCodigo.getText();
            double peso = UtilServicios.leerReal(txtPeso.getText());
            double distancia = UtilServicios.leerReal(txtDistancia.getText());

            if (!cliente.isEmpty() && !codigo.isEmpty() && peso>=0 && distancia>=0) {
                String codigoCompleto = "ENV-" + codigo;
                GestionEnvios.Agregar((TipoTransporte)cmbTipoTransporte.getSelectedItem(), 
                    codigoCompleto, cliente, peso, distancia); // Envio e = ? Funiciona sin eso
                GestionEnvios.mostrar(tblEnvios);
            } else {
                JOptionPane.showMessageDialog(null, "Datos no válidos");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     private void btnCancelarEnvioClick(){
        pnlEditarEnvio.setVisible(false);
    }
}