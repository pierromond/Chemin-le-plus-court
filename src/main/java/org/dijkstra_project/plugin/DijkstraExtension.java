package org.dijkstra_project.plugin;

/***********************************
 * ANR EvalPDU
 * IFSTTAR 11_05_2011
 * @author Pierre Aumond
 ***********************************/

import org.dijkstra_project.core.Dijkstra;
import org.dijkstra_project.run.WeightedGraph;
import org.gdms.sql.function.math.Power;
import org.orbisgis.core.ui.pluginSystem.Extension;
import org.orbisgis.core.ui.pluginSystem.PlugInContext;
import org.orbisgis.core.ui.pluginSystem.workbench.FeatureInstaller;


public class DijkstraExtension extends Extension {

	@Override
	public void configure(PlugInContext context) throws Exception {
		FeatureInstaller fi=context.getFeatureInstaller();
		fi.addRegisterFunction(Dijkstra.class);
                fi.addRegisterFunction(Power.class);
		fi.addRegisterFunction(WeightedGraph.class);
		System.out.println("Dijkstra extension plugin loaded..");
	}

}
