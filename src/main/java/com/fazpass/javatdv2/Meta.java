package com.fazpass.javatdv2;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Meta {

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
    private Device device;

    @JsonProperty("sim_serial")
    private List<String> simSerial;

    @JsonProperty("sim_operator")
    private List<String> simOperator;

    @JsonProperty("geolocation")
    private Map<String, String> geolocation;

    @JsonProperty("client_ip")
    private String clientIp;

    @JsonProperty("notifiable_devices")
    private List<Device> linkedDevices;

    @JsonProperty("is_notifiable")
    private Boolean isNotifiable;

    public String getFazpassId() {
        return fazpassId;
    }

    public void setFazpassId(String fazpassId) {
        this.fazpassId = fazpassId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Boolean getIsRooted() {
        return isRooted;
    }

    public void setIsRooted(Boolean isRooted) {
        this.isRooted = isRooted;
    }

    public Boolean getIsEmulator() {
        return isEmulator;
    }

    public void setIsEmulator(Boolean isEmulator) {
        this.isEmulator = isEmulator;
    }

    public Boolean getIsGpsSpoof() {
        return isGpsSpoof;
    }

    public void setIsGpsSpoof(Boolean isGpsSpoof) {
        this.isGpsSpoof = isGpsSpoof;
    }

    public Boolean getIsAppTempering() {
        return isAppTempering;
    }

    public void setIsAppTempering(Boolean isAppTempering) {
        this.isAppTempering = isAppTempering;
    }

    public Boolean getIsVpn() {
        return isVpn;
    }

    public void setIsVpn(Boolean isVpn) {
        this.isVpn = isVpn;
    }

    public Boolean getIsCloneApp() {
        return isCloneApp;
    }

    public void setIsCloneApp(Boolean isCloneApp) {
        this.isCloneApp = isCloneApp;
    }

    public Boolean getIsScreenSharing() {
        return isScreenSharing;
    }

    public void setIsScreenSharing(Boolean isScreenSharing) {
        this.isScreenSharing = isScreenSharing;
    }

    public Boolean getIsDebug() {
        return isDebug;
    }

    public void setIsDebug(Boolean isDebug) {
        this.isDebug = isDebug;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<String> getSimSerial() {
        return simSerial;
    }

    public void setSimSerial(List<String> simSerial) {
        this.simSerial = simSerial;
    }

    public List<String> getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(List<String> simOperator) {
        this.simOperator = simOperator;
    }

    public Map<String, String> getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Map<String, String> geolocation) {
        this.geolocation = geolocation;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public List<Device> getLinkedDevices() {
        return linkedDevices;
    }

    public void setLinkedDevices(List<Device> linkedDevices) {
        this.linkedDevices = linkedDevices;
    }

    public Boolean getIsNotifiable() {
        return isNotifiable;
    }

    public void setIsNotifiable(Boolean isNotifiable) {
        this.isNotifiable = isNotifiable;
    }

    public Meta(String fazpassId, Boolean isActive, Double scoring, String riskLevel, String timeStamp, String platform,
            Boolean isRooted, Boolean isEmulator, Boolean isGpsSpoof, Boolean isAppTempering, Boolean isVpn,
            Boolean isCloneApp, Boolean isScreenSharing, Boolean isDebug, String application, Device device,
            List<String> simSerial, List<String> simOperator, Map<String, String> geolocation, String clientIp,
            List<Device> linkedDevices, Boolean isNotifiable) {
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
        this.device = device;
        this.simSerial = simSerial;
        this.simOperator = simOperator;
        this.geolocation = geolocation;
        this.clientIp = clientIp;
        this.linkedDevices = linkedDevices;
        this.isNotifiable = isNotifiable;
    }

    public Meta() {
    }

   

}

class Device {

    @JsonProperty("name")
    private String name;

    @JsonProperty("os_version")
    private String osVersion;

    @JsonProperty("series")
    private String series;

    @JsonProperty("cpu")
    private String cpu;

    @JsonProperty("id")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Device(String name, String osVersion, String series, String cpu, String id) {
        this.name = name;
        this.osVersion = osVersion;
        this.series = series;
        this.cpu = cpu;
        this.id = id;
    }

    public Device() {
    }

    

}
