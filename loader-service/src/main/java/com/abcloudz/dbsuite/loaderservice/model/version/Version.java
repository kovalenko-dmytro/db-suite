package com.abcloudz.dbsuite.loaderservice.model.version;

import com.abcloudz.dbsuite.loaderservice.common.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Version implements Comparable<Version> {

    private int major;
    private int minor;

    public Version(String version) {
        parse(version);
    }

    private void parse(String version) {
        String[] parts = version.split("\\.");
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

    @Override
    public String toString() {
        return major + CommonConstant.DOT.getValue() + minor;
    }
}
