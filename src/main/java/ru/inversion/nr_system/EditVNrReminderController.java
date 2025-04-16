package ru.inversion.nr_system;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;
import static javassist.CtMethod.ConstParameter.string;
import ru.inversion.fx.app.AppException;

/**
 * @author  Lesin
 * @since   Sun Apr 13 13:47:40 MSK 2025
 */
public class EditVNrReminderController extends JInvFXFormController <PVNrReminder> 
{  
//
//
//
//    @FXML JInvLongField IREM;
    @FXML JInvTextField CHEADING;
//    @FXML JInvTextField CTEXT;
    @FXML JInvLabel lblDDATEREMINDER;
    @FXML JInvCalendar DDATEREMINDER;
    @FXML JInvComboBox CB;
    @FXML JInvTextField CCONDITION;
    
    private void initCB()
    {
        CB.setValue("По дате");
        CB.setItems(FXCollections.observableArrayList("По дате","По условию"));
        
    }
//
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init (); 
        initCB();
        
    }    
    
    @Override
    protected void afterInit() throws AppException{
        
        if (this.CCONDITION.getText()!=null)
        {
            CB.setValue("По условию");
            DDATEREMINDER.setDisable(true);
            DDATEREMINDER.setVisible(false);
            lblDDATEREMINDER.setText("Условие напоминания");
            CCONDITION.setVisible(true);
        }
        
        super.afterInit();
        
    }
    @FXML
    public final String HidingCB()
    {
        String temp = (String) CB.getValue();
        if(CB.getValue()=="По условию")
            {
                DDATEREMINDER.setDisable(true);
                DDATEREMINDER.setVisible(false);
                DDATEREMINDER.setValue(null);
                lblDDATEREMINDER.setText("Условие напоминания");
                CCONDITION.setVisible(true);
                CCONDITION.setDisable(false);
            }
        else 
        {
            System.out.println(CCONDITION.getText()+"|||||||||||||||||||||||||||||||||||||||||||||||||||||");
            DDATEREMINDER.setDisable(false);
            DDATEREMINDER.setVisible(true);
            lblDDATEREMINDER.setText("Дата напоминания");
            CCONDITION.setVisible(false);
            CCONDITION.setDisable(true);
            CCONDITION.setText(null);
        }
        return(temp);
    }
}


