/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dijkstra_project.plugin;



import org.orbisgis.core.ui.pluginSystem.AbstractPlugIn;
import org.orbisgis.core.ui.pluginSystem.PlugInContext;
/**
 *
 * @author fortin
 */
public class DijkstraDeclaration extends AbstractPlugIn  {
    DijkstraExtension dijkstraExt;
    @Override
    public void initialize(PlugInContext context) throws Exception {
        dijkstraExt=new DijkstraExtension();
    }

    @Override
    public boolean execute(PlugInContext context) throws Exception {
        dijkstraExt.configure(context);
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
