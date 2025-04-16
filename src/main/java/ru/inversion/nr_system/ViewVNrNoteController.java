package ru.inversion.nr_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;
import ru.inversion.meta.EntityMetadataFactory;
import ru.inversion.meta.IEntityProperty;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;

import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;

/**
 *
 * @author  Lesin
 * @since   Sun Apr 13 13:46:42 MSK 2025
 */
public class ViewVNrNoteController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PVNrNote> V_NR_NOTE;   
    @FXML private JInvTable<PVNrReminder> V_NR_REMINDER;

    @FXML private JInvToolBar toolBar;
    
    @FXML private TabPane tabPane;
    @FXML private Tab idTabNote;
    @FXML private Tab idTabRem;
 
   
    private final XXIDataSet<PVNrNote> dsV_NR_NOTE = new XXIDataSet<> ();   
    private final XXIDataSet<PVNrReminder> dsV_NR_REMINDER = new XXIDataSet<> ();
    
    @FXML 
    private String handleTabChanged()
    {
        Tab currentTab = tabPane.getSelectionModel().getSelectedItem();
        if (currentTab == idTabNote)
        {
            System.out.println("Выбрана вкладка Note");
            return("Note");
        } else if (currentTab == idTabRem)
        {
            System.out.println("Выбрана вкладка Rem");
            return("Rem");
        }
        return null;
    }
    
    
//
// initDataSet
//    
    private void initDataSet () throws Exception 
    {
        dsV_NR_NOTE.setTaskContext (getTaskContext ());
        dsV_NR_NOTE.setRowClass (PVNrNote.class);
        dsV_NR_REMINDER.setTaskContext (getTaskContext ());
        dsV_NR_REMINDER.setRowClass (PVNrReminder.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("Напоминания и заметки"));
        
        initDataSet ();
        DSFXAdapter<PVNrNote> dsfx = DSFXAdapter.bind (dsV_NR_NOTE, V_NR_NOTE, null, false); 
        DSFXAdapter<PVNrReminder> dsfxx = DSFXAdapter.bind (dsV_NR_REMINDER, V_NR_REMINDER, null, false); 
        
        dsfx.setEnableFilter (true);
        dsfxx.setEnableFilter (true);
 
                
        initToolBar ();

        V_NR_NOTE.setToolBar (toolBar);       
        V_NR_NOTE.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        V_NR_NOTE.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation (FormModeEnum.VM_NONE));
        V_NR_NOTE.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        V_NR_NOTE.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        V_NR_NOTE.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        V_NR_NOTE.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());
        
        V_NR_REMINDER.setToolBar (toolBar);       
        V_NR_REMINDER.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        V_NR_REMINDER.setAction (ActionFactory.ActionTypeEnum.CREATE_BY, (a) -> doOperation (FormModeEnum.VM_NONE));
        V_NR_REMINDER.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        V_NR_REMINDER.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        V_NR_REMINDER.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        V_NR_REMINDER.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
// doRefresh
//    
    private void doRefresh () 
    {
        V_NR_NOTE.executeQuery ();
        V_NR_REMINDER.executeQuery ();
    }
//
// initToolBar
//    
    private void initToolBar () 
    {
        //JInvButtonPrint bp = new JInvButtonPrint (this::setPrintParam);        
        //bp.setReportTypeId (200000);
        //toolBar.getItems ().add (bp);

        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.CREATE, 
                                    ActionFactory.ActionTypeEnum.CREATE_BY, 
                                    ActionFactory.ActionTypeEnum.VIEW,
                                    ActionFactory.ActionTypeEnum.UPDATE,
                                    ActionFactory.ActionTypeEnum.DELETE);
    }
//
// setPrintParam
//
    private void setPrintParam ( ApReport ap ) 
    {
        if (dsV_NR_NOTE.isEmpty ())
            throw new StopExecuteActionBiCompException ();
        if (dsV_NR_REMINDER.isEmpty ())
            throw new StopExecuteActionBiCompException ();
    }
//
// doOperation
//    
    private void doOperation ( JInvFXFormController.FormModeEnum mode ) 
    {
        if ("Note".equals(handleTabChanged())){
        PVNrNote p = null;
        
        
        switch (mode) {
            case VM_INS:
                p = new PVNrNote ();
                break;
            case VM_NONE:
                if (dsV_NR_NOTE.getCurrentRow () == null) 
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PVNrNote ();
                for (IEntityProperty<PVNrNote, ?> ep : EntityMetadataFactory.getEntityMetaData (PVNrNote.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsV_NR_NOTE.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsV_NR_NOTE.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<> (this, EditVNrNoteController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResultNote)    
                .doModal ();
    }
        else if ("Rem".equals(handleTabChanged())){
            PVNrReminder p = null;
            switch (mode) {
            case VM_INS:
                p = new PVNrReminder ();
                break;
            case VM_NONE:
                if (dsV_NR_REMINDER.getCurrentRow () == null) 
                    break;
                mode = FormModeEnum.VM_INS;
                p = new PVNrReminder ();
                for (IEntityProperty<PVNrReminder, ?> ep : EntityMetadataFactory.getEntityMetaData (PVNrReminder.class).getPropertiesMap ().values ())
                    if (! (ep.isTransient () || ep.isId ()))
                        ep.invokeSetter (p, ep.invokeGetter (dsV_NR_REMINDER.getCurrentRow ()));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsV_NR_REMINDER.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<> (this, EditVNrReminderController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResultRem)    
                .doModal ();
        }
    }
//
// doFormResult 
//
    private void doFormResultNote ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PVNrNote> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsV_NR_NOTE.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    doRefresh();
                    break;
                case VM_EDIT:                
                    dsV_NR_NOTE.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsV_NR_NOTE.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        V_NR_NOTE.requestFocus ();
    }
    
    private void doFormResultRem ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PVNrReminder> dctl )    
    {
        
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsV_NR_REMINDER.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    doRefresh();
                    break;
                case VM_EDIT:                
                    dsV_NR_REMINDER.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsV_NR_REMINDER.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        V_NR_REMINDER.requestFocus ();
    }
//
//
//    
}

