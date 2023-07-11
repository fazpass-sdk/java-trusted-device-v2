package com.fazpass.javatdv2;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Device {
    @JsonProperty("fazpass_id")
    private String fazpassId;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("scoring")
    private Double scoring;

    @JsonProperty("risk_level")
    private String riskLevel;

    @JsonProperty("time_stamp")
    private String timeStamp;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("is_rooted")
    private Boolean isRooted;

    @JsonProperty("is_emulator")
    private Boolean isEmulator;

    @JsonProperty("is_gps_spoof")
    private Boolean isGpsSpoof;

    @JsonProperty("is_app_tempering")
    private Boolean isAppTempering;

    @JsonProperty("is_vpn")
    private Boolean isVpn;

    @JsonProperty("is_clone_app")
    private Boolean isCloneApp;

    @JsonProperty("is_screen_sharing")
    private Boolean isScreenSharing;

    @JsonProperty("is_debug")
    private Boolean isDebug;

    @JsonProperty("application")
    private String application;

    @JsonProperty("device_id")
    private Map<String, String> deviceId;

    @JsonProperty("sim_serial")
    private List<String> simSerial;

    @JsonProperty("sim_operator")
    private List<String> simOperator;

    @JsonProperty("geolocation")
    private Map<String, String> geolocation;

    @JsonProperty("client_ip")
    private String clientIp;

    public Device() {

    }

    public Device(String fazpassId,
                  Boolean isActive,
                  Double scoring,
                  String riskLevel,
                  String timeStamp,
                  String platform,
                  Boolean isRooted,
                  Boolean isEmulator,
                  Boolean isGpsSpoof,
                  Boolean isAppTempering,
                  Boolean isVpn,
                  Boolean isCloneApp,
                  Boolean isScreenSharing,
                  Boolean isDebug,
                  String application,
                  Map<String, String> deviceId,
                  List<String> simSerial,
                  List<String> simOperator,
                  Map<String, String> geolocation,
                  String clientIp) {
        this.fazpassId = fazpassId;
        this.isActive = isActive;
        this.scoring = scoring;
        this.riskLevel = riskLevel;
        this.timeStamp = timeStamp;
        this.platform = platform;
        this.isRooted = isRooted;
        this.isEmulator = isEmulator;
        this.isGpsSpoof = isGpsSpoof;
        this.isAppTempering = isAppTempering;
        this.isVpn = isVpn;
        this.isCloneApp = isCloneApp;
        this.isScreenSharing = isScreenSharing;
        this.isDebug = isDebug;
        this.application = application;
        this.deviceId = deviceId;
        this.simSerial = simSerial;
        this.simOperator = simOperator;
        this.geolocation = geolocation;
        this.clientIp = clientIp;
    }

    public String getFazpassId() {
        return fazpassId;
    }

    public void setFazpassId(String fazpassId) {
        this.fazpassId = fazpassId;
    }

    public Double getScoring() {
        return scoring;
    }

    public void setScoring(Double scoring) {
        this.scoring = scoring;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Boolean getRooted() {
        return isRooted;
    }

    public void setRooted(Boolean rooted) {
        isRooted = rooted;
    }

    public Boolean getEmulator() {
        return isEmulator;
    }

    public void setEmulator(Boolean emulator) {
        isEmulator = emulator;
    }

    public Boolean getGpsSpoof() {
        return isGpsSpoof;
    }

    public void setGpsSpoof(Boolean gpsSpoof) {
        isGpsSpoof = gpsSpoof;
    }

    public Boolean getAppTempering() {
        return isAppTempering;
    }

    public void setAppTempering(Boolean appTempering) {
        isAppTempering = appTempering;
    }

    public Boolean getVpn() {
        return isVpn;
    }

    public void setVpn(Boolean vpn) {
        isVpn = vpn;
    }

    public Boolean getCloneApp() {
        return isCloneApp;
    }

    public void setCloneApp(Boolean cloneApp) {
        isCloneApp = cloneApp;
    }

    public Boolean getScreenSharing() {
        return isScreenSharing;
    }

    public void setScreenSharing(Boolean screenSharing) {
        isScreenSharing = screenSharing;
    }

    public Boolean getDebug() {
        return isDebug;
    }

    public void setDebug(Boolean debug) {
        isDebug = debug;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Map<String, String> getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Map<String, String> deviceId) {
        this.deviceId = deviceId;
    }

    public List<String> getSimSerial() {
        return simSerial;
    }

    public void setSimSerial(List<String> simSerial) {
        this.simSerial = simSerial;
    }

    public Map<String, String> getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Map<String, String> geolocation) {
        this.geolocation = geolocation;
    }

    public List<String> getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(List<String> simOperator) {
        this.simOperator = simOperator;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
