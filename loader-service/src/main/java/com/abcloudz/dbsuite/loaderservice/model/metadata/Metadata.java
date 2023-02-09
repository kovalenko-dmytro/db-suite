package com.abcloudz.dbsuite.loaderservice.model.metadata;

import com.abcloudz.dbsuite.loaderservice.model.category.MetadataCategory;
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
@Table(name = "metadata")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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
    private Metadata parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Metadata> children = new ArrayList<>();

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
