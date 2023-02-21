package com.abcloudz.dbsuite.loaderservice.model.category;

import com.abcloudz.dbsuite.loaderservice.model.version.Version;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
@Getter
@Setter
@EqualsAndHashCode(exclude="parent")
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
    @Fetch(FetchMode.SELECT)
    private List<MetadataCategory> subCategories = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MetadataCategory [");
        if (metadataCategoryGuid != null) {
            builder.append("metadataCategoryGuid = ");
            builder.append(metadataCategoryGuid).append(", ");
        }
        if (type != null) {
            builder.append("type = ");
            builder.append(type.getType()).append(", ");
        }
        if (type != null) {
            builder.append("type = ");
            builder.append(type.getType()).append(", ");
        }
        builder.append("root = ");
        builder.append(root).append(", ");
        if (vendorType != null) {
            builder.append("vendorType = ");
            builder.append(vendorType.getVendorType()).append(", ");
        }
        if (parent != null) {
            builder.append("parent = ");
            builder.append(parent.getMetadataCategoryGuid()).append(", ");
        }
        if (versionFrom != null) {
            builder.append("versionFrom = ");
            builder.append(versionFrom).append(", ");
        }
        if (addedAt != null) {
            builder.append("addedAt = ");
            builder.append(addedAt).append(", ");
        }
        if (subCategories != null) {
            builder.append("subCategories = ");
            builder.append(subCategories);
        }
        builder.append("]");
        return builder.toString();
    }
}
