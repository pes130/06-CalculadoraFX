module Calculadora {
	requires javafx.fxml;
	requires javafx.controls;

	exports es.cursojavafx.calculadora;

	opens es.cursojavafx.calculadora.controller;
	opens es.cursojavafx.calculadora.model;
}