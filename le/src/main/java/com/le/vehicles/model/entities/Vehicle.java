package com.le.vehicles.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carro")
public class Vehicle{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @Column(name = "url_foto",length = 268)
    private String urlFoto;

    @Column(name = "url_video",length = 268)
    private String urlVideo;
    private String latitude;
    private String longitude;
    private String tipo;

}
