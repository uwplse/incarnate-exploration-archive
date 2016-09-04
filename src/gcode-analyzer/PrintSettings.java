import java.util.*;

public class PrintSettings {
  private class SettingUnits {
    public String units;
    public double value;
    
    public SettingUnits(String units) { 
      this(units, 0.0);
    }

    public SettingUnits(String units, double value) { 
      this.units = units;
      this.value = value;
    } 

    public String getUnits() {
      return this.units;
    }
    
    public double getValue() {
      return this.value;
    }

    public void setValue(double value) {
      this.value = value;
    }

    public String toString() {
      return this.value + " " + this.units;
    }
  }

  private Map<String, SettingUnits> settingValues;
  public static final String DISTANCE_UNITS = "mm";
  public static final String EXTRACTION_SPEED_UNITS = "mm/min";
  public static final String FLOW_RATE_UNITS = "???";
  public static final String MINIMAL_LAYER_TIME_UNITS = "???";
  public static final String TEMP_UNITS = "degrees C";
  public static final String TRAVEL_SPEED_UNITS = "???";
  
  // Initializes print setting object with all values set to 0.0
  public PrintSettings() {
    this.settingValues = new TreeMap<String, SettingUnits>();
    this.settingValues.put("bed temperature", new SettingUnits(TEMP_UNITS));
    this.settingValues.put("bottom-top thickness", new SettingUnits(DISTANCE_UNITS));
    this.settingValues.put("extruder diameter", new SettingUnits(DISTANCE_UNITS));
    this.settingValues.put("extruder temperature", new SettingUnits(TEMP_UNITS));
    this.settingValues.put("extruder retraction distance", new SettingUnits(DISTANCE_UNITS));
    this.settingValues.put("extruder retraction speed", new SettingUnits(EXTRACTION_SPEED_UNITS));
    this.settingValues.put("flow rate", new SettingUnits("???"));
    this.settingValues.put("layer height", new SettingUnits(DISTANCE_UNITS));
    this.settingValues.put("minimal layer time", new SettingUnits(MINIMAL_LAYER_TIME_UNITS));
    this.settingValues.put("shell thickness", new SettingUnits("???"));
    this.settingValues.put("travel speed", new SettingUnits(TRAVEL_SPEED_UNITS));
  }

  private void checkValidSetting(String setting) {
    if (!this.settingValues.containsKey(setting)) {
      throw new IllegalArgumentException("Setting not found. Please pass in one of the following test settings: \n" +
                                          this.settingValues.keySet());
    }
  }

  public boolean contains(String setting) {
    return this.settingValues.containsKey(setting.toLowerCase());
  }

  public double getValue(String setting) {
    checkValidSetting(setting);
    return this.settingValues.get(setting).getValue();
  }

  public void put(String setting, double value) {
    if (this.contains(setting)) {
      this.setValue(setting, value);
    } else {
      System.out.println("Setting not found as a testing variable. Ignoring update to print settings");
    }
  }

  public void setValue(String setting, double value) {
    checkValidSetting(setting);
    this.settingValues.get(setting).setValue(value);
  }

  public String toString() {
    String result = "Print settings:\n";
    for (String setting : this.settingValues.keySet()) {
      result += setting + ": " + this.settingValues.get(setting).toString() + "\n";
    }
    return result;
  }
      
}
