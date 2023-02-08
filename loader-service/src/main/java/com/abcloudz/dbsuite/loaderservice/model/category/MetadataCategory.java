package com.abcloudz.dbsuite.loaderservice.model.category;

import com.abcloudz.dbsuite.loaderservice.model.version.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metadata_category",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"type", "vendor", "parent_"})})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MetadataCategory {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "metadata_category_", columnDefinition = "bpchar", unique = true, nullable = false)
    private String metadataCategoryGuid;

    @Column(name = "type", nullable = false)
    private MetadataCategoryType type;

    @Column(name = "root", nullable = false)
    private boolean root;

    @Column(name = "vendor", nullable = false)
    private VendorType vendorType;

    @ManyToOne
    @JoinColumn(name = "parent_")
    private MetadataCategory parent;

    @Column(name = "version_from", nullable = false)
    private Version versionFrom;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MetadataCategory> subCategories = new ArrayList<>();
}
