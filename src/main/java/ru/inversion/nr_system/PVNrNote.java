package ru.inversion.nr_system;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  Lesin
@since   2025/04/13 13:46:38
*/
@Entity (name="ru.inversion.nr_system.PVNrNote")
@Table (name="LESNIK.V_NR_NOTE")
public class PVNrNote implements Serializable
{
    private static final long serialVersionUID = 13_04_2025_13_46_38l;

    private Long INOTE_ID;
    private String CHEADING;
    private String CTEXT;

    public PVNrNote(){}

    @Id 
    @Column(name="INOTE_ID",nullable = false,length = 0)
    public Long getINOTE_ID() {
        return INOTE_ID;
    }
    public void setINOTE_ID(Long val) {
        INOTE_ID = val; 
    }
    @Column(name="CHEADING",length = 30)
    public String getCHEADING() {
        return CHEADING;
    }
    public void setCHEADING(String val) {
        CHEADING = val; 
    }
    @Column(name="CTEXT",length = 200)
    public String getCTEXT() {
        return CTEXT;
    }
    public void setCTEXT(String val) {
        CTEXT = val; 
    }
}