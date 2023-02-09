package com.abcloudz.dbsuite.loaderservice.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "metadata_property",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"property_name", "metadata_"})})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MetadataProperty {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "metadata_property_", columnDefinition = "bpchar", unique = true, nullable = false)
    private String propertyGuid;

    @Column(name = "property_name", nullable = false)
    private MetadataPropertyName name;

    @Column(name = "property_value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "metadata_")
    private Metadata metadata;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
