# Fazpass V2 Library Documentation
![example workflow](https://github.com/fazpass-sdk/java-trusted-device-v2/actions/workflows/main.yml/badge.svg)
[![codecov](https://codecov.io/gh/fazpass-sdk/java-trusted-device-v2/branch/main/graph/badge.svg?token=9134KNQ1IU)](https://codecov.io/gh/fazpass-sdk/java-trusted-device-v2)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

<!--[![Maven Central]&#40;https://maven-badges.herokuapp.com/maven-central/com.fazpass/javatdv2/badge.svg&#41;]&#40;https://maven-badges.herokuapp.com/maven-central/com.fazpass/javatdv2&#41;)-->
## Table of Contents
1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Fazpass Class](#fazpass-class)
4. [TrustedDeviceImpl Class](#trusteddeviceimpl-class)

## Introduction

The Fazpass library provides a set of functions to manage trusted devices. With this library, you can initialize a Trusted Device, and perform operations such as checking, enrolling, validating, and removing a device.

## Getting Started
### Maven
```maven
<dependencies>
    <dependency>
        <groupId>com.fazpass</groupId>
        <artifactId>javatdv2</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
### Gradle
```groovy
dependencies {
    implementation 'com.fazpass:javatdv2:1.0.0'
}

```
## Fazpass Class

### initialize

```java
TrustedDevice td = Fazpass.initialize(privateKey, url);
```

Initializes a TrustedDevice object with the provided private key and base URL. Both must be non-null and not empty.

<b>privateKey</b>: A string that represents the private key for the Trusted Device.

<b>baseUrl</b>: A string that represents the base URL.

### Usage Example
```java
public class Sample
{
    public static void main( String[] args )
    {
        String url = "http://localhost:8080";
        try {
            String privateKey = readKeyFromFile("./key.priv");
            TrustedDevice td = Fazpass.initialize(privateKey, url);
            Device d = td.enrollDevice("0858*******","meta_data");
            System.out.println(d.getFazpassId());
            System.out.println(d.getScoring());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FazpassException e) {
            System.out.println(e.getMessage());
        }

    }

    public static String readKeyFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}

```

