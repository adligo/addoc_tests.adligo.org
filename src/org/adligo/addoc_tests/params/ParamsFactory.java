package org.adligo.addoc_tests.params;

import org.adligo.addoc_tests.client.presenter.content.A_ContentTrials;
import org.adligo.tests4j.system.shared.api.AbstractParamsFactory;
import org.adligo.tests4j.system.shared.api.Tests4J_Params;
import org.adligo.tests4j_4jacoco.plugin.factories.Tests4J_4MockitoPluginFactory;

import java.util.Collections;

public class ParamsFactory extends AbstractParamsFactory {

  @Override
  public Tests4J_Params create() {
    Tests4J_Params params = new Tests4J_Params();
    params.setAdditionalNonInstrumentedPackages(Collections.singletonList("com.google.gwt."));
    A_ContentTrials me = new A_ContentTrials();
    params.setCoveragePluginFactoryClass(Tests4J_4MockitoPluginFactory.class);
    return params;
  }

}
