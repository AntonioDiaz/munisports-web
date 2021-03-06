package com.adiaz.madrid.entities;


import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode
public class Group implements Serializable{

    @Id
    private String id;
    @Index
    private Integer codTemporada;
    @Index
    private String codCompeticion;
    @Index
    private Integer codFase;
    @Index
    private Integer codGrupo;
    private String nombreTemporada;
    private String nombreCompeticion;
    private String nombreFase;
    private String nombreGrupo;

    @Index
    private String deporte;
    private String categoria;

    @Index
    private String distrito;

}
