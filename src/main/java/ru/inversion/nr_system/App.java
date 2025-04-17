package ru.inversion.nr_system;

import java.util.Collections;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import ru.inversion.annotation.StartMethod;
import ru.inversion.fx.app.BaseApp;
import ru.inversion.fx.app.es.JInvErrorService;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.fx.form.JInvFXBrowserController;
import ru.inversion.fx.form.ViewContext;
import ru.inversion.tc.TaskContext;

public class App extends BaseApp
{
    @Override
    public String getAppID() {
        return "NR_SYSTEM";
    }
    
    @Override
    public void showMainWindow() 
    {        
        showVViewMain (getPrimaryViewContext (), null, Collections.emptyMap ());  
    }

    @Override
    public ResourceBundle getCommonResourceBundle() {
        return ResourceBundle.getBundle("bndl");
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Throwable ex) {
            if (appLog != null) {
                appLog.error(ex.toString());
            } else {
                ex.printStackTrace();
            }
        }

    }
    @StartMethod (description = "Не поленитесь указать описание для showViewNrNoteView, JInvDesktop будет премного благодарен") 
    public static void showVViewMain ( ViewContext vc, TaskContext tc, Map<String, Object> map ) 
    {
        new FXFormLauncher<> (tc, vc, ViewMainController.class)
            .initProperties (map)
            .show ();
    }
}
