package br.com.carlosjunior.cliente.escola.gradecurricular.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.carlosjunior.cliente.escola.gradecurricular.exceptions.MateriaException;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.ErrorMapResponse;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.ErrorMapResponse.ErrorMapResponseBuilder;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.ErrorResponse;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.ErrorResponse.ErrorResponseBuilder;


@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m) {

		Map<String,String> erros = new HashMap<>();
		m.getBindingResult().getAllErrors().forEach(erro->{
			String campo = ((FieldError)erro).getField();
			String mensagem = erro.getDefaultMessage();
			erros.put(campo,mensagem);
		});
		
		ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
		
		errorMap.erros(erros)
				.httpStatus(HttpStatus.BAD_REQUEST.value())
				.timeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
	}

	@ExceptionHandler(MateriaException.class)
	public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m) {
		ErrorResponseBuilder erro = ErrorResponse.builder();
		erro.httpStatus(m.getHttpStatus().value());
		erro.mensagem(m.getMessage());
		erro.timeStamp(System.currentTimeMillis());
		return ResponseEntity.status(m.getHttpStatus()).body(erro.build());
	}

}
