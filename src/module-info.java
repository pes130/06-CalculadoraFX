module Calculadora {
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.web;
	requires javafx.base;

	exports es.cursojavafx.calculadora;

	opens es.cursojavafx.calculadora.controller;
	opens es.cursojavafx.calculadora.model;
}