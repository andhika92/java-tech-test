package com.techtest.consumerbackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbm_consumer")
public class Consumer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Field nama is required")
	private String nama;
	
	private String alamat;
	
	@NotBlank(message = "Field kota is required")
	private String kota;
	
	@NotBlank(message = "Field provinsi is required")
	private String provinsi;
	
	@NotNull(message = "Field tanggal registrasi is required")
	private LocalDateTime tglRegistrasi;
	
	@NotNull(message = "Field status is required")
	@Pattern(regexp = "Y|N", message = "Field status must be 'Y' or 'N'")
	private String status;
}
