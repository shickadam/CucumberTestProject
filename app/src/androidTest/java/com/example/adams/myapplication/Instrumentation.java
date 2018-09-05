package com.example.adams.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import androidx.test.runner.MonitoringInstrumentation;
import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;
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
    glue = "com.emmasuzuki.cucumberespressodemo.test")
public class Instrumentation extends MonitoringInstrumentation {

  private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

  @Override
  public void onCreate(Bundle arguments) {
    super.onCreate(arguments);



    instrumentationCore.create(arguments);
    start();
  }

  @Override
  public void onStart() {
    super.onStart();

    waitForIdleSync();
    instrumentationCore.start();
  }
}
