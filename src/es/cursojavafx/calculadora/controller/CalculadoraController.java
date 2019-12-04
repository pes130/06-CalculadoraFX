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

	private String operacionActual = "";

	private String operando1;
	private String operando2;

	
	/**
	 * Ejecutado cada vez que le damos al botón C de limpiar.
	 * 
	 * @param event
	 */
	@FXML
	void limpiar(ActionEvent event) {
		this.lblPantalla.setText("");
		this.operando1 = null;
		this.operacionActual = null;
		this.operando2 = null;
	}

	/**
	 * Ejecutado cuando pulsamos en el botón igual
	 * 
	 * @param event
	 */
	@FXML
	void calcularAction(ActionEvent event) {
		if (this.operando1 != null && this.operacionActual != null && !ultimoCaracterEsOperacion()) {
			ejecutarCalculo();
		}

	}

	@FXML
	void botonPulsado(ActionEvent event) {
		Button btnPulsado = (Button) event.getSource();
		if (botonEsNumero(btnPulsado)) {
			this.lblPantalla.setText(this.lblPantalla.getText() + btnPulsado.getText());
		} else {
			if (botonEsOperacion(btnPulsado)) {
				if (this.operando1 == null && !this.lblPantalla.getText().isEmpty()) {
					// 3 x
					this.operando1 = this.lblPantalla.getText();
					this.operacionActual = btnPulsado.getText();
					this.lblPantalla.setText(this.lblPantalla.getText() + btnPulsado.getText());
				} else {
					if (ultimoCaracterEsOperacion()) {
						// Ya hay una operación -> cambiamos una por otra
						this.lblPantalla.setText(
								this.lblPantalla.getText().replace(this.operacionActual, btnPulsado.getText()));
						this.operacionActual = btnPulsado.getText();
					} else {
						// 3x2+ Calculamos operación y ponemos resultado como operando1
						ejecutarCalculo();
					}

				}
			}
		}
	}

	private void ejecutarCalculo() {
		this.operando2 = this.lblPantalla.getText().replace(this.operando1 + this.operacionActual, "");
		Integer num1 = Integer.valueOf(this.operando1);
		Integer num2 = Integer.valueOf(this.operando2);
		
		Integer resultadoOperacion = calcular(num1, this.operacionActual,num2);
		Resultado resultado = new Resultado(num1, num2, operacionActual, resultadoOperacion);
		this.tablaHistorial.getItems().add(resultado);
		
		this.operacionActual = null;
		this.operando1 = null;
		this.operando2 = null;
		this.lblPantalla.setText(String.valueOf(resultadoOperacion));
	}

	private boolean ultimoCaracterEsOperacion() {
		return esOperacion(this.lblPantalla.getText().substring(this.lblPantalla.getText().length() - 1));
	}

	private Integer calcular(Integer num1, String operacionActual2, Integer num2) {
		Integer resultado = 0;
		switch (operacionActual2) {
		case "+":
			resultado = num1 + num2;
			break;
		case "-":
			resultado = num1 - num2;
			break;
		case "x":
			resultado = num1 * num2;
			break;
		case "/":
			resultado = num1 / num2;
			break;
		default:
			System.out.println("Operación desconocida");
		}
		return resultado;
	}

	private boolean botonEsNumero(Button btn) {
		String txt = btn.getText();
		if ("0123456789".contains(txt)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean botonEsOperacion(Button btn) {
		String txt = btn.getText();
		if (esOperacion(txt)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean esOperacion(String texto) {
		if ("+/x-".contains(texto)) {
			return true;
		} else {
			return false;
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
