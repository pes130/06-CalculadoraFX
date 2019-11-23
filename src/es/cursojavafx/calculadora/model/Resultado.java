package es.cursojavafx.calculadora.model;

public class Resultado {

	private Integer operando1;
	private Integer operando2;
	private String operacion;
	private Integer resultado;

	public Resultado(Integer operando1, Integer operando2, String operacion, Integer resultado) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
		this.operacion = operacion;
		this.resultado = resultado;
	}

	public Integer getOperando1() {
		return operando1;
	}

	public void setOperando1(Integer operando1) {
		this.operando1 = operando1;
	}

	public Integer getOperando2() {
		return operando2;
	}

	public void setOperando2(Integer operando2) {
		this.operando2 = operando2;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}
	
	@Override
	public String toString() {
		return "Resultado [operando1=" + operando1 + ", operando2=" + operando2 + ", operacion=" + operacion
				+ ", resultado=" + resultado + "]";
	}
}
