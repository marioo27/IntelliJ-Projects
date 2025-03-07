package org.example.informereservas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import win.zqxu.jrviewer.JRViewerFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Mario Garcia Rodriguez
 * @version 1
 * @since 18/02/2025
 * Clase controladora para gestionar la generacion del informe y el guardado en formato pdf.
 */
public class HelloController implements Initializable {

    @FXML
    private Button btnViewReport;
    @FXML
    private Button btnSaveReport;
    @FXML
    private Pane panelInforme;

    private ObservableList<Habitacion> habitacionesList;

    @FXML
    private SwingNode helpButtonNode;

    private JButton helpButton;

    /**
     * Metodo que inicializa una lista con habitaciones y sus respectivos datos.
     */
    private void inicializarDatos() {
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion(101, 50.0, new Reserva(2, "Mario Garcia", 15)));
        habitaciones.add(new Habitacion(102, 85.0, new Reserva(1, "Cristiano Ronaldo", 2)));
        habitaciones.add(new Habitacion(103, 55.0, new Reserva(10, "Marco Aurelio", 4)));
        habitaciones.add(new Habitacion(104, 70.0, new Reserva(6, "Nicki Minaj", 7)));
        habitaciones.add(new Habitacion(105, 150.0, new Reserva(4, "Felipe VI", 1)));

        habitacionesList = FXCollections.observableArrayList(habitaciones);
    }

    /**
     * Metodo que configura la tabla con los valores de las habitaciones.
     */
    private void configurarTabla() {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colHuesped.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(() -> cellData.getValue().getReserva().getHuespedPrincipal()));
        colDuracion.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue().getReserva().getDuracion()));

        tableView.setItems(habitacionesList);
    }

    /**
     * Metodo que genera un informe y lo muestra en el panel de informes.
     */
    @FXML
    private void generarInforme() {
        try {
            File fichero = new File("./Informes/EntregaHotelMario.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(habitacionesList);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./Informes/img.png");
            JasperPrint print = JasperFillManager.fillReport(informe, parametros, coleccion);
            JRViewerFX visor = new JRViewerFX(print);
            panelInforme.getChildren().add(visor);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que guarda el informe generado en formato pdf.
     */
    @FXML
    private void guardarInforme() {
        try {
            File fichero = new File("./Informes/EntregaHotelMario.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(habitacionesList);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./Informes/img.png");
            JasperPrint print = JasperFillManager.fillReport(informe, parametros, coleccion);

            File reportFile = new File("Informes/EntregaHotelMario.pdf");
            OutputStream output = new FileOutputStream(reportFile);
            JasperExportManager.exportReportToPdfStream(print, output);
        } catch (FileNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que inicializa la clase configurando los datos y la tabla.
     *
     * @param url URL de inicialización.
     * @param resourceBundle Recursos utilizados para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarDatos();
        configurarTabla();
        initJavaHelpConfig();
    }

    private void initJavaHelpConfig() {
        try {
            // Cargar el archivo de configuración de JavaHelp
            File helpFile = new File("help/help_set.hs");
            URL helpURL = helpFile.toURI().toURL();
            HelpSet helpSet = new HelpSet(getClass().getClassLoader(), helpURL);
            HelpBroker helpBroker = helpSet.createHelpBroker();

            SwingUtilities.invokeLater(() -> {
                helpButton = new JButton("JavaHelp");
                helpButton.setBounds(0, 0, 150, 50);
                helpButtonNode.setContent(helpButton);

                // Asociar el botón con la ayuda de JavaHelp
                helpBroker.enableHelpOnButton(helpButton, "aplicacion", helpSet);

                // Evita que el botón se vea en negro
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2),
                        e -> helpButtonNode.getContent().repaint()));
                timeline.playFromStart();
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR: no se pudo cargar la ayuda de JavaHelp", e);
        }
    }
}
