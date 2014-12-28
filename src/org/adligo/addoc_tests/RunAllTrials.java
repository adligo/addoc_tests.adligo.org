package org.adligo.addoc_tests;

import org.adligo.addoc_tests.client.presenter.content.A_ContentTrials;
import org.adligo.addoc_tests.params.ParamsFactory;
import org.adligo.tests4j.run.api.Tests4J;
import org.adligo.tests4j.system.shared.api.Tests4J_Params;
import org.adligo.tests4j_4jacoco.plugin.factories.Tests4J_4MockitoPluginFactory;

public class RunAllTrials {
  
  public static void main(String [] args) {
    try {
      Tests4J_Params params = new ParamsFactory().create();
      A_ContentTrials content = new A_ContentTrials();
      params.addTrials(content);
      
      Tests4J.run(params);
    } catch (Exception x) {
      x.printStackTrace();
    }
  }
}
