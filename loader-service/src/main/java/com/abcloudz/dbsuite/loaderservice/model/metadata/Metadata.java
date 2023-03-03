package com.abcloudz.dbsuite.loaderservice.model.metadata;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
import com.abcloudz.dbsuite.loaderservice.model.version.Version;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metadata")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Metadata {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "metadata_", columnDefinition = "bpchar", unique = true, nullable = false)
    private String metadataGuid;

    @Column(name = "connection_", columnDefinition = "bpchar", nullable = false)
    private String connectionGuid;

    @Column(name = "type", nullable = false)
    private MetadataType type;

    @ManyToOne
    @JoinColumn(name = "metadata_category_")
    private MetadataCategory category;

    @OneToMany(mappedBy = "metadata", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MetadataProperty> properties;

    @ManyToOne
    @JoinColumn(name = "parent_")
    @EqualsAndHashCode.Exclude
    private Metadata parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @EqualsAndHashCode.Exclude
    private List<Metadata> children = new ArrayList<>();

    @Column(name = "added_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime addedAt;

    public String extractProperty(MetadataType type, MetadataPropertyName name) {
        Metadata targetMetadata = this;
        while (!targetMetadata.getType().equals(type)) {
            targetMetadata = targetMetadata.getParent();
        }
        return targetMetadata.getProperties().stream()
            .filter(metadataProperty -> metadataProperty.getName().equals(name))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new)
            .getValue();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Metadata [");
        if (metadataGuid != null) {
            builder.append("metadataGuid = ");
            builder.append(metadataGuid).append(", ");
        }
        if (connectionGuid != null) {
            builder.append("connectionGuid = ");
            builder.append(connectionGuid).append(", ");
        }
        if (type != null) {
            builder.append("type = ");
            builder.append(type.getType()).append(", ");
        }
        if (category != null) {
            builder.append("category = ");
            builder.append(category.getMetadataCategoryGuid()).append(", ");
        }
        if (properties != null) {
            builder.append("properties = ");
            builder.append(properties).append(", ");
        }
        if (parent != null) {
            builder.append("parent = ");
            builder.append(parent.getMetadataGuid()).append(", ");
        }
        if (children != null) {
            builder.append("children = ");
            builder.append(children).append(", ");
        }
        if (addedAt != null) {
            builder.append("addedAt = ");
            builder.append(addedAt);
        }
        builder.append("]");
        return builder.toString();
    }
}
