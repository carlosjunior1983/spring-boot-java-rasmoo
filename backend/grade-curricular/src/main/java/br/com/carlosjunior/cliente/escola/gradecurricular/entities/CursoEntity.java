package br.com.carlosjunior.cliente.escola.gradecurricular.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_curso")
@Data // Lombok - Criar os Getters e Setters Automático
@NoArgsConstructor // Lombok - Criar os Construtores Automático
public class CursoEntity implements Serializable {

	private static final long serialVersionUID = 2601197269293634394L;

	@JsonInclude(Include.NON_NULL) // Não pode ser Nulo
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long id;

	@JsonInclude(Include.NON_EMPTY) // Nao pode ser BRANCO
	@Column(name = "nome")
	private String nome;

	@Column(name = "hrs")
	private int horas;

	@JsonInclude(Include.NON_EMPTY)
	@Column(name = "codigo")
	private String codigo;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "materia_id")
	private List<MateriaEntity> materias;
	
}
