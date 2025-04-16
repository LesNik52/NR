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
@since   2025/04/13 13:47:37
*/
@Entity (name="ru.inversion.nr_system.PVNrReminder")
@Table (name="LESNIK.V_NR_REMINDER")
public class PVNrReminder implements Serializable
{
    private static final long serialVersionUID = 13_04_2025_13_47_37l;

    private Long IREM;
    private String CHEADING;
    private String CTEXT;
    private LocalDate DDATEREMINDER;
    private String CCONDITION;

    public PVNrReminder(){}

    @Id 
    @Column(name="IREM",nullable = false,length = 0)
    public Long getIREM() {
        return IREM;
    }
    public void setIREM(Long val) {
        IREM = val; 
    }
    @Column(name="CHEADING",length = 20)
    public String getCHEADING() {
        return CHEADING;
    }
    public void setCHEADING(String val) {
        CHEADING = val; 
    }
    @Column(name="CTEXT",length = 100)
    public String getCTEXT() {
        return CTEXT;
    }
    public void setCTEXT(String val) {
        CTEXT = val; 
    }
    @Column(name="DDATEREMINDER")
    public LocalDate getDDATEREMINDER() {
        return DDATEREMINDER;
    }
    public void setDDATEREMINDER(LocalDate val) {
        DDATEREMINDER = val; 
    }
    
    @Column(name="CCONDITION", length = 100)
    public String getCCONDITION() {
        return CCONDITION;
    }
    public void setCCONDITION(String val) {
        CCONDITION = val; 
    }
}