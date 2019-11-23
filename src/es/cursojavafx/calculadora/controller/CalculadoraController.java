package es.cursojavafx.calculadora.controller;

import java.net.URL;
import java.util.ResourceBundle;

import es.cursojavafx.calculadora.model.Resultado;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador asociado a nuestra vista Calculadora.fxml
 * 
 * @author javafx
 *
 */
public class CalculadoraController implements Initializable {

	@FXML
	private Button btn7;

	@FXML
	private Button btn8;

	@FXML
	private Button btn9;

	@FXML
	private Button btn4;

	@FXML
	private Button btn5;

	@FXML
	private Button btn6;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn3;

	@FXML
	private Button btn0;

	@FXML
	private Button btnSuma;

	@FXML
	private Button btnResta;

	@FXML
	private Button btnPor;

	@FXML
	private Button btnDiv;
	@FXML
	private TextField lblPantalla;

	@FXML
	private Button btnCalcular;

	@FXML
	private Button btnLimpiar;

	@FXML
	private TableView<Resultado> tablaHistorial;

	@FXML
	private TableColumn<Resultado, Integer> operando1Col;

	@FXML
	private TableColumn<Resultado, String> operacionCol;

	@FXML
	private TableColumn<Resultado, Integer> resultadoCol;

	@FXML
	private TableColumn<Resultado, Integer> operando2Col;

	private String operandoActual = "";

	/**
	 * Ejecutado cada vez que pulsamos un número del 1 al 9.
	 * 
	 * @param event
	 */
	@FXML
	void numeroPulsado(ActionEvent event) {
		this.lblPantalla.setText(lblPantalla.getText() + ((Button) event.getSource()).getText());
	}

	/**
	 * Ejecutado cada vez que le damos al botón C de limpiar.
	 * 
	 * @param event
	 */
	@FXML
	void limpiar(ActionEvent event) {
		this.lblPantalla.setText("");
		activarOperandos();
		this.operandoActual = "";
	}

	/**
	 * Ejecutado cada vez que pulsamos sobre un botón de operaciòn.
	 * 
	 * @param event
	 */
	@FXML
	void operacionPulsada(ActionEvent event) {
		if (this.operandoActual.isEmpty()) {
			this.lblPantalla.setText(lblPantalla.getText() + ((Button) event.getSource()).getText());
			this.operandoActual = ((Button) event.getSource()).getText();
			if (this.operandoActual.equals("+")) {
				this.operandoActual = "s";
			}
			desactivarOperandos();
		}
	}

	private void desactivarOperandos() {
		this.btnDiv.setDisable(true);
		this.btnSuma.setDisable(true);
		this.btnResta.setDisable(true);
		this.btnPor.setDisable(true);
	}

	private void activarOperandos() {
		this.btnDiv.setDisable(false);
		this.btnSuma.setDisable(false);
		this.btnResta.setDisable(false);
		this.btnPor.setDisable(false);
	}

	/**
	 * Ejecutado cuando pulsamos en el botón igual
	 * 
	 * @param event
	 */
	@FXML
	void calcular(ActionEvent event) {
		String operacionString = this.lblPantalla.getText();
		activarOperandos();
		String[] partes = operacionString.split(this.operandoActual);
		if (partes.length == 2) {
			Integer operando1 = Integer.parseInt(partes[0]);
			Integer operando2 = Integer.parseInt(partes[1]);
			Integer resultado = 0;
			switch (this.operandoActual) {
			case "s":
				resultado = operando1 + operando2;
				break;
			case "-":
				resultado = operando1 - operando2;
				break;
			case "x":
				resultado = operando1 * operando2;
				break;
			case "/":
				resultado = operando1 / operando2;
				break;
			default:
				System.out.println("Operación desconocida");
			}
			this.lblPantalla.setText(String.valueOf(resultado));
			Resultado res = new Resultado(operando1, operando2, this.operandoActual, resultado);
			this.operandoActual = "";
			this.tablaHistorial.getItems().add(res);

		}
	}

	/**
	 * Para borrar una fila.
	 * 
	 * @param event
	 */
	@FXML
	void borrarFila(ActionEvent event) {
		Resultado elementoSeleccionado = this.tablaHistorial.getSelectionModel().getSelectedItem();
		System.out.println("Vamoso a borrar " + elementoSeleccionado);
		this.tablaHistorial.getItems().remove(elementoSeleccionado);
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.operando1Col.setCellValueFactory(new PropertyValueFactory<Resultado, Integer>("operando1"));
		this.operando2Col.setCellValueFactory(new PropertyValueFactory<Resultado, Integer>("operando2"));
		this.resultadoCol.setCellValueFactory(new PropertyValueFactory<Resultado, Integer>("resultado"));
		this.operacionCol.setCellValueFactory(new PropertyValueFactory<Resultado, String>("operacion"));
		
		this.tablaHistorial.getItems().addListener(new ListChangeListener<Resultado>() {

			@Override
			public void onChanged(Change<? extends Resultado> c) {
				System.out.println("Has cambiado algo!!!!!");		
				System.out.println(c);
			}
			
		});

	}

}
