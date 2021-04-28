package com.bee.hello.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

// https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-application-info-custom
@Component
public class HostInformation implements InfoContributor {

    private static final Logger LOG = LoggerFactory.getLogger(HostInformation.class);

    @Override
    public void contribute(Info.Builder builder) {

        String host = "Unknown";
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOG.warn("Cannot access to the host address", e);
        }
        builder.withDetail("host", host);
    }
}
