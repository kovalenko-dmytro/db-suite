package com.abcloudz.dbsuite.vendorservice.model.connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Version implements Comparable<Version> {

    private int major;
    private int minor;

    public Version(String serverVersion) {
        parse(serverVersion);
    }

    private void parse(String serverVersion) {
        String[] parts = serverVersion.split("\\.");
        major = Integer.parseInt(parts[0]);
        minor = Integer.parseInt(parts[1]);
    }

    @Override
    public int compareTo(Version anotherVersion) {
        if (this.major != anotherVersion.getMajor()) {
            return this.major - anotherVersion.getMajor();
        }
        return this.minor - anotherVersion.getMinor();
    }
}
