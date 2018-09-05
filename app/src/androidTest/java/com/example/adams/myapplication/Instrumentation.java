package com.example.adams.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import androidx.test.runner.MonitoringInstrumentation;
import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;
import java.io.File;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@CucumberOptions(
    features = "features",
    glue = "com.example.adams.myapplication")
public class Instrumentation extends MonitoringInstrumentation {

  private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

  @Override
  public void onCreate(final Bundle bundle) {
    bundle.putString("plugin", getPluginConfigurationString()); // we programmatically create the plugin configuration
    super.onCreate(bundle);
    instrumentationCore.create(bundle);
    start();
  }

  @Override
  public void onStart() {
    super.onStart();
    waitForIdleSync();
    instrumentationCore.start();
  }

  /**
   * Since we want to checkout the external storage directory programmatically, we create the plugin configuration
   * here, instead of the {@link CucumberOptions} annotation.
   *
   * @return the plugin string for the configuration, which contains XML, HTML and JSON paths
   */
  private String getPluginConfigurationString() {
    final String cucumber = "cucumber";
    final String separator = "--";
    return
        "junit:" + getAbsoluteFilesPath() + "/" + cucumber + ".xml" + separator +
            "html:" + getAbsoluteFilesPath() + "/" + cucumber + ".html" + separator +
            "json:" + getAbsoluteFilesPath() + "/" + cucumber + ".json";
  }

  /**
   * The path which is used for the report files.
   *
   * @return the absolute path for the report files
   */
  private String getAbsoluteFilesPath() {
    // Since stupidly, connected-check tasks uninstall the applications,
    // we have to find a directory outside the application directory.
    File directory = Environment.getExternalStorageDirectory();
    return new File(directory, "/cucumber").getAbsolutePath();
  }
}
