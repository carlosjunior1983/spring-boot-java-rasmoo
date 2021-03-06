package br.com.carlosjunior.cliente.escola.gradecurricular.models;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorMapResponse {

	private int httpStatus;
	private Map<String, String> erros;
	private Long timeStamp;

}
